fetch('data.json')
    .then(response => response.json())
    .then(data => {
        console.log(data);
        document.getElementById('text').innerHTML = JSON.stringify(data, null, 2);
    })
    .catch(error => console.error('Error fetching data:', error));