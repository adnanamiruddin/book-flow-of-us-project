package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.SceneAbstract;
import com.developersoffxinnovate.bookflowofus.interfaces.ScenePropsInterface;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomePageScene extends SceneAbstract implements ScenePropsInterface {

    public HomePageScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show(String nim) {
        Label headerText = new Label("Home Page Area");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        Image bookHomePage = new Image(getClass().getClassLoader().getResourceAsStream("img/bookHomePage.jpg"));
        ImageView containerBookHomePage = new ImageView(bookHomePage);
        containerBookHomePage.setFitHeight(170);
        containerBookHomePage.setFitWidth(300);

        Label namaMahasiswa = new Label("Welcome %nama");
        Label prodiMahasiswa = new Label("%prodi");
        Label bukuDipinjam = new Label("Kamu sedang meminjam %d buku");
        bukuDipinjam.getStyleClass().add("bukuDipinjam");
        // Mahasiswa mahasiswa = MahasiswaController.getMahasiswaByNim(nim);
        // Label namaMahasiswa = new Label(String.format("Hai %s", mahasiswa.getNama()));
        // Label prodiMahasiswa = new Label(String.format("Kamu dari prodi %s kann ><", mahasiswa.getProdi()));
        // Label bukuDipinjam = new Label(String.format("Yahaha kamu sedang meminjam %d buku", mahasiswa.getBukuDipinjam()));
        VBox containerProfileText = new VBox(namaMahasiswa, prodiMahasiswa, bukuDipinjam);
        containerProfileText.getStyleClass().add("containerProfileText");
        containerProfileText.setAlignment(Pos.CENTER_LEFT);

        HBox containerProfile = new HBox(containerBookHomePage, containerProfileText);
        containerProfile.getStyleClass().add("containerProfile");

        FlowPane containerApp = new FlowPane();
        containerApp.getChildren().addAll();

        VBox main = new VBox(containerHeader, containerApp, containerProfile);
        main.getStyleClass().add("backgroundHomePage");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/HomePageScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();






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
