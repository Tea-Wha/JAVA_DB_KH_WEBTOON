package controller;

import dao.Post_DAO;
import dao.Reply_DAO;
import dao.Reply_Evaluation_DAO;

import java.util.Scanner;

public class Notice_Reply_Controller {
    private static int notice_Post_Number = -1;
    private static int user_Number = -1;
    private static int reply_Number = -1;
    private static int reply_Like = 0;
    private static int reply_Dislike = 1;

    public static void notice_Reply_None_Member_Start(){
        notice_Post_Number = Notice_Post_Controller.getNotice_Post_Num_Select();
        user_Number = User_Session_Controller.getInstance().getUser_Num();
        Top_Controller controller = new Top_Controller();
        Post_DAO pdao = new Post_DAO();
        Reply_DAO rdao = new Reply_DAO();
        Scanner scanner = new Scanner(System.in);

        System.out.printf("====== KH WEBTOON 공지 게시글 [%d]번 (비회원) =======\n",notice_Post_Number);
        System.out.println("[1]게시글 조회 [2]게시글 수정 [3]게시글 삭제 [4]댓글 보기 [5]댓글 작성 [6]댓글 수정 [7]댓글 삭제 [8]댓글 공감 [9]댓글 비공감 [10]공감/비공감 취소 [11]뒤로 가기 [12]메인페이지 이동 [13]종료");
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
            case 8, 9, 10:
                System.out.println("권한이 없습니다. 로그인 해주세요.");
                break;
            case 11: // 뒤로 가기
                Notice_Post_Controller.setIsNoticeReply(false);
                break;
            case 12:
                Notice_Post_Controller.setIsNoticeReply(false);
                Post_Controller.setIsNoticePost(false);
                controller.setPostIn(false);
                break;
            case 13:
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
        Reply_Evaluation_DAO redao = new Reply_Evaluation_DAO();
        Scanner scanner = new Scanner(System.in);
        boolean isSuccess;
        boolean isLike;
        boolean isDislike;

        System.out.printf("====== KH WEBTOON 공지 게시글 [%d]번 (회원) =======\n",notice_Post_Number);
        System.out.println("[1]게시글 조회 [2]게시글 수정 [3]게시글 삭제 [4]댓글 보기 [5]댓글 작성 [6]댓글 수정 [7]댓글 삭제 [8]댓글 공감 [9]댓글 비공감 [10]공감/비공감 취소 [11]뒤로 가기 [12]메인페이지 이동 [13]종료");
        System.out.print("이동 메뉴 선택 : ");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                pdao.postSelect_Result(pdao.post_Single_Select(notice_Post_Number));
                rdao.replySelect_Result(rdao.reply_Select(notice_Post_Number));
                break;
            case 2:
                if (pdao.post_Check(notice_Post_Number,user_Number)){
                    isSuccess = pdao.post_Update(pdao.postUpdate_Input_Auto(notice_Post_Number));
                    if (isSuccess) System.out.println("게시글 수정 성공");
                    else System.out.println("게시글 수정 실패");
                }
                else System.out.println("수정 권한이 없습니다.");
                break;
            case 3:
                isSuccess = pdao.post_Delete_Auto_All(notice_Post_Number, user_Number);
                if (isSuccess) {
                    redao.post_Reply_Evaluation_Delete_Auto(notice_Post_Number);
                    rdao.post_Reply_Delete_Auto(notice_Post_Number);
                    pdao.post_Delete_Auto(notice_Post_Number);
                    System.out.println("게시글 삭제 성공");
                    Notice_Post_Controller.setIsNoticeReply(false);
                }
                else System.out.println("삭제 권한이 없습니다.");
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
                System.out.print("공감할 댓글 번호 입력 : ");
                reply_Number = scanner.nextInt();
                isSuccess = redao.replyEvaluation_Check(user_Number, reply_Number);
                if (!isSuccess){
                    isLike = redao.replyEval_Insert(redao.replyEvalInsert_Input_Auto(user_Number,reply_Number,reply_Like));
                    if (isLike) {
                        rdao.reply_Update_Like_Up(rdao.replyUpdate_Like_Dislike_Input_Auto(reply_Number));
                        System.out.println("공감합니다.");
                    }
                    else System.out.println("오류 발생");
                }
                else System.out.println("이미 공감했습니다.");
                break;
            case 9:
                System.out.print("비공감할 댓글 번호 입력 : ");
                reply_Number = scanner.nextInt();
                isSuccess = redao.replyEvaluation_Check(user_Number, reply_Number);
                if (!isSuccess){
                    isDislike = redao.replyEval_Insert(redao.replyEvalInsert_Input_Auto(user_Number,reply_Number,reply_Dislike));
                    if (isDislike) {
                        rdao.reply_Update_Dislike_Up(rdao.replyUpdate_Like_Dislike_Input_Auto(reply_Number));
                        System.out.println("비공감합니다.");
                    }
                    else System.out.println("오류 발생");
                }
                else System.out.println("이미 비공감했습니다.");
                break;
            case 10:
                System.out.print("공감/비공감 취소할 댓글 번호 입력 : ");
                reply_Number = scanner.nextInt();
                int like_Before_Number = redao.replyEvaluation_Comparison_Before(reply_Number); // Delete 하기 전의 개수
                isSuccess =  redao.reply_Evaluation_Delete_Auto(user_Number,reply_Number); 
                int like_After_Number = redao.replyEvaluation_Comparison_After(reply_Number); // Delete 한 후의 개수
                if (isSuccess) {
                    if (like_Before_Number == like_After_Number) {
                        rdao.reply_Update_Dislike_Down(rdao.replyUpdate_Like_Dislike_Input_Auto(reply_Number));
                        System.out.println("취소 성공");
                    } else {
                        rdao.reply_Update_Like_Down(rdao.replyUpdate_Like_Dislike_Input_Auto(reply_Number));
                        System.out.println("취소 성공");
                    }
                }
                else System.out.println("공감/비공감 하지 않았습니다.");
                break;
            case 11:
                Notice_Post_Controller.setIsNoticeReply(false);
                break;
            case 12:
                Notice_Post_Controller.setIsNoticeReply(false);
                Post_Controller.setIsNoticePost(false);
                controller.setPostIn(false);
                break;
            case 13:
                System.exit(0);
                break;
        }
    }
}
