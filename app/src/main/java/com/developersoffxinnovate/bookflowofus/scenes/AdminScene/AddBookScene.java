package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBookScene extends AbstractScene implements InterfaceSceneProps {

    public AddBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {}

    @Override
    public void show(String userName) {
        /* NAVBAR SECTION START */
        Button toHomePageAdminScene = new Button("Home");
        toHomePageAdminScene.getStyleClass().add("toHomePageAdminScene");
        Button toBookListAdminScene = new Button("Book List");
        toBookListAdminScene.getStyleClass().add("toBookListAdminScene");
        Button toReturnBookAdminScene = new Button("Return Book");
        toReturnBookAdminScene.getStyleClass().add("toReturnBookAdminScene");
        VBox containerNavbarMenu = new VBox(toHomePageAdminScene, toBookListAdminScene, toReturnBookAdminScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");

        Button logOutButton = new Button("Log Out");
        VBox containerNavbarFooter = new VBox(logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");
        /* NAVBAR SECTION END */

        Label headerText = new Label("Book Flow of Admin");
        Image imageBook = new Image(getClass().getClassLoader().getResourceAsStream("img/admin.png"));
        ImageView containerImageBook = new ImageView(imageBook);
        containerImageBook.setFitHeight(110);
        containerImageBook.setFitWidth(100);
        HBox containerHeader = new HBox(containerImageBook, headerText);
        containerHeader.getStyleClass().add("headerContent");
        containerHeader.setAlignment(Pos.CENTER_LEFT);

        Label headerContent = new Label("Book Loan Data");

        HBox containerMain = new HBox(containerNavbar);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/AdminScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();
    }
    
}
