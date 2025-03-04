const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Zadej první číslo ", (num1) => {
    rl.question("Zadej operaci ", (operator) => {
        rl.question("Zadej druhé číslo ", (num2) => {
            const a = parseFloat(num1);
            const b = parseFloat(num2);
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
                    result = b !== 0 ? a / b : "Error dělení nulou";
                    break;
                default:
                    result = "Nelze";
            }

            console.log(`${result}`);
            rl.close();
        });
    });
});
