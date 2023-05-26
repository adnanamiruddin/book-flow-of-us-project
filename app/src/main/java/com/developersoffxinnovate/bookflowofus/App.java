package com.developersoffxinnovate.bookflowofus;

import com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene.BorrowBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene.HomePageScene;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Book Flow of Us App");
        Image image = new Image(getClass().getClassLoader().getResourceAsStream("img/bookIcon.png"));
        stage.getIcons().add(image);

        LoginScene loginScene = new LoginScene(stage);
        // loginScene.show();
        HomePageScene homePageScene = new HomePageScene(stage);
        homePageScene.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
