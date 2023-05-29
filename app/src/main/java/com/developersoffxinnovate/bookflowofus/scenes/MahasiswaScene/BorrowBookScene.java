package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import java.util.List;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.controllers.BorrowBookController;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Book;
import com.developersoffxinnovate.bookflowofus.models.Mahasiswa;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;

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

        TableView<Book> tableBorrowBook = new TableView<>();
        tableBorrowBook.getStyleClass().add("tableBorrowBook");
        TableColumn<Book, Integer> column1 = new TableColumn<>("No.");
        column1.getStyleClass().add("columnBorrowNo");
        column1.setResizable(false);
        TableColumn<Book, String> column2 = new TableColumn<>("Judul");
        column2.getStyleClass().add("columnBorrowJudul");
        column2.setResizable(false);
        TableColumn<Book, String> column3 = new TableColumn<>("Pengarang");
        column3.getStyleClass().add("columnBorrowPengarang");
        column3.setResizable(false);
        TableColumn<Book, Integer> column4 = new TableColumn<>("Stok");
        column4.getStyleClass().add("columnBorrowStok");
        column4.setResizable(false);

        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("judul"));
        column3.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        column4.setCellValueFactory(new PropertyValueFactory<>("stok"));

        tableBorrowBook.getColumns().addAll(column1, column2, column3, column4);
        tableBorrowBook.setItems(books);
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

        Label headerContent = new Label("Skuy Borrow Book");

        Label bookChoice = new Label("(My Choice)");
        bookChoice.getStyleClass().add("bookChoice");
        Label borrowBookStatus = new Label("Status: Belum Pinjam Buku");
        Button confirmButton = new Button("Konfirmasi\nPinjaman");
        HBox containerFooterContent = new HBox(bookChoice, borrowBookStatus, confirmButton);
        containerFooterContent.getStyleClass().add("containerFooterContent");
        containerFooterContent.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox(headerContent, tableBorrowBook, containerFooterContent);
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

        /* ===> LOGIC AREA <=== */
        String[] judulBuku = { "" };
        int[] idBuku = { -1 };
        tableBorrowBook.setOnMouseClicked(e -> {
            Book selectedBook = tableBorrowBook.getSelectionModel().getSelectedItem();
            int idSelectedBook = selectedBook.getId();
            judulBuku[0] = selectedBook.getJudul();
            idBuku[0] = idSelectedBook;
            bookChoice.setText(selectedBook.getJudul());
            bookChoice.getStyleClass().add("bookChoiceSelected");
        });

        confirmButton.setOnAction(e -> {
            if (idBuku[0] != -1) {
                bookChoice.getStyleClass().clear();
                borrowBookStatus.getStyleClass().clear();
                confirmButton.setDisable(true);
                bookChoice.setText("Loading...");
                bookChoice.getStyleClass().add("bookChoiceLoading");
                borrowBookStatus.setText("Loading...");
                borrowBookStatus.getStyleClass().add("borrowBookStatusLoading");
                if (MahasiswaController.validatePinjamBuku(mahasiswa.getId())) {
                    if (BooksController.validateStok(idBuku[0])) {
                        if (BorrowBookController.borrowBook(mahasiswa.getId(), idBuku[0])) {
                            Thread thread1 = new Thread(() -> {
                                try {
                                    Thread.sleep(2000);
                                    Platform.runLater(() -> {
                                        books.clear();
                                        tableBorrowBook.setDisable(true);
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
                                        bookChoice.setText(judulBuku[0]);
                                        bookChoice.getStyleClass().remove("bookChoiceLoading");
                                        bookChoice.getStyleClass().add("bookChoiceSelected");
                                        borrowBookStatus.setText("Success Borrow Book!");
                                        borrowBookStatus.getStyleClass().add("borrowBookStatusSuccess");
                                        books.setAll(BooksController.getAllBuku());
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
                                        tableBorrowBook.setDisable(false);
                                        bookChoice.setText("Redirecting to Home...");
                                        bookChoice.getStyleClass().add("bookChoiceReturn");
                                        borrowBookStatus.setText("Happy Reading :D");
                                        borrowBookStatus.getStyleClass().add("borrowBookStatusReturn");
                                    });
                                } catch (InterruptedException err) {
                                    err.printStackTrace();
                                }
                            });
                            Thread thread4 = new Thread(() -> {
                                try {
                                    thread3.join();
                                    Thread.sleep(3000);
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
                        } else {
                            borrowBookStatus.setText("Gagal Pinjam Buku AOWKAOKWK");
                            borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
                            bookChoice.setText(judulBuku[0]);
                            bookChoice.getStyleClass().add("bookChoiceFailed");
                            confirmButton.setDisable(false);
                        }
                    } else {
                        borrowBookStatus.setText("Out of Stock");
                        borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
                        bookChoice.setText(judulBuku[0]);
                        bookChoice.getStyleClass().add("bookChoiceFailed");
                        confirmButton.setDisable(false);
                    }
                } else {
                    borrowBookStatus.setText("Exceeding The Loan Limit");
                    borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
                    bookChoice.setText(judulBuku[0]);
                    bookChoice.getStyleClass().add("bookChoiceFailed");
                    confirmButton.setDisable(false);
                }
            } else {
                borrowBookStatus.setText("Please Choose One Book :)");
                borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
                bookChoice.setText("null");
                bookChoice.getStyleClass().add("bookChoiceFailed");
            }
        });

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
