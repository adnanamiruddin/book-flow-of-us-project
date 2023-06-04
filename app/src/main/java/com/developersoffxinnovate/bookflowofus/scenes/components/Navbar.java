package com.developersoffxinnovate.bookflowofus.scenes.components;

import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.AddBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.BookListAdminScene;
import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.DeleteBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.HomePageAdminScene;
import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.ReturnBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene.BooksListScene;
import com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene.BorrowBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene.HistoryBorrowBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene.HomePageScene;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Navbar {
    private static Button activeNav;

    public static void setActiveNav(Button activeNav) {
        Navbar.activeNav = activeNav;
    }

    public static VBox getNavbarMahasiswa(Stage stage, String nim, String activeNavItem) {
        Button toHomePageScene = new Button("Home");
        Button toBookListScene = new Button("Book List");
        Button toBorrowBookScene = new Button("Borrow Book");
        Button toHistoryBorrowBookScene = new Button("History");
        VBox containerNavbarMenu = new VBox(toHomePageScene, toBookListScene, toBorrowBookScene,
                toHistoryBorrowBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");

        Button logOutButton = new Button("Log Out");
        VBox containerNavbarFooter = new VBox(logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");

        /* ===> LOGIC AREA <=== */
        switch (activeNavItem) {
            case "Home Page":
                setActiveNav(toHomePageScene);
                break;
            case "Book List":
                setActiveNav(toBookListScene);
                break;
            case "Borrow Book":
                setActiveNav(toBorrowBookScene);
                break;
            case "History":
                setActiveNav(toHistoryBorrowBookScene);
                break;
            default:
                setActiveNav(toHomePageScene); // Default activeNav
                break;
        }
        ;
        activeNav.setId("activeNav");

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
            stage.close();
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        return containerNavbar;
    }

    public static VBox getNavbarAdmin(Stage stage, String user, String activeNavItem) {
        Button toHomePageAdminScene = new Button("Home");
        Button toBookListAdminScene = new Button("Book List");
        Button toAddBookScene = new Button("Add Book");
        Button toReturnBookScene = new Button("Return Book");
        Button toDeleteBookScene = new Button("Delete Book");
        VBox containerNavbarMenu = new VBox(toHomePageAdminScene, toBookListAdminScene, toAddBookScene,
                toReturnBookScene, toDeleteBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");

        Button logOutButton = new Button("Log Out");
        VBox containerNavbarFooter = new VBox(logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");

        /* ===> LOGIC AREA <=== */
        switch (activeNavItem) {
            case "Home Page":
                setActiveNav(toHomePageAdminScene);
                break;
            case "Book List":
                setActiveNav(toBookListAdminScene);
                break;
            case "Add Book":
                setActiveNav(toAddBookScene);
                break;
            case "Return Book":
                setActiveNav(toReturnBookScene);
                break;
            case "Delete Book":
                setActiveNav(toDeleteBookScene);
                break;
            default:
                setActiveNav(toHomePageAdminScene); // Default activeNav
                break;
        }
        ;
        activeNav.setId("activeNav");

        toHomePageAdminScene.setOnAction(e -> {
            HomePageAdminScene homePageAdminScene = new HomePageAdminScene(stage);
            homePageAdminScene.show(user);
        });
        toBookListAdminScene.setOnAction(e -> {
            BookListAdminScene bookListAdminScene = new BookListAdminScene(stage);
            bookListAdminScene.show(user);
        });
        toAddBookScene.setOnAction(e -> {
            AddBookScene addBookScene = new AddBookScene(stage);
            addBookScene.show(user);
        });
        toReturnBookScene.setOnAction(e -> {
            ReturnBookScene returnBookScene = new ReturnBookScene(stage);
            returnBookScene.show(user);
        });
        toDeleteBookScene.setOnAction(e -> {
            DeleteBookScene deleteBookScene = new DeleteBookScene(stage);
            deleteBookScene.show(user);
        });
        logOutButton.setOnAction(e -> {
            stage.close();
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        return containerNavbar;
    }

}
