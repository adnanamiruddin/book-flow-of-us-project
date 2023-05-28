package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import java.util.List;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceScene;
import com.developersoffxinnovate.bookflowofus.models.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BooksListScene extends AbstractScene implements InterfaceScene {

    public BooksListScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        /* ===> INSTANCE AREA START <=== */
        List<Book> booksData = BooksController.getAllBuku();

        ObservableList<Book> books = FXCollections.observableArrayList();
        for (Book book : booksData) {
            books.add(new Book(book.getId(), book.getJudul(), book.getPengarang(), book.getPenerbit(), book.getTahunTerbit(), book.getStok()));
        }

        TableView<Book> tableBook = new TableView<>();
        tableBook.getStyleClass().add("tableBook");
        TableColumn<Book, Integer> column1 = new TableColumn<>("No.");
        column1.getStyleClass().add("columnNo");
        column1.setResizable(false);
        TableColumn<Book, String> column2 = new TableColumn<>("Judul");
        column2.getStyleClass().add("columnJudul");
        column2.setResizable(false);
        TableColumn<Book, String> column3 = new TableColumn<>("Pengarang");
        column3.getStyleClass().add("columnPengarang");
        column3.setResizable(false);
        TableColumn<Book, String> column4 = new TableColumn<>("Penerbit");
        column4.getStyleClass().add("columnPenerbit");
        column4.setResizable(false);
        TableColumn<Book, Integer> column5 = new TableColumn<>("Tahun Terbit");
        column5.getStyleClass().add("columnTahun");
        column5.setResizable(false);

        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("judul"));
        column3.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        column4.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        column5.setCellValueFactory(new PropertyValueFactory<>("tahunTerbit"));

        tableBook.getColumns().addAll(column1, column2, column3, column4, column5);
        tableBook.setItems(books);
        /* ===> INSTANCE AREA END <=== */

        /* NAVBAR SECTION START */
        Button toHomePageScene = new Button("Home");
        toHomePageScene.getStyleClass().add("toHomePageScene");
        Button toBookListScene = new Button("Book List");
        toBookListScene.getStyleClass().add("toBookListScene");
        Button toBorrowBookScene = new Button("Borrow Book");
        toBorrowBookScene.getStyleClass().add("toBorrowBookScene");
        Button toRateBookScene = new Button("History");
        toRateBookScene.getStyleClass().add("toContactAdminScene");
        VBox containerNavbarMenu = new VBox(toHomePageScene, toBookListScene, toBorrowBookScene, toRateBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");

        Button logOutButton = new Button("Log Out");
        Button toContactAdminScene = new Button("Contact Admin");
        toContactAdminScene.getStyleClass().add("toContactAdminScene");
        VBox containerNavbarFooter = new VBox(toContactAdminScene, logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");
        /* NAVBAR SECTION END */

        Label headerText = new Label("Book Flow of Us");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        Label headerContent = new Label("Find Your Favourite Book");
        VBox containerContent = new VBox(headerContent, tableBook);
        containerContent.getStyleClass().add("containerContentBook");
        containerContent.setAlignment(Pos.TOP_CENTER);

        HBox containerMain = new HBox(containerNavbar, containerContent);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/MahasiswaScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();
    }
}
