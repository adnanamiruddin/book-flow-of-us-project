package com.midnightboys.bookflowofus;

import com.midnightboys.bookflowofus.scenes.OpenScene.LoginScene;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Book Flow of Us");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("img/bookIcon.png"));
        stage.getIcons().add(image);

        LoginScene loginScene = new LoginScene(stage);
        loginScene.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
