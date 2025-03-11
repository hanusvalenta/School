const express = require('express');
const hbs = require('hbs');
const path = require('path');

const app = express();

app.set('view engine', 'hbs');

hbs.registerPartials(path.join(__dirname, 'views/partials'), function (err) {
    if (err) {
        console.error('Error registering partials:', err);
    }
});

hbs.registerHelper('helper_name', function (options) {
    return 'helper value';
});

hbs.registerPartial('partial_name', 'partial value');

app.set('views', path.join(__dirname, 'views'));

app.get('/', (req, res) => {
    res.render('index');
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});