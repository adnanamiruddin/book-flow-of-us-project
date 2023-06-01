package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.AdminController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddBookScene extends AbstractScene implements InterfaceSceneProps {

    public AddBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {}

    @Override
    public void show(String user) {
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

        VBox containerInputs = new VBox(inputJudul, input1, inputPengarang, input2, inputPenerbit, input3, inputTahunTerbit, input4, inputStok, input5);
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

        HBox containerMain = new HBox(containerNavbar, containerContent);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
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

                addBookButton.setDisable(true);
                addBookStatus.setText("Loading...");
                Thread thread1 = new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        Platform.runLater(() -> {
                            addBookStatus.setText("Checking all data input...");
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
                                addBookStatus.setText("Insert a new book to Database...");
                            } else {
                                addBookStatus.setText("Failed to add new book. There is something wrong");
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
                            addBookStatus.setText("Redirecting to Home Page...");
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
                addBookStatus.setText("Failed to add new book. Please fill in all input");
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
    }
    
}