package controller;

import dao.Board_DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Post_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;
    private static boolean isNoticePost = false;
    private static boolean isFreePost = false;

    public static void post_None_Member_Start(){
        Top_Controller controller = new Top_Controller();
        Board_DAO bdao = new Board_DAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 게시판 페이지(비회원) =======");
        System.out.println("[1]게시판 조회 [2]게시판 선택 [3]뒤로 가기 [4]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                bdao.boardSelect_Result(bdao.boardSelect());
                break;
            case 2:
                System.out.println("[1]공지 게시판 [2]자유 게시판");
                System.out.print("이동 게시판 선택 : ");
                int select = scanner.nextInt();
                switch (select){
                    case 1:
                        isNoticePost = true;
                        while (isNoticePost){
                        Notice_Post_Controller.notice_Post_None_Member_Start();
                        }
                        break;
                    case 2:
                        isFreePost = true;
                        while (isFreePost){
                        Free_Post_Controller.free_Post_None_Member_Start();
                        }
                        break;
                    default :
                        break;
                }
                break;
            case 3:
                controller.setPostIn(false);
                break;
            case 4:
                System.exit(0);
                break;
        }
    }
    public static void post_Member_Start(){
        Top_Controller controller = new Top_Controller();
        Board_DAO bdao = new Board_DAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("====== KH WEBTOON 게시판 페이지(회원) =======");
        System.out.println("[1]게시판 조회 [2]게시판 선택 [3]뒤로 가기 [4]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                bdao.boardSelect_Result(bdao.boardSelect());
                break;
            case 2:
                System.out.println("[1]공지 게시판 [2]자유 게시판");
                System.out.print("이동 게시판 선택 : ");
                int select = scanner.nextInt();
                switch (select){
                    case 1:
                        isNoticePost = true;
                        while (isNoticePost){
                        Notice_Post_Controller.notice_Post_Member_Start();
                        }
                        break;
                    case 2:
                        isFreePost = true;
                        while (isFreePost){
                        Free_Post_Controller.free_Post_Member_Start();
                        }
                        break;
                    default :
                        break;
                }
                break;
            case 3:
                controller.setPostIn(false);
                break;
            case 4:
                System.exit(0);
                break;
        }
    }
    public static boolean isIsNoticePost() {
        return isNoticePost;
    }
    public static void setIsNoticePost(boolean isNoticePost) {
        Post_Controller.isNoticePost = isNoticePost;
    }
    public static boolean isIsFreePost() {
        return isFreePost;
    }
    public static void setIsFreePost(boolean isFreePost) {
        Post_Controller.isFreePost = isFreePost;
    }
}