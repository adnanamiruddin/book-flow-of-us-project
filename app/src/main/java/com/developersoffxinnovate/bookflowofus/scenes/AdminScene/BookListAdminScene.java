package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Book;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;
import com.developersoffxinnovate.bookflowofus.scenes.helpers.BookList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookListAdminScene extends AbstractScene implements InterfaceSceneProps {

    public BookListAdminScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {}

    @Override
    public void show(String user) {
        /* ===> INSTANCE AREA START <=== */
        TableView<Book> tableBook = BookList.getBookList();
        /* ===> INSTANCE AREA END <=== */

        /* NAVBAR SECTION START */
        Button toHomePageAdminScene = new Button("Home");
        Button toBookListAdminScene = new Button("Book List");
        Button toAddBookScene = new Button("Add Book");
        Button toReturnBookScene = new Button("Return Book");
        VBox containerNavbarMenu = new VBox(toHomePageAdminScene, toBookListAdminScene, toAddBookScene, toReturnBookScene);
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

        Label headerContent = new Label("Library");

        Label bookSelection = new Label("(Judul)");
        bookSelection.getStyleClass().add("dataNameSelection");
        TextField inputConfirmation = new TextField();
        inputConfirmation.setPromptText("KONFIRMASI");
        inputConfirmation.getStyleClass().add("inputConfirmation");
        VBox containerBookSelection = new VBox(bookSelection, inputConfirmation);
        containerBookSelection.getStyleClass().add("containerDataSelection");

        Label deleteBookStatus = new Label("Status:\nBelum Mengonfirmasi");
        Button confirmButton = new Button("Konfirmasi\nPengembalian");
        HBox containerFooterContent = new HBox(containerBookSelection, deleteBookStatus, confirmButton);
        containerFooterContent.getStyleClass().add("containerFooterContent");
        containerFooterContent.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox(headerContent, tableBook);
        containerContent.getStyleClass().add("containerContentBook");
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
        Book[] book = { null };

        tableBook.setOnMouseClicked(e -> {
            Book selectedBook = tableBook.getSelectionModel().getSelectedItem();
            book[0] = BooksController.getBookById(selectedBook.getId());
        });

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
    }
}
