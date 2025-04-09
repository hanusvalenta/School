const db = require("./db")
const bcrypt = require('bcrypt');
const saltRounds = 10;
const myPlaintextPassword = 's0/\/\P4$$w0rD';
const someOtherPlaintextPassword = 'not_bacon';

/*let argumentsCount = process.argv.length;
let arguments = process.argv.slice(2);

console.log(arguments);
console.log(argumentsCount);*/

function insertRow(){
    const [name, surname, mobile] = process.argv.slice(2);
    db.run(
    `INSERT INTO contacts (name, surname, mobile) VALUES (?, ?, ?);`,
    [name, surname, mobile],
    function(error){
            if(error){
                console.error(error.message);
            }
            console.log(`Inserted a row with ID: ${this.lastID}`)
        }
    )
}
insertRow();


