const notesContainer = document.querySelector(".notes-container");
const createBtn = document.querySelector(".create-button");

showdata();

createBtn.addEventListener("click", () => {
  let inputBox = document.createElement("div");
  inputBox.setAttribute("class", "input-box");
  inputBox.setAttribute("contenteditable", "true");

  let img = document.createElement("img");
  img.src = "images/delete.png";

  img.addEventListener("click", () => {
    notesContainer.removeChild(inputBox);
    savedata();
  });

  inputBox.appendChild(img);
  notesContainer.appendChild(inputBox);

  savedata();
});

notesContainer.addEventListener("click", function (e) {
  if (e.target.tagName === "IMG") {
    e.target.parentElement.remove();
    savedata();
  } else if (e.target.tagName === "P") {
    notes = document.querySelectorAll(".input-box");
    notes.forEach((nt) => {
      nt.onkeyup = function () {
        savedata();
      };
    });
  }
});

function savedata() {
  localStorage.setItem("data", notesContainer.innerHTML);
}

function showdata() {
  const data = localStorage.getItem("data");
  if (data) {
    notesContainer.innerHTML = data;
  }
}

document.addEventListener("keydown", (event) => {
  if (event.key == "Enter") {
    document.execCommand("insertLineBreak");
    event.preventDefault();
  }
});
