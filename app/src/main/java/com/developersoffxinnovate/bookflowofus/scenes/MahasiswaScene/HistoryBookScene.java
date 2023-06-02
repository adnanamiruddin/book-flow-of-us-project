package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryBookScene extends AbstractScene implements InterfaceSceneProps {

    public HistoryBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String userName) {
        /* NAVBAR SECTION START */
        Button toHomePageScene = new Button("Home");
        Button toBookListScene = new Button("Book List");
        Button toBorrowBookScene = new Button("Borrow Book");
        Button toHistoryBookScene = new Button("History");
        VBox containerNavbarMenu = new VBox(toHomePageScene, toBookListScene, toBorrowBookScene, toHistoryBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");
        toHomePageScene.setId("activeNav");

        Button logOutButton = new Button("Log Out");
        Button toContactAdminScene = new Button("Contact Admin");
        toContactAdminScene.getStyleClass().add("toContactAdminScene");
        VBox containerNavbarFooter = new VBox(toContactAdminScene, logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");
        /* NAVBAR SECTION END */

        
    }
    
}
