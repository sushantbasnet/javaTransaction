package AllClass.Forms;

import java.rmi.server.UID;

/**
 *
 * @author Basnet
 */
public class ClassTrigger {
    private String trigID;
    private String username;
    private String date;
    private String description;
    private double amount;
    private String triggerType;

    private UID IDGenerator(){
      UID userId = new UID();
      return userId;
    }
    
    
//creating object before writing
    public ClassTrigger(String username, String date, String description, 
            double amount, String transactionType) {
        this.trigID = date + username+ IDGenerator();
        this.username = username;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.triggerType = transactionType;
    }

    //creating object while reading
     public ClassTrigger(String username , String triggerID, String date, 
             String description, double amount, String triggerType,boolean a) {
        this.trigID = triggerID;
        this.username = username;
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.triggerType = triggerType;
    }

    public String getTriggerID() {
        return trigID;
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

    public String getTriggerType() {
        return triggerType;
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

    public void setTriggerType(String TriggerType) {
        this.triggerType = TriggerType;
    }

    public String getUsername() {
        return username;
    }

}
