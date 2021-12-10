public class Account {
    private String emailAddress;
    private int id;
    private String password;
    private int idTemp;
    private String passwordTemp;

    public boolean verifyPassword() {
        return password.equals(passwordTemp);
    }

    public boolean validateLogin(int userId, String password) {
        idTemp = userId;
        passwordTemp = password;
        return (validateLogin() && verifyPassword());
    }

    public boolean validateLogin() {
        return (id == idTemp);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public long getAccountId() {
        return id;
    }

    public void setAccountId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
