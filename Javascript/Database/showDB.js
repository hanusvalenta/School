const db = require("./db");

db.exec(`
    SELECT * FROM users;
`, (err, results) => {
    if (err) {
        console.error("Error executing query:", err);
        return;
    }
    console.log("Query Results:", results);
});