package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.scenes.components.BookList;
import com.developersoffxinnovate.bookflowofus.scenes.components.Header;
import com.developersoffxinnovate.bookflowofus.scenes.components.NavbarAdmin;

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
        HBox containerMain = new HBox(NavbarAdmin.getNavbar(stage, user, activeNavItem), containerContent);

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
