package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.AdminController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Admin;
import com.developersoffxinnovate.bookflowofus.scenes.Navbar;

import javafx.geometry.Pos;
import javafx.scene.Scene;
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

        Label headerText = new Label("Book Flow of Us");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        Image bookHomePage = new Image(getClass().getClassLoader().getResourceAsStream("img/bookHomePage.jpg"));
        ImageView containerBookHomePage = new ImageView(bookHomePage);
        containerBookHomePage.setFitHeight(200);
        containerBookHomePage.setFitWidth(350);

        Label userNameAdmin = new Label(String.format("Welcome %s", admin.getUser()));
        Label greetingText = new Label("How are you? :D");
        VBox containerProfile = new VBox(userNameAdmin);
        containerProfile.getStyleClass().add("containerProfile");
        containerProfile.setAlignment(Pos.CENTER);

        // Label namaMahasiswa = new Label(String.format("Welcome %s", mahasiswa.getNama()));
        // Label prodiMahasiswa = new Label(String.format("===>  %s  <===", mahasiswa.getProdi()));
        // Label bukuDipinjam = new Label(String.format("Kamu sedang meminjam %d buku", mahasiswa.getBukuDipinjam()));
        // bukuDipinjam.getStyleClass().add("bukuDipinjam");

        VBox containerContent = new VBox(containerBookHomePage);
        containerContent.getStyleClass().add("containerContent");
        containerContent.setAlignment(Pos.TOP_CENTER);

        HBox containerMain = new HBox(Navbar.getNavbar(stage, user), containerContent);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/AdminScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();
    }
    
}
