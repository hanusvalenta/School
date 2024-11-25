document.getElementById('search-weather').addEventListener('click', () => {
    const city = document.getElementById('city-name').value.trim();

    if (city === '') {
        alert('Zadejte prosím název města!');
        return;
    }

    const apiKey = '5b3daa7c9aac6299a1b7a0d5eac03de6';
    const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;

    const translateToCzech = (key) => {
        const translations = {
            "Temperature": "Teplota",
            "Weather": "Počasí",
            "Humidity": "Vlhkost",
            "Wind Speed": "Rychlost větru",
        };
        return translations[key] || key;
    };

    fetch(apiUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Chyba ${response.status}: ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            const weatherDiv = document.getElementById('weather-result');
            weatherDiv.innerHTML = `
                <div class="weather-tile">
                    <p><strong>${data.name}</strong>, ${data.sys.country}</p>
                    <p>${translateToCzech("Temperature")}: ${data.main.temp}°C</p>
                    <p>${translateToCzech("Weather")}: ${data.weather[0].description}</p>
                    <p>${translateToCzech("Humidity")}: ${data.main.humidity}%</p>
                    <p>${translateToCzech("Wind Speed")}: ${data.wind.speed} m/s</p>
                </div>
            `;
        })
        .catch(error => {
            console.error('Error fetching weather data:', error);
            document.getElementById('weather-result').innerHTML = `<p style="color: red;">Chyba: ${error.message}</p>`;
        });
});
