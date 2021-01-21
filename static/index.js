var sideMove = "";
var scriptBar = document.getElementById("blocklyContainerID");
var draghandleRight = document.getElementById("draghandleRight");
var sidebarRight = document.getElementById("sidebarRight");
var diptychWidth = window.innerWidth / 2;

function barsInitialPlacement() {
  scriptBar.style.left = "0px";
  scriptBar.style.width = diptychWidth + "px";
  draghandleRight.style.left = diptychWidth + "px";
  sidebarRight.style.left = diptychWidth + "px";
  sidebarRight.style.width = diptychWidth + "px";
  onresize();
}

function initResize(e) {
  window.addEventListener("mousemove", Resize, false);
  window.addEventListener("mouseup", stopResize, false);
}

function Resize(e) {
  onresize();

  sidebarRight.style.left = e.clientX + "px";
  sidebarRight.style.width = window.innerWidth - e.clientX + "px";
  draghandleRight.style.left = e.clientX + "px";

  scriptBar.style.width = parseInt(window.innerWidth, 10)
    - (
      parseInt(sidebarRight.style.width, 10)
      ) + "px";
  }

function stopResize(e) {
  onresize();
  window.removeEventListener("mousemove", Resize, false);
  window.removeEventListener("mouseup", stopResize, false);
}
