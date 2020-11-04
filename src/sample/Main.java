package sample;

//Author Name: Alex Porter
//Date: 10/11/2020
//Program Name: Main
//Purpose: Launches the application

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Word Counter GUI");
        primaryStage.setScene(new Scene(root, 761, 421));
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
