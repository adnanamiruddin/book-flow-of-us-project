package com.developersoffxinnovate.bookflowofus.scenes.AdminScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;

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
        input1.setPromptText("Judul...");

        Label inputPengarang = new Label("Pengarang :");
        TextField input2 = new TextField();
        input2.setPromptText("Pengarang...");

        Label inputPenerbit = new Label("Penerbit : ");
        TextField input3 = new TextField();
        input3.setPromptText("Penerbit...");

        Label inputTahunTerbit = new Label("Tahun Terbit : ");
        TextField input4 = new TextField();
        input4.setPromptText("Tahun Terbit...");

        Label inputStok = new Label("Stok : ");
        TextField input5 = new TextField();
        input4.setPromptText("Stok...");

        VBox containerInputs = new VBox(headerContent, inputJudul, input1, inputPengarang, input2, inputPenerbit, input3, inputTahunTerbit, input4, inputStok, input5);
        containerInputs.getStyleClass().add("containerInputsAddBook");
        containerInputs.setAlignment(Pos.CENTER_LEFT);

        Label addBookStatus = new Label("Status : Belum Menambahkan Buku");
        Button addBookButton = new Button("Add");
        addBookButton.getStyleClass().add("addBookSubmitButton");
        VBox containerFooter = new VBox(addBookStatus, addBookButton);
        containerFooter.getStyleClass().add("containerFooter");
        containerFooter.setAlignment(Pos.CENTER);
        containerFooter.setSpacing(8);

        VBox containerContent = new VBox(containerInputs, containerFooter);

        HBox containerMain = new HBox(containerNavbar, containerContent);

        VBox main = new VBox(containerHeader, containerMain);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/AdminScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */

    }
    
}
