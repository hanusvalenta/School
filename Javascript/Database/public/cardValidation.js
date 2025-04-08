document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector('form[action="/submit-card"]');
  
  form.addEventListener("submit", (e) => {
    const cardNumber = form["card-number"].value.replace(/\s+/g, "");
    const expiry = form["expiry-date"].value;
    const cvv = form["cvv"].value;

    if (!isValidCardNumber(cardNumber)) {
      alert("Invalid card number.");
      e.preventDefault();
      return;
    }

    if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(expiry)) {
      alert("Invalid expiry date format. Use MM/YY.");
      e.preventDefault();
      return;
    }

    if (!/^\d{3}$/.test(cvv)) {
      alert("CVV must be 3 digits.");
      e.preventDefault();
      return;
    }
  });

  function isValidCardNumber(number) {
    let sum = 0;
    let shouldDouble = false;

    for (let i = number.length - 1; i >= 0; i--) {
      let digit = parseInt(number.charAt(i), 10);

      if (shouldDouble) {
        digit *= 2;
        if (digit > 9) digit -= 9;
      }

      sum += digit;
      shouldDouble = !shouldDouble;
    }

    return sum % 10 === 0;
  }
});
