package controller;

import dao.Member_DAO;

import java.util.Scanner;

public class Member_Info_Controller {
    private static int user_Number = -1;
    private static String user_ID = null;

    public static void memberInfo(){
        Top_Controller controller = new Top_Controller();
        User_Session_Controller session = User_Session_Controller.getInstance();
        user_Number = User_Session_Controller.getInstance().getUser_Num();
        user_ID = User_Session_Controller.getInstance().getUser_ID();
        Member_DAO mdao = new Member_DAO();
        boolean isSuccess;
        Scanner scanner = new Scanner(System.in);
        System.out.println("====== KH WEBTOON 마이 페이지 =======");
        System.out.println("[1]내 정보 [2]선호 장르 [3]회원 정보 수정(비밀번호 변경) [4]회원 탈퇴 [5]뒤로 가기 [6] 종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                mdao.single_MemberSelect_Result(mdao.single_Member_Select(user_Number));
                break;
            case 2:

                break;
            case 3:
                System.out.println("====== 비밀번호 변경 ======");
                isSuccess = mdao.member_Update(mdao.memberUpdate_Input_Auto(user_ID));
                if (isSuccess) System.out.println("비밀번호 변경 성공");
                else System.out.println("현재 비밀번호가 일치하지 않습니다.");
                break;
            case 4:
                System.out.println("====== KH WEBTOON 회원 탈퇴 ======");
                isSuccess = mdao.member_Delete_Auto(user_Number);
                if (isSuccess){
                    System.out.println("회원 탈퇴 성공");
                    Top_Controller.setIsMyPage(false);
                    session.logout();
                    Top_Controller.setIsLoggedIn(false);
                }
                else System.out.println("회원 탈퇴 실패");
                break;
            case 5:
                Top_Controller.setIsMyPage(false);
                break;
            case 6:
                System.exit(0);
                break;
        }
    }
}
