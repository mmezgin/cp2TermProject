package menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import operations.DeleteController;
import operations.InsertController;
import operations.UpdateController;
import sample.DBConnect;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class menuController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private Button insertButton;
    @FXML
    private Button updateButton;
    @FXML
    private TableView<empData> empDataTableView;
    @FXML
    private TableColumn<empData,Integer> columnID;           //1
    @FXML
    private TableColumn<empData,String> columnName;          //2
    @FXML
    private TableColumn<empData,String> columnSurname;       //3
    @FXML
    private TableColumn<empData,String> columnEmail;         //4
    @FXML
    private TableColumn<empData,Integer> columnSalary;       //5
    @FXML
    private TableColumn<empData,String> columnCountry;       //6
    @FXML
    private TableColumn<empData,String> columnCity;          //7
    @FXML
    private TableColumn<empData,String> columnNationality;   //8
    @FXML
    private TableColumn<empData,Integer> columnHired;        //9
    @FXML
    private TableColumn<empData,Integer> columnOffdays;      //10
    @FXML
    private TableColumn<empData,String> columnDomain;        //11
    @FXML
    private TableColumn<empData,String> columnUsername;      //12
    @FXML
    private TableColumn<empData,String> columnPrivilage;     //13


    private DBConnect db;
    private ResultSet rs;
    private Connection con;
    private PreparedStatement ps;
    private ObservableList<empData> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.db = new DBConnect();
    }

    @FXML
    private void loadData(ActionEvent event) throws SQLException{
        try {

            ps = db.getCon().prepareStatement("SELECT * FROM sys.info");
            this.data = FXCollections.observableArrayList();
            rs = ps.executeQuery();

            while(rs.next()){
                this.data.add(new empData(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getInt(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13)));
            }
        } catch(SQLException e){
            e.getCause().printStackTrace();
        }

        this.columnID.setCellValueFactory(new PropertyValueFactory<empData,Integer>("empID"));
        this.columnName.setCellValueFactory(new PropertyValueFactory<empData,String>("name"));
        this.columnSurname.setCellValueFactory(new PropertyValueFactory<empData,String>("surname"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<empData,String>("email"));
        this.columnSalary.setCellValueFactory(new PropertyValueFactory<empData,Integer>("salary"));
        this.columnCountry.setCellValueFactory(new PropertyValueFactory<empData,String>("country"));
        this.columnCity.setCellValueFactory(new PropertyValueFactory<empData,String>("city"));
        this.columnNationality.setCellValueFactory(new PropertyValueFactory<empData,String>("nationality"));
        this.columnHired.setCellValueFactory(new PropertyValueFactory<empData,Integer>("yearOnWork"));
        this.columnOffdays.setCellValueFactory(new PropertyValueFactory<empData,Integer>("remainingOffDays"));
        this.columnDomain.setCellValueFactory(new PropertyValueFactory<empData,String>("domain"));
        this.columnUsername.setCellValueFactory(new PropertyValueFactory<empData,String>("username"));
        this.columnPrivilage.setCellValueFactory(new PropertyValueFactory<empData,String>("privilage"));

        this.empDataTableView.setItems(null);
        this.empDataTableView.setItems(this.data);
    }

    @FXML
    void insertData(ActionEvent event) throws SQLException{
        Stage stage = (Stage)this.insertButton.getScene().getWindow();
        stage.close();

        try{
            Stage insertStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/operations/insert.fxml").openStream());
            InsertController insertController = (InsertController) loader.getController();
            Scene scene = new Scene(root);
            insertStage.setScene(scene);
            insertStage.setTitle("Insert New Employee Data");
            insertStage.setResizable(false);
            insertStage.show();
        }
        catch (IOException ex){
            ex.getCause().printStackTrace();
        }
    }

    @FXML
    void updateData(ActionEvent event) throws SQLException{
        Stage stage = (Stage) this.updateButton.getScene().getWindow();
        stage.close();

        try{
            Stage updateStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/operations/update.fxml").openStream());
            UpdateController updateController = (UpdateController) loader.getController();
            Scene scene = new Scene(root);
            updateStage.setScene(scene);
            updateStage.setTitle("Update An Employee's Data");
            updateStage.setResizable(false);
            updateStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void deleteData(ActionEvent event) throws SQLException{
        Stage stage = (Stage)this.deleteButton.getScene().getWindow();
        stage.close();

        try {
            Stage deleteStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane) loader.load(getClass().getResource("/operations/delete.fxml").openStream());
            DeleteController deleteController = (DeleteController) loader.getController();
            Scene scene = new Scene(root);
            deleteStage.setScene(scene);
            deleteStage.setTitle("Delete An Employee's Data");
            deleteStage.setResizable(false);
            deleteStage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}