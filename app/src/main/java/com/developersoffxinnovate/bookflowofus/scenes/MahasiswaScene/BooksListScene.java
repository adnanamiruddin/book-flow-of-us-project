package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;
import com.developersoffxinnovate.bookflowofus.scenes.components.BookList;
import com.developersoffxinnovate.bookflowofus.scenes.components.Header;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BooksListScene extends AbstractScene implements InterfaceSceneProps {

    public BooksListScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String nim) {
        /* NAVBAR SECTION START */
        Button toHomePageScene = new Button("Home");
        Button toBookListScene = new Button("Book List");
        Button toBorrowBookScene = new Button("Borrow Book");
        Button toHistoryBorrowBookScene = new Button("History");
        VBox containerNavbarMenu = new VBox(toHomePageScene, toBookListScene, toBorrowBookScene, toHistoryBorrowBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");
        toBookListScene.setId("activeNav");

        Button logOutButton = new Button("Log Out");
        Button toContactAdminScene = new Button("Contact Admin");
        toContactAdminScene.getStyleClass().add("toContactAdminScene");
        VBox containerNavbarFooter = new VBox(toContactAdminScene, logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");
        toBookListScene.getStyleClass().add("activeNav");
        /* NAVBAR SECTION END */

        Label headerContent = new Label("Find Your Favourite Book");
        VBox containerContent = new VBox(headerContent, BookList.getBookList());
        containerContent.getStyleClass().add("containerContentBook");
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

        toHistoryBorrowBookScene.setOnAction(e -> {
            HistoryBorrowBookScene historyBookScene = new HistoryBorrowBookScene(stage);
            historyBookScene.show(nim);
        });

        logOutButton.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });
    }
}
