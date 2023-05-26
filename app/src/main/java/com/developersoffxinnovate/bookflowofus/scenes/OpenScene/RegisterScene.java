package com.developersoffxinnovate.bookflowofus.scenes.OpenScene;

import com.developersoffxinnovate.bookflowofus.abstracts.AbstractScene;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.InterfaceScene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterScene extends AbstractScene implements InterfaceScene {

    public RegisterScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label headerText = new Label("Register Area");
        VBox containerHeader = new VBox(headerText);
        containerHeader.getStyleClass().add("header");
        containerHeader.setAlignment(Pos.CENTER);
        
        Label headerInput = new Label("Silahkan isi data diri!");
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

        VBox containerInputs = new VBox(headerInput, inputNama, input1, inputNim, input2, inputProdi, input3, inputAlamat, input4, inputNoTelp, input5, inputPassword, input6);
        containerInputs.getStyleClass().add("containerInputsRegister");
        containerInputs.setAlignment(Pos.CENTER_LEFT);

        Label registerStatus = new Label("Status : Belum Register");
        Button registerButton = new Button("Register");
        registerButton.getStyleClass().add("registerSubmitButton");
        Button backToLoginSceneButton = new Button("Back To Home");
        VBox containerFooter = new VBox(registerStatus, registerButton, backToLoginSceneButton);
        containerFooter.getStyleClass().add("containerFooter");
        containerFooter.setAlignment(Pos.CENTER);
        containerFooter.setSpacing(8);

        VBox main = new VBox(containerHeader, containerInputs, containerFooter);
        main.getStyleClass().add("backgroundRegister");

        Scene scene = new Scene(main, 750, 700);
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
            if (MahasiswaController.validateRegister(nama, nim, prodi, alamat, noTelp, password)) {
                registerStatus.setText("Berhasil Register");
            } else {
                registerStatus.setText("Maaf, gagal register");
            }
        });

        backToLoginSceneButton.setOnAction(e -> {
            LoginScene loginScene = new LoginScene(stage);
            loginScene.show();
        });
    }
    
}
