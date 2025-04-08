const sqlite3 = require("sqlite3").verbose();
const filepath = "./contacts.db";
const fs = require("fs");

function createConnection() {
    if (fs.existsSync(filepath)) {
        return new sqlite3.Database(filepath);
    } else {
        const db = new sqlite3.Database(filepath, (error) => {
            if (error) {
                return console.error(error.message);
            }
            //vytvoření tabulky
            createTable(db);
        });
        console.log("Connection has been established");
        return db;
    }
}

function createTable(db) {
    db.exec(
        `CREATE TABLE contacts(
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            name VARCHAR(50) NOT NULL,
            surname VARCHAR(50) NOT NULL,
            mobile VARCHAR(20) NOT NULL
        );`
    )
}

module.exports = createConnection();