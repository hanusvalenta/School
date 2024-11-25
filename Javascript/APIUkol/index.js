document.getElementById('search-weather').addEventListener('click', () => {
    const city = document.getElementById('city-name').value.trim();

    if (city === '') {
        alert('Please enter a city name!');
        return;
    }

    const apiKey = '5b3daa7c9aac6299a1b7a0d5eac03de6';
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error ${response.status}: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            const weatherDiv = document.getElementById('weather-result');
            weatherDiv.innerHTML = `
                <p><strong>${data.name}</strong>, ${data.sys.country}</p>
                <p>Temperature: ${data.main.temp}°C</p>
                <p>Weather: ${data.weather[0].description}</p>
                <p>Humidity: ${data.main.humidity}%</p>
                <p>Wind Speed: ${data.wind.speed} m/s</p>
            `;
        })
        .catch(error => {
            console.error('Error fetching weather data:', error);
            document.getElementById('weather-result').innerHTML = `<p style="color: red;">Error: ${error.message}</p>`;
        });
});
