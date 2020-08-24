import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchDirectoriesUtilities {

    public static class SearchDirectoriesUtilitiesMethods {
        public static List<File> locales;//all directories named locale
        public static List<File> localesWithoutLan = new ArrayList<>();//all directories named locale without en_US directory inside
        public static List<File> en_US = new ArrayList<>();//all directories named en_US inside locale directory
        public static List<File> el_GR = new ArrayList<>();//all directories named el_GR inside locale directory

        public static List<File> missing_el_GR = new ArrayList<>();//all el_GR directories who exist at en_US but missing from the greek version
        public static List<File> extra_en_US = new ArrayList<>();//all the en_US directories which the el_GR version does not exist

        private static List<File> temp_result;

        public static final String path = "C:\\xampp\\htdocs\\ojs3_2_1";//ojs installation path
        public static final File root = new File(path);
        //directory names
        public static final String directoryLocale = "locale";
        public static final String directoryEn_US = "en_US";
        public static final String directoryEl_GR = "el_GR";

        //helping method printing an arraylist
        public static void printMyList(List<File> myList, String directoryName){
            System.out.println("** All "+directoryName+" directories.");
            int i=1;
            for (File path :myList) {
                System.out.println(i+++" "+path);
            }
            System.out.println("**"+myList.size()+" directories found named "+directoryName+".");
        }//printMyList

        //get the paths for locale directories, en_US directories inside locale directories, el_GR directories inside locale directories
        public static void getThePaths(){
            locales = findDirectoriesWithSameName(directoryLocale,root);//get the list of all locale directories paths

            //for each locale directory search for a directory named "en_US" -> there must be up to one en_US directory inside a locale directory
            for (File f :locales) {
                temp_result = findDirectoriesWithSameName(directoryEn_US,f);
                if(!temp_result.isEmpty()) {
                    en_US.add(temp_result.get(0));
                } else {
                    localesWithoutLan.add(f);//all locale directories without en_US directory inside
                }
            }

            //for each locale directory search for a directory named "el_GR" -> there must be up to one el_GR directory inside a locale directory
            for (File f :locales) {
                temp_result = findDirectoriesWithSameName(directoryEl_GR,f);
                if(!temp_result.isEmpty()) {
                    el_GR.add(temp_result.get(0));
                }
            }

        }//getThePaths

        //get all directories named as directoryName and add them on the List, return the list with all the paths
        public static List<File> findDirectoriesWithSameName(String directoryName,File root) {

            List<File> localeResult = new ArrayList<>();

            for (File file : root.listFiles()) {
                if (file.isDirectory()) {
                    if (file.getName().equals(directoryName)) {
                        if(!file.toString().contains("public")){//public directory files are created by the users, so we ignore them for our search
                            localeResult.add(file);
                        }
                    }

                    localeResult.addAll(findDirectoriesWithSameName(directoryName,file));
                }
            }

            return localeResult;
        }//findDirectoriesWithSameName

        //receive the en_US & el_GR lists, compares them and exports the el_GR directories that doesn't exist
        public static void compareTheLists(){

            for(int i=0;i<en_US.size();i++){
                String enTempPath = en_US.get(i).toString().substring(0,en_US.get(i).toString().length() -5);//build a String without the en_US path part
                String elTempPath = enTempPath+"el_GR";//add the el_GR path part to the end of the path

                File tempFileEl = new File(elTempPath);

                if(!el_GR.contains(tempFileEl)){
                    missing_el_GR.add(tempFileEl);
                    extra_en_US.add(en_US.get(i));
                }

            }

        }//compareTheLists


    }//fileUtilitiesMethods class

}
