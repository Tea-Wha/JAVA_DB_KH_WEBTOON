import dao.Board_DAO;
import dao.Member_DAO;
import dao.Member_Type_DAO;
import dao.Reply_DAO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//          board_Function_Test();
//        comment_Function_Test();
//        member_Type_Function_Test();
        member_Function_Test();
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
        Reply_DAO dao = new Reply_DAO();
        boolean isSuccess;
        // MEMBER 전용 구현 추후 추가해야함
        while (true){
            System.out.println("========= 댓글 TABLE =========");
            System.out.println("메뉴 선택");
            System.out.print("[1]댓글 보기 [2]댓글 추가 [3]댓글 수정 [4]댓글 삭제 [5]종료 ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    dao.replySelect_Result(dao.replySelect());
                    break;
                case 2:
                    isSuccess = dao.replyInsert(Reply_DAO.reply_Insert_Input());
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
    public static void member_Type_Function_Test(){
        Scanner scanner = new Scanner(System.in);
        Member_Type_DAO dao = new Member_Type_DAO();
        boolean isSuccess;
        // ADMIN 전용 / MEMBER 기능 분리 구현 추후 추가해야함
        while (true){
            System.out.println("========= 회원종류유형 TABLE =========");
            System.out.println("메뉴 선택");
            System.out.print("[1]회원종류 보기 [2]회원종류 추가 [3]회원종류 수정 [4]회원종류 삭제 [5]종료 ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    dao.memberTypeSelect_Result(dao.memberTypeSelect());
                    break;
                case 2:
                    isSuccess = dao.memberType_Insert(Member_Type_DAO.memberTypeInsert_Input());
                    if (isSuccess) System.out.println("회원종류 추가 성공");
                    else System.out.println("회원종류 추가 실패");
                    break;
                case 3:
                    isSuccess = dao.memberType_Update(Member_Type_DAO.memberTypeUpdate_Input());
                    if (isSuccess) System.out.println("회원종류 수정 성공");
                    else System.out.println("회원종류 추가 실패");
                    break;
                case 4:
                    isSuccess = dao.memberType_Delete();
                    if (isSuccess) System.out.println("회원종류 삭제 성공");
                    else System.out.println("회원종류 삭제 실패");
                    break;
                case 5:
                    System.out.println("회원종류 페이지 종료");
                    return;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
                    break;

            }
        }
    }
    public static void member_Function_Test(){
        Scanner scanner = new Scanner(System.in);
        Member_DAO dao = new Member_DAO();
        boolean isSuccess;
        // ADMIN 전용 / MEMBER 기능 분리 구현 추후 추가해야함
        while (true){
            System.out.println("========= 회원 TABLE =========");
            System.out.println("메뉴 선택");
            System.out.print("[1]회원 보기 [2]회원가입 [3]회원 정보 수정 [4]회원 삭제 [5]종료 ");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    dao.memberSelect_Result(dao.member_Select());
                    break;
                case 2:
                    isSuccess = dao.member_Insert(Member_DAO.memberInsert_Input());
                    if (isSuccess) System.out.println("회원가입 성공");
                    else System.out.println("회원가입 실패");
                    break;
                case 3:
                    isSuccess = dao.member_Update(Member_DAO.memberUpdate_Input());
                    if (isSuccess) System.out.println("비밀번호 변경 성공");
                    else System.out.println("비밀번호 변경 실패");
                    break;
                case 4:
                    isSuccess = dao.member_Delete();
                    if (isSuccess) System.out.println("회원 탈퇴 성공");
                    else System.out.println("회원 탈퇴 실패");
                    break;
                case 5:
                    System.out.println("회원 메뉴 종료");
                    return;
                default:
                    System.out.println("입력이 잘못 되었습니다.");
                    break;

            }
        }
    }
}
