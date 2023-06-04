package com.developersoffxinnovate.bookflowofus.abstracts;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public abstract class AbstractScene {
    private VBox vBox;
    protected Stage stage;
    protected Scene scene;

    public AbstractScene(Stage stage) {
        this.stage = stage;
        vBox = new VBox();
        scene = new Scene(vBox, 750, 700);
    }

    protected Scene getScene() {
        return scene;
    }

    public abstract void show();
}
