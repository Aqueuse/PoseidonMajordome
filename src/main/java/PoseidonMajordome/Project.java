package PoseidonMajordome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// copy base project sample (with gradle embedded) in the location
// folder name = project name
// description = description in the build.gradle
public class Project {
    public static boolean IsProjectWeb;
    public static String ProjectPathStr;
    public static String PackageName;
    public static String inFolder;
    public static String outFolder;

    public void createProject (String ProjectName, String ProjectDescription,
                        String packageName, String ProjectAbsolutePath,
                        String ProjectInputFolder, String ProjectOutputFolder,
                        boolean IsWebApplication)
    {
        IsProjectWeb = IsWebApplication;
        ProjectPathStr = ProjectAbsolutePath+"/";
        PackageName = packageName;
        inFolder = ProjectInputFolder;
        outFolder = ProjectOutputFolder;
        
        try {
            // unzip the folder (folder name = project name)
            unzip("Samples/BaseProject.zip", ProjectAbsolutePath+"/"+ProjectName);

            // rename the package (app/src/main/java/MyPackages/ to the chosen name)
            Files.copy(Paths.get("app/src/main/Java/MyPackages/"),
                       Paths.get(ProjectPathStr+"app/main/java/"+PackageName),
                       REPLACE_EXISTING);
        
            if (IsWebApplication == true) {
                // add the apache tomcat embedded to the external dependencies
                unzip("externalDependencies/apache-tomcat-embedded.zip",
                    ProjectAbsolutePath+"/externalDependencies/apache-tomcat-embedded.zip");
            }
        }
        catch (IOException io) {
            System.out.println(io);
        }
    }
    
    public void addSample(String SampleName, String chosenName) {
        try {
            Files.copy(Paths.get("Samples/"+SampleName),
                       Paths.get(ProjectPathStr+"app/main/java/"+
                                 PackageName+"/"+chosenName+".java"),
                       REPLACE_EXISTING);
        }
        catch (IOException io) {
            System.out.println(io+"in PoseidonMajordome.addRscript.create()");
        }
    }
    
    // move the actual input folder to the chosen new destination
    public void moveInFolder(String actualInFolder, String newPath) {
        outFolder=newPath;
    }

    // move the actual output folder to the chosen new destination
    public void moveOutFolder(String actualOutFolder, String newPath) {
        inFolder=newPath;
    }
    
    public void copyDependencies(String dependenciePath, String dependencieName) {
        try {
            Files.copy(Paths.get(dependenciePath),
                       Paths.get(ProjectPathStr+"/"+dependencieName),
                       REPLACE_EXISTING);
       }
       catch (IOException io) {
           System.out.println(io+"in PoseidonMajordome.Project.copyDependencies()");
       }
    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream FileInputStr;

        //buffer to read and write data to file
        byte[] buffer = new byte[1024];
        try {
            FileInputStr = new FileInputStream(zipFilePath);
            ZipInputStream ZipInput = new ZipInputStream(FileInputStr);
            ZipEntry entry = ZipInput.getNextEntry();
            while(entry != null){
                String fileName = entry.getName();
                File newFile = new File(destDir + File.separator + fileName);
                
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream FileOutputStr = new FileOutputStream(newFile);
                
                int len;
                while ((len = FileInputStr.read(buffer)) > 0) {
                    FileOutputStr.write(buffer, 0, len);
                }

                FileOutputStr.close();
                ZipInput.closeEntry();
                entry = ZipInput.getNextEntry();
            }
            ZipInput.closeEntry();
            FileInputStr.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
}