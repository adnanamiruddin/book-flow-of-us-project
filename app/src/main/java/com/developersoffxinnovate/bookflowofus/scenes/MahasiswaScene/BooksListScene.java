package com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceSceneProps;
import com.developersoffxinnovate.bookflowofus.models.Mahasiswa;
import com.developersoffxinnovate.bookflowofus.scenes.Navbar;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BooksListScene extends AbstractScene implements InterfaceSceneProps {

    public BooksListScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show(String nim) {
        /* ===> INSTANCE AREA START <=== */
        Mahasiswa mahasiswa = MahasiswaController.getMahasiswaByNim(nim);
        /* ===> INSTANCE AREA END <=== */

        Label headerText = new Label("Book Flow of Us");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        VBox containerContent = new VBox();
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













        // Label label = new Label("Book List Scene");

        // List<Book> books = BooksController.getBuku();

        // ArrayList<Label> booksId = new ArrayList<>();
        // ArrayList<Label> booksJudul = new ArrayList<>();
        // ArrayList<Label> booksPengarang = new ArrayList<>();
        // ArrayList<Label> booksPenerbit = new ArrayList<>();
        // ArrayList<Label> booksTahunTerbit = new ArrayList<>();
        // ArrayList<Label> booksStocks = new ArrayList<>();
        // ArrayList<Label> booksBarrier = new ArrayList<>();

        // for (Book book : books) {
        //     booksId.add(new Label(String.format("%s", book.getId())));
        //     booksJudul.add(new Label(book.getJudul()));
        //     booksPengarang.add(new Label(book.getPengarang()));
        //     booksPenerbit.add(new Label(book.getPenerbit()));
        //     booksTahunTerbit.add(new Label(String.format("%s", book.getTahunTerbit())));
        //     booksStocks.add(new Label(String.format("%s", book.getStocks())));
        //     booksBarrier.add(new Label("-----------------"));
        // }

        // VBox containerBooksId = new VBox();
        // containerBooksId.getChildren().addAll(booksId);
        // VBox containerBooksJudul = new VBox();
        // containerBooksJudul.getChildren().addAll(booksJudul);
        // VBox containerBooksPengarang = new VBox();
        // containerBooksPengarang.getChildren().addAll(booksPengarang);

        // HBox containerBooks = new HBox(containerBooksId, containerBooksJudul, containerBooksPengarang);
        // containerBooks.setSpacing(20);

        // VBox containerScene = new VBox(label, containerBooks);

        // HBox main = new HBox(containerScene);

        // Scene scene = new Scene(main, 400, 600);
        // stage.setScene(scene);
        // stage.show();
    }
    
}
