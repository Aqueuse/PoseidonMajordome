const { spawn } = require('child_process');
var express = require('express');
var app = express();

const bat = spawn('cmd.exe', ['/c', 'init.bat']);

app.use('/static', express.static("./static/"));

app.get('/', function (req, res) {
    res.send("Test app running");
});

bat.stdout.on('data', (data) => {
    console.log(data.toString());
});
  
bat.stderr.on('data', (data) => {
    console.error(data.toString());
});
  
bat.on('exit', (code) => {
    server.close();
});

var server = app.listen(3003, bat, function () {
        console.log("listen " + 3003)
    }
)