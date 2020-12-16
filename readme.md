Objectif
========

* easily connect with any API :
  - dependences : okhttp/okio/kotlin

* easily retrieve the data and save it to mongoDB
  - dependences : mongo-driver-sync/SL4J/SL4J-Simple

* easily analyze data with R scripts and access the result
  - dependences : Jackson annotation/Jackon core/Jackson databind

* Do all of this in prebuild Servlets
  - dependences : javax servlet api

* or do all of this in a desktop application
  - dependences : java swing

Usage
=====

web application
---------------
+ create a servlet
+ connect to one or more API
+ retrieve the result
+ analyse it
+ save it and/or the analyse result
locally or in a mongoDB database
+ serve it to the user with HTML

Desktop application
-------------------
+ create a simple swing interface
+ connect to one or more API
+ retrieve the result
+ analyse it
+ save it and/or save the analyse's results
locally or in a mongoDB database
+ serve it to the user in the app and/or with HTML/JSON/CSV/IMG/PDF export