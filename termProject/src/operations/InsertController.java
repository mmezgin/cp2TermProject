package operations;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.menuController;
import sample.DBConnect;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InsertController implements Initializable {

    ObservableList<String> privilageList = FXCollections.observableArrayList("Admin","Employee");
    ObservableList<String> countryList = FXCollections.observableArrayList(
            "Afghanistan","Albania","Algeria","Andorra","Angola","Argentina","Armenia","Australia",
            "Austria","Azerbaijan","The Bahamas","Bahrain","Bangladesh","Barbados","Belarus","Belgium",
            "Belize","Benin","Bhutan","Bolivia","Bosnia and Herzegovina","Botswana","Brazil","Brunei",
            "Cambodia","Cameroon","Canada","Central African Republic","Chad","Chile","China","Colombia",
            "Comoros","Costa Rica","Croatia","Cuba","Cyprus","Czech Republic","Denmark","Dominican Republic",
            "Ecuador","Egypt","El Salvador","Eritrea","Estonia","Eswatini","Ethiopia","Fiji","Finland",
            "France","Gabon","The Gambia","Georgia","Germany","Ghana","Greece","Grenada","Guatemala",
            "Guinea","Guyana","Haiti","Honduras","Hungary","Iceland","India","Indonesia","Iran","Iraq",
            "Ireland","Israel","Italy","Jamaica","Japan","Jordan","Kazakhstan","Kenya","Kiribati","South Korea",
            "Kosovo","Kuwait","Kyrgyzstan","Laos","Latvia","Lebanon","Lesotho","Liberia","Libya","Liechtenstein",
            "Lithuania","Luxembourg","Madagascar","Malawi","Malaysia","Maldives","Mali","Mexico","Moldova",
            "Monaco","Mongolia","Montenegro","Morocco","Mozambique","Myanmar","Namibia","Nauru","Nepal",
            "Netherlands","New Zealand","Niger","Nigeria","North Macedonia","Norway","Oman","Pakistan",
            "Palau","Panama","Papua New Guinea","Paraguay","Peru","Philippines","Poland","Portugal","Qatar",
            "Romania","Russia","Rwanda","Saint Lucia","Samoa","San Marino","Saudi Arabia","Senegal","Serbia",
            "Sierra Leone","Singapore","Slovakia","Slovenia","Solomon Islands","Somalia","South Africa",
            "Spain","Sri Lanka","Sudan","Sweden","Switzerland","Syria","Taiwan","Tajikistan","Tanzania",
            "Thailand","Togo","Tonga","Tunisia","Turkey","Turkmenistan","Tuvalu","Uganda","Ukraine",
            "United Arab Emirates","United Kingdom","United States","Uruguay","Uzbekistan","Vanuatu",
            "Vatican City","Venezuela","Vietnam","Yemen","Zambia","Zimbabwe");
    @FXML
    private TextField textFieldID;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldSurname;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldSalary;
    @FXML
    private TextField textFieldCity;
    @FXML
    private TextField textFieldNationality;
    @FXML
    private TextField textFieldHired;
    @FXML
    private TextField textFieldOffdays;
    @FXML
    private TextField textFieldDomain;
    @FXML
    private TextField textFieldUsername;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox comboBoxCountry;
    @FXML
    private ComboBox comboBoxPrivilage;
    @FXML
    private Button menuButton;

    private DBConnect db;
    private PreparedStatement ps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.db = new DBConnect();
        comboBoxCountry.setItems(countryList);
        comboBoxPrivilage.setItems(privilageList);
    }

    @FXML
    void saveData(ActionEvent event) throws SQLException {
        String query = "INSERT INTO sys.info(empID,name,surname,email,salary,country,city,nationality,yearOnWork,remainingOffDays,domain,privilage,username,password)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try{
            ps = this.db.getCon().prepareStatement(query);

            ps.setInt(1, Integer.parseInt(this.textFieldID.getText()));
            ps.setString(2,this.textFieldName.getText());
            ps.setString(3,this.textFieldSurname.getText());
            ps.setString(4,this.textFieldEmail.getText());
            ps.setInt(5,Integer.parseInt(this.textFieldSalary.getText()));
            ps.setString(6,this.comboBoxCountry.getValue().toString());
            ps.setString(7,this.textFieldCity.getText());
            ps.setString(8,this.textFieldNationality.getText());
            ps.setInt(9,Integer.parseInt(this.textFieldHired.getText()));
            ps.setInt(10,Integer.parseInt(this.textFieldOffdays.getText()));
            ps.setString(11,this.textFieldDomain.getText());
            ps.setString(12,this.comboBoxPrivilage.getValue().toString());
            ps.setString(13,this.textFieldUsername.getText());
            ps.setString(14,this.passwordField.getText());

            ps.execute();

            JOptionPane.showMessageDialog(null,"You have successfully made changes in database!");
        }
        catch (SQLException sql){
            sql.printStackTrace();
        }
        finally {
            this.db.getCon().close();
            ps.close();
        }
    }

    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage)this.menuButton.getScene().getWindow();
        stage.close();

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
}