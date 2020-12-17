Objectif
========

* easily connect with any API (with or without authentication)
* easily retrieve the data and save it to mongoDB
* easily analyze data with R scripts and access the result
* Do all of this in prebuild Servlets
* ... Or do all of this in a desktop application

* ... And retrieve the generated sourcecode to do whatever you want with ! <3

Download
========
+ a Java library
+ a Shell application
+ a Desktop application

Usage
=====
+ create a project interface
+ create a servlet (if you want)
+ connect to one or more API
+ retrieve the result
+ analyse it with R
+ save it and/or save the analyse's results
locally or in a mongoDB database
+ serve it to the user in the app and/or with HTML/JSON/CSV/IMG/PDF export

Dependencies
============
Internal dependencies (includes in the final app)
-------------------------------------------------
  - mongo-driver-sync/SL4J/SL4J-Simple
  - okhttp/okio/kotlin
  - Jackson annotation/Jackon core/Jackson databind
  - javax servlet api
  - Apache Tomcat embedded

External dependencies (need to be installed beside Poseidon)
------------------------------------------------------------
 - Java Virtual Machine (https://www.java.com/en/download/)
 - R language (https://cran.r-project.org/mirrors.html)
 - MongoDB Community Server (https://www.mongodb.com/try/download/community) (**needed if you want to save your data in**)
  
