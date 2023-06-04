package com.midnightboys.bookflowofus.scenes.OpenScene;

import com.midnightboys.bookflowofus.abstracts.AbstractScene;
import com.midnightboys.bookflowofus.controllers.MahasiswaController;

import javafx.application.Platform;
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

public class RegisterScene extends AbstractScene {

    public RegisterScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label headerText = new Label("Book Flow of Register");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("headerRegister");
        containerHeader.setAlignment(Pos.CENTER);

        Label headerInput = new Label("Silahkan isi data diri dengan JUJUR!");
        headerInput.getStyleClass().add("headerInput");

        Label inputNama = new Label("Nama : ");
        TextField input1 = new TextField();
        input1.setPromptText("Nama");

        Label inputNim = new Label("NIM :");
        TextField input2 = new TextField();
        input2.setPromptText("NIM");

        Label inputProdi = new Label("Program Studi : ");
        TextField input3 = new TextField();
        input3.setPromptText("Prodi");

        Label inputAlamat = new Label("Alamat : ");
        TextField input4 = new TextField();
        input4.setPromptText("Alamat");

        Label inputNoTelp = new Label("Nomor Telepon : ");
        TextField input5 = new TextField();
        input5.setPromptText("Nomor Telepon");

        Label inputPassword = new Label("PASSWORD : ");
        PasswordField input6 = new PasswordField();
        input6.setPromptText("Password");

        VBox containerInputs = new VBox(headerInput, inputNama, input1, inputNim, input2, inputProdi, input3,
                inputAlamat, input4, inputNoTelp, input5, inputPassword, input6);
        containerInputs.getStyleClass().add("containerInputsRegister");
        containerInputs.setAlignment(Pos.CENTER_LEFT);

        Label registerStatus = new Label("Status : Belum Register");
        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("registerSubmitButton");
        Button backToLoginSceneButton = new Button("Back To Login Page");
        VBox containerFooter = new VBox(registerStatus, registerButton, backToLoginSceneButton);
        containerFooter.getStyleClass().add("containerFooter");
        containerFooter.setAlignment(Pos.CENTER);
        containerFooter.setSpacing(8);

        VBox main = new VBox(containerHeader, containerInputs, containerFooter);
        main.getStyleClass().add("backgroundApp");

        Image imageBg = new Image(getClass().getClassLoader().getResourceAsStream("img/register.jpg"));
        ImageView containerImageBg = new ImageView(imageBg);
        StackPane sp = new StackPane(containerImageBg, main);

        super.getScene().setRoot(sp);
        scene.getStylesheets().add(getClass().getResource("/styles/OpenScene.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        main.requestFocus();

        /* ===> LOGIC AREA <=== */
        registerButton.setOnAction(e -> {
            String nama = input1.getText();
            String nim = input2.getText();
            String prodi = input3.getText();
            String alamat = input4.getText();
            String noTelp = input5.getText();
            String password = input6.getText();

            input1.setDisable(true);
            input2.setDisable(true);
            input3.setDisable(true);
            input4.setDisable(true);
            input5.setDisable(true);
            input6.setDisable(true);
            registerButton.setDisable(true);
            backToLoginSceneButton.setDisable(true);
            registerStatus.getStyleClass().add("loadingProccess");
            registerStatus.setText("Loading...");
            Thread thread1 = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        registerStatus.setText("Checking all input data...");
                    });
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            });
            Thread thread2 = new Thread(() -> {
                try {
                    thread1.join();
                    Thread.sleep(3000);
                    Platform.runLater(() -> {
                        if (MahasiswaController.validateRegister(nama, nim, prodi, alamat, noTelp, password)) {
                            registerStatus.setText("Register Success");
                        } else {
                            registerStatus.getStyleClass().clear();
                            registerStatus.getStyleClass().add("failedRequest");
                            registerStatus.setText("Failed to register. There is something wrong");
                            registerButton.getStyleClass().add("failedRequest");
                        }
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
                        registerStatus.getStyleClass().clear();
                        registerStatus.setText("Redirecting to Login Page...");
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
                        stage.close();
                        LoginScene loginScene = new LoginScene(stage);
                        loginScene.show();
                    });
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            });
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
        });

        backToLoginSceneButton.setOnAction(e -> {
            stage.close();
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });
    }

}
