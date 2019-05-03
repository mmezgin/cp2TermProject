package menu;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class empData {
    private final IntegerProperty empID;
    private final StringProperty name;
    private final StringProperty surname;
    private final StringProperty email;
    private final IntegerProperty salary;
    private final StringProperty country;
    private final StringProperty city;
    private final StringProperty nationality;
    private final IntegerProperty yearOnWork;
    private final IntegerProperty remainingOffDays;
    private final StringProperty domain;
    private final StringProperty username;
    private final StringProperty privilage;

    //Constructor
    public empData(Integer ID,String Name,String Surname,String Email,Integer Salary,String Country,String City,String Nationality,Integer Hired,Integer OffDays,String Domain,String Username,String Privilage){
        this.empID = new SimpleIntegerProperty(ID);
        this.name = new SimpleStringProperty(Name);
        this.surname = new SimpleStringProperty(Surname);
        this.email = new SimpleStringProperty(Email);
        this.salary = new SimpleIntegerProperty(Salary);
        this.country = new SimpleStringProperty(Country);
        this.city = new SimpleStringProperty(City);
        this.nationality = new SimpleStringProperty(Nationality);
        this.yearOnWork = new SimpleIntegerProperty(Hired);
        this.remainingOffDays = new SimpleIntegerProperty(OffDays);
        this.domain = new SimpleStringProperty(Domain);
        this.username = new SimpleStringProperty(Username);
        this.privilage = new SimpleStringProperty(Privilage);
    }

    public int getEmpID(){
        return empID.get();
    }

    public IntegerProperty empIDProperty() {
        return empID;
    }

    public void setEmpID(int empID){
        this.empID.set(empID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getSalary() {
        return salary.get();
    }

    public IntegerProperty salaryProperty() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary.set(salary);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getNationality() {
        return nationality.get();
    }

    public StringProperty nationalityProperty() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }

    public int getYearOnWork() {
        return yearOnWork.get();
    }

    public IntegerProperty yearOnWorkProperty() {
        return yearOnWork;
    }

    public void setYearOnWork(int yearOnWork) {
        this.yearOnWork.set(yearOnWork);
    }

    public int getRemainingOffDays() {
        return remainingOffDays.get();
    }

    public IntegerProperty remainingOffDaysProperty() {
        return remainingOffDays;
    }

    public void setRemainingOffDays(int remainingOffDays) {
        this.remainingOffDays.set(remainingOffDays);
    }

    public String getDomain() {
        return domain.get();
    }

    public StringProperty domainProperty() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain.set(domain);
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPrivilage() {
        return privilage.get();
    }

    public StringProperty privilageProperty() {
        return privilage;
    }

    public void setPrivilage(String privilage) {
        this.privilage.set(privilage);
    }
}