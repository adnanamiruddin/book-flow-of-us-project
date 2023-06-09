package com.midnightboys.bookflowofus.scenes.OpenScene;

import com.midnightboys.bookflowofus.abstracts.AbstractScene;
import com.midnightboys.bookflowofus.controllers.AdminController;
import com.midnightboys.bookflowofus.scenes.AdminScene.HomePageAdminScene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginAdminScene extends AbstractScene {

    public LoginAdminScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label headerText = new Label("Login as Admin");
        Image imageAdmin = new Image(getClass().getClassLoader().getResourceAsStream("img/admin.png"));
        ImageView containerImageAdmin = new ImageView(imageAdmin);
        containerImageAdmin.setFitHeight(170);
        containerImageAdmin.setFitWidth(140);

        VBox containerHeader = new VBox(headerText, containerImageAdmin);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);

        Label inputUsername = new Label("Input User Name");
        TextField input1 = new TextField();
        input1.setPromptText("User Name...");
        Label inputPassword = new Label("Input Password Admin");
        inputPassword.setPadding(new Insets(10, 0, 0, 0));
        PasswordField input2 = new PasswordField();
        input2.setPromptText("Password...");
        Label loginStatus = new Label("Status: Belum Login");
        loginStatus.getStyleClass().add("loginStatus");

        VBox containerInputs = new VBox(inputUsername, input1, inputPassword, input2, loginStatus);
        containerInputs.getStyleClass().add("containerInputsAdmin");
        containerInputs.setAlignment(Pos.CENTER);

        Button loginButton = new Button("Login Admin");
        Button backToLoginSceneButton = new Button("Back To Login Mahasiswa");
        backToLoginSceneButton.getStyleClass().add("backToLoginSceneButton");
        VBox containerButtons = new VBox(loginButton, backToLoginSceneButton);
        containerButtons.getStyleClass().add("containerButtonsAdmin");
        containerButtons.setAlignment(Pos.CENTER);
        containerButtons.setSpacing(10);

        VBox main = new VBox(containerHeader, containerInputs, containerButtons);
        main.getStyleClass().add("backgroundApp");

        Image imageBg = new Image(getClass().getClassLoader().getResourceAsStream("img/flowerBg.jpg"));
        ImageView containerImageBg = new ImageView(imageBg);
        StackPane sp = new StackPane(containerImageBg, main);

        super.getScene().setRoot(sp);
        scene.getStylesheets().add(getClass().getResource("/styles/OpenScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */
        loginButton.setOnAction(e -> {
            try {
                String user = input1.getText();
                String password = input2.getText();
                if (AdminController.validateLoginAdmin(user, password)) {
                    loginStatus.setText("LU DAH LOGIN BANG, JAGO BANGET LU");
                    stage.close();
                    HomePageAdminScene homePageAdminScene = new HomePageAdminScene(stage);
                    homePageAdminScene.show(user);
                } else {
                    loginStatus.getStyleClass().add("failedRequest");
                    loginStatus.setText("Maaf, gagal login");
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

        backToLoginSceneButton.setOnAction(e -> {
            stage.close();
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });
    }

}
