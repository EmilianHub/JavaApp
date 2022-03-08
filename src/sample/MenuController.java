package sample;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private ImageView Exit;

    @FXML
    private Label MenuBack;

    @FXML
    private Label MenuStart;

    @FXML
    private Button LogoutButton;

    @FXML
    private VBox slider;

    @FXML
    private AnchorPane SettingsPane;
    @FXML
    private Button SettingsButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        slider.setTranslateX(-245);
        MenuStart.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();;
            slider.setTranslateX(-245);

            slide.setOnFinished((ActionEvent e)->{
                MenuStart.setVisible(false);
                MenuBack.setVisible(true);
            });
        }
        );

        MenuBack.setOnMouseClicked(event->{
                    TranslateTransition slide = new TranslateTransition();
                    slide.setDuration(Duration.seconds(0.4));
                    slide.setNode(slider);
                    slide.setToX(-245);
                    slide.play();;
                    slider.setTranslateX(0);

                    slide.setOnFinished((ActionEvent e)->{
                        MenuStart.setVisible(true);
                        MenuBack.setVisible(false);
                    });
                }
        );

    };

    public void LogOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage loginStage = (Stage) LogoutButton.getScene().getWindow();
        loginStage.setScene(new Scene(root, 520, 400));
    }

    public void SettingButtonOnAction(ActionEvent e)
    {
        if(SettingsPane.isVisible()) {
            SettingsPane.setVisible(false);
        }else{
            SettingsPane.setVisible(true);
        }
    }

    @FXML
    private BorderPane Background;
    @FXML
    private AnchorPane Background2;
    @FXML
    private Label ChooseLabel;
    @FXML
    private Label ChangePassLabel;
    @FXML
    private Label ChangeFirstLabel;
    @FXML
    private Label ChangeLastLabel;

    public void BlackColorChange(ActionEvent ev)
    {
        Background.setStyle("-fx-background-color: #1c1e21");
        Background2.setStyle("-fx-background-color: #1c1e21");
        SettingsPane.setStyle("-fx-background-color: #1c1e21");
        ChooseLabel.setStyle("-fx-text-fill: White");
        ChangeFirstLabel.setStyle("-fx-text-fill: White");
        ChangeLastLabel.setStyle("-fx-text-fill: White");
        ChangePassLabel.setStyle("-fx-text-fill: White");

    }
    public void WhiteColorChange(ActionEvent eve)
    {
        Background.setStyle("-fx-background-color: White");
        Background2.setStyle("-fx-background-color: White");
        SettingsPane.setStyle("-fx-background-color: White");
        ChooseLabel.setStyle("-fx-text-fill: Black");
        ChangeFirstLabel.setStyle("-fx-text-fill: Black");
        ChangeLastLabel.setStyle("-fx-text-fill: Black");
        ChangePassLabel.setStyle("-fx-text-fill: Black");
    }
}
