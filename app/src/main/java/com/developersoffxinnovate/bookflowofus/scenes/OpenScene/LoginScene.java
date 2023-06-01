package com.developersoffxinnovate.bookflowofus.scenes.OpenScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.scenes.MahasiswaScene.HomePageScene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends AbstractScene {

    public LoginScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label headerText = new Label("Book Flow of Us");
        Image imageBook = new Image(getClass().getClassLoader().getResourceAsStream("img/book.jpg"));
        ImageView containerImageBook = new ImageView(imageBook);
        containerImageBook.setFitHeight(170);
        containerImageBook.setFitWidth(300);
        VBox containerHeader = new VBox(headerText, containerImageBook);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        Label inputNim = new Label("Input NIM");
        TextField input1 = new TextField();
        input1.setPromptText("Nomor Induk Mahasiswa...");
        Label inputPassword = new Label("Input Password");
        inputPassword.setPadding(new Insets(10, 0, 0, 0));
        PasswordField input2 = new PasswordField();
        input2.setPromptText("Password...");

        Label loginStatus = new Label("Status: Belum Login");
        Label contactAdmin = new Label("Contact Admin");
        contactAdmin.getStyleClass().add("contactAdmin");
        HBox containerFooterInputs = new HBox(loginStatus, contactAdmin);
        containerFooterInputs.getStyleClass().add("containerFooterInputs");
        containerFooterInputs.setAlignment(Pos.CENTER);

        VBox containerInputs = new VBox(inputNim, input1, inputPassword, input2, containerFooterInputs);
        containerInputs.getStyleClass().add("containerInputs");
        containerInputs.setAlignment(Pos.CENTER);

        Button loginButton = new Button("Login");

        Button adminButton = new Button("Admin?");
        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("registerButton");
        HBox containerLowerButtons = new HBox(adminButton, registerButton);
        containerLowerButtons.getStyleClass().add("containerLowerButtons");
        containerLowerButtons.setAlignment(Pos.CENTER);
        containerLowerButtons.setSpacing(10);

        VBox containerButtons = new VBox(loginButton, containerLowerButtons);
        containerButtons.getStyleClass().add("containerButtons");
        containerButtons.setAlignment(Pos.CENTER);
        containerButtons.setSpacing(10);

        VBox main = new VBox(containerHeader, containerInputs, containerButtons);
        main.getStyleClass().add("backgroundApp");

        Scene scene = new Scene(main, 750, 700);
        scene.getStylesheets().add(getClass().getResource("/styles/OpenScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */
        contactAdmin.setOnMouseClicked(e -> {
            ContactAdminScene contactAdminScene = new ContactAdminScene(stage);
            contactAdminScene.show();
        });

        loginButton.setOnAction(e -> {
            try {
                String nim = input1.getText();
                String password = input2.getText();
                if (MahasiswaController.validateLogin(nim, password)) {
                    loginStatus.setText("LU DAH LOGIN BANG, JAGO BANGET LU");
                    HomePageScene homePageScene = new HomePageScene(stage);
                    homePageScene.show(nim);
                } else {
                    loginStatus.setText("Maaf, gagal login");
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

        registerButton.setOnAction(e -> {
            RegisterScene registerScene = new RegisterScene(stage);
            registerScene.show();
        });

        adminButton.setOnAction(e -> {
            LoginAdminScene loginAdminScene = new LoginAdminScene(stage);
            loginAdminScene.show();
        });
    }
    
}
