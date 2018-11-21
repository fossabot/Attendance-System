package Login;

import Classes.Teacher;
import Sidebar.SidebarController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class LoginController implements Initializable {

    @FXML private JFXTextField id;

    @FXML private JFXPasswordField pass;

    @FXML private Label wrongData;

    @FXML private void dataCheck(ActionEvent event) throws IOException {
        // create a sample teacher first
        Teacher Abubakar = new Teacher();
        Abubakar.setID(1625897);
        Abubakar.setName("Abubakar Yagoub Ibrahim");
        Abubakar.setEmail("Abubakaryagob@gmail.com");
        Abubakar.setGender(false);
        Abubakar.setPass("blacksuan19");
        Abubakar.setXP("bla bla bla");
        if (Integer.parseInt(id.getText()) == Abubakar.getID() && pass.getText().equals(Abubakar.getPass())) {
            // switch to the home scene
            System.out.println("Welcome back fam!!"); // some background action.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../Sidebar/Sidebar.fxml"));
            Parent homeParent = loader.load();
            Scene home = new Scene(homeParent);
            SidebarController controller = loader.getController();
            controller.logoName(Abubakar); // passing the object.
            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setTitle("Sidebar");
            window.setScene(home);
            window.show();
        } else {
            System.out.println("Nah Not today ma dude");
            // display the wrong information text in red if data doesn't match available one.
            wrongData.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // validator to make sure id field is only numbers and not empty and password filed is not empty.
        NumberValidator numberValidator = new NumberValidator();
        RequiredFieldValidator fieldValidate = new RequiredFieldValidator();
        RequiredFieldValidator passValidate = new RequiredFieldValidator();
        id.getValidators().add(fieldValidate);
        id.getValidators().add(numberValidator);
        pass.getValidators().add(passValidate);
        numberValidator.setMessage("Please enter a number 1-9");
        fieldValidate.setMessage(numberValidator.getMessage());
        passValidate.setMessage("Please enter your password");
        id.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            wrongData.setVisible(false); // hide the wrong information text when the user focuses on one of the text fields again
            if (!newValue){
                id.validate();
            }
        });
        pass.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            wrongData.setVisible(false); // hide the wrong information text when the user focuses on one of the text fields again
            if (!newValue){
                pass.validate();
            }
        });
    }
}