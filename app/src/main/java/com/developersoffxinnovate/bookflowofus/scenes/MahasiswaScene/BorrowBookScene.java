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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorrowBookScene extends AbstractScene implements InterfaceSceneProps {

    public BorrowBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String nim) {
        /* ===> INSTANCE AREA START <=== */
        Mahasiswa mahasiswa = MahasiswaController.getMahasiswaByNim(nim);
        List<Book> booksData = BooksController.getAllBuku();

        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(booksData);
        // for (Book book : booksData) {
        //     books.add(book);
        // }

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
        Button toBookListScene = new Button("Book List");
        Button toBorrowBookScene = new Button("Borrow Book");
        Button toRateBookScene = new Button("History");
        VBox containerNavbarMenu = new VBox(toHomePageScene, toBookListScene, toBorrowBookScene, toRateBookScene);
        containerNavbarMenu.getStyleClass().add("containerNavbarMenu");
        toBorrowBookScene.setId("activeNav");

        Button logOutButton = new Button("Log Out");
        Button toContactAdminScene = new Button("Contact Admin");
        toContactAdminScene.getStyleClass().add("toContactAdminScene");
        VBox containerNavbarFooter = new VBox(toContactAdminScene, logOutButton);
        containerNavbarFooter.getStyleClass().add("containerNavbarFooter");

        VBox containerNavbar = new VBox(containerNavbarMenu, containerNavbarFooter);
        containerNavbar.getStyleClass().add("containerNavbar");
        /* NAVBAR SECTION END */

        Label headerText = new Label("Book Flow of Us");
        Image imageBook = new Image(getClass().getClassLoader().getResourceAsStream("img/book.jpg"));
        ImageView containerImageBook = new ImageView(imageBook);
        containerImageBook.setFitHeight(110);
        containerImageBook.setFitWidth(120);
        HBox containerHeader = new HBox(containerImageBook, headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER_LEFT);

        Label headerContent = new Label("Skuy Borrow Book");

        Label bookSelection = new Label("(My Selection)");
        bookSelection.getStyleClass().add("bookSelection");
        Label borrowBookStatus = new Label("Status:\nBelum Pinjam Buku");
        Button confirmButton = new Button("Konfirmasi\nPinjaman");
        HBox containerFooterContent = new HBox(bookSelection, borrowBookStatus, confirmButton);
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
            judulBuku[0] = selectedBook.getJudul();
            idBuku[0] = selectedBook.getId();
            bookSelection.getStyleClass().clear();
            bookSelection.setText(selectedBook.getJudul());
            bookSelection.getStyleClass().add("bookSelectionSelected");
        });

        confirmButton.setOnAction(e -> {
            if (idBuku[0] != -1) {
                if (MahasiswaController.validatePinjamBuku(mahasiswa.getId())) {
                    if (BooksController.validateStok(idBuku[0])) {
                        if (BorrowBookController.borrowBook(mahasiswa.getId(), idBuku[0])) {
                            bookSelection.getStyleClass().clear();
                            borrowBookStatus.getStyleClass().clear();
                            confirmButton.setDisable(true);
                            bookSelection.setText("Loading...");
                            bookSelection.getStyleClass().add("bookSelectionLoading");
                            borrowBookStatus.setText("Loading...");
                            borrowBookStatus.getStyleClass().add("borrowBookStatusLoading");
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
                                        bookSelection.getStyleClass().clear();
                                        borrowBookStatus.getStyleClass().clear();
                                        books.setAll(BooksController.getAllBuku());
                                        bookSelection.setText(judulBuku[0]);
                                        bookSelection.getStyleClass().add("bookSelectionSelected");
                                        borrowBookStatus.setText("Success Borrow Book!");
                                        borrowBookStatus.getStyleClass().add("borrowBookStatusSuccess");
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
                                        bookSelection.getStyleClass().clear();
                                        borrowBookStatus.getStyleClass().clear();
                                        tableBorrowBook.setDisable(false);
                                        bookSelection.setText("Happy Reading :D");
                                        bookSelection.getStyleClass().add("bookSelectionReturn");
                                        borrowBookStatus.setText("Redirecting to Home...");
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
                            bookSelection.setText(judulBuku[0]);
                            bookSelection.getStyleClass().add("bookSelectionFailed");
                            borrowBookStatus.setText("Gagal Pinjam Buku AOWKAOKWK");
                            borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
                        }
                    } else {
                        bookSelection.setText(judulBuku[0]);
                        bookSelection.getStyleClass().add("bookSelectionFailed");
                        borrowBookStatus.setText("Out of Stock");
                        borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
                    }
                } else {
                    bookSelection.setText(judulBuku[0]);
                    bookSelection.getStyleClass().add("bookSelectionFailed");
                    borrowBookStatus.setText("Exceeding The\nLoan Limit");
                    borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
                }
            } else {
                bookSelection.setText("???");
                bookSelection.getStyleClass().add("bookSelectionFailed");
                borrowBookStatus.setText("Please Choose\nOne Book :)");
                borrowBookStatus.getStyleClass().add("borrowBookStatusFailed");
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
