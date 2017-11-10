package info.ykqfrost.beans;

/**
 * @author YaoKeQi
 * @date 2017/10/26
 */
public class Reader {
    private String username;
    private String password;
    private double account;

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "Reader{" +
                "  username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
