/* Appel de tous nos outils */
const express = require("express");
const expressApp = express();
const http = require("http").Server(expressApp);

const path = require("path");

/* Initialisation des variables */
const router = {
  isStarted: false,
};

function start(callback) {
  if (router.isStarted === false) {
    init(function () {
      loadRoutes(function () {
        /* Lance le serveur web sur le port 3000 */
        http.listen(3000, function () {
          console.log("Application is running on port 3000");
          router.isStarted = true;
          if (typeof callback != "undefined") {
            callback();
          }
        });
      });
    });
  } else {
    console.log("Application already started");
    if (typeof callback != "undefined") {
      callback();
    }
  }
}

function init(callback) {
  /* On s'assure que le serveur n'est vraiment pas démarré */
  router.isStarted = false;

  /* J'utilise ici EJS comme moteur de template */
  expressApp.set("view engine", "ejs");

  // settings the working directories to keep a clean organization client/server
  expressApp.use(express.static('client/static'));
  expressApp.set('views','./client/views');

  if (typeof callback != "undefined") {
    callback();
  }
}

/* ROUTES */

function loadRoutes(callback) {
  expressApp.get("/", function (req, res) {
    res.render("index.ejs");
  });

  if (typeof callback != "undefined") {
    callback();
  }
}

module.exports = {
  start: start,
};
