package com.midnightboys.bookflowofus.scenes.AdminScene;

import com.midnightboys.bookflowofus.abstracts.AbstractScene;
import com.midnightboys.bookflowofus.interfaces.InterfaceSceneProps;
import com.midnightboys.bookflowofus.scenes.components.BookList;
import com.midnightboys.bookflowofus.scenes.components.Header;
import com.midnightboys.bookflowofus.scenes.components.Navbar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookListAdminScene extends AbstractScene implements InterfaceSceneProps {

    public BookListAdminScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String user) {
        Label headerContent = new Label("Library");
        VBox containerContent = new VBox(headerContent, BookList.getBookList());
        containerContent.getStyleClass().add("containerContentBook");
        containerContent.setAlignment(Pos.TOP_CENTER);

        String activeNavItem = "Book List";
        HBox containerMain = new HBox(Navbar.getNavbarAdmin(stage, user, activeNavItem), containerContent);

        Header containerHeader = new Header();
        VBox main = new VBox(containerHeader.getHeaderAdmin(), containerMain);
        main.getStyleClass().add("backgroundApp");

        super.getScene().setRoot(main);
        scene.getStylesheets().add(getClass().getResource("/styles/AdminScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();
    }

}
