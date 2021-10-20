cd E:\backup_data_files\JavaProjects\PoseidonMajordome

javac -d . --module-path '.\ressources\javaFX\lib\' --add-modules javafx.controls src\application\PoseidonApplication.java src\application\factories\FactoryBox.java src\application\factories\RequestSettings.java .\src\application\factories\ReaderSettings.java .\src\application\factories\WriterSettings.java  src\application\builders\RequestBuilder.java .\src\application\builders\WriteBuilder.java .\src\application\builders\ReadBuilder.java .\src\application\composants\ApplicationMessages.java ; java --module-path '.\ressources\javaFX\lib\' --add-modules javafx.controls application.PoseidonApplication

It will compile in an application folder, don't forget to add ressources on it

Don't forget to put dependencies into 'dependencie1.jar;dependencie2.jar' 

