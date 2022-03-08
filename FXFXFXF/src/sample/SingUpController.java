package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField singUpLastName;

    @FXML
    private TextField singUpAddress;

    @FXML
    private TextField singUpUserName;

    @FXML
    private PasswordField singUpPassword;

    @FXML
    private Button singUpSubmit;

    @FXML
    void initialize() {
        singUpSubmit.setOnAction(event -> {
            singUpNewUser();
            singUpSubmit.getScene().getWindow().hide();
        });
    }

    private void singUpNewUser(){
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String firstName = signUpFirstName.getText();
        String lastName = singUpLastName.getText();
        String userName = singUpUserName.getText();
        String password = singUpPassword.getText();
        String address = singUpAddress.getText();

        User user = new User(firstName, lastName, userName, password, address);
        databaseHandler.singUpUser(user);
    }
}

