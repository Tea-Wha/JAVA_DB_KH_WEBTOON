package controller;

import dao.Post_DAO;
import dao.Reply_DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Notice_Reply_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;
    private static int notice_Post_Number = -1;
    private static int user_Number = -1;
    private static int reply_Number = -1;

    public static void notice_Reply_None_Member_Start(){
        notice_Post_Number = Notice_Post_Controller.getNotice_Post_Num_Select();
        user_Number = User_Session_Controller.getInstance().getUser_Num();
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Reply_DAO rdao = new Reply_DAO();
        Scanner scanner = new Scanner(System.in);

        System.out.printf("====== KH WEBTOON 공지 게시글 [%d]번 (비회원) =======\n",notice_Post_Number);
        System.out.println("[1]게시글 조회 [2]게시글 수정 [3]게시글 삭제 [4]댓글 보기 [5]댓글 작성 [6]댓글 수정 [7]댓글 삭제 [8]댓글 공감 [9]댓글 비공감 [10]뒤로 가기 [11]메인페이지 이동 [12]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pdao.postSelect_Result(pdao.post_Single_Select(notice_Post_Number));
                rdao.replySelect_Result(rdao.reply_Select(notice_Post_Number));
                break;
            case 2:
                System.out.println("게시글 수정 권한이 없습니다.");
                break;
            case 3:
                System.out.println("삭제 권한이 없습니다.");
                break;
            case 4:
                rdao.replySelect_Result(rdao.reply_Select(notice_Post_Number));
                break;
            case 5:
                System.out.println("작성 권한이 없습니다. 로그인 해주세요.");
                break;
            case 6:
                System.out.println("댓글 수정 권한이 없습니다.");
                break;
            case 7:
                System.out.println("댓글 삭제 권한이 없습니다.");
            case 8, 9:
                System.out.println("권한이 없습니다. 로그인 해주세요.");
                break;
            case 10: // 뒤로 가기
                Notice_Post_Controller.setIsNoticeReply(false);
                break;
            case 11:
                Notice_Post_Controller.setIsNoticeReply(false);
                Post_Controller.setIsNoticePost(false);
                controller.setPostIn(false);
                break;
            case 12:
                System.exit(0);
                break;
        }
    }
    public static void notice_Reply_Member_Start(){
        notice_Post_Number = Notice_Post_Controller.getNotice_Post_Num_Select();
        user_Number = User_Session_Controller.getInstance().getUser_Num();
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Reply_DAO rdao = new Reply_DAO();
        Scanner scanner = new Scanner(System.in);
        boolean isSuccess;

        System.out.printf("====== KH WEBTOON 공지 게시글 [%d]번 (회원) =======\n",notice_Post_Number);
        System.out.println("[1]게시글 조회 [2]게시글 수정 [3]게시글 삭제 [4]댓글 보기 [5]댓글 작성 [6]댓글 수정 [7]댓글 삭제 [8]댓글 공감 [9]댓글 비공감 [10]뒤로 가기 [11]메인페이지 이동 [12]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pdao.postSelect_Result(pdao.post_Single_Select(notice_Post_Number));
                rdao.replySelect_Result(rdao.reply_Select(notice_Post_Number));
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                rdao.replySelect_Result(rdao.reply_Select(notice_Post_Number));
                break;
            case 5:
                isSuccess = rdao.replyInsert(rdao.replyInsert_Input_Auto(user_Number, notice_Post_Number));
                if (isSuccess) System.out.println("댓글 작성 성공");
                else System.out.println("댓글 작성 실패");
                break;
            case 6:
                System.out.print("수정할 댓글 번호 입력 : ");
                reply_Number = scanner.nextInt();
                if (rdao.reply_Check(reply_Number,user_Number)){
                    isSuccess = rdao.reply_Update(rdao.replyUpdate_Input_Auto(reply_Number));
                    if (isSuccess) System.out.println("댓글 수정 성공");
                    else System.out.println("댓글 수정 실패");
                }
                else System.out.println("수정 권한이 없습니다.");
                break;
            case 7:
                System.out.print("삭제할 댓글 번호 입력 : ");
                reply_Number = scanner.nextInt();
                isSuccess = rdao.reply_Delete_Auto(reply_Number,user_Number);
                if (isSuccess) System.out.println("댓글 삭제 성공");
                else System.out.println("댓글 삭제 실패");
                break;
            case 8:
                break;
            case 9:
                break;
            case 10:
                Notice_Post_Controller.setIsNoticeReply(false);
                break;
            case 11:
                Notice_Post_Controller.setIsNoticeReply(false);
                Post_Controller.setIsNoticePost(false);
                controller.setPostIn(false);
                break;
            case 12:
                System.exit(0);
                break;
        }
    }
}
