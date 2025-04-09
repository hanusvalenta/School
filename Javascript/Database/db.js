const sqlite3 = require("sqlite3").verbose();
const filepath = "./users.db";
const fs = require("fs");

function createConnection() {
    if (fs.existsSync(filepath)) {
        return new sqlite3.Database(filepath);
    } else {
        const db = new sqlite3.Database(filepath, (error) => {
            if (error) {
                return console.error(error.message);
            }
            createTable(db);
        });
        console.log("Connection has been established");
        return db;
    }
}

function createTable(db) {
    db.exec(
        `CREATE TABLE users(
            ID INTEGER PRIMARY KEY AUTOINCREMENT,
            username VARCHAR(50) NOT NULL,
            password VARCHAR(100) NOT NULL
        );`
    )
}

module.exports = createConnection();