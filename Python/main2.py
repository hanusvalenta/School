from decimal import Decimal
import os
from win10toast import ToastNotifier

toast = ToastNotifier()

while True:
    a = Decimal(input("Enter the first number: "))
    b = Decimal(input("Enter the second: "))
    c = str(input("Enter the operation: "))

    if b == 0 and c == "/":
        toast.show_toast(
            "You can't divide by zero! w(ﾟДﾟ)w",
            "Performing emergency shutdown.",
            duration=60,
            threaded=True,
        )
        os.system('shutdown -s')
        break

    match c:
        case "+":
            print(f"The result is {a + b}")
            break

        case "-":
            print(f"The result is {a - b}")
            break

        case "*":
            print(f"The result is {a * b}")
            break

        case "/":
            print(f"The result is {a / b}")
            break