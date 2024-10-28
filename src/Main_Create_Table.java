import controller.Table_Controller;


// 프라이머리 키도 설정안하고 Connection DAO를 따로 만들어서 맨 마지막에 한번에 하는 방법을
// 나중에 구현해볼 것
public class Main_Create_Table {
    public static void main(String[] args) {
        Table_Controller tController = new Table_Controller();
        tController.createTable();
        tController.defaultValue();
    }
}
