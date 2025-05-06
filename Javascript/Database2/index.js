const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const path = require('path');

const app = express();
const port = 3000;

app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());

const db = new sqlite3.Database('./mydb.sqlite', (err) => {
    if (err) {
        console.error('Failed to connect to the database:', err.message);
        process.exit(1);
    }
    createTables();
});

function createTables() {
    const queries = [
        `CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            email TEXT NOT NULL UNIQUE
        )`,
        `CREATE TABLE IF NOT EXISTS tasks (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            description TEXT NOT NULL,
            completed BOOLEAN NOT NULL DEFAULT 0,
            user_id INTEGER,
            FOREIGN KEY (user_id) REFERENCES users(id)
        )`,
    ];

    queries.forEach((query) => db.run(query, (err) => {
        if (err) console.error('Error creating table:', err.message);
    }));
}

function handleError(res, err, customMessage = 'Server error') {
    console.error(err.message);
    res.status(500).json({
        error: customMessage
    });
}

app.route('/users')
    .get((req, res) => {
        const {
            query
        } = req.query;
        const sql = query ?
            'SELECT * FROM users WHERE name LIKE ? OR email LIKE ?' :
            'SELECT * FROM users';
        const params = query ? [`%${query}%`, `%${query}%`] : [];

        db.all(sql, params, (err, rows) => {
            if (err) return handleError(res, err);
            res.json(rows);
        });
    })
    .post((req, res) => {
        const {
            name,
            email
        } = req.body;
        if (!name || !email) {
            return res.status(400).json({
                error: 'Name and email are required'
            });
        }

        db.run('INSERT INTO users (name, email) VALUES (?, ?)', [name, email], function(err) {
            if (err) {
                if (err.errno === 19) {
                    return res.status(400).json({
                        error: 'Email already exists'
                    });
                }
                return handleError(res, err);
            }
            res.status(201).json({
                id: this.lastID,
                name,
                email
            });
        });
    });

app.route('/users/:id')
    .get((req, res) => {
        const {
            id
        } = req.params;
        db.get('SELECT * FROM users WHERE id = ?', [id], (err, row) => {
            if (err) return handleError(res, err);
            if (!row) return res.status(404).json({
                error: 'User not found'
            });
            res.json(row);
        });
    })
    .put((req, res) => {
        const {
            id
        } = req.params;
        const {
            name,
            email
        } = req.body;
        if (!name || !email) {
            return res.status(400).json({
                error: 'Name and email are required'
            });
        }

        db.run('UPDATE users SET name = ?, email = ? WHERE id = ?', [name, email, id], function(err) {
            if (err) return handleError(res, err);
            if (this.changes === 0) return res.status(404).json({
                error: 'User not found'
            });
            res.json({
                message: 'User updated',
                id,
                name,
                email
            });
        });
    })
    .delete((req, res) => {
        const {
            id
        } = req.params;
        db.run('DELETE FROM users WHERE id = ?', [id], function(err) {
            if (err) return handleError(res, err);
            if (this.changes === 0) return res.status(404).json({
                error: 'User not found'
            });
            res.json({
                message: 'User deleted'
            });
        });
    });

app.route('/tasks')
    .get((req, res) => {
        db.all('SELECT * FROM tasks', [], (err, rows) => {
            if (err) return handleError(res, err);
            res.json(rows);
        });
    })
    .post((req, res) => {
        const {
            description,
            user_id,
            completed
        } = req.body;
        if (!description || user_id === undefined) {
            return res.status(400).json({
                error: 'Description and user_id are required'
            });
        }

        const completedValue = completed === undefined ? 0 : completed;
        db.run(
            'INSERT INTO tasks (description, user_id, completed) VALUES (?, ?, ?)',
            [description, user_id, completedValue],
            function(err) {
                if (err) {
                    if (err.errno === 19) {
                        return res.status(400).json({
                            error: 'Invalid user_id'
                        });
                    }
                    return handleError(res, err);
                }
                res.status(201).json({
                    id: this.lastID,
                    description,
                    user_id,
                    completed: completedValue
                });
            }
        );
    });

app.route('/tasks/:id')
    .get((req, res) => {
        const {
            id
        } = req.params;
        db.get('SELECT * FROM tasks WHERE id = ?', [id], (err, row) => {
            if (err) return handleError(res, err);
            if (!row) return res.status(404).json({
                error: 'Task not found'
            });
            res.json(row);
        });
    })
    .put((req, res) => {
        const {
            id
        } = req.params;
        const {
            description,
            completed,
            user_id
        } = req.body;
        if (!description || user_id === undefined || completed === undefined) {
            return res.status(400).json({
                error: 'Description, completed, and user_id are required'
            });
        }

        db.run(
            'UPDATE tasks SET description = ?, completed = ?, user_id = ? WHERE id = ?',
            [description, completed, user_id, id],
            function(err) {
                if (err) {
                    if (err.errno === 19) {
                        return res.status(400).json({
                            error: 'Invalid user_id'
                        });
                    }
                    return handleError(res, err);
                }
                if (this.changes === 0) return res.status(404).json({
                    error: 'Task not found'
                });
                res.json({
                    message: 'Task updated',
                    id,
                    description,
                    completed,
                    user_id
                });
            }
        );
    })
    .delete((req, res) => {
        const {
            id
        } = req.params;
        db.run('DELETE FROM tasks WHERE id = ?', [id], function(err) {
            if (err) return handleError(res, err);
            if (this.changes === 0) return res.status(404).json({
                error: 'Task not found'
            });
            res.json({
                message: 'Task deleted'
            });
        });
    });

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});