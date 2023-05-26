package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceScene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BorrowBookScene extends AbstractScene implements InterfaceScene {

    public BorrowBookScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label headerText = new Label("Borrow Book Area");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        VBox main = new VBox(containerHeader);
        main.getStyleClass().add("backgroundBorrowBook");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/HomePageScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();












        
        // Label label = new Label("Borrow Book Scene");

        // Mahasiswa mahasiswa = MahasiswaController.getMahasiswaByNim(nim);

        // List<Book> books = BooksController.getBuku();

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
