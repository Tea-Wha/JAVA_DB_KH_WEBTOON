package controller;

import dao.Board_DAO;
import dao.Post_DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Notice_Post_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;
    private static boolean isNoticeReply = false;
    private static int boardType = 0; // 공지 게시판

    public static void notice_Post_None_Member_Start(){
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 공지 게시판(비회원) =======");
        System.out.println("[1]전체 게시글 조회 [2]게시글 선택 [3]게시글 작성 [4]게시글 검색 [5]뒤로 가기 [6]메인페이지 이동 [7]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pdao.postSelect_Result_Array(pdao.notice_Post_Select());
                break;
            case 2:
                isNoticeReply = true;
                while (isNoticeReply){
                    Notice_Reply_Controller.notice_Reply_None_Member_Start();
                }
                break;
            case 3:
                System.out.println("작성 권한이 없습니다. 로그인 해주세요.");
                break;
            case 4:
                break;
            case 5:
                Post_Controller.setIsNoticePost(false);
                break;
            case 6:
                Post_Controller.setIsNoticePost(false);
                controller.setPostIn(false);
                break;
            case 7:
                System.exit(0);
                break;
        }
    }
    public static void notice_Post_Member_Start(){
        User_Session_Controller session = User_Session_Controller.getInstance();
        int member_Num;
        member_Num = session.getUser_Num();
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Scanner scanner = new Scanner(System.in);
        boolean isSuccess;

        System.out.println("====== KH WEBTOON 공지 게시판(회원) =======");
        System.out.println("[1]전체 게시글 조회 [2]게시글 선택 [3]게시글 작성 [4]게시글 검색 [5]뒤로 가기 [6]메인페이지 이동 [7]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pdao.postSelect_Result_Array(pdao.notice_Post_Select());
                break;
            case 2:
                isNoticeReply = true;
                while (isNoticeReply){
                    Notice_Reply_Controller.notice_Reply_Member_Start();
                }
                break;
            case 3:
                isSuccess = pdao.post_Insert(pdao.postInsert_Input_Auto(member_Num,boardType));
                if (isSuccess) System.out.println("게시글 작성 성공");
                else System.out.println("게시글 작성 실패");
                break;
            case 4:
                break;
            case 5:
                Post_Controller.setIsNoticePost(false);
                break;
            case 6:
                Post_Controller.setIsNoticePost(false);
                controller.setPostIn(false);
                break;
            case 7:
                System.exit(0);
                break;
        }
    }
    public static boolean isIsNoticeReply() {
        return isNoticeReply;
    }
    public static void setIsNoticeReply(boolean isNoticeReply) {
        Notice_Post_Controller.isNoticeReply = isNoticeReply;
    }
}
