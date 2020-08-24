import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {
        
        System.out.println("---Locales Java Program Running---");
        //create the 2 directories(parent & child) for storing the new files
        FileUtilities.FileUtilitiesMethods.createDirectories();
        //get the paths for: 1. locale directories, 2. en_US directories inside locale 3. el_GR directories inside locale
        SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.getThePaths();
        //find the el_GR missing files
        SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.compareTheLists();
        //====do the printing====
        //print all locale directories
        SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.printMyList(SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.locales, SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.directoryLocale);
        //print all locale directories without en_US or el_GR directory inside
        SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.printMyList(SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.localesWithoutLan,"Locales without en_US and el_GR inside");
        //print all en_US directories
        SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.printMyList(SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.en_US, SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.directoryEn_US);
        //print all el_GR directories
        SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.printMyList(SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.el_GR, SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.directoryEl_GR);
        //print all el_GR MISSING directories
        SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.printMyList(SearchDirectoriesUtilities.SearchDirectoriesUtilitiesMethods.missing_el_GR,"MISSING el_GR");
        //do the copy work ** copy all the en_US files that doesn't exist for the el_GR version
        FileUtilities.FileUtilitiesMethods.copyElFilesThatDidntExist();

    }//main

}
