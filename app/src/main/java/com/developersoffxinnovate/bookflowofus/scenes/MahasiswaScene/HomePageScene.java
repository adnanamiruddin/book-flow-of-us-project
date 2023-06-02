package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Mahasiswa;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;
import com.developersoffxinnovate.bookflowofus.scenes.components.Header;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        HBox containerMain = new HBox(containerNavbar, containerContent);

        Header containerHeader = new Header();
        VBox main = new VBox(containerHeader.getHeader(), containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/MahasiswaScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */
        toHomePageScene.setOnAction(e -> {
            HomePageScene homePageScene = new HomePageScene(stage);
            homePageScene.show(nim);
        });

        toBookListScene.setOnAction(e -> {
            BooksListScene booksListScene = new BooksListScene(stage);
            booksListScene.show(nim);
        });

        toBorrowBookScene.setOnAction(e -> {
            BorrowBookScene borrowBookScene = new BorrowBookScene(stage);
            borrowBookScene.show(nim);
        });

        logOutButton.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });
    }

}
