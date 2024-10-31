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
    private static boolean isNoticeReply = false;
    private static int boardType = 0; // 공지 게시판
    private static String notice_Post_Search;
    private static int notice_Post_Num_Select = -1;

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
                pdao.postSelect_Result_Array(pdao.post_Select(boardType));
                break;
            case 2:
                System.out.print("확인할 공지 게시글 번호 입력 : ");
                notice_Post_Num_Select = scanner.nextInt();
                if (pdao.post_Exist(notice_Post_Num_Select, boardType)) {
                    pdao.post_Visit(notice_Post_Num_Select);
                    isNoticeReply = true;
                    while (isNoticeReply) {
                        Notice_Reply_Controller.notice_Reply_None_Member_Start();
                    }
                }
                else System.out.println("해당 게시글이 존재하지 않습니다.");
                break;
            case 3:
                System.out.println("작성 권한이 없습니다. 로그인 해주세요.");
                break;
            case 4:
                System.out.print("공지 게시글 제목 검색 : ");
                scanner.nextLine();
                notice_Post_Search = scanner.nextLine();
                pdao.postSelect_Result_Array(pdao.post_Select_Search(boardType, notice_Post_Search));
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
                pdao.postSelect_Result_Array(pdao.post_Select(boardType));
                break;
            case 2:
                System.out.print("확인할 공지 게시글 번호 입력 : ");
                notice_Post_Num_Select = scanner.nextInt();
                if (pdao.post_Exist(notice_Post_Num_Select, boardType)) {
                    pdao.post_Visit(notice_Post_Num_Select);
                    isNoticeReply = true;
                    while (isNoticeReply) {
                        Notice_Reply_Controller.notice_Reply_Member_Start();
                    }
                }
                else System.out.println("해당 게시글이 존재하지 않습니다.");
                break;
            case 3:
                isSuccess = pdao.post_Insert(pdao.postInsert_Input_Auto(member_Num,boardType));
                if (isSuccess) System.out.println("게시글 작성 성공");
                else System.out.println("게시글 작성 실패");
                break;
            case 4:
                System.out.print("공지 게시글 제목 검색 : ");
                scanner.nextLine();
                notice_Post_Search = scanner.nextLine();
                pdao.postSelect_Result_Array(pdao.post_Select_Search(boardType, notice_Post_Search));
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
    public static int getNotice_Post_Num_Select() {
        return notice_Post_Num_Select;
    }
    public static void setNotice_Post_Num_Select(int notice_Post_Num_Select) {
        Notice_Post_Controller.notice_Post_Num_Select = notice_Post_Num_Select;
    }
}
