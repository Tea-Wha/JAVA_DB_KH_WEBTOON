package controller;

import dao.Post_DAO;
import dao.Reply_DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Free_Reply_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;
    private static int free_Post_Number = -1;
    private static int user_Number = -1;

    public static void free_Reply_None_Member_Start(){
        free_Post_Number = Free_Post_Controller.getFree_Post_Num_Select();
        user_Number = User_Session_Controller.getInstance().getUser_Num();
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Reply_DAO rdao = new Reply_DAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 자유 게시글(비회원) =======");
        System.out.println("[1]게시글 조회 [2]게시글 수정 [3]게시글 삭제 [4]댓글 보기 [5]댓글 작성 [6]댓글 공감 [7]댓글 비공감 [8]뒤로 가기 [9]메인페이지 이동 [10]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pdao.postSelect_Result(pdao.post_Single_Select(free_Post_Number));
                rdao.replySelect_Result(rdao.reply_Select(free_Post_Number));
                break;
            case 2:
                System.out.println("수정 권한이 없습니다.");
                break;
            case 3:
                System.out.println("삭제 권한이 없습니다.");
                break;
            case 4:
                rdao.replySelect_Result(rdao.reply_Select(free_Post_Number));
                break;
            case 5:
                System.out.println("작성 권한이 없습니다. 로그인 해주세요.");
                break;
            case 6, 7:
                System.out.println("권한이 없습니다. 로그인 해주세요.");
                break;
            case 8: // 뒤로 가기
                Free_Post_Controller.setIsFreeReply(false);
                break;
            case 9:
                Free_Post_Controller.setIsFreeReply(false);
                Post_Controller.setIsFreePost(false);
                controller.setPostIn(false);
                break;
            case 10:
                System.exit(0);
                break;
        }
    }
    public static void free_Reply_Member_Start(){
        free_Post_Number = Free_Post_Controller.getFree_Post_Num_Select();
        user_Number = User_Session_Controller.getInstance().getUser_Num();
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Reply_DAO rdao = new Reply_DAO();
        Scanner scanner = new Scanner(System.in);
        boolean isSuccess;

        System.out.println("====== KH WEBTOON 자유 게시글(회원) =======");
        System.out.println("[1]게시글 조회 [2]게시글 수정 [3]게시글 삭제 [4]댓글 보기 [5]댓글 작성 [6]댓글 공감 [7]댓글 비공감 [8]뒤로 가기 [9]메인페이지 이동 [10]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pdao.postSelect_Result(pdao.post_Single_Select(free_Post_Number));
                rdao.replySelect_Result(rdao.reply_Select(free_Post_Number));
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
                Free_Post_Controller.setIsFreeReply(false);
                break;
            case 9:
                Free_Post_Controller.setIsFreeReply(false);
                Post_Controller.setIsFreePost(false);
                controller.setPostIn(false);
                break;
            case 10:
                System.exit(0);
                break;
        }
    }
}
