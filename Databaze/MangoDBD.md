# Zadání:
1. Vytvořte v databázi kolekci cars a vyplňte ji vzorovými daty.
2. Vypište všechny dokumenty z kolekce cars.
3. Vypište všechna auta jedné vybrané značky. 
4. Vypište všechna auta vyrobená v roce 2000 nebo později.
5. Vypište všechna auta, jejichž cena se pohybuje v rozmezí 500000 - 1000000.
6. Najdete všechna auta, která v některém ze svých komentářů obsahují jedno Vámi vybrané slovo.
7. Vypište všechna auta, která mají přesně 2 fotografie.
8. Najděte auta, která mají spotřebu větší jak 8l/100km a mist k sezení méně jak 5.
9. Najděte vozidla, která mají buď výkon větší jak 150kWh nebo mají najeto mezi 1000 až 2000 km.
10. Vymyslete si vlastní dotaz s použitím $and a $or.

# 1. 
use autobazar;

db.cars.insertMany([
  {
    znacka: 'Alfa Romeo',
    model: 'Giulia Quadrifoglio',
    rychlost: 307,
    vaha: 1524,
    cena: 1890000,
    mist: 5,
    VIN: 'ZARFBUC1298765432',
    vykon: 375,
    najeto: 25000,
    rok_vyroby: 2018,
    popis: 'Vrcholový sportovní sedan s výjimečným výkonem a zvukem. Perfektní stav.',
    spotreba: 9.8,
    komentare: ['Absolutní špička', 'Krásný vůz'],
    fotky: [{ url: 'http://example.com/alfa_1.jpg', popis: 'Přední pohled' }]
  },
  {
    znacka: 'Škoda',
    model: 'Octavia Combi 2.0 TDI',
    rychlost: 220,
    vaha: 1450,
    cena: 399000,
    mist: 5,
    VIN: 'TMBJE7NE7L0123456',
    vykon: 110,
    najeto: 115000,
    rok_vyroby: 2016,
    popis: 'Spolehlivý rodinný kombík s nízkou spotřebou. Pravidelný servis.',
    spotreba: 5.1,
    komentare: ['Praktické auto', 'Super pro rodinu'],
    fotky: [{ url: 'http://example.com/skoda_1.jpg', popis: 'Zadní část' }]
  },
  {
    znacka: 'BMW',
    model: 'M3 Competition',
    rychlost: 290,
    vaha: 1730,
    cena: 2550000,
    mist: 4,
    VIN: 'WBS3B31040J135790',
    vykon: 375,
    najeto: 12000,
    rok_vyroby: 2021,
    popis: 'Extrémně výkonné sportovní kupé, plná výbava, jako nové.',
    spotreba: 10.5,
    komentare: ['Dynamická jízda', 'Nutno vidět!'],
    fotky: [{ url: 'http://example.com/bmw_1.jpg', popis: 'Detail interiéru' }]
  },
  {
    znacka: 'Tesla',
    model: 'Model 3 Long Range',
    rychlost: 233,
    vaha: 1847,
    cena: 1150000,
    mist: 5,
    VIN: '5YJ3E1EA5KF123456',
    vykon: 366,
    najeto: 45000,
    rok_vyroby: 2020,
    popis: 'Elektrické vozidlo s dlouhým dojezdem a autopilotem. Záruka baterie.',
    spotreba: 0, // Elektrické auto
    komentare: ['Ticho a akcelerace', 'Budoucnost'],
    fotky: [{ url: 'http://example.com/tesla_1.jpg', popis: 'Při nabíjení' }]
  },
  {
    znacka: 'Ford',
    model: 'Mustang GT 5.0 V8',
    rychlost: 250,
    vaha: 1680,
    cena: 780000,
    mist: 4,
    VIN: '1FAV6GBC5J5123456',
    vykon: 331,
    najeto: 65000,
    rok_vyroby: 2017,
    popis: 'Legendární americký muscle car s atmosférickým motorem.',
    spotreba: 12.5,
    komentare: ['Hlasitý motor', 'Záruka zábavy'],
    fotky: [{ url: 'http://example.com/ford_1.jpg', popis: 'Boční profil' }]
  },
  {
    znacka: 'Toyota',
    model: 'Yaris Hybrid',
    rychlost: 175,
    vaha: 1090,
    cena: 289000,
    mist: 5,
    VIN: 'JTDAB3C3401234567',
    vykon: 85,
    najeto: 85000,
    rok_vyroby: 2014,
    popis: 'Ideální auto do města s velmi nízkou spotřebou.',
    spotreba: 3.8,
    komentare: ['Spolehlivý hybrid', 'Nenáročný provoz'],
    fotky: [{ url: 'http://example.com/toyota_1.jpg', popis: 'Pohled zepředu' }]
  },
  {
    znacka: 'Audi',
    model: 'A6 Allroad 3.0 BiTDI',
    rychlost: 250,
    vaha: 1980,
    cena: 650000,
    mist: 5,
    VIN: 'WAUZZZ4G5BN123456',
    vykon: 235,
    najeto: 140000,
    rok_vyroby: 2015,
    popis: 'Vzduchový podvozek, quattro, ideální na delší cesty.',
    spotreba: 6.9,
    komentare: ['Komfortní svezení', 'Vhodné i do terénu'],
    fotky: [{ url: 'http://example.com/audi_1.jpg', popis: 'Na polní cestě' }]
  },
  {
    znacka: 'Peugeot',
    model: '208 GTi',
    rychlost: 230,
    vaha: 1160,
    cena: 220000,
    mist: 5,
    VIN: 'VF3CC250501234567',
    vykon: 147,
    najeto: 75000,
    rok_vyroby: 2013,
    popis: 'Malý hot hatch s ostrými jízdními vlastnostmi.',
    spotreba: 6.2,
    komentare: ['Zábavné řízení', 'Rychlé auto'],
    fotky: [{ url: 'http://example.com/peugeot_1.jpg', popis: 'Akční záběr' }]
  },
  {
    znacka: 'Land Rover',
    model: 'Defender 110 P400',
    rychlost: 191,
    vaha: 2340,
    cena: 2999000,
    mist: 7,
    VIN: 'SALDCGVE3MA123456',
    vykon: 294,
    najeto: 8000,
    rok_vyroby: 2022,
    popis: 'Moderní ikona off-roadu, 7 místná verze, minimální nájezd.',
    spotreba: 11.2,
    komentare: ['Nepřekonatelný terén', 'Luxus a síla'],
    fotky: [{ url: 'http://example.com/defender_1.jpg', popis: 'V terénu' }]
  },
  {
    znacka: 'Mercedes-Benz',
    model: 'A 180 d',
    rychlost: 202,
    vaha: 1375,
    cena: 480000,
    mist: 5,
    VIN: 'WDD1770031J123456',
    vykon: 85,
    najeto: 55000,
    rok_vyroby: 2019,
    popis: 'Kompaktní hatchback s nízkou spotřebou a moderním interiérem.',
    spotreba: 4.5,
    komentare: ['Prémiový interiér', 'Úsporný motor'],
    fotky: [{ url: 'http://example.com/mercedes_1.jpg', popis: 'Kokpit' }]
  }
])

# 2. 
db.cars.find({})

# 3.
db.cars.find({ znacka: "BMW" })

# 4.
db.cars.find({ rok_vyroby: { $gte: 2000 } })

# 5.
db.cars.find({
  cena: { $gte: 500000, $lte: 1000000 }
})

# 6.
db.cars.find({ komentare: "Výkon" })

# 7.
db.cars.find({ fotky: { $size: 2 } })

# 8.
db.cars.find({
  spotreba: { $gt: 8 },
  mist: { $lt: 5 }
})

# 9.
db.cars.find({
  $or: [
    { vykon: { $gt: 150 } },
    { najeto: { $gte: 1000, $lte: 2000 } }
  ]
})

# 10.
vypíše auta BMW nebo Audi které byly vyrobeny 2019
db.cars.find({
  $and: [
    {
      $or: [
        { znacka: "BMW" },
        { znacka: "Audi" }
      ]
    },
    { rok_vyroby: { $gt: 2019 } }
  ]
})