package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceScene;
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

public class HomePageScene extends AbstractScene implements InterfaceScene {

    public HomePageScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label headerText = new Label("Home Page Area");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        Button toHomePageScene = new Button("Home");
        toHomePageScene.getStyleClass().add("toHomePageScene");
        Button toBookListScene = new Button("Book List");
        toBookListScene.getStyleClass().add("toBookListScene");
        Button toBorrowBookScene = new Button("Borrow Book");
        toBorrowBookScene.getStyleClass().add("toBorrowBookScene");
        Button toRateBookScene = new Button("History");
        toRateBookScene.getStyleClass().add("toContactAdminScene");
        VBox containerNavbar = new VBox(toHomePageScene, toBookListScene, toBorrowBookScene, toRateBookScene);
        containerNavbar.getStyleClass().add("containerNavbar");

        Image bookHomePage = new Image(getClass().getClassLoader().getResourceAsStream("img/bookHomePage.jpg"));
        ImageView containerBookHomePage = new ImageView(bookHomePage);
        containerBookHomePage.setFitHeight(200);
        containerBookHomePage.setFitWidth(350);

        Label namaMahasiswa = new Label("Welcome %nama");
        Label prodiMahasiswa = new Label("%prodi");
        Label bukuDipinjam = new Label("Kamu sedang meminjam %d buku");
        bukuDipinjam.getStyleClass().add("bukuDipinjam");
        VBox containerProfile = new VBox(namaMahasiswa, prodiMahasiswa, bukuDipinjam);
        containerProfile.getStyleClass().add("containerProfile");
        containerProfile.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox(containerBookHomePage, containerProfile);
        containerContent.getStyleClass().add("containerContent");

        HBox containerMain = new HBox(containerNavbar, containerContent);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/HomePageScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        
        

        // Button toBookListScene = new Button("Book\nList");
        // toBookListScene.getStyleClass().add("toBookListScene");
        // Button toBorrowBookScene = new Button("Borrow\nBook");
        // toBookListScene.getStyleClass().add("toBorrowBookScene");
        // Button toRateBookScene = new Button("History\nBorrow");
        // toBookListScene.getStyleClass().add("toContactAdminScene");
        // HBox containerMenu = new HBox(toBookListScene, toBorrowBookScene, toRateBookScene);
        // containerMenu.getStyleClass().add("containerMenu");
        // containerMenu.setAlignment(Pos.CENTER);

        // Button logOutButton = new Button("Log Out");
        // Button toContactAdminScene = new Button("Contact Admin");
        // toContactAdminScene.getStyleClass().add("toContactAdminScene");

        // HBox containerFooter = new HBox(toContactAdminScene, logOutButton);
        // containerFooter.getStyleClass().add("containerFooter");
        // containerFooter.setAlignment(Pos.CENTER);

        // VBox main = new VBox(containerHeader, containerProfile, containerMenu, containerFooter);
        // main.getStyleClass().add("backgroundHomePage");

        // Scene scene = new Scene(main, 750, 700);
        // scene.getStylesheets().add(getClass().getResource("/styles/HomePageScene.css").toExternalForm());
        // stage.setScene(scene);
        // stage.show();
        // main.requestFocus();

        // /* ===> LOGIC AREA <=== */
        // logOutButton.setOnAction(e -> {
        //     LoginScene loginScene = new LoginScene(stage);
        //     loginScene.show();
        // });









        // Label label = new Label("Home Page Sceme");

        // Button backToHomeButton = new Button("Log Out");
        // backToHomeButton.setOnAction(e -> showLandingPageScene());

        // Button toBorrowBookScene = new Button("Borrow Book");
        // toBorrowBookScene.setOnAction(e -> showBorrowBookScene(nim));

        // VBox containerScene = new VBox(label, containerHeader, backToHomeButton, toBorrowBookScene);

        // HBox main = new HBox(containerScene);

        // Scene scene = new Scene(main, 400, 600);
        // stage.setScene(scene);
        // stage.show();
    }
    
}
