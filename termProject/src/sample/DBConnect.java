package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import menu.menuController;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.lang.String;

public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    //Getters & Setters
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getSt() {
        return st;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    //Constructor
    public DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useUnicode=true&usetetimeCode=false&serverTimezone=Turkey","root","orapronobislucifer");
            st = con.createStatement();
        }
        catch(Exception ex){
            System.out.println("Error: " + ex);
        }
    }

    public boolean checkUser(String username, String password, String privilage) throws SQLException {
        try{
            ps = this.con.prepareStatement("SELECT * FROM sys.info WHERE username=? AND password=? AND privilage=?");
            ps.setString(1,username);
            ps.setString(2,password);
            ps.setString(3,privilage);

            rs = ps.executeQuery();

            if(!rs.next())
                return false;
            else
                return true;
        }
        catch(SQLException sql){
            sql.getCause().printStackTrace();
            return false;
        }
    }
}