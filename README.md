# Ojs_Create_Customize_Missing_El_Gr_Files

Copy & Customize existing El_Gr Files - Java App
Skip to end of metadata
Created by Δημήτρης Σιούλας, last modified about 3 hours agoGo to start of metadata
Scans the ojs installation and copies all the existing el_Gr locale files in a new directory in the desktop.

Compares each El_Gr file with the corresponding En_Us file and adds all the missing id's to the El_Gr file. Furthermore it compares again each El_Gr file with the corresponging En_Us File and removes all the extra unused msgid's from the El_Gr file.

→ In the FileUtilsMethod class change the variable generalPath regarding the ojs installation path.
