let fileTree = '';

document.getElementById("inputOpenFolder").addEventListener("change", function(event) { refreshFolderTree(event, 'navigatorTab')}, false);

function retrieve_json_tree() {
    let myInit = {
        method: 'GET',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        cache: 'default'
    };

    fetch('navigator', myInit).then(
        function (response) {
            return response.json()
        }
    ).then(
        function (data) {
            //refreshFolderTree(data.value, 'navigatorTab');
            console.log('plop');
        }
    );
}

function loopJSON(parent) {
    parent.forEach(function (value) {
        fileTree = fileTree + '<li>';
        if (value.hasOwnProperty('children') && value.children.length >= 1) { // folder
            fileTree = fileTree + '<span  class="caret">' + value.name + '</span><ul class="nested">\n';
            loopJSON(value.children);
            fileTree = fileTree + '</ul>';
        } else {  // file
            fileTree = fileTree + value.name;
        }
        fileTree = fileTree + '</li>';
    });
}

function openNavigator(baseURL) {
    window.open(baseURL+'/navigator');
    let output = document.getElementById(containerID);
    let files = event.target.files;

    console.log('contains '+event.target.files.length);

    if (event.target.files.length === 0) {
        console.log('folder empty');
//        console.log(event.target);
    }

    // for (let i=0; i<files.length; i++) {
    //     console.log(event.target.files.item(i));
    // }
    // let folder = event.dataTransfer.items;
    //
    // for (let i=0; i<folder.length; i++) {
    //     console.log('folder listening ...');
    //     let item = folder[i].webkitGetAsEntry();
    //     if (item.isDirectory()) {
    //         console.log(item.name);
    //     }
    // }

    // for (let i = 0; i < files.length; i++) {
    //     let item = document.createElement("li");
    //     item.innerHTML = files[i].webkitRelativePath;
    //     output.appendChild(item);
    // }
    //
    // let parsed_json = JSON.parse(json);
    // let ul = document.createElement('ul');
    // let toggler = document.getElementsByClassName("caret");
    //
    // let oldUl = document.getElementById('myUL');
    // if (oldUl) {
    //     oldUl.remove();
    //     fileTree = '';
    // }
    //
    // ul.id = 'myUL';
    // loopJSON([parsed_json]);
    // ul.innerHTML = fileTree;
    // document.getElementById(containerID).append(ul);

    // for (let i = 0; i < toggler.length; i++) {
    //     toggler[i].addEventListener("click", function () {
    //         this.parentElement.querySelector(".nested").classList.toggle("active");
    //         this.classList.toggle("caret-down");
    //     });
    // }
}

