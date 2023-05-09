package edu.guilford;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Animation extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        //all the graphics stuff should start here
        int width = 1000;
        int height = 800;
        AnimationPane aPane = new AnimationPane(width, height);
        scene = new Scene(aPane, width, height);
        stage.setScene(scene);
        stage.show();
    }

  

    public static void main(String[] args) {
        launch();
        //this launches the start method
    }

}