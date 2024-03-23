import java.util.List;
import java.util.UUID;

public class Account {

    private String email;
    private String passWord;
    private String userName;
    private UUID accountUUID;
    private List<UUID> postList;
    private List<UUID> subRedditList;

    public  Account(String email, String passWord, String userName) {
        this.email = email;
        this.passWord = Reddit.hash(passWord);
        this.userName = userName;
        this.accountUUID = UUID.randomUUID();
    }
    public boolean ValidPassWord(String passWord) {
        return Reddit.hash(passWord).equals(this.passWord);
    }
    public String getEmail() {
        return email;
    }
    public UUID getAccountUUID() {
        return accountUUID;
    }

    public String getUserName() {
        return userName;
    }
    public void changeUserName(String userName) {
        if (userName.equals(this.userName)) {
            System.out.println("already this is your userName!");
        }
        else if (Reddit.validUserName(userName) == 0) {
            System.out.println("this is not valid userName.");
        }
        else if (Reddit.validUserName(userName) == 1) {
            System.out.println("this userName already exist.");
        }
        else {
            this.userName = userName;
            System.out.println("userName changed.");
        }
    }
    public void timeLine() {

    }
}