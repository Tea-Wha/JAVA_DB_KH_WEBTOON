package controller;

import dao.Post_DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Free_Post_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;
    private static boolean isFreeReply = false;

    public static void free_Post_None_Member_Start(){
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 자유 게시판(비회원) =======");
        System.out.println("[1]전체 게시글 조회 [2]게시글 선택 [3]게시글 작성 [4]게시글 검색 [5]뒤로 가기 [6]메인페이지 이동 [7]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:

                break;
            case 2:
                isFreeReply = true;
                while (isFreeReply){
                    Free_Reply_Controller.free_Reply_None_Member_Start();
                }
                break;
            case 3:
                System.out.println("작성 권한이 없습니다. 로그인 해주세요.");
                break;
            case 4:
                break;
            case 5:
                Post_Controller.setIsFreePost(false);
                break;
            case 6:
                Post_Controller.setIsFreePost(false);
                controller.setPostIn(false);
                break;
            case 7:
                System.exit(0);
                break;
        }
    }
    public static void free_Post_Member_Start(){
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Scanner scanner = new Scanner(System.in);
        boolean isSuccess;

        System.out.println("====== KH WEBTOON 자유 게시판(회원) =======");
        System.out.println("[1]전체 게시글 조회 [2]게시글 선택 [3]게시글 작성 [4]게시글 검색 [5]뒤로 가기 [6]메인페이지 이동 [7]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:

                break;
            case 2:
                isFreeReply = true;
                while (isFreeReply){
                    Free_Reply_Controller.free_Reply_Member_Start();
                }
                break;
            case 3:
                isSuccess = pdao.post_Insert(Post_DAO.postInsert_Input());
                break;
            case 4:
                break;
            case 5:
                Post_Controller.setIsFreePost(false);
                break;
            case 6:
                Post_Controller.setIsFreePost(false);
                controller.setPostIn(false);
                break;
            case 7:
                System.exit(0);
                break;
        }
    }
    public static boolean isIsFreeReply() {
        return isFreeReply;
    }
    public static void setIsFreeReply(boolean isFreeReply) {
        Free_Post_Controller.isFreeReply = isFreeReply;
    }
}
