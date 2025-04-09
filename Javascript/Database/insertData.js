const db = require("./createDB")
const bcrypt = require('bcrypt');
const readline = require('readline');
const saltRounds = 10;

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question('Enter your password: ', (password) => {
    bcrypt.hash(password, saltRounds, function(err, hash) {
        if (err) {
            console.error(err.message);
        } else {
            console.log(`Hashed password: ${hash}`);
        }
        rl.close();
    });
});

function insertRow(){
    const [username, hash] = process.argv.slice(2);
    db.run(
    `INSERT INTO users (name, surname, mobile) VALUES (?, ?, ?);`,
    [username, hash],
    function(error){
            if(error){
                console.error(error.message);
            }
            console.log(`Inserted a row with ID: ${this.lastID}`)
        }
    )
}
insertRow();


