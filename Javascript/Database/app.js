const fs = require("fs")
fs.writeFileSync("soubor.txt", "CHC")

const utils = require("./utils.js")
utils()

var validator = require('validator');
const result = validator.isEmail('foo@bar.com'); //=> true
console.log(result)