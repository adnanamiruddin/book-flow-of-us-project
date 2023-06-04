package com.midnightboys.bookflowofus.scenes.AdminScene;

import com.midnightboys.bookflowofus.abstracts.AbstractScene;
import com.midnightboys.bookflowofus.interfaces.InterfaceSceneProps;
import com.midnightboys.bookflowofus.scenes.components.Content;
import com.midnightboys.bookflowofus.scenes.components.Header;
import com.midnightboys.bookflowofus.scenes.components.Navbar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteBookScene extends AbstractScene implements InterfaceSceneProps {

    public DeleteBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String user) {
        /* ===> INSTANCE AREA START <=== */
        Content content = new Content();
        /* ===> INSTANCE AREA END <=== */

        String activeNavItem = "Delete Book";
        HBox containerMain = new HBox(Navbar.getNavbarAdmin(stage, user, activeNavItem),
                content.getContainerComingSoon());

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
