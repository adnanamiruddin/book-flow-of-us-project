package com.midnightboys.bookflowofus.scenes.components;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Content {

    public VBox getContainerComingSoon() {
        Image comingSoon = new Image(getClass().getClassLoader().getResourceAsStream("img/comingSoon.jpg"));
        ImageView containerComingSoon = new ImageView(comingSoon);
        containerComingSoon.setFitHeight(350);
        containerComingSoon.setFitWidth(500);

        VBox containerContent = new VBox(containerComingSoon);
        containerContent.getStyleClass().add("containerContent");
        containerContent.setStyle("-fx-background-color: rgb(32, 32, 32); -fx-background-radius: 10px 0px;");
        containerContent.setAlignment(Pos.CENTER);

        return containerContent;
    }

}
