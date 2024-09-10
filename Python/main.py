from decimal import Decimal
import webbrowser
from win10toast import ToastNotifier
import os

toast = ToastNotifier()

while True:
    print("Enter numbers higer than 0 and less than 10")

    a = Decimal(input("Num1 = "))
    b = Decimal(input("Num2 = "))
    c = Decimal(input("Num3 = "))

    if a>10 or b>10 or c>10 or a<0 or b<0 or c<0:
        toast.show_toast(
            "Invalid 🖕",
            "You're a dumfuck! Deleting myself now!",
            duration=60,
            threaded=True,
        )
        webbrowser.open('https://youtu.be/S280Pqq3T_w?si=7R0w3ARdoWEncl7v')
        os.remove(__file__)

    elif a>b and a>c:
        print("Num1 is the highest number")
        break

    elif b>a and b>c:
        print("Num2 is the highest number")
        break

    elif c>a and c>b:
        print("Num3 is the highest number")
        break

    else:
        print("Some numbers are equal")
        break