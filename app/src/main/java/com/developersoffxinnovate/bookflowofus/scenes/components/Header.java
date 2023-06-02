package com.developersoffxinnovate.bookflowofus.scenes.components;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class Header {
    public HBox getHeader() {
        Label headerText = new Label("Book Flow of Us");
        Image imageBook = new Image(getClass().getClassLoader().getResourceAsStream("img/book.jpg"));
        ImageView containerImageBook = new ImageView(imageBook);
        containerImageBook.setFitHeight(110);
        containerImageBook.setFitWidth(120);
        HBox containerHeader = new HBox(containerImageBook, headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER_LEFT);

        return containerHeader;
    }
}