var express = require('express');
var app = express();

var ejs = require('ejs');
var bodyParser = require('body-parser');

app.set('view engine', 'ejs');
app.use(express.json({limit: '1mb'}));

var baseURL = "http://localhost:3000/";

// settings the working directories to keep a clean organization client/server
app.use(express.static('client/static'));
app.set('views','./client/views');

////////// the routes /////////////

app.get('/*', function(request, response) {
   response.render('index.ejs');
});

app.listen(3000, () => console.log('Server running at 3000'));