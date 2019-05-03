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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import menu.menuController;
import sample.DBConnect;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {

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
    private ComboBox cbCountry;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfNationality;
    @FXML
    private TextField tfHired;
    @FXML
    private TextField tfOffdays;
    @FXML
    private TextField tfDomain;
    @FXML
    private TextField tfID;
    @FXML
    private Button bMenu;

    private DBConnect db;
    private PreparedStatement ps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.db = new DBConnect();
        this.cbCountry.setItems(countryList);
    }

    @FXML
    void goBack(ActionEvent event) {
        Stage stage = (Stage)this.bMenu.getScene().getWindow();
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

    @FXML
    void applyChanges() throws SQLException{
        String query = "UPDATE sys.info SET name = ?, surname = ?, email = ?, salary = ?, country = ?, city = ?, nationality = ?, yearOnWork = ?, remainingOffDays = ?, domain = ? WHERE (empID = ?)";

        try{
            ps = this.db.getCon().prepareStatement(query);

            ps.setString(1,this.tfName.getText());
            ps.setString(2,this.tfSurname.getText());
            ps.setString(3,this.tfEmail.getText());
            ps.setInt(4,Integer.parseInt(this.tfSalary.getText()));
            ps.setString(5,this.cbCountry.getValue().toString());
            ps.setString(6,this.tfCity.getText());
            ps.setString(7,this.tfNationality.getText());
            ps.setInt(8,Integer.parseInt(this.tfHired.getText()));
            ps.setInt(9,Integer.parseInt(this.tfOffdays.getText()));
            ps.setString(10,this.tfDomain.getText());
            ps.setInt(11,Integer.parseInt(this.tfID.getText()));

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
}