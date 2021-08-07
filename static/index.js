////////////////// pan management //////////////////////////////
const scriptPan = document.getElementById("editorView");
const scriptTextArea = document.querySelector('.textEditorArea');
const draghandle = document.getElementById("draghandle");
const plotPan = document.getElementById("plotView");
const blocklyDiv = document.getElementById('blocklyDiv');
const tablinks = document.getElementsByClassName("tablink");
const navBar = document.getElementById('navBar');
const dropdown = document.querySelector('.dropdown');

function initialPlacement() {
  var diptychWidth = window.innerWidth / 2;

  scriptPan.style.left = "0px";
  scriptPan.style.width = diptychWidth + "px";
  scriptPan.style.height = window.innerHeight-navBar.offsetHeight+"px";
  scriptPan.style.top = navBar.offsetHeight + "px";

  scriptTextArea.style.width = diptychWidth-1 + "px";
  scriptTextArea.style.height = window.innerHeight - (navBar.offsetHeight+dropdown.offsetHeight)+"px";
  scriptTextArea.style.resize = "none";

  draghandle.style.left = diptychWidth + "px";
  draghandle.style.height = window.innerHeight - navBar.offsetHeight+"px";
  draghandle.style.top = navBar.offsetHeight + "px";

  plotPan.style.left = diptychWidth + "px";
  plotPan.style.width = diptychWidth + "px";
  plotPan.style.height = window.innerHeight - navBar.offsetHeight+"px";
  plotPan.style.top = navBar.offsetHeight + "px";

  // Position blocklyDiv over blocklyContainer
  blocklyDiv.style.left = '0px';
  blocklyDiv.style.top = '0px';
  blocklyDiv.style.width = "100%";
  blocklyDiv.style.height = window.innerHeight
    - (navBar.offsetHeight+dropdown.offsetHeight)+"px";
  Blockly.svgResize(workspace);
}

///////////// the pan resize ///////////////////
function initPanResize(e) {
  window.addEventListener("mousemove", dragHandlePanResize, false);
  window.addEventListener("mouseup", stopPanResize, false);
}

function stopPanResize(e) {
  window.removeEventListener("mousemove", dragHandlePanResize, false);
  window.removeEventListener("mouseup", stopPanResize, false);
}

function dragHandlePanResize(e) {
  scriptPan.style.left = "0px";
  scriptPan.style.width =  parseInt(window.innerWidth, 10) - ( parseInt(plotPan.style.width, 10) ) + "px";

  scriptTextArea.style.width = parseInt(window.innerWidth, 10) - ( parseInt(plotPan.style.width, 10) ) + "px";

  plotPan.style.left = e.clientX + "px";
  plotPan.style.width = window.innerWidth - e.clientX + "px";

  draghandle.style.left = e.clientX + "px";

  // Reposition blocklyDiv over blocklyContainer
  blocklyDiv.style.left = '0px';
  blocklyDiv.style.width = "100%";
  Blockly.svgResize(workspace);
}

/////////////// the window resize ///////////////////////
function windowResize() {
  const diptychWidth = window.innerWidth / 2;

  scriptPan.style.left = "0px";
  scriptPan.style.width = diptychWidth + "px";
  scriptPan.style.height = window.innerHeight - navBar.offsetHeight+"px";

  scriptTextArea.style.left = "0px";
  scriptTextArea.style.width = diptychWidth-1 + "px";
  scriptTextArea.style.height = window.innerHeight - (navBar.offsetHeight+dropdown.offsetHeight)+"px";

  draghandle.style.left = diptychWidth + "px";
  draghandle.style.height = window.innerHeight - navBar.offsetHeight+"px"; 

  plotPan.style.left = diptychWidth + "px";
  plotPan.style.width = diptychWidth + "px";
  plotPan.style.height = window.innerHeight - navBar.offsetHeight+"px";

  blocklyDiv.style.height = window.innerHeight
    - (navBar.offsetHeight+dropdown.offsetHeight)+"px";
  Blockly.svgResize(workspace);    
}

////////////// the tab management /////////////////
function openTab(tabID, element, color) {
  for (let i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  for (let i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }

  document.getElementById(tabID).style.display = "block";
  element.style.backgroundColor = color;
}

function switchTab(containerID, tabIDToShow, activeTab, color) {
  const container = document.getElementById(containerID);

  //loop the tabs to hide the others except the tab container
  for (let i = 0; i < container.children.length-1; i++) {
    container.children.item(i).style.display = "none";
  }
  document.getElementById(tabIDToShow).style.display = "block";

  //loop the tablink to uncolor the others
  for (let i = 0; i < activeTab.parentElement.children.length; i++) {
      activeTab.parentElement.children.item(i).style.backgroundColor = "";
  }
  activeTab.style.backgroundColor = color;
}

document.getElementById("plotView").getElementsByTagName('button').item(1).click();
document.getElementById("editorView").getElementsByTagName('button').item(0).click();
