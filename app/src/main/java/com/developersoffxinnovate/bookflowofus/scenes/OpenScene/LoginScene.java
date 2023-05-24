package com.developersoffxinnovate.bookflowofus.scenes.OpenScene;

import com.developersoffxinnovate.bookflowofus.abstracts.SceneAbstract;
import com.developersoffxinnovate.bookflowofus.controllers.MahasiswaController;
import com.developersoffxinnovate.bookflowofus.interfaces.SceneInterface;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends SceneAbstract implements SceneInterface {

    public LoginScene(Stage stage) {
        super(stage);
    }

    @Override
    public void show() {
        Label label = new Label("Hello Adnan");

        TextField input1 = new TextField();
        PasswordField input2 = new PasswordField();

        Button loginButton = new Button("Login");
        // AtomicBoolean isLogin = new AtomicBoolean(false);
        Label loginStatus = new Label("Belum Login");

        loginButton.setOnAction(e -> {
            try {
                String nim = input1.getText();
                String password = input2.getText();

                if (MahasiswaController.validateLogin(nim, password)) {
                    // isLogin.set(true);
                    loginStatus.setText("LU DAH LOGIN BANG, JAGO BANGET LU");
                    // showBookListScene();
                    // showHomePageScene(nim);
                } else {
                    loginStatus.setText("Maaf, gagal login");
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        });

        Button adminButton = new Button("Admin?");
        // adminButton.setOnAction(e -> showLoginAdminScene());

        Button registerButton = new Button("Register sini dek");
        // registerButton.setOnAction(e -> showRegisterScene());

        VBox containerLogin = new VBox();
        containerLogin.getChildren().addAll(label, input1, input2, loginButton, loginStatus, adminButton, registerButton);

        HBox main = new HBox(containerLogin);

        Scene scene = new Scene(main, 400, 600);
        stage.setScene(scene);
        stage.show();
    }
    
}
