package dao;

import common.Common;
import vo.Reply_Evaluation_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reply_Evaluation_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;
    private int member_Number;
    private int reply_Number;
    private int reply_Eval_Type;
    PreparedStatement psmt_c1 = null;
    PreparedStatement psmt_c2 = null;
    ResultSet rs_c1 = null;
    ResultSet rs_c2 = null;
    private int post_Number;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Reply_Evaluation_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현
    public void reply_Eval_Create(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 댓글평가 ("+"회원번호 INTEGER NOT NULL,"+"댓글번호 INTEGER NOT NULL,"+"평가유형 NUMBER(1) NOT NULL CHECK (평가유형 IN (0,1)),"+"PRIMARY KEY (회원번호, 댓글번호),"+"FOREIGN KEY (회원번호) REFERENCES 회원 (회원번호),"+"FOREIGN KEY (댓글번호) REFERENCES 댓글 (댓글번호))";
            stmt = conn.createStatement();
            stmt.executeUpdate(createTableSQL);
            System.out.println("테이블 생성 성공");
        }
        catch (Exception e){
            System.out.println("테이블 생성 실패");
        }
        finally{
            Common.close(conn);
            Common.close(stmt);
        }
    }
    // SELECT(조회) 기능 구현
    // 댓글 평가 유형 확인 기능
    public List<Reply_Evaluation_VO> replySelect(){
        List<Reply_Evaluation_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 댓글평가 ORDER BY 댓글번호"; // 게시판 유형 테이블 쿼리문 구성
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int memberNum = rs.getInt("회원번호"); // 회원번호 열 데이터 가져오기
                int replyNum = rs.getInt("댓글번호"); // 댓글번호 열 데이터 가져오기
                int replyEvalType = rs.getInt("댓글유형");
                Reply_Evaluation_VO vo = new Reply_Evaluation_VO(memberNum,replyNum,replyEvalType);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("댓글 평가 유형 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
    public boolean replyEval_Insert(Reply_Evaluation_VO vo){
        String sql = "INSERT INTO 댓글평가 (회원번호, 댓글번호, 평가유형) VALUES (?,?,?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,vo.getMember_Num());
            psmt.setInt(2,vo.getReply_Num());
            psmt.setInt(3,vo.getReply_Eval_Type());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("공감 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    public Reply_Evaluation_VO replyEvalInsert_Input_Auto(int member_Number, int reply_Number, int reply_Eval_Type){
        this.member_Number = member_Number;
        this.reply_Number = reply_Number;
        this.reply_Eval_Type = reply_Eval_Type;
        Reply_Evaluation_VO vo = new Reply_Evaluation_VO(member_Number, reply_Number, reply_Eval_Type);
        return vo;
    }
    public boolean replyEvaluation_Check(int member_Number, int reply_Number){
        this.member_Number = member_Number;
        this.reply_Number = reply_Number;
        boolean check = false;
        String sql = "SELECT COUNT(*) FROM 댓글평가 WHERE 회원번호 = ? AND 댓글번호 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, member_Number);
            psmt.setInt(2, reply_Number);
            rs = psmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                check = count > 0;
            }
            return check;
        }
        catch (Exception e){
            System.out.print("입력된 정보가 일치하지 않습니다.");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(rs);
            Common.close(conn);
        }
    }
    public boolean reply_Evaluation_Delete_Auto(int member_Number, int reply_Number) {
        this.reply_Number = reply_Number;
        this.member_Number = member_Number;
        Scanner scanner = new Scanner(System.in);
        if (replyEvaluation_Check(member_Number, reply_Number)) {
            System.out.print("공감/비공감을 취소하시겠습니까? [1]예 [2]아니오 : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String sql = "DELETE FROM 댓글평가 WHERE 회원번호 = ? AND 댓글번호 = ? ";
                    try {
                        conn = Common.getConnection();
                        psmt = conn.prepareStatement(sql);
                        psmt.setInt(1, member_Number);
                        psmt.setInt(2,reply_Number);
                        psmt.executeUpdate();
                        return true;
                    } catch (Exception e) {
                        System.out.println("공감/비공감 취소 실패");
                        return false;
                    } finally {
                        Common.close(psmt);
                        Common.close(conn);
                    }
                case 2:
                    System.out.print("메뉴로 돌아갑니다.");
                    return false;
                default:
                    System.out.print("입력이 잘못되었습니다. 메뉴로 돌아갑니다.");
                    return false;
            }
        }
        else return false;
    }
    public int replyEvaluation_Comparison_Before(int reply_Number){
        this.reply_Number = reply_Number;
        int like_Number = 0;
        String sql = "SELECT COUNT(*) FROM 댓글평가 WHERE 댓글번호 = ? AND 평가유형 = 0";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, reply_Number);
            rs = psmt.executeQuery();
            if (rs.next()) {
                like_Number = rs.getInt(1);
            }
            return like_Number;
        }
        catch (Exception e){
            System.out.println(e);
            System.out.print("입력된 정보가 일치하지 않습니다.");
        }
        finally {
            Common.close(psmt);
            Common.close(rs);
            Common.close(conn);
        }
        return like_Number;
    }
    public int replyEvaluation_Comparison_After(int reply_Number){
        this.reply_Number = reply_Number;
        int like_Number = 0;
        String sql = "SELECT COUNT(*) FROM 댓글평가 WHERE 댓글번호 = ? AND 평가유형 = 0";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, reply_Number);
            rs = psmt.executeQuery();
            if (rs.next()) {
                like_Number = rs.getInt(1);
            }
            return like_Number;
        }
        catch (Exception e){
            System.out.println(e);
            System.out.print("입력된 정보가 일치하지 않습니다.");
        }
        finally {
            Common.close(psmt);
            Common.close(rs);
            Common.close(conn);
        }
        return like_Number;
    }
    public void post_Reply_Evaluation_Delete_Auto (int post_Number){
        this.post_Number = post_Number;
        String delete_Query = "DELETE FROM 댓글평가 WHERE 댓글번호 = ?";
        String select_Query = "SELECT 댓글번호 FROM 댓글 WHERE 게시글번호 = ?";
        try{
            conn = Common.getConnection();
            psmt_c1 = conn.prepareStatement(select_Query);
            psmt_c1.setInt(1,post_Number);
            rs_c1 = psmt_c1.executeQuery();
            psmt_c2 = conn.prepareStatement(delete_Query);
            while (rs_c1.next()){
                int reply_Number = rs_c1.getInt("댓글번호");
                psmt_c2.setInt(1,reply_Number);
                psmt_c2.executeUpdate();
            }
        }
        catch (Exception e){
            System.out.println("삭제 실패");
        }
        finally {
            Common.close(psmt_c1);
            Common.close(psmt_c2);
            Common.close(rs_c1);
            Common.close(conn);
        }
    }
}
