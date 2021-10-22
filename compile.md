cd E:\backup_data_files\JavaProjects\PoseidonMajordome

javac -d . --module-path '.\ressources\javaFX\lib\' --add-modules javafx.controls,javafx.web src\application\PoseidonApplication.java src\application\factories\FactoryBox.java src\application\factories\RequestSettings.java .\src\application\factories\RSettings.java .\src\application\factories\GUISettings.java  src\application\builders\RequestBuilder.java .\src\application\builders\GUIBuilder.java .\src\application\builders\RBuilder.java .\src\application\composants\ApplicationMessages.java ; java --module-path '.\ressources\javaFX\lib\' --add-modules javafx.controls,javafx.web application.PoseidonApplication

It will compile in an application folder, don't forget to add ressources on it

Don't forget to put dependencies into 'dependencie1.jar;dependencie2.jar' 

If you have multiples modules to include, use this format :    
    --add-modules module1,module2

don't forget to add the in javac AND java