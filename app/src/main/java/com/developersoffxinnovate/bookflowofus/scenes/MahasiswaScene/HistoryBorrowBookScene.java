package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.scenes.components.Header;
import com.developersoffxinnovate.bookflowofus.scenes.components.Navbar;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        Image comingSoon = new Image(getClass().getClassLoader().getResourceAsStream("img/comingSoon.jpg"));
        ImageView containerComingSoon = new ImageView(comingSoon);
        containerComingSoon.setFitHeight(350);
        containerComingSoon.setFitWidth(500);

        VBox containerContent = new VBox(containerComingSoon);
        containerContent.getStyleClass().add("containerContent");
        containerContent.getStyleClass().add("backgroundHistory");
        containerContent.setAlignment(Pos.CENTER);

        String activeNavItem = "History";
        HBox containerMain = new HBox(Navbar.getNavbar(stage, nim, activeNavItem), containerContent);

        Header containerHeader = new Header();
        VBox main = new VBox(containerHeader.getHeader(), containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/MahasiswaScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();
    }
    
}
