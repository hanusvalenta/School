function Add(){
    let Name = document.getElementById('fName').value;
    let Note = document.getElementById('fNote').value;

    let todoList = JSON.parse(localStorage.getItem('todoList')) || [];
    let currentTime = new Date().toLocaleString();

    let newEntry = {
        name: Name,
        note: Note,
        time: currentTime
    };

    todoList.push(newEntry);
    localStorage.setItem('todoList', JSON.stringify(todoList));

    let listHtml = todoList.map((entry, index) => `
        <li>
            ${entry.name}: ${entry.note} (added on ${entry.time})
            <button onclick="deleteEntry(${index})">Delete</button>
        </li>
    `).join('');
    document.getElementById('text').innerHTML = `<ul>${listHtml}</ul>`;
}

window.onload = function() {
    let todoList = JSON.parse(localStorage.getItem('todoList')) || [];
    let listHtml = todoList.map(entry => `<li>${entry.name}: ${entry.note} (added on ${entry.time})</li>`).join('');
    document.getElementById('text').innerHTML = `<ul>${listHtml}</ul>`;
};