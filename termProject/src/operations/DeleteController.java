package operations;

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
import menu.menuController;
import sample.DBConnect;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteController implements Initializable {

    @FXML
    private Button buttonID;
    @FXML
    private Button menuButton;
    @FXML
    private TextField textFieldID;

    private DBConnect db;
    private PreparedStatement ps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.db = new DBConnect();
    }

    @FXML
    void deleteEmp(ActionEvent event) throws SQLException {
        String query = "DELETE FROM sys.info WHERE (empID = ?)";

        try{
            ps = this.db.getCon().prepareStatement(query);
            ps.setInt(1,Integer.parseInt(this.textFieldID.getText()));

            ps.execute();

            JOptionPane.showMessageDialog(null,"You have successfully made changes in database!");
        }
        catch(SQLException sql){
            sql.printStackTrace();
        }
        finally {
            this.db.getCon().close();
            ps.close();
        }
    }

    @FXML
    void goBack(ActionEvent event){
        Stage stage = (Stage) this.menuButton.getScene().getWindow();
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