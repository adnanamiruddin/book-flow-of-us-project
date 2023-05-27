package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import java.util.List;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Book;
import com.developersoffxinnovate.bookflowofus.models.Mahasiswa;
import com.developersoffxinnovate.bookflowofus.scenes.Navbar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BooksListScene extends AbstractScene implements InterfaceSceneProps {

    public BooksListScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show(String nim) {
        /* ===> INSTANCE AREA START <=== */
        Mahasiswa mahasiswa = MahasiswaController.getMahasiswaByNim(nim);
        List<Book> booksData = BooksController.getAllBuku();

        ObservableList<Book> books = FXCollections.observableArrayList();
        for (Book book : booksData) {
            books.add(new Book(book.getId(), book.getJudul(), book.getPengarang(), book.getPenerbit(), book.getTahunTerbit(), book.getStok()));
        }

        TableView<Book> tableBook = new TableView<>();
        tableBook.getStyleClass().add("tableBook");
        TableColumn<Book, Integer> column1 = new TableColumn<>("No.");
        column1.getStyleClass().add("columnNo");
        TableColumn<Book, String> column2 = new TableColumn<>("Judul");
        column2.getStyleClass().add("columnJudul");
        TableColumn<Book, String> column3 = new TableColumn<>("Pengarang");
        column3.getStyleClass().add("columnPengarang");
        TableColumn<Book, String> column4 = new TableColumn<>("Penerbit");
        column4.getStyleClass().add("columnPenerbit");
        TableColumn<Book, Integer> column5 = new TableColumn<>("Tahun Terbit");
        column5.getStyleClass().add("columnTahun");

        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("judul"));
        column3.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        column4.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        column5.setCellValueFactory(new PropertyValueFactory<>("tahunTerbit"));

        tableBook.getColumns().addAll(column1, column2, column3, column4, column5);
        tableBook.setItems(books);
        /* ===> INSTANCE AREA END <=== */

        Label headerText = new Label("Book Flow of Us");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox(tableBook);
        containerContent.getStyleClass().add("containerContent");
        containerContent.setAlignment(Pos.TOP_CENTER);

        HBox containerMain = new HBox(Navbar.getNavbar(stage, mahasiswa.getNim()), containerContent);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/MahasiswaScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();













        // Label label = new Label("Book List Scene");

        // ArrayList<Label> booksId = new ArrayList<>();
        // ArrayList<Label> booksJudul = new ArrayList<>();
        // ArrayList<Label> booksPengarang = new ArrayList<>();
        // ArrayList<Label> booksPenerbit = new ArrayList<>();
        // ArrayList<Label> booksTahunTerbit = new ArrayList<>();
        // ArrayList<Label> booksStocks = new ArrayList<>();
        // ArrayList<Label> booksBarrier = new ArrayList<>();

        // for (Book book : books) {
        //     booksId.add(new Label(String.format("%s", book.getId())));
        //     booksJudul.add(new Label(book.getJudul()));
        //     booksPengarang.add(new Label(book.getPengarang()));
        //     booksPenerbit.add(new Label(book.getPenerbit()));
        //     booksTahunTerbit.add(new Label(String.format("%s", book.getTahunTerbit())));
        //     booksStocks.add(new Label(String.format("%s", book.getStocks())));
        //     booksBarrier.add(new Label("-----------------"));
        // }

        // VBox containerBooksId = new VBox();
        // containerBooksId.getChildren().addAll(booksId);
        // VBox containerBooksJudul = new VBox();
        // containerBooksJudul.getChildren().addAll(booksJudul);
        // VBox containerBooksPengarang = new VBox();
        // containerBooksPengarang.getChildren().addAll(booksPengarang);

        // HBox containerBooks = new HBox(containerBooksId, containerBooksJudul, containerBooksPengarang);
        // containerBooks.setSpacing(20);

        // VBox containerScene = new VBox(label, containerBooks);

        // HBox main = new HBox(containerScene);

        // Scene scene = new Scene(main, 400, 600);
        // stage.setScene(scene);
        // stage.show();
    }
    
}
