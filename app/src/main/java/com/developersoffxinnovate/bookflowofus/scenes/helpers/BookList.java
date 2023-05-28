package com.developersoffxinnovate.bookflowofus.scenes.helpers;

import java.util.List;

import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.models.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookList {
    public static TableView<Book> getBookList() {
        /* ===> INSTANCE AREA START <=== */
        List<Book> booksData = BooksController.getAllBuku();

        ObservableList<Book> books = FXCollections.observableArrayList();
        for (Book book : booksData) {
            books.add(new Book(book.getId(), book.getJudul(), book.getPengarang(), book.getPenerbit(),
                    book.getTahunTerbit(), book.getStok()));
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

        return tableBook;
    }
}
