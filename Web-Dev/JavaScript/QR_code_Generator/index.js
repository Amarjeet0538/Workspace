const api_url =
  "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";
const input = document.querySelector(".input");
const genBtn = document.querySelector(".generate");
const output = document.querySelector(".output");

async function assignQR(data) {
  output.setAttribute("src", api_url + data);
}

genBtn.addEventListener("click", () => {
  let data = input.value;
  assignQR(data);
});
