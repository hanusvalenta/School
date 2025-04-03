const express = require('express');
const path = require('path');
const bodyParser = require('body-parser');

const app = express();
const port = 3000;

app.use(bodyParser.urlencoded({ extended: false }));
app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.post('/submit', (req, res) => {
    const { name, surname, email, phone, year } = req.body;
  
    const nameValid = name.length >= 2 && /^[A-Za-z]+$/.test(name);
    const emailValid = /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    const phoneValid = /^\d{1,12}$/.test(phone);
    const currentYear = new Date().getFullYear();
    const yearValid = year <= currentYear && year >= currentYear - 120;
  
    if (!nameValid || !emailValid || !phoneValid || !yearValid) {
      return res.status(400).send("Invalid input data.");
    }
  
    console.log('Form submission:', { name, surname, email, phone, year });
    res.send('Form submitted successfully!');
  }); 

  app.post('/submit-card', (req, res) => {
    console.log('Received data:', req.body);

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

    console.log('Card information submitted:', { cardNumber, expiryDate, cvv });

    res.send('Card information submitted successfully!');
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
