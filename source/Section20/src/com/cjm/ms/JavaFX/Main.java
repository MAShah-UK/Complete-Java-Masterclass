package com.cjm.ms.JavaFX;

import com.cjm.ms.JavaFX.model.DataSource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void init() throws Exception {
        // Calling the super implementation won't do anything,
        // but that could change in a future version of JavaFX.
        super.init();
        if(!DataSource.getInstance().open()) {
            // Normally will use a popup dialog to inform user,
            // but the focus isn't on UI in this section.
            System.out.println("Fatal error: Couldn't connect to database.");
            Platform.exit();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.listArtists();

        primaryStage.setTitle("Music Database");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
