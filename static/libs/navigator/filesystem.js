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
            return response.text()
        }
    ).then(
        function (data) {
//            refresh_folder_tree(data);
            console.log(data);
        }
    );
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
    loopJSON(json);
    document.getElementById('navigatorTab').innerHTML = fileTree;
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
