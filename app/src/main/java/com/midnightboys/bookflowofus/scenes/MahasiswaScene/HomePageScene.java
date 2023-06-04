package com.midnightboys.bookflowofus.scenes.MahasiswaScene;

import com.midnightboys.bookflowofus.abstracts.AbstractScene;
import com.midnightboys.bookflowofus.controllers.MahasiswaController;
import com.midnightboys.bookflowofus.interfaces.InterfaceSceneProps;
import com.midnightboys.bookflowofus.models.Mahasiswa;
import com.midnightboys.bookflowofus.scenes.components.Header;
import com.midnightboys.bookflowofus.scenes.components.Navbar;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageScene extends AbstractScene implements InterfaceSceneProps {

    public HomePageScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String nim) {
        /* ===> INSTANCE AREA START <=== */
        Mahasiswa mahasiswa = MahasiswaController.getMahasiswaByNim(nim);
        /* ===> INSTANCE AREA END <=== */

        Image bookHomePage = new Image(getClass().getClassLoader().getResourceAsStream("img/bookHomePage.jpg"));
        ImageView containerBookHomePage = new ImageView(bookHomePage);
        containerBookHomePage.setFitHeight(200);
        containerBookHomePage.setFitWidth(350);

        Label namaMahasiswa = new Label(String.format("Hi %s", mahasiswa.getNama()));
        Label prodiMahasiswa = new Label(String.format("===>  %s  <===", mahasiswa.getProdi()));
        // Singular dan plural dalam aturan Bahasa Inggris
        Label bukuDipinjam = new Label(mahasiswa.getBukuDipinjam() > 1
                ? String.format("You are borrowing %d books", mahasiswa.getBukuDipinjam())
                : String.format("You are borrowing %d book", mahasiswa.getBukuDipinjam()));
        bukuDipinjam.getStyleClass().add("bukuDipinjam");
        VBox containerProfile = new VBox(namaMahasiswa, prodiMahasiswa, bukuDipinjam);
        containerProfile.getStyleClass().add("containerProfile");
        containerProfile.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox(containerBookHomePage, containerProfile);
        containerContent.getStyleClass().add("containerContent");
        containerContent.setAlignment(Pos.TOP_CENTER);

        String activeNavItem = "Home Page";
        HBox containerMain = new HBox(Navbar.getNavbarMahasiswa(stage, nim, activeNavItem), containerContent);

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
