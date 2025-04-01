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
  

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
