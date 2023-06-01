package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import java.util.List;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.AdminController;
import com.developersoffxinnovate.bookflowofus.controllers.BooksController;
import com.developersoffxinnovate.bookflowofus.controllers.BorrowBookController;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Book;
import com.developersoffxinnovate.bookflowofus.models.DataPeminjamanBuku;
import com.developersoffxinnovate.bookflowofus.models.Mahasiswa;
import com.developersoffxinnovate.bookflowofus.scenes.OpenScene.LoginScene;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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

public class ReturnBookScene extends AbstractScene implements InterfaceSceneProps {

    public ReturnBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {}

    @Override
    public void show(String user) {
        /* ===> INSTANCE AREA START <=== */
        List<DataPeminjamanBuku> dataPeminjamanBuku = AdminController.getAllDataPeminjamanBuku();

        ObservableList<DataPeminjamanBuku> listPeminjamanBuku = FXCollections.observableArrayList();
        listPeminjamanBuku.addAll(dataPeminjamanBuku);
        // for (DataPeminjamanBuku peminjamanBuku : dataPeminjamanBuku) {
        //     listPeminjamanBuku.add(new DataPeminjamanBuku(peminjamanBuku.getId(), peminjamanBuku.getIdMahasiswa(),
        //             peminjamanBuku.getIdBuku(), peminjamanBuku.getTanggalPinjam(), peminjamanBuku.getTanggalKembali(),
        //             peminjamanBuku.getStatus()));
        // }

        TableView<DataPeminjamanBuku> tableDataPeminjamanBuku = new TableView<>();
        tableDataPeminjamanBuku.getStyleClass().add("tableDataPeminjamanBuku");
        TableColumn<DataPeminjamanBuku, Integer> column1 = new TableColumn<>("ID");
        column1.getStyleClass().add("columnReturnNo");
        TableColumn<DataPeminjamanBuku, String> column2 = new TableColumn<>("NIM Peminjam");
        column2.getStyleClass().add("columnReturnPeminjam");
        TableColumn<DataPeminjamanBuku, String> column3 = new TableColumn<>("Judul Buku");
        column3.getStyleClass().add("columnReturnJudul");
        TableColumn<DataPeminjamanBuku, String> column4 = new TableColumn<>("T.P.");
        column4.getStyleClass().add("columnReturnTP");
        TableColumn<DataPeminjamanBuku, String> column5 = new TableColumn<>("T.K.");
        column5.getStyleClass().add("columnReturnTK");
        TableColumn<DataPeminjamanBuku, String> column6 = new TableColumn<>("Status");
        column6.getStyleClass().add("columnReturnStatus");

        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(cellData -> {
            int idMahasiswa = cellData.getValue().getIdMahasiswa();
            String nimMahasiswa = MahasiswaController.getMahasiswaById(idMahasiswa).getNim();
            return new SimpleStringProperty(nimMahasiswa);
        });
        column3.setCellValueFactory(cellData -> {
            int idBuku = cellData.getValue().getIdBuku();
            String judulBuku = BooksController.getBookById(idBuku).getJudul();
            return new SimpleStringProperty(judulBuku);
        });
        column4.setCellValueFactory(new PropertyValueFactory<>("tanggalPinjam"));
        column5.setCellValueFactory(new PropertyValueFactory<>("tanggalKembali"));
        column6.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableDataPeminjamanBuku.getColumns().addAll(column1, column2, column3, column4, column5, column6);
        tableDataPeminjamanBuku.setItems(listPeminjamanBuku);
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

        Label headerContent = new Label("Book Loan Data");

        Label dataNameSelection = new Label("(Nama)");
        dataNameSelection.getStyleClass().add("dataNameSelection");
        Label dataTitleSelection = new Label("(Judul)");
        dataTitleSelection.getStyleClass().add("dataTitleSelection");
        VBox containerDataSelection = new VBox(dataNameSelection, dataTitleSelection);
        containerDataSelection.getStyleClass().add("containerDataSelection");

        Label returnBookStatus = new Label("Status:\nBelum Mengonfirmasi");
        Button confirmButton = new Button("Konfirmasi\nPengembalian");
        HBox containerFooterContent = new HBox(containerDataSelection, returnBookStatus, confirmButton);
        containerFooterContent.getStyleClass().add("containerFooterContent");
        containerFooterContent.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox(headerContent, tableDataPeminjamanBuku, containerFooterContent);
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
        Mahasiswa[] mahasiswa = { null };
        Book[] book = { null };
        DataPeminjamanBuku[] dataPeminjaman = { null };
        int[] idPeminjaman = { -1 };

        tableDataPeminjamanBuku.setOnMouseClicked(e -> {
            DataPeminjamanBuku selectedData = tableDataPeminjamanBuku.getSelectionModel().getSelectedItem();
            mahasiswa[0] = MahasiswaController.getMahasiswaById(selectedData.getIdMahasiswa());
            book[0] = BooksController.getBookById(selectedData.getIdBuku());
            dataPeminjaman[0] = AdminController.getDataPeminjamanBukuById(selectedData.getId());
            idPeminjaman[0] = selectedData.getId();
            dataNameSelection.getStyleClass().clear();
            dataTitleSelection.getStyleClass().clear();
            dataNameSelection.setText(mahasiswa[0].getNama());
            dataNameSelection.getStyleClass().add("dataNameSelectionSelected");
            dataTitleSelection.setText(book[0].getJudul());
            dataTitleSelection.getStyleClass().add("dataTitleSelectionSelected");
        });

        confirmButton.setOnAction(e -> {
            if (idPeminjaman[0] != -1) {
                if (dataPeminjaman[0].getStatus().equals("Kembali")) {
                    dataNameSelection.getStyleClass().clear();
                    dataTitleSelection.getStyleClass().clear();
                    returnBookStatus.getStyleClass().clear();
                    dataNameSelection.getStyleClass().add("dataSelectionFailed");
                    dataTitleSelection.getStyleClass().add("dataSelectionFailed");
                    returnBookStatus.setText("Book Has Been Returned");
                    returnBookStatus.getStyleClass().add("returnBookStatusFailed");
                } else {
                    dataNameSelection.getStyleClass().clear();
                    dataTitleSelection.getStyleClass().clear();
                    returnBookStatus.getStyleClass().clear();
                    confirmButton.setDisable(true);
                    dataNameSelection.setText("Loading...");
                    dataNameSelection.getStyleClass().add("dataSelectionLoading");
                    dataTitleSelection.setText("Loading...");
                    dataTitleSelection.getStyleClass().add("dataSelectionLoading");
                    returnBookStatus.setText("Loading...");
                    returnBookStatus.getStyleClass().add("returnBookStatusLoading");
                    if (BorrowBookController.returnBook(dataPeminjaman[0].getId(), mahasiswa[0].getId(),
                            book[0].getId())) {
                        Thread thread1 = new Thread(() -> {
                            try {
                                Thread.sleep(2000);
                                Platform.runLater(() -> {
                                    listPeminjamanBuku.clear();
                                    tableDataPeminjamanBuku.setDisable(true);
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
                                    dataNameSelection.getStyleClass().clear();
                                    dataTitleSelection.getStyleClass().clear();
                                    returnBookStatus.getStyleClass().clear();
                                    listPeminjamanBuku.setAll(AdminController.getAllDataPeminjamanBuku());
                                    dataNameSelection.setText(mahasiswa[0].getNama());
                                    dataNameSelection.getStyleClass().add("dataNameSelectionSelected");
                                    dataTitleSelection.setText(book[0].getJudul());
                                    dataTitleSelection.getStyleClass().add("dataTitleSelectionSelected");
                                    returnBookStatus.setText("Successful Confirm!");
                                    returnBookStatus.getStyleClass().add("returnBookStatusSuccess");
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
                                    dataNameSelection.getStyleClass().clear();
                                    dataTitleSelection.getStyleClass().clear();
                                    returnBookStatus.getStyleClass().clear();
                                    tableDataPeminjamanBuku.setDisable(false);
                                    dataNameSelection.setText("Thank You");
                                    dataNameSelection.getStyleClass().add("dataSelectionReturn");
                                    dataTitleSelection.setText("For Confirming");
                                    dataTitleSelection.getStyleClass().add("dataSelectionReturn");
                                    returnBookStatus.setText("Redirecting to Home...");
                                    returnBookStatus.getStyleClass().add("returnBookStatusReturn");
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
                                    HomePageAdminScene homePageAdminScene = new HomePageAdminScene(stage);
                                    homePageAdminScene.show(user);
                                });
                            } catch (InterruptedException err) {
                                err.printStackTrace();
                            }
                        });
                        thread1.start();
                        thread2.start();
                        thread3.start();
                        thread4.start();
                    }
                }
            } else {
                dataNameSelection.setText("???");
                dataNameSelection.getStyleClass().add("dataSelectionFailed");
                dataTitleSelection.setText("???");
                dataTitleSelection.getStyleClass().add("dataSelectionFailed");
                returnBookStatus.setText("Please Choose One Data :)");
                returnBookStatus.getStyleClass().add("returnBookStatusFailed");
            }
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
