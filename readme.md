Objectif
========

* easily connect with any API (with or without authentication)
* easily retrieve the data and save it to mongoDB
* easily analyze data with R scripts and access the result
* Do all of this in prebuild Servlets
* or do all of this in a desktop application

Usage
=====

web application
---------------
+ create a servlet
+ connect to one or more API
+ retrieve the result
+ analyse it with R
+ save it and/or the analyse's results
locally or in a mongoDB database
+ serve it to the user with HTML/JSON/CSV export

Desktop application
-------------------
+ create a project interface
+ connect to one or more API
+ retrieve the result
+ analyse it with R
+ save it and/or save the analyse's results
locally or in a mongoDB database
+ serve it to the user in the app and/or with HTML/JSON/CSV/IMG/PDF export

Dependencies :
==============
Internal dependencies (includes in the final app)
-------------------------------------------------
  - mongo-driver-sync/SL4J/SL4J-Simple
  - okhttp/okio/kotlin
  - Jackson annotation/Jackon core/Jackson databind
  - javax servlet api
  
  External dependencies (need to be installed beside Poseidon)
  ------------------------------------------------------------
  - MongoDB Community Server (https://www.mongodb.com/try/download/community)
  - Java Virtual Machine (https://www.java.com/en/download/)
 
