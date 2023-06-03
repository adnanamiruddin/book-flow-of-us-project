package com.developersoffxinnovate.bookflowofus.scenes.components;

import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.AddBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.BookListAdminScene;
import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.HomePageAdminScene;
import com.developersoffxinnovate.bookflowofus.scenes.AdminScene.ReturnBookScene;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NavbarAdmin {
    private static Button activeNav;

    public static void setActiveNav(Button activeNav) {
        NavbarAdmin.activeNav = activeNav;
    }

    public static VBox getNavbar(Stage stage, String user, String activeNavItem) {
        Button toHomePageAdminScene = new Button("Home");
        Button toBookListAdminScene = new Button("Book List");
        Button toAddBookScene = new Button("Add Book");
        Button toReturnBookScene = new Button("Return Book");
        VBox containerNavbarMenu = new VBox(toHomePageAdminScene, toBookListAdminScene, toAddBookScene, toReturnBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");
        toAddBookScene.setId("activeNav");

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
            default:
                setActiveNav(toHomePageAdminScene); // Default activeNav
                break;
        };

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
            ReturnBookScene returnBook = new ReturnBookScene(stage);
            returnBook.show(user);
        });

        logOutButton.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });

        return containerNavbar;
    }
}
