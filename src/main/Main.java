package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.LoginView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize primary stage
        primaryStage.setTitle("University Management System");

        // Initial scene setup (login screen)
        LoginView loginView = new LoginView();
        Scene scene = new Scene(loginView.getView(), 800, 600);


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
