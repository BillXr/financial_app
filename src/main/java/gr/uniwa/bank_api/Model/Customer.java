package gr.uniwa.bank_api.Model;
import javax.persistence.*;

@Entity //creates table named as class name
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private int customerId; //this value is id of table (due to @Id) and its value is generated automatically (due to @GeneratedValue) and column is named id_customer
    private String fname;
    private String lname;
    private String address;
    private String birthdate;
    @Column(unique = true)
    private String idNumber; //this column contains values that are uniques

    public Customer() {
    }

    public Customer(int customerId, String fname, String lname, String address, String birthdate, String idNumber) {
        this.customerId = customerId;
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.birthdate = birthdate;
        this.idNumber = idNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
}
