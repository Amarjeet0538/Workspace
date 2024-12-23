const inputBox = document.querySelector(".task-input");
var addBtn = document.querySelector(".add-button");
const listContainer = document.querySelector(".task-container");

function addTask() {
  if (inputBox.value == "") {
    document.querySelector(".error").style.display = "block";
  } else {
    document.querySelector(".error").style.display = "none";
    let li = document.createElement("li");
    li.innerHTML = inputBox.value;
    listContainer.appendChild(li);

    let span = document.createElement("span");
    span.innerHTML = "\u00d7";
    li.appendChild(span);
  }
  inputBox.value = "";
  saveData();
}

listContainer.addEventListener(
  "click",
  function (e) {
    if (e.target.tagName == "LI") {
      e.target.classList.toggle("checked");
      saveData();
    } else if (e.target.tagName == "SPAN") {
      e.target.parentElement.remove();
      saveData();
    }
  },
  false
);

addBtn.addEventListener("click", () => {
  addTask();
});

function saveData() {
  localStorage.setItem("data", listContainer.innerHTML);
}

function showTask() {
  listContainer.innerHTML = localStorage.getItem("data");
}

showTask();
