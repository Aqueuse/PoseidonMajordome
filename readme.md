Objectif
========

* create a swing project interface
* create a servlet (if you want)
* easily connect with any API (with or without authentication)
* easily analyze the data with R scripts and access the result
* save to mongoDB (if you want)
* or export it to HTML/JSON/CSV/IMG/PDF

+ Do all of this in prebuild Servlets
+ ... Or do all of this in a desktop application

* ... And retrieve the generated sourcecode to do whatever you want with ! <3

Download
========
+ a Java library
+ a Shell application
+ a Desktop application
+ ... or use the web generator

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
