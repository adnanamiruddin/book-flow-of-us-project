package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.AdminController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Admin;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    public void show(String user) {
        /* ===> INSTANCE AREA START <=== */
        Admin admin = AdminController.getAdminByUser(user);
        /* ===> INSTANCE AREA END <=== */

        /* NAVBAR SECTION START */
        Button toHomePageScene = new Button("Home");
        toHomePageScene.getStyleClass().add("toHomePageScene");
        Button toBookListScene = new Button("Book List");
        toBookListScene.getStyleClass().add("toBookListScene");
        Button toBorrowBookScene = new Button("Borrow Book");
        toBorrowBookScene.getStyleClass().add("toBorrowBookScene");
        VBox containerNavbarMenu = new VBox(toHomePageScene, toBookListScene, toBorrowBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");

        Button logOutButton = new Button("Log Out");
        VBox containerNavbarFooter = new VBox(logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");
        /* NAVBAR SECTION END */

        Label headerText = new Label("Book Flow of Us");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("headerContent");
        containerHeader.setAlignment(Pos.CENTER);

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

        HBox containerMain = new HBox(containerNavbar, containerContent);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/AdminScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */
        toBookListScene.setOnAction(e -> {
            BookListAdminScene bookListAdminScene = new BookListAdminScene(stage);
            bookListAdminScene.show(user);
        });

        logOutButton.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });
    }
    
}
