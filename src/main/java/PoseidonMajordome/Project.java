package PoseidonMajordome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

// copy base project sample (with gradle embedded) in the location
// folder name = project name
// description = description in the build.gradle
public class Project {
    public static boolean IsProjectWeb;
    public static String ProjectPathStr;
    public static String ProjectDescr;
    public static String PackageName;

    public static void createProject(String ProjectName, String ProjectDescription,
                                     String packageName, String ProjectAbsolutePath,
                                     boolean IsWebApplication) {
        IsProjectWeb = IsWebApplication;
        ProjectPathStr = ProjectAbsolutePath + "/"+ProjectName+"/";
        PackageName = packageName;
        ProjectDescr = ProjectDescription;

        try {
        copyDirectory("Samples/BaseProject", ProjectPathStr);
            // rename the package (app/src/main/java/MyPackages/ to the chosen name)
            Files.move(Paths.get(ProjectPathStr+"app/src/main/Java/MyPackages"),
                       Paths.get(ProjectPathStr+"app/src/main/java/"+PackageName),
                       REPLACE_EXISTING);

            if (IsWebApplication) {
                // add the apache tomcat embedded to the external dependencies
                copyDirectory("externalDependencies/apache-tomcat-embedded",
                    ProjectPathStr+"app/externalDependencies/apache-tomcat-embedded");
            }
        }
        catch (IOException io) {
            System.out.println(io+" in class Project in method createProject");
        }
    }

    public void addSample(String SampleName) {
        try {
            Files.copy(Paths.get("Samples/" + SampleName),
                    Paths.get(ProjectPathStr + "app/main/java/" +
                            PackageName + "/" + SampleName + ".java"),
                    REPLACE_EXISTING);
        } catch (IOException io) {
            System.out.println(io + "in PoseidonMajordome.addRscript.create()");
        }
    }

    public void copyDependencies(String dependenciePath, String dependencieName) {
        try {
            Files.copy(Paths.get(dependenciePath),
                    Paths.get(ProjectPathStr + "app/externalDependencies/" + dependencieName),
                    REPLACE_EXISTING);
        } catch (IOException io) {
            System.out.println(io + "in PoseidonMajordome.Project.copyDependencies()");
        }
    }

    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation) throws IOException {
        Files.walk(Paths.get(sourceDirectoryLocation))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectoryLocation, source.toString()
                            .substring(sourceDirectoryLocation.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}