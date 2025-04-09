const db = require("./db");

db.all(`SELECT * FROM users;`, (err, rows) => {
    if (err) {
        console.error("Error executing query:", err);
        return;
    }
    console.log(rows);
});
