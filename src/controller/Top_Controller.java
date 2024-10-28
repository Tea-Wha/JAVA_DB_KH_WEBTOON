package controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

// 메인페이지 구현
// 범위 제한 ->
public class Top_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public static void top_Controller_Start(){
        Scanner scanner = new Scanner(System.in);
        
        while (true){
            System.out.println("====== KH WEBTOOM 메인 페이지(비회원) =======");
            System.out.println("[1]로그인 [2]회원가입 [3]웹툰 통합 조회 서비스 [4] 커뮤니티 게시판 서비스 [5] 종료");
            System.out.print("이동 메뉴 선택 : ");
            int choice = scanner.nextInt();
            Sign_In_Controller in_controller = new Sign_In_Controller();
            Sign_Up_Controller up_controller = new Sign_Up_Controller();
            switch (choice) {
                case 1:
                    if (in_controller.sign_In()) {
                        System.out.println("로그인 성공");
                        Top_Controller.top_Controller_Member();
                        return;
                    } else System.out.println("로그인 실패");
                    break;
                case 2:
                    if (up_controller.sign_Up()){
                        System.out.println("회원가입 성공");
                        break;
                    } else System.out.println("회원가입 실패");
                    break;
                case 3:

                    return;
                case 4:

                    return;
                case 5:
                    System.out.println("KH WEBTOON 종료");
                    return;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
                    break;
            }
        }
    }
    public static void top_Controller_Member(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("====== KH WEBTOOM 메인 페이지(회원) =======");
            System.out.println("[1]로그아웃 [2]마이페이지 [3]웹툰 통합 조회 서비스 [4] 커뮤니티 게시판 서비스 [5] 종료");
            System.out.print("이동 메뉴 선택 : ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:

                case 2:

                case 3:

                case 4:

                case 5:
                    System.out.println("KH WEBTOON 종료");
                    return;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
                    break;
            }
        }
    }
}

