package gr.uniwa.bank_api.Model;

import javax.persistence.*;

@Entity //creates table named as class name
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_account")
    private int accountId; //this value is id of table (due to @Id) and its value is generated automatically (due to @GeneratedValue) and column is named id_account
    private double balance;
    private boolean status;
    @ManyToOne //means that there is a bond between customer and account, many accounts can be owned by one customer
    private Customer customer;

    public Account() {
        balance = 0.0;
        status = true;
    }

    public Account(int accountId, double balance, boolean status) {
        this.accountId = accountId;
        this.balance = balance;
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
