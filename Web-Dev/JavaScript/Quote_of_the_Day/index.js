const api_url = "https://api.quotable.io/random";
const quote = document.querySelector(".quote");
const author = document.querySelector(".author");
const nextBtn = document.querySelector(".next");
const tweetBtn = document.querySelector(".tweet");

async function getQuote(url) {
  const response = await fetch(url);
  var data = await response.json();
  quote.innerHTML = data.content;
  author.innerHTML = data.author;
}

nextBtn.addEventListener("click", () => {
  getQuote(api_url);
});

tweetBtn.addEventListener("click", () => {
  tweet();
});

function tweet() {
  window.open(
    "https://twitter.com/intent/tweet?text=" +
      quote.innerHTML +
      "---- by" +
      author.innerHTML
  );
}

getQuote(api_url);
