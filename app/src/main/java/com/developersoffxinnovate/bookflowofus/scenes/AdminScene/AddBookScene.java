package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.AdminController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.scenes.components.Header;
import com.developersoffxinnovate.bookflowofus.scenes.components.Navbar;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBookScene extends AbstractScene implements InterfaceSceneProps {

    public AddBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
    }

    @Override
    public void show(String user) {
        Label headerContent = new Label("Add New Book");

        Label inputJudul = new Label("Judul : ");
        TextField input1 = new TextField();
        input1.setPromptText("Judul... *");

        Label inputPengarang = new Label("Pengarang :");
        TextField input2 = new TextField();
        input2.setPromptText("Pengarang... *");

        Label inputPenerbit = new Label("Penerbit : ");
        TextField input3 = new TextField();
        input3.setPromptText("Penerbit...");

        Label inputTahunTerbit = new Label("Tahun Terbit : ");
        TextField input4 = new TextField();
        input4.setPromptText("Tahun Terbit... *");

        Label inputStok = new Label("Stok : ");
        TextField input5 = new TextField();
        input5.setPromptText("Stok... *");

        VBox containerInputs = new VBox(inputJudul, input1, inputPengarang, input2, inputPenerbit, input3,
                inputTahunTerbit, input4, inputStok, input5);
        containerInputs.getStyleClass().add("containerInputsAddBook");
        containerInputs.setAlignment(Pos.CENTER_LEFT);

        Label addBookStatus = new Label("Status : Belum Menambahkan Buku");
        Button addBookButton = new Button("Add Book");
        addBookButton.getStyleClass().add("addBookSubmitButton");
        HBox containerFooter = new HBox(addBookStatus, addBookButton);
        containerFooter.getStyleClass().add("containerFooterAddBook");
        containerFooter.setAlignment(Pos.CENTER);
        containerFooter.setSpacing(8);

        VBox containerContent = new VBox(headerContent, containerInputs, containerFooter);
        containerContent.getStyleClass().add("containerAddBook");
        containerContent.setAlignment(Pos.TOP_CENTER);

        String activeNavItem = "Add Book";
        HBox containerMain = new HBox(Navbar.getNavbarAdmin(stage, user, activeNavItem), containerContent);

        Header containerHeader = new Header();
        VBox main = new VBox(containerHeader.getHeaderAdmin(), containerMain);
        main.getStyleClass().add("backgroundApp");

        super.getScene().setRoot(main);
        scene.getStylesheets().add(getClass().getResource("/styles/AdminScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */
        addBookButton.setOnAction(e -> {
            if (!input4.getText().isEmpty() && !input5.getText().isEmpty()) {
                String judul = input1.getText();
                String pengarang = input2.getText();
                String penerbit = input3.getText();
                int tahunTerbit = Integer.parseInt(input4.getText());
                int stok = Integer.parseInt(input5.getText());

                input1.setDisable(true);
                input2.setDisable(true);
                input3.setDisable(true);
                input4.setDisable(true);
                input5.setDisable(true);
                addBookButton.setDisable(true);
                addBookStatus.getStyleClass().clear();
                addBookStatus.setText("Loading...");
                addBookStatus.getStyleClass().add("addBookStatusLoading");
                Thread thread1 = new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        Platform.runLater(() -> {
                            addBookStatus.getStyleClass().clear();
                            addBookStatus.setText("Checking all input data...");
                            addBookStatus.getStyleClass().add("addBookStatusChecking");
                        });
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                });
                Thread thread2 = new Thread(() -> {
                    try {
                        thread1.join();
                        Thread.sleep(2500);
                        Platform.runLater(() -> {
                            if (AdminController.validateAddBook(judul, pengarang, penerbit, tahunTerbit, stok)) {
                                addBookStatus.getStyleClass().clear();
                                addBookStatus.setText("Inserting a new book\nto Database...");
                                addBookStatus.getStyleClass().add("addBookStatusSuccess");
                            } else {
                                addBookStatus.getStyleClass().clear();
                                addBookStatus.setText("Failed to add new book. There is something wrong");
                                addBookStatus.getStyleClass().add("addBookStatusFailed");
                            }
                        });
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                });
                Thread thread3 = new Thread(() -> {
                    try {
                        thread2.join();
                        Thread.sleep(3500);
                        Platform.runLater(() -> {
                            addBookStatus.getStyleClass().clear();
                            addBookStatus.setText("Redirecting to Home Page...");
                            addBookStatus.getStyleClass().add("addBookStatusReturn");
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
            } else {
                addBookStatus.getStyleClass().clear();
                addBookStatus.setText("Failed to add new book. Please fill in all input");
                addBookStatus.getStyleClass().add("addBookStatusFailed");
            }
        });
    }
}
