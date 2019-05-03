package empMenu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.DBConnect;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RequestController implements Initializable {

    @FXML
    private TextField tfID;
    @FXML
    private TextField tfRequest;
    @FXML
    private Button btRequest;
    @FXML
    private Button btMenu;

    private DBConnect db;
    private PreparedStatement ps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.db = new DBConnect();
    }

    @FXML
    public void request(ActionEvent event){
        String query = "UPDATE sys.info SET remainingOffDays = (remainingOffDays - ?) WHERE (empID = ?)";

        try{
            ps = this.db.getCon().prepareStatement(query);

            ps.setString(1,this.tfRequest.getText());
            ps.setInt(2,Integer.parseInt(this.tfID.getText()));

            ps.execute();

            JOptionPane.showMessageDialog(null,"You have successfully made changes in database!");
        }
        catch (SQLException sql){
            sql.printStackTrace();
        }
    }

    @FXML
    public void goBack(ActionEvent event){
        Stage stage = (Stage)this.btMenu.getScene().getWindow();
        stage.close();

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
}