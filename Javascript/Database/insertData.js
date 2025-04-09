const db = require("./db");
const bcrypt = require('bcrypt');
const { hash } = require("crypto");
const readline = require('readline');
const saltRounds = 10;

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question('Enter your username: ', (username) => {
    rl.question('Enter your password: ', (password) => {
        bcrypt.hash(password, saltRounds, function(err, hash) {
            if (err) {
                console.error(err.message);
            } else {
                console.log(`Hashed password: ${hash}`);
                insertRow(username, hash);
            }
            rl.close();
        });
    });
});

function insertRow(username, hash) {
    db.run(
        `INSERT INTO users (username, password) VALUES (?, ?);`,
        [username, hash],
        function(error) {
            if (error) {
                console.error(error.message);
            } else {
                console.log(`Inserted a row with ID: ${this.lastID}`);
            }
        }
    );
}

insertRow();


