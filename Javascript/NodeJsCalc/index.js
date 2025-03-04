const fs = require("fs");
const readline = require("readline-sync");

function factorial(n) {
    if (n < 0) return "Faktorial nelze spocitat pro zaporna cisla";
    if (n === 0 || n === 1) return 1;
    let result = 1;
    for (let i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

function calculate(a, operator, b) {
    let result;
    switch (operator) {
        case "+":
            result = a + b;
            break;
        case "-":
            result = a - b;
            break;
        case "*":
            result = a * b;
            break;
        case "/":
            result = b !== 0 ? a / b : "Chyba deleni nulou";
            break;
        case "%":
            result = b !== 0 ? a % b : "Chyba deleni nulou";
            break;
        case "^":
            result = Math.pow(a, b);
            break;
        case "sqrt":
            result = a >= 0 ? Math.sqrt(a) : "Odmocnina ze zaporného cisla";
            break;
        case "!":
            result = factorial(a);
            break;
        default:
            result = "Neplatna operace";
    }
    return result;
}

function readFromConsole() {
    let num1 = parseFloat(readline.question("Zadej prvni cislo "));
    let operator = readline.question("Zadej operaci +, -, *, /, %, ^, sqrt, ! ");
    let num2 = operator !== "sqrt" && operator !== "!" ? parseFloat(readline.question("Zadej druhe cislo ")) : null;

    let result = calculate(num1, operator, num2);
    console.log(`Vysledek ${result}`);

    fs.appendFileSync("vysledky.txt", `${num1} ${operator} ${num2 !== null ? num2 : ""} = ${result}\n`);
}

function readFromFile(filePath) {
    try {
        const data = fs.readFileSync(filePath, "utf8").trim().split("\n");
        data.forEach(line => {
            const parts = line.split(" ");
            if (parts.length === 3 || (parts.length === 2 && (parts[1] === "sqrt" || parts[1] === "!"))) {
                let num1 = parseFloat(parts[0]);
                let operator = parts[1];
                let num2 = parts.length === 3 ? parseFloat(parts[2]) : null;
                let result = calculate(num1, operator, num2);
                console.log(`${num1} ${operator} ${num2 !== null ? num2 : ""} =  ${result}`);
                fs.appendFileSync("vysledky.txt", `${num1} ${operator} ${num2 !== null ? num2 : ""} = ${result}\n`);
            } else {
                console.log(`Neplatny vstup ${line}`);
            }
        });
    } catch (error) {
        console.error("Neplatny soubor ", error);
    }
}

let inputMethod = readline.question("Vstup z konzole C || soubor S? ").toUpperCase();
if (inputMethod === "C") {
    readFromConsole();
} else if (inputMethod === "S") {
    let filePath = readline.question("Nazev souboru ");
    readFromFile(filePath);
} else {
    console.log("Neplatna moznost");
}