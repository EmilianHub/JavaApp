package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    @FXML
    private Button CloseButton;

    public void CloseButtonOnAction(ActionEvent e) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Label ErrorMessage;

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button LoginButton;

    public void LoginButtonOnAction(ActionEvent e) throws SQLException, IOException {
        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank())
        {
            validateLogin();
        }else if(usernameTextField.getText().isBlank() && passwordTextField.getText().isBlank()){
            ErrorMessage.setText("Fill the blanks");
        }else{
            ErrorMessage.setText("Please register first");
        }
    }

    public void validateLogin() throws SQLException, IOException {
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDb = connect.getConnection();
        if(connect.Offline)
        {
            if(usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("admin"))
            {
                Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                Stage MenuStage = (Stage) LoginButton.getScene().getWindow();
                MenuStage.setScene(new Scene(root, 838, 500));
            }
            ErrorMessage.setText("Your offline, please login using admin account");
        }
        if(!connect.Offline) {
            String veryfyLogin = "SELECT count(1) FROM user_accounts WHERE username = '" + usernameTextField.getText() + "' AND password ='" + passwordTextField.getText() + "'";
            try {
                Statement statement = connectDb.createStatement();
                ResultSet result = statement.executeQuery(veryfyLogin);
                while (result.next()) {
                    if (result.getInt(1) == 1) {
                        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                        Stage MenuStage = (Stage) LoginButton.getScene().getWindow();
                        MenuStage.setScene(new Scene(root, 838, 500));
                    } else {
                        ErrorMessage.setText("Wrong username and password");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    @FXML
    private Button RegisterButton;

    public void registrationform(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = (Stage) RegisterButton.getScene().getWindow();
            registerStage.setScene(new Scene(root, 520, 500));

        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

}
