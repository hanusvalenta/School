const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const path = require('path');

const app = express();
const port = 3000;

app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());

const db = new sqlite3.Database('./mydb.sqlite', (err) => {
    if (err) {
        console.error('Error connecting to database:', err.message);
        process.exit(1);
    }
    console.log('Connected to the SQLite database.');
    createTables();
});

function createTables() {
    db.serialize(() => {
        db.run(
            `CREATE TABLE IF NOT EXISTS users (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            email TEXT NOT NULL UNIQUE
        )`,
            (err) => {
                if (err) {
                    console.error('Error creating users table:', err.message);
                } else {
                    console.log('Users table created (or already exists).');
                }
            }
        );

        db.run(
            `CREATE TABLE IF NOT EXISTS tasks (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            description TEXT NOT NULL,
            completed BOOLEAN NOT NULL DEFAULT 0,
            user_id INTEGER,
            FOREIGN KEY (user_id) REFERENCES users(id)
        )`,
            (err) => {
                if (err) {
                    console.error('Error creating tasks table:', err.message);
                } else {
                    console.log('Tasks table created (or already exists).');
                }
            }
        );
    });
}

app.get('/users', (req, res) => {
    db.all('SELECT * FROM users', [], (err, rows) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        res.json(rows);
    });
});

app.get('/users/:id', (req, res) => {
    const id = req.params.id;
    db.get('SELECT * FROM users WHERE id = ?', [id], (err, row) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        if (!row) {
            res.status(404).json({ error: 'User not found' });
            return;
        }
        res.json(row);
    });
});

app.post('/users', (req, res) => {
    const { name, email } = req.body;
    if (!name || !email) {
        res.status(400).json({ error: 'Name and email are required' });
        return;
    }
    db.run('INSERT INTO users (name, email) VALUES (?, ?)', [name, email], function (err) {
        if (err) {
            console.error(err.message);
            if (err.errno === 19) {
                res.status(400).json({ error: 'Email already exists' });
            } else {
                res.status(500).json({ error: 'Internal server error' });
            }
            return;
        }
        res.status(201).json({ id: this.lastID, name, email });
    });
});

app.put('/users/:id', (req, res) => {
    const id = req.params.id;
    const { name, email } = req.body;
    if (!name || !email) {
        res.status(400).json({ error: 'Name and email are required' });
        return;
    }
    db.run('UPDATE users SET name = ?, email = ? WHERE id = ?', [name, email, id], (err) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        if (this.changes === 0) {
            res.status(404).json({ error: 'User not found' });
            return;
        }
        res.json({ message: 'User updated successfully', id, name, email });
    });
});

app.delete('/users/:id', (req, res) => {
    const id = req.params.id;
    db.run('DELETE FROM users WHERE id = ?', [id], (err) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        if (this.changes === 0) {
            res.status(404).json({ error: 'User not found' });
            return;
        }
        res.json({ message: 'User deleted successfully' });
    });
});

app.delete('/tasks/:id', (req, res) => {
    const id = req.params.id;
    db.run('DELETE FROM tasks WHERE id = ?', [id], (err) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        if (this.changes === 0) {
            res.status(404).json({ error: 'Task not found' });
            return;
        }
        res.json({ message: 'Task deleted successfully' });
    });
});

app.get('/tasks', (req, res) => {
    db.all('SELECT * FROM tasks', [], (err, rows) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        res.json(rows);
    });
});

app.get('/tasks/:id', (req, res) => {
    const id = req.params.id;
    db.get('SELECT * FROM tasks WHERE id = ?', [id], (err, row) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        if (!row) {
            res.status(404).json({ error: 'Task not found' });
            return;
        }
        res.json(row);
    });
});

app.post('/tasks', (req, res) => {
    const { description, user_id, completed } = req.body;
    if (!description || user_id === undefined) {
        res.status(400).json({ error: 'Description and user_id are required' });
        return;
    }
    const completedValue = completed === undefined ? 0 : completed;
    db.run(
        'INSERT INTO tasks (description, user_id, completed) VALUES (?, ?, ?)',
        [description, user_id, completedValue],
        function (err) {
            if (err) {
                console.error(err.message);
                if (err.errno === 19) {
                    res.status(400).json({ error: 'Foreign key constraint failed (invalid user_id)' });
                } else {
                    res.status(500).json({ error: 'Internal server error' });
                }
                return;
            }
            res.status(201).json({ id: this.lastID, description, user_id, completed: completedValue });
        }
    );
});

app.put('/tasks/:id', (req, res) => {
    const id = req.params.id;
    const { description, completed, user_id } = req.body;
    if (!description || user_id === undefined || completed === undefined) {
        res.status(400).json({ error: 'Description, completed, and user_id are required' });
        return;
    }
    db.run(
        'UPDATE tasks SET description = ?, completed = ?, user_id = ? WHERE id = ?',
        [description, completed, user_id, id],
        (err) => {
            if (err) {
                console.error(err.message);
                if (err.errno === 19) {
                    res.status(400).json({ error: 'Foreign key constraint failed (invalid user_id)' });
                } else {
                    res.status(500).json({ error: 'Internal server error' });
                }
                return;
            }
            if (this.changes === 0) {
                res.status(404).json({ error: 'Task not found' });
                return;
            }
            res.json({ message: 'Task updated successfully', id, description, completed, user_id });
        }
    );
});

app.delete('/tasks/:id', (req, res) => {
    const id = req.params.id;
    db.run('DELETE FROM tasks WHERE id = ?', [id], (err) => {
        if (err) {
            console.error(err.message);
            res.status(500).json({ error: 'Internal server error' });
            return;
        }
        if (this.changes === 0) {
            res.status(404).json({ error: 'Task not found' });
            return;
        }
        res.json({ message: 'Task deleted successfully' });
    });
});

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
