package controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Webtoon_Recommend_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public static void webtoon_Recommend_None_Member_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 추천 웹툰(비회원) =======");
        System.out.println("[1]추천 웹툰 조회 [2]뒤로 가기 [3]메인페이지 이동 [4]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                break;
            case 2:
                Webtoon_Controller.setIsRecommend(false);
                break;
            case 3:
                Webtoon_Controller.setIsRecommend(false);
                controller.setWebtoonIn(false);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public static void webtoon_Recommend_Member_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 추천 웹툰(회원) =======");
        System.out.println("[1]맞춤형 추천 웹툰 조회 [2]뒤로 가기 [3]메인페이지 이동 [4]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                break;
            case 2:
                Webtoon_Controller.setIsRecommend(false);
                break;
            case 3:
                Webtoon_Controller.setIsRecommend(false);
                controller.setWebtoonIn(false);
                break;
            case 4:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
