import java.io.Serializable;

/**
 * Created by Ixbitz on 5-5-2017 in DACTest
 */
public class User implements Serializable{
    private int id;
    private String userName;
    private String passWord;

    public User(int id, String userName, String passWord){
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return this.passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String toString(){
        return new String("ID: " + id + " | Username: " + userName + " | Password: " + passWord);
    }
}
