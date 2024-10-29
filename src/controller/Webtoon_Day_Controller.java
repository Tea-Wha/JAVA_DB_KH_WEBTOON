package controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Webtoon_Day_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public static void webtoon_Day_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 요일별 웹툰 =======");
        System.out.println("[1]월요일 웹툰 [2]화요일 웹툰 [3]수요일 웹툰 [4]목요일 웹툰 [5]금요일 웹툰 [6]토요일 웹툰 [7]일요일 웹툰 [8]뒤로 가기 [9]메인페이지 이동 [10]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                Webtoon_Controller.setIsDay(false);
                break;
            case 9:
                Webtoon_Controller.setIsDay(false);
                controller.setWebtoonIn(false);
                break;
            case 10:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
