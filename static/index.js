////////////////// pan management //////////////////////////////
function blocklyResize(e) {
  var blocklyDiv = document.getElementById('blocklyDiv');

  // Position blocklyDiv over blocklyContainer
  blocklyDiv.style.left = '0px';
  blocklyDiv.style.top = '0px';
  blocklyDiv.style.width = "100%";
  blocklyDiv.style.height = "100%";
  Blockly.svgResize(workspace);
};

function barsInitialPlacement() {
  var scriptPan = document.getElementById("editorView");
  var draghandle = document.getElementById("draghandle");
  var plotPan = document.getElementById("plotView");
  var diptychWidth = window.innerWidth / 2;

  scriptPan.style.left = "0px";
  scriptPan.style.width = diptychWidth + "px";
  draghandle.style.left = diptychWidth + "px";
  plotPan.style.left = diptychWidth + "px";
  plotPan.style.width = diptychWidth + "px";
  blocklyResize();
}

function initResize(e) {
  window.addEventListener("mousemove", Resize, false);
  window.addEventListener("mouseup", stopResize, false);
}

function Resize(e) {
  blocklyResize();

  var scriptPan = document.getElementById("editorView");
  var plotPan = document.getElementById("plotView");
  plotPan.style.left = e.clientX + "px";
  plotPan.style.width = window.innerWidth - e.clientX + "px";
  draghandle.style.left = e.clientX + "px";

  scriptPan.style.width = parseInt(window.innerWidth, 10)
    - (
      parseInt(plotPan.style.width, 10)
      ) + "px";
  }

function stopResize(e) {
  blocklyResize();
  window.removeEventListener("mousemove", Resize, false);
  window.removeEventListener("mouseup", stopResize, false);
}

////////////// tab management /////////////////

function openTab(tabID, element, color) {
  // Hide all elements with class="tabcontent" by default */
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Remove the background color of all tablinks/buttons
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }

  document.getElementById(tabID).style.display = "block";
  element.style.backgroundColor = color;
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();