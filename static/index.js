var sideMove = "";
var sidebarLeft = document.getElementById("sidebarLeft");
var draghandleLeft = document.getElementById("draghandleLeft");
var scriptBar = document.getElementById("blocklyContainerID");
var draghandleRight = document.getElementById("draghandleRight");
var sidebarRight = document.getElementById("sidebarRight");
var triptychWidth = window.innerWidth / 3;

function barsInitialPlacement() {
  sidebarLeft.style.left = 0 + "px";
  sidebarLeft.style.width = triptychWidth + "px";
  draghandleLeft.style.left = triptychWidth + "px";
  scriptBar.style.left = triptychWidth + "px";
  scriptBar.style.width = triptychWidth + "px";
  draghandleRight.style.left = triptychWidth * 2 + "px";
  sidebarRight.style.left = triptychWidth * 2 + "px";
  sidebarRight.style.width = triptychWidth + "px";
  onresize();
}

function initResize(e) {
  window.addEventListener("mousemove", Resize, false);
  window.addEventListener("mouseup", stopResize, false);
}

function Resize(e) {
  var handleLeftPos = parseInt(draghandleLeft.style.left, 10);
  var handleRightPos = parseInt(draghandleRight.style.left, 10);
  onresize();

  if (handleLeftPos + 20 < handleRightPos) { /// prevention anti pane crashing
    if (sideMove == "left") {
      sidebarLeft.style.width = e.clientX - sidebarLeft.offsetLeft + "px";
      draghandleLeft.style.left = e.clientX - sidebarLeft.offsetLeft + "px";

      scriptBar.style.left = e.clientX + "px";
      scriptBar.style.width = parseInt(window.innerWidth, 10)
        - (
          parseInt(sidebarLeft.style.width, 10)
          + parseInt(sidebarRight.style.width, 10)
        ) + "px";
    }
    else {  // sideMove == right
      sidebarRight.style.left = e.clientX + "px";
      sidebarRight.style.width = window.innerWidth - e.clientX + "px";
      draghandleRight.style.left = e.clientX + "px";

      scriptBar.style.left = parseInt(sidebarLeft.style.width, 10) + "px";
      scriptBar.style.width = parseInt(window.innerWidth, 10)
        - (
          parseInt(sidebarLeft.style.width, 10)
          + parseInt(sidebarRight.style.width, 10)
        ) + "px";
    }
  }
  else {   /// the sidebar must resist !
    if (sideMove == "left") {
      draghandleLeft.style.left = parseInt(draghandleLeft.style.left, 10) + "px";
    }
    else {
      draghandleRight.style.left = parseInt(draghandleRight.style.left, 10) + "px";
    }
  }
}

function stopResize(e) {
  draghandleLeft.style.left = parseInt(draghandleLeft.style.left, 10) - 5 + "px";
  sidebarLeft.style.width = parseInt(sidebarLeft.style.width, 10) - 5 + "px";
  onresize();
  window.removeEventListener("mousemove", Resize, false);
  window.removeEventListener("mouseup", stopResize, false);
}
