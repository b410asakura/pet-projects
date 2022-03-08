package sample;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button enterButton;

    @FXML
    private Button loginSignupButton;

    @FXML
    void initialize() {

        enterButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            } else System.out.println("login or password is empty");
        });

        loginSignupButton.setOnAction(event -> {
            openNewWindow("/sample/singUp.fxml");
        });
    }

    private void loginUser(String loginText, String loginPassword)  {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        ResultSet resultSet = databaseHandler.getUserData(user);
        try {
            if(resultSet.next()) {
                openNewWindow("/sample/app.fxml");
            }
            else{
                Shake loginAnim = new Shake(loginField);
                Shake passwordAnim = new Shake(passwordField);
                loginAnim.playAnimation();
                passwordAnim.playAnimation();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void openNewWindow(String path){
        loginSignupButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();
    }
}
