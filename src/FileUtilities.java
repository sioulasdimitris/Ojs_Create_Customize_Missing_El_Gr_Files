import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtilities {


    public static class FileUtilitiesMethods{

        public static String desktopPath ="";//Desktop path
        public static String rootDirectoryName = "\\new_locales";//root directory name
        public static String elFilesThatDidntExistDirectoryName = "\\el-files-that-didnt-exist";//child directory name

        public static String localesRootDirectory = "";//root directory path
        public static String elFilesThatDidntExistDirectory = "";//child directory path

        public static void createDirectories(){

            desktopPath = getDesktopPath();

            localesRootDirectory = desktopPath+rootDirectoryName;
            elFilesThatDidntExistDirectory = desktopPath+rootDirectoryName+elFilesThatDidntExistDirectoryName;

            new File(localesRootDirectory).mkdirs(); //create the root directory
            new File(elFilesThatDidntExistDirectory).mkdirs();//create the child directory inside the root directory
        }//createDirectories

        //takes the original path of each missing el_GR file and replace the // with - character in order to name the destination directory
        public static String createTheNewPath(String destination, String dirName){

            String enTempPath = dirName.substring(0,dirName.length() -5);//build a String without the en_US path part
            String elTempPath = enTempPath+"el_GR";//add the el_GR path part to the end of the path

            String newDirName;
            newDirName = elTempPath.replaceAll(":",".").replaceAll("\\\\","-");
            return destination+"\\"+newDirName;
        }//createTheNewPath

        public static void copyElFilesThatDidntExist() throws IOException {

            System.out.println("** Copying directories.");
            int i = 0;
            for (File path : SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.extra_en_US) {

                i++;
                File f1 = new File(path.toString());
                File f2 = new File(createTheNewPath(elFilesThatDidntExistDirectory,path.toString()));
                copyFolder(f1,f2);
            }

            System.out.println("** "+i+" en_US files have been copied.");

        }//copyElFilesThatDidntExist

        //copy directory with all its contents and paste it in a certain destination (if the destination does not exist then create it)
        public static void copyFolder(File sourceFolder, File destinationFolder) throws IOException
        {
            //Check if sourceFolder is a directory or file
            //If sourceFolder is file; then copy the file directly to new location
            if (sourceFolder.isDirectory())
            {
                //Verify if destinationFolder is already present; If not then create it
                if (!destinationFolder.exists())
                {
                    destinationFolder.mkdir();
                    //System.out.println("Directory created :: " + destinationFolder);
                }

                //Get all files from source directory
                String files[] = sourceFolder.list();

                //Iterate over all files and copy them to destinationFolder one by one
                for (String file : files)
                {
                    File srcFile = new File(sourceFolder, file);
                    File destFile = new File(destinationFolder, file);

                    //Recursive function call
                    copyFolder(srcFile, destFile);
                }
            }
            else
            {
                //Copy the file content from one place to another
                Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
                //System.out.println("File copied :: " + destinationFolder);
            }
        }//copyFolder


        public static String getDesktopPath(){
            File desktopDir = new File(System.getProperty("user.home"), "Desktop");
            desktopPath = desktopDir.getPath();
            return desktopPath;
        }//getDesktopPath

    }

}
