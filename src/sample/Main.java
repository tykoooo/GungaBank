package sample;

/**
 * JavaFX Imports
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.core.objects.User;
import sample.util.operations.FileOperations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Self Imports
 * <p>
 * Java Imports
 */
/**
 * Java Imports
 */

/**
 * TODO: 1. In RegisterPageController, check to see if email exist and if it does direct them to the login page
 *       2. LoginPageController add logic for logging in. With error checking, also check to see if a user has bank accounts before logging them in. If they dont redirect them (I will make the UI for that when I wake up)
 *       3. Check to see if both data structures are <br> FULLY </br> working. If they are replace the them where we are using the Java ones
 *       4. Someone learn how to make it so we can effectively hide Scenes.
 *          - setRoot(String fxml, int l, int w, boolean resize, StageStyle style, int index) @ line 84 this is bad someone fix.
 *       5. Delete useless files in sample/dependencies
 *       6. Double check all files in sample/core/objects
 *       7. Find a way to clean up the start method @ line 39.
 *       8. Clean up RegisterPageController with AlertShorter and add regex checks to string operations?*
 *       9. Comment code in all files and rename variables if not descriptive enough
 *       10. Create more object files that we will need
 *
 */

public class Main extends Application {
    private static Scene scene;
    public static java.util.ArrayList<Stage> stages;
    public static HashMap<String, User> users = new HashMap<>();

    public static void setRoot(String fxml, int l, int w, boolean resize, StageStyle style, int index) throws IOException {
        scene = new Scene(loadFXML(fxml), l, w);
        stages.get(index).hide();
        Stage stg = new Stage();
        stg.setScene(scene);
        stg.setResizable(resize);
        stg.initStyle(style);
        stg.show();
        stages.add(stg);

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * On start load all of things we had before.
         */
      FileOperations.loadInformation();


        stages = new ArrayList<>();
        scene = new Scene(loadFXML("gui/loginpage"), 700, 500);
        primaryStage.setTitle("Gunga fart");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.show();
        stages.add(primaryStage);

    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {



            launch(args);

    }
}
