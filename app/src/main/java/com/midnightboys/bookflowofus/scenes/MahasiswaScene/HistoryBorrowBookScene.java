package com.midnightboys.bookflowofus.scenes.MahasiswaScene;

import com.midnightboys.bookflowofus.abstracts.AbstractScene;
import com.midnightboys.bookflowofus.interfaces.InterfaceSceneProps;
import com.midnightboys.bookflowofus.scenes.components.Content;
import com.midnightboys.bookflowofus.scenes.components.Header;
import com.midnightboys.bookflowofus.scenes.components.Navbar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryBorrowBookScene extends AbstractScene implements InterfaceSceneProps {

    public HistoryBorrowBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String nim) {
        /* ===> INSTANCE AREA START <=== */
        Content content = new Content();
        /* ===> INSTANCE AREA END <=== */

        String activeNavItem = "History";
        HBox containerMain = new HBox(Navbar.getNavbarMahasiswa(stage, nim, activeNavItem),
                content.getContainerComingSoon());

        Header containerHeader = new Header();
        VBox main = new VBox(containerHeader.getHeaderMahasiswa(), containerMain);
        main.getStyleClass().add("backgroundApp");

        super.getScene().setRoot(main);
        scene.getStylesheets().add(getClass().getResource("/styles/MahasiswaScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();
    }

}
