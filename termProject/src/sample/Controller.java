package sample;

import empMenu.EmpMenuController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.menuController;
import register.RegisterController;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ObservableList<String> privilageList = FXCollections.observableArrayList("Admin","Employee");
    @FXML
    private TextField textField1;
    @FXML
    private PasswordField passwordField1;
    @FXML
    private ComboBox privilageBox;
    @FXML
    private Button button1;
    @FXML
    private Label label1;

    private DBConnect db;

    DBConnect lg = new DBConnect();

    @FXML
    void login(ActionEvent event) throws SQLException {

        if(lg.checkUser(this.textField1.getText(), this.passwordField1.getText(), this.privilageBox.getValue() != null ? this.privilageBox.getValue().toString() : "Employee")){
            Stage stage = (Stage)this.button1.getScene().getWindow();
            stage.close();

            switch (this.privilageBox.getValue().toString()){
                case "Admin":
                    adminMenu();
                    break;
                case "Employee":
                    employeeMenu();
                    break;
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Wrong username or password. Please check your privilage also and try again.");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.db = new DBConnect();
        privilageBox.setValue("Admin");
        privilageBox.setItems(privilageList);
    }

    public void adminMenu(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Pane)loader.load(getClass().getResource("/menu/menu.fxml").openStream());
            menuController adminController = (menuController) loader.getController();
            Scene scene = new Scene(root);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Menu");
            adminStage.setResizable(false);
            adminStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void employeeMenu(){
        try{
            Stage empStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Pane)loader.load(getClass().getResource("/empMenu/empMenu.fxml").openStream());
            EmpMenuController empMenuController = (EmpMenuController) loader.getController();
            Scene scene = new Scene(root);
            empStage.setScene(scene);
            empStage.setTitle("Employee Menu");
            empStage.setResizable(false);
            empStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void register(){
        Stage stage = (Stage)this.label1.getScene().getWindow();
        stage.close();

        try{
            Stage registerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Parent root = (Pane) loader.load(getClass().getResource("/register/register.fxml").openStream());
            RegisterController registerController = (RegisterController) loader.getController();
            Scene scene = new Scene(root);
            registerStage.setScene(scene);
            registerStage.setTitle("Register");
            registerStage.setResizable(false);
            registerStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}