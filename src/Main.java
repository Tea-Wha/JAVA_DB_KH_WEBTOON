import dao.Board_DAO;
import dao.Comment_DAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        board_Function_Test();
        comment_Function_Test();
    }
    public static void board_Function_Test(){
        Scanner scanner = new Scanner(System.in);
        Board_DAO dao = new Board_DAO();
        boolean isSuccess;
        // ADMIN 전용 / MEMBER 기능 분리 구현 추후 추가해야함
        while (true){
            System.out.println("========= 게시판유형 TABLE =========");
            System.out.println("메뉴 선택");
            System.out.print("[1]게시판 보기 [2]게시판 추가 [3]게시판 수정 [4]게시판 삭제 [5]종료 ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    dao.boardSelect_Result(dao.boardSelect());
                    break;
                case 2:
                    isSuccess = dao.boardInsert(Board_DAO.board_Insert_Input());
                    if (isSuccess) System.out.println("게시판 추가 성공");
                    else System.out.println("게시판 추가 실패");
                    break;
                case 3:
                    isSuccess = dao.boardUpdate(Board_DAO.board_Update_Input());
                    if (isSuccess) System.out.println("게시판 수정 성공");
                    else System.out.println("게시판 추가 실패");
                    break;
                case 4:
                    isSuccess = dao.boardDelete();
                    if (isSuccess) System.out.println("게시판 삭제 성공");
                    else System.out.println("게시판 삭제 실패");
                    break;
                case 5:
                    System.out.println("게시판 페이지 종료");
                    return;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
                    break;

            }
        }
    }
    public static void comment_Function_Test(){
        Scanner scanner = new Scanner(System.in);
        Comment_DAO dao = new Comment_DAO();
        boolean isSuccess;
        // MEMBER 전용 구현 추후 추가해야함
        while (true){
            System.out.println("========= 댓글 TABLE =========");
            System.out.println("메뉴 선택");
            System.out.print("[1]댓글 보기 [2]댓글 추가 [3]댓글 수정 [4]댓글 삭제 [5]종료 ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    dao.commentSelect_Result(dao.commentSelect());
                    break;
                case 2:
                    isSuccess = dao.commentInsert(Comment_DAO.comment_Insert_Input());
                    if (isSuccess) System.out.println("댓글 추가 성공");
                    else System.out.println("댓글 추가 실패");
                    break;
//                case 3:
//                    isSuccess = dao.boardUpdate(Board_DAO.board_Update_Input());
//                    if (isSuccess) System.out.println("게시판 수정 성공");
//                    else System.out.println("게시판 추가 실패");
//                    break;
//                case 4:
//                    isSuccess = dao.boardDelete();
//                    if (isSuccess) System.out.println("게시판 삭제 성공");
//                    else System.out.println("게시판 삭제 실패");
//                    break;
//                case 5:
//                    System.out.println("게시판 페이지 종료");
//                    return;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
                    break;

            }
        }
    }
}
