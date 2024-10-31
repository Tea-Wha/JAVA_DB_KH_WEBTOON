package controller;

import java.sql.*;
import java.util.Scanner;

// 메인페이지 구현
// 범위 제한 ->
public class Top_Controller {
    private static boolean isLoggedIn = false;
    private static boolean isWebtoonIn = false;
    private static boolean isPostIn = false;
    private static boolean isMyPage = false;

    public static void top_Controller_Start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        while (true){
            if (isLoggedIn){
                top_Controller_Member();
            }
            else {
                System.out.println("====== KH WEBTOON 메인 페이지(비회원) =======");
                System.out.println("[1]로그인 [2]회원가입 [3]웹툰 통합 조회 서비스 [4]커뮤니티 게시판 서비스 [5] 종료");
                System.out.print("이동 메뉴 선택 : ");
                int choice = scanner.nextInt();
                Sign_In_Controller in_controller = new Sign_In_Controller();
                Sign_Up_Controller up_controller = new Sign_Up_Controller();
                switch (choice) {
                    case 1:
                        if (in_controller.sign_In()) {
                            System.out.println("로그인 성공");
                            isLoggedIn = true;
                        } else System.out.println("로그인 실패");
                        break;
                    case 2:
                        if (up_controller.sign_Up()) {
                            System.out.println("회원가입 성공");
                            break;
                        } else System.out.println("회원가입 실패");
                        break;
                    case 3:
                        isWebtoonIn = true;
                        while (isWebtoonIn){
                            Webtoon_Controller.webtoon_None_Member_Start();
                        }
                        break;
                    case 4:
                        isPostIn = true;
                        while (isPostIn){
                            Post_Controller.post_None_Member_Start();
                        }
                        break;
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
    public static void top_Controller_Member() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        while (isLoggedIn){
            System.out.println("====== KH WEBTOON 메인 페이지(회원) =======");
            System.out.println("[1]로그아웃 [2]마이페이지 [3]웹툰 통합 조회 서비스 [4]커뮤니티 게시판 서비스 [5] 종료");
            System.out.print("이동 메뉴 선택 : ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: // 로그아웃
                    User_Session_Controller session = User_Session_Controller.getInstance();
                    if (session.getUser_ID() != null){
                        System.out.println("ID : "+session.getUser_ID());
                    }
                    else{
                        System.out.println("로그인된 사용자가 없습니다.");
                    }
                    System.out.println("연결을 끊겠습니까? [1]YES [2]NO");
                    System.out.print("선택 : ");
                    int select = scanner.nextInt();
                    if (select == 1) {
                        System.out.println("로그아웃 합니다.");
                        session.logout();
                        isLoggedIn = false;
                    }
                    else {
                        System.out.println("메뉴 선택으로 돌아갑니다.");
                    }
                    break;
                case 2:
                    isMyPage = true;
                    while (isMyPage){
                     Member_Info_Controller.memberInfo();
                    }
                    break;
                case 3:
                    isWebtoonIn = true;
                    while (isWebtoonIn){
                        Webtoon_Controller.webtoon_Member_Start();
                    }
                    break;
                case 4:
                    isPostIn = true;
                    while (isPostIn){
                     Post_Controller.post_Member_Start();
                    }
                    break;
                case 5:
                    System.out.println("KH WEBTOON 종료");
                    System.exit(0);
                    break;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
                    break;
            }
        }
    }

    public boolean isWebtoonIn() {
        return isWebtoonIn;
    }
    public void setWebtoonIn(boolean webtoonIn) {
        isWebtoonIn = webtoonIn;
    }
    public boolean isPostIn() {
        return isPostIn;
    }
    public void setPostIn(boolean postIn) {
        isPostIn = postIn;
    }
    public static boolean isIsMyPage() {
        return isMyPage;
    }
    public static void setIsMyPage(boolean isMyPage) {
        Top_Controller.isMyPage = isMyPage;
    }
    public static boolean isIsLoggedIn() {
        return isLoggedIn;
    }
    public static void setIsLoggedIn(boolean isLoggedIn) {
        Top_Controller.isLoggedIn = isLoggedIn;
    }
}

