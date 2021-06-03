let fileTree = '';

function load_project_fetch() {
    let myInit = {
        method: 'GET',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        cache: 'default'
    };

    fetch('open', myInit).then(
        function (response) {
            return response.json();
        }
    ).then(
        function (data) {
            refresh_folder_tree(data);
        }
    ).catch(error => {
        console.log(error);
    });
}

function create_project_fetch() {
    let myInit = {
        method: 'GET',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        cache: 'default'
    };

    fetch('create', myInit).then(
        function (response) {
//            return response.json()
            return 'plop';
        }
    ).then(
        function (data) {
//            refresh_folder_tree(data);
            console.log('plop');
        }
    );
}

function refresh_folder_tree(json) {
    fileTree = "";
    loopJSON(json);
    document.getElementById('navigatorTab').innerHTML = "<ul id='myUL'>" + fileTree + "</ul>";
    // replace the event listener for each folder in the new tree
    let toggler = document.getElementsByClassName("caret");

    for (let i = 0; i < toggler.length; i++) {
        toggler[i].addEventListener("click", function () {
            this.parentElement.querySelector(".nested").classList.toggle("active");
            this.classList.toggle("caret-down");
        });
    }
}

function loopJSON(parent) {
    parent.children.forEach(function (value) {
        fileTree = fileTree + '<li>';
        if (value.hasOwnProperty('children') && value.children.length >= 1) { // folder
            fileTree = fileTree + '<span  class="caret">' + value.name + '</span><ul class="nested">\n';
            loopJSON(value);
            fileTree = fileTree + '</ul>';
        } else {  // file
            fileTree = fileTree + "<a onclick=plot_fetch('"+encodeURI(value.path+"/"+value.name)+"')>" + value.name + "</a>";
        }
        fileTree = fileTree + '</li>';
    });
}

function plot_fetch(path) {
    let myInit = {
        method: 'POST',
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        cache: 'default',
        body: path
    };

    fetch('plot_img', myInit).then(
        // the server will copy the image and paste it in the static folder then return the link
        // (http://127.0.0.1:port/static/temp/plot.png), the browser will the refresh the plot tab with
        // the image and switch to the plot tab
        function (response) {
            return response.json();
        }
    ).then(
        function (data) {
            if (data.log.split(" ")[0] === "OK") {
                if (data.log.split(" ")[1] === 'image') {
                    document.getElementById('plotImage').src = window.location+"static/temp/plot.png";
                }
                if (data.log.split(" ")[1] === 'text') {
                    document.getElementById('plotText').src = window.location;
                }
            } else {
                document.getElementById('log').innerHTML = data.log;
            }
        }
        ).catch(error => {
            console.log(error);
        });
}
