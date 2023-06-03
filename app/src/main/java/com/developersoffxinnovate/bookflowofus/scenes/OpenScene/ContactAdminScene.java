package com.developersoffxinnovate.bookflowofus.scenes.OpenScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ContactAdminScene extends AbstractScene {

    public ContactAdminScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label headerText = new Label("Book Flow of Contact");

        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        Image imageBook = new Image(getClass().getClassLoader().getResourceAsStream("img/book.jpg"));
        ImageView containerImageBook = new ImageView(imageBook);
        containerImageBook.setFitHeight(110);
        containerImageBook.setFitWidth(240);
        Image imageAdmin = new Image(getClass().getClassLoader().getResourceAsStream("img/admin.png"));
        ImageView containerImageAdmin = new ImageView(imageAdmin);
        containerImageAdmin.setFitHeight(110);
        containerImageAdmin.setFitWidth(90);
        HBox containerImage = new HBox(containerImageAdmin, containerImageBook);
        containerImage.setAlignment(Pos.CENTER);
        containerImage.setSpacing(70);
        containerImage.getStyleClass().add("containerImage");

        Label headerTextContact = new Label("Contact Admin");

        Image imageGmail = new Image(getClass().getClassLoader().getResourceAsStream("img/gmail.png"));
        ImageView containerImageGmail = new ImageView(imageGmail);
        containerImageGmail.setFitHeight(50);
        containerImageGmail.setFitWidth(60);
        Label contactGmail = new Label("adnan.amiruddin34@gmail.com");
        contactGmail.getStyleClass().add("contactGmail");
        HBox cardContactGmail = new HBox(containerImageGmail, contactGmail);
        cardContactGmail.getStyleClass().add("cardContact");
        cardContactGmail.setStyle("-fx-background-color: darkred;");
        cardContactGmail.setAlignment(Pos.CENTER_LEFT);

        Image imageWA = new Image(getClass().getClassLoader().getResourceAsStream("img/whatsapp.png"));
        ImageView containerImageWA = new ImageView(imageWA);
        containerImageWA.setFitHeight(60);
        containerImageWA.setFitWidth(60);
        Label contactWA = new Label("+6281245255702");
        HBox cardContactWA = new HBox(containerImageWA, contactWA);
        cardContactWA.getStyleClass().add("cardContact");
        cardContactWA.setStyle("-fx-background-color: darkgreen;");
        cardContactWA.setAlignment(Pos.CENTER_LEFT);

        Image imageIG = new Image(getClass().getClassLoader().getResourceAsStream("img/instagram.png"));
        ImageView containerImageIG = new ImageView(imageIG);
        containerImageIG.setFitHeight(60);
        containerImageIG.setFitWidth(60);
        Label contactIG = new Label("@muh.adnan_putra");
        HBox cardContactIG = new HBox(containerImageIG, contactIG);
        cardContactIG.getStyleClass().add("cardContact");
        cardContactIG.setStyle("-fx-background-color: purple;");
        cardContactIG.setAlignment(Pos.CENTER_LEFT);

        Button backToLoginSceneButton = new Button("Back To Login Mahasiswa");
        backToLoginSceneButton.getStyleClass().add("backToLoginSceneButton");
        VBox containerButtons = new VBox(backToLoginSceneButton);
        containerButtons.getStyleClass().add("containerButtonsAdmin");
        containerButtons.setAlignment(Pos.CENTER);

        VBox containerContact = new VBox(containerImage, headerTextContact, cardContactGmail, cardContactWA, cardContactIG, containerButtons);
        containerContact.getStyleClass().add("containerContact");
        containerContact.setAlignment(Pos.CENTER);

        VBox main = new VBox(containerHeader, containerContact);
        main.getStyleClass().add("backgroundContact");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/OpenScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */
        backToLoginSceneButton.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });
    }
    
}
