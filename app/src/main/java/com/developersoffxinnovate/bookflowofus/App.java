package com.developersoffxinnovate.bookflowofus;

import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setResizable(false);
        stage.setTitle("Book Flow of Us App");

        LoginScene loginScene = new LoginScene(stage);
        loginScene.show();

        MahasiswaController.getDataMahasiswa();
        BooksController.getDataBuku();
    }

    public static void main(String[] args) {
        launch();
    }

}
