package AllClass.Forms;

import java.rmi.server.UID;

/**
 *
 * @author Basnet
 */
public class ClassTransaction {
    private String tranID;
    private String username;
    private String date;
    private String description;
    private double amount;
    private String transactionType;

    private UID IDGenerator(){
      UID userId = new UID();
      return userId;
    }
    
    
//creating object before writing
    public ClassTransaction(String username, String date, String description,
            double amount, String transactionType) {
        this.tranID = date + username+ IDGenerator();
        this.username = username;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    //creating object while reading
     public ClassTransaction(String username , String transactionID, String date, 
             String description, double amount,
             String transactionType,boolean x) {
        this.tranID = transactionID;
        this.username = username;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getTransactionID() {
        return tranID;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getUsername() {
        return username;
    }

}
