package controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Webtoon_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public static void webtoon_None_Member_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 웹툰 페이지(비회원) =======");
        System.out.println("[1]웹툰 조회 [2]웹툰 검색 [3]웹툰 추천 [4]뒤로 가기 [5] 종료");
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
                controller.setWebtoonIn(false);
                break;
            case 5:
                System.exit(0);
                break;
            default:
                break;
        }
    }
    public static void webtoon_Member_Start() {
        Top_Controller controller = new Top_Controller();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 웹툰 페이지(회원) =======");
        System.out.println("[1]웹툰 조회 [2]웹툰 검색 [3]맞춤형 웹툰 추천 [4]선호 장르 설정 [5]웹툰 즐겨찾기 설정 [6]뒤로 가기 [7] 종료");
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
                controller.setWebtoonIn(false);
                break;
            case 7:
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
