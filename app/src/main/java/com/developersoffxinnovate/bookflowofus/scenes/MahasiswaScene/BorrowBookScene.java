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

public class BorrowBookScene extends AbstractScene implements InterfaceSceneProps {

    public BorrowBookScene(Stage stage) {
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
        TableColumn<Book, Integer> column4 = new TableColumn<>("Stok");
        column4.getStyleClass().add("columnTahun");

        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("judul"));
        column3.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        column4.setCellValueFactory(new PropertyValueFactory<>("stok"));

        tableBook.getColumns().addAll(column1, column2, column3, column4);
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

        /* ===> LOGIC AREA <=== */
        tableBook.setOnMouseClicked(e -> {
            Book selectedBook = tableBook.getSelectionModel().getSelectedItem();

            System.out.println(selectedBook.getJudul());
        });

        // VBox containerBooks = new VBox();
        // containerBooks.setSpacing(20);

        // Label pilihan = new Label("-");
        // Label statusPinjamBuku = new Label("-");
        // final int[] idBuku = {-1};

        // for (Book book : books) {
        //     Button bookButton = new Button(String.format("%s - %s - %s - %d", book.getId(), book.getJudul(), book.getPengarang(), book.getStocks()));
        //     containerBooks.getChildren().add(bookButton);

        //     bookButton.setOnAction(e -> {
        //         idBuku[0] = book.getId();
        //         pilihan.setText(book.getJudul());
        //     });
    }
    
}
