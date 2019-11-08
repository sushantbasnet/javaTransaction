package AllClass.Forms;

/**
 *
 * @author Basnet
 */
public class AccountAndUserCreation {
    String username,password,accountType,name;
    
    public AccountAndUserCreation(String name,String accountType, String userName,
            String password) {
        this.name = name;
        this.username = userName;
        this.password = password;
        this.accountType = accountType;
    }

    public AccountAndUserCreation(String name,String accountType, String userName,
            String password,  boolean account) {
        this.name = name;
        this.username = userName;
        this.password = password;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }
}
