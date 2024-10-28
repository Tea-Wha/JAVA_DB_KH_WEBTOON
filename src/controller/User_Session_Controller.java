package controller;

public class User_Session_Controller {
    private static User_Session_Controller instance;
    private String user_ID;
    private int user_Num;

    private User_Session_Controller(){
        user_Num = -1;
    }
    public static User_Session_Controller getInstance() {
        if (instance == null){
            instance = new User_Session_Controller();
        }
        return instance;
    }
    public void setUserInfo(String user_ID, int user_Num){
        this.user_ID = user_ID;
        this.user_Num = user_Num;
    }
    public String getUser_ID(){
        return user_ID;
    }
    public int getUser_Num(){
        return user_Num;
    }
    public void logout(){
        user_ID = null;
        user_Num = -1;
    }
}


