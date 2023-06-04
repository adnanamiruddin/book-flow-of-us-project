package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.AdminController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Admin;
import com.developersoffxinnovate.bookflowofus.scenes.components.HeaderAdmin;
import com.developersoffxinnovate.bookflowofus.scenes.components.NavbarAdmin;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageAdminScene extends AbstractScene implements InterfaceSceneProps {

    public HomePageAdminScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String user) {
        /* ===> INSTANCE AREA START <=== */
        Admin admin = AdminController.getAdminByUser(user);
        /* ===> INSTANCE AREA END <=== */

        Image bookHomePage = new Image(getClass().getClassLoader().getResourceAsStream("img/bookHomePage.jpg"));
        ImageView containerBookHomePage = new ImageView(bookHomePage);
        containerBookHomePage.setFitHeight(200);
        containerBookHomePage.setFitWidth(350);

        Label userNameAdmin = new Label(String.format("Welcome %s", admin.getUser()));
        userNameAdmin.getStyleClass().add("userNameAdmin");
        Label greetingText = new Label("How are you? :D");
        VBox containerProfile = new VBox(userNameAdmin, greetingText);
        containerProfile.getStyleClass().add("containerProfile");
        containerProfile.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox(containerBookHomePage, containerProfile);
        containerContent.getStyleClass().add("containerContent");
        containerContent.setAlignment(Pos.TOP_CENTER);

        String activeNavItem = "Home Page";
        HBox containerMain = new HBox(NavbarAdmin.getNavbar(stage, user, activeNavItem), containerContent);

        HeaderAdmin containerHeader = new HeaderAdmin();
        VBox main = new VBox(containerHeader.getHeader(), containerMain);
        main.getStyleClass().add("backgroundApp");

        super.getScene().setRoot(main);
        scene.getStylesheets().add(getClass().getResource("/styles/AdminScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();
    }
}
