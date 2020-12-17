Desktop application :
=====================
* actionsListeners :
    + class Project
        * create a project => 
            + Set project name
            + Set project description
            + Set project location
            + Set project style
                + desktop application :
                    - copy base project sample (with gradle embedded) in the location
                      folder name = project name and description = description in the build.gradle
                    - gradle init

                + web application :
                    - copy base project sample (with gradle/tomcat embedded) in the location
                      with folder name = project name and description in the build.gradle
                    - gradle init
    + class Add
        * add Servlet template => copy Servlet Sample with embed tomcat and basic get/post/credentials management
            /!\ if desktop project : warning for project type transformation
        * add Rscript template => copy Rscript access/run Sample
        * add MongoDB attachement template => copy mongoDB access Sample with basic connect/read/add/remove management
        * add distant API connection => copy DistantAPI connection Sample
        * add input folder => add input folder in the build.gradle file
        * add output folder => add output folder in the build.gradle file
        * add external dependencies folder => open the externalDependencies folder in the file explorer

    + class Exec
        * test locally : gradle run + console output
        * create application bundle : gradle fatbuild + explorateur de fichiers pour sauver