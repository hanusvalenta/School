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
  console.log('Form submission:', { name, surname, email, phone, year });
  res.send('Form submitted successfully!');
});

app.listen(port, () => {
  console.log(`Server running at http://localhost:${port}`);
});
