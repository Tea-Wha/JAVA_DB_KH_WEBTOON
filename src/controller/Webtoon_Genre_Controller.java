package controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Webtoon_Genre_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public static void webtoon_Genre_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 장르별 웹툰 =======");
        System.out.println("[1]드라마 [2]로맨스 [3]무협 [4]액션 [5]판타지 [6]기타 [7]뒤로 가기 [8]메인페이지 이동 [9]종료");
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
                Webtoon_Controller.setIsGenre(false);
                break;
            case 8:
                Webtoon_Controller.setIsGenre(false);
                controller.setWebtoonIn(false);
                break;
            case 9:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
