package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import java.util.List;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.controllers.BorrowBookController;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Book;
import com.developersoffxinnovate.bookflowofus.models.Mahasiswa;
import com.developersoffxinnovate.bookflowofus.scenes.Navbar;

import javafx.application.Platform;
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
            books.add(new Book(book.getId(), book.getJudul(), book.getPengarang(), book.getPenerbit(),
                    book.getTahunTerbit(), book.getStok()));
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

        Label bookChoice = new Label("Pilihan: ");
        Label borrowBookStatus = new Label("Status: Belum Pinjam Buku");
        Button confirmButton = new Button("Konfirmasi Pinjaman");
        HBox containerFooterContent = new HBox(bookChoice, borrowBookStatus, confirmButton);
        containerFooterContent.getStyleClass().add("containerFooterContent");
        containerFooterContent.setSpacing(10);

        VBox containerContent = new VBox(tableBook, containerFooterContent);
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
        int[] idBuku = { -1 };
        tableBook.setOnMouseClicked(e -> {
            Book selectedBook = tableBook.getSelectionModel().getSelectedItem();
            int idSelectedBook = selectedBook.getId();
            idBuku[0] = idSelectedBook;
            bookChoice.setText(selectedBook.getJudul());
        });

        confirmButton.setOnAction(e -> {
            if (BorrowBookController.pinjamBuku(mahasiswa.getId(), idBuku[0])) {
                borrowBookStatus.setText("Loading... :v");
                Thread thread1 = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        Platform.runLater(() -> {
                            books.clear();
                            tableBook.setDisable(true);
                        });
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                });
                Thread thread2 = new Thread(() -> {
                    try {
                        thread1.join();
                        Thread.sleep(500);
                        Platform.runLater(() -> {
                            borrowBookStatus.setText("Berhasil Pinjam Buku");
                        });
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                });
                Thread thread3 = new Thread(() -> {
                    try {
                        thread2.join();
                        Thread.sleep(3000);
                        Platform.runLater(() -> {
                            books.setAll(BooksController.getAllBuku());
                            // tableBook.setDisable(false); (MASIH BIMBANG)
                            borrowBookStatus.setText("Returning to Home...");
                        });
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                });
                Thread thread4 = new Thread(() -> {
                    try {
                        thread3.join();
                        Thread.sleep(2000);
                        Platform.runLater(() -> {
                            HomePageScene homePageScene = new HomePageScene(stage);
                            homePageScene.show(nim);
                        });
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                });
                thread1.start();
                thread2.start();
                thread3.start();
                thread4.start();

                // Thread myThread = new Thread(() -> {
                // try {
                // Thread.sleep(2000);
                // Platform.runLater(() -> {
                // borrowBookStatus.setText("Returning to Home...");
                // });
                // Thread.sleep(3000);
                // Platform.runLater(() -> {
                // HomePageScene homePageScene = new HomePageScene(stage);
                // homePageScene.show(nim);
                // });
                // } catch (InterruptedException err) {
                // }
                // });
                // myThread.start();
            } else {
                borrowBookStatus.setText("Gagal Pinjam Buku AOWKAOKWK");
            }
        });

        // Label pilihan = new Label("-");
        // Label statusPinjamBuku = new Label("-");
        // final int[] idBuku = {-1};

        // for (Book book : books) {
        // Button bookButton = new Button(String.format("%s - %s - %s - %d",
        // book.getId(), book.getJudul(), book.getPengarang(), book.getStocks()));
        // containerBooks.getChildren().add(bookButton);

        // bookButton.setOnAction(e -> {
        // idBuku[0] = book.getId();
        // pilihan.setText(book.getJudul());
        // });
        // }

        // Button confirmButton = new Button("Konfirmasi Pinjaman");
        // confirmButton.setOnAction(e -> {
        // if (MahasiswaController.validatePinjamBuku(mahasiswa.getId())) {
        // if (BooksController.validateStock(idBuku[0])) {
        // statusPinjamBuku.setText("Validasi Berhasil");
        // if (BorrowBookController.pinjamBuku(mahasiswa.getId(), idBuku[0])) {
        // statusPinjamBuku.setText("Berhasil Pinjam Buku");
        // } else {
        // statusPinjamBuku.setText("Gagal Pinjam Buku AOWKAOKWK");
        // }
        // } else {
        // statusPinjamBuku.setText("Wahh Stock Buku Dah Habis Bang :v");
        // }
        // } else {
        // statusPinjamBuku.setText("Batas Peminjaman Buku Hanya Dua ya Bang");
        // }
        // // BorrowBookController.pinjamBuku(idMahasiswa[0], idBuku[0]);
        // });
    }

}
