const outputBox = document.querySelector(".password");
const copyBtn = document.querySelector(".copy-button");
const genBtn = document.querySelector(".generate-button");
const inputBox = document.querySelector(".length");

const upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const lowerCase = "abcdefghijklmnopqrstuvwxyz";
const number = "0123456789";
const symbol = "~!@#$%^&*()_+-={}[]|;:<>?/";
const allChar = upperCase + lowerCase + number + symbol;

function genPassword() {
  const length = parseInt(inputBox.value, 10);

  if (isNaN(length) || length < 4) {
    alert("Please enter a valid length (minimum 4).");
    return;
  }

  let password = "";
  password += upperCase[Math.floor(Math.random() * upperCase.length)];
  password += symbol[Math.floor(Math.random() * symbol.length)];
  password += lowerCase[Math.floor(Math.random() * lowerCase.length)];
  password += number[Math.floor(Math.random() * number.length)];

  while (length > password.length) {
    password += allChar[Math.floor(Math.random() * allChar.length)];
  }
  outputBox.value = password;
}

async function copyPassword() {
  await navigator.clipboard.writeText(outputBox.value);
}

genBtn.addEventListener("click", () => {
  genPassword();
});

copyBtn.addEventListener("click", () => {
  copyPassword();
});
