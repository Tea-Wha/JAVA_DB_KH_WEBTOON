package dao;

import common.Common;
import vo.Board_VO;
import vo.Reply_Evaluation_VO;
import vo.Reply_VO;

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
            Common.close(conn);
        }
    }
}
