fetch('data.json')
    .then(response => response.json())
    .then(data => {
        console.log(data);
        document.getElementById('text').innerHTML = data;
    })
    .catch(error => console.error('Error fetching data:', error));