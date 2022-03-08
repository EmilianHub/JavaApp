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
import java.sql.Statement;

public class RegisterController {
    @FXML
    private TextField FirstnameTextField;
    @FXML
    private TextField SecondnameTextField;
    @FXML
    private TextField UsernameTextField;
    @FXML
    private PasswordField SetPasswordField;
    @FXML
    private PasswordField ConfirmPasswordField;
    @FXML
    private Label UsernameTaken;
    @FXML
    private Label IncorrectPassword;
    @FXML
    private Label RegistryMessage;
    @FXML
    private Button CancelButton;
    @FXML
    private Button RegisterButton;

    Stage stage;
    public void CancelButtonOnAction(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage loginStage = (Stage) CancelButton.getScene().getWindow();
        loginStage.setScene(new Scene(root, 520, 400));
    }

    public void registration(ActionEvent event){
        DatabaseConnection connect = new DatabaseConnection();
        Connection connectDb = connect.getConnection();

        String firstname = FirstnameTextField.getText();
        String secondname = SecondnameTextField.getText();
        String Username = UsernameTextField.getText();
        String password = SetPasswordField.getText();
        String Insert = "INSERT INTO user_accounts(Firstname, Secondname, username, password) values ('";
        String Values = firstname + "', '" + secondname + "', '" + Username + "', '" + password + "')";
        String register = Insert + Values;

        String veryfyUser = "SELECT count(1) FROM user_accounts where username = '" +  Username + "'";

        try {
            Statement userstatement = connectDb.createStatement();
            ResultSet result = userstatement.executeQuery(veryfyUser);
            result.next();
            IncorrectPassword.setText("");
            UsernameTaken.setText("");
            if(SetPasswordField.getText().equals(ConfirmPasswordField.getText()) && result.getInt(1) != 1)
            {

                Statement statement = connectDb.createStatement();
                statement.executeUpdate(register);
                RegistryMessage.setText("User registred successfully");
            }
        else if(SetPasswordField.getText().equals(ConfirmPasswordField.getText()) == false && result.getInt(1)==1){
            UsernameTaken.setText("Username is already taken");
            IncorrectPassword.setText("Password doesn't match");
        }
        else if(SetPasswordField.getText().equals(ConfirmPasswordField.getText()) == false)
            {
                IncorrectPassword.setText("Password doesn't match");
            }else if(result.getInt(1)==1) {
                UsernameTaken.setText("Username is already taken");
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            e.getCause();
        }
    }

}
