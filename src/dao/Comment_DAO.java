package dao;

import common.Common;
import vo.Board_VO;
import vo.Comment_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Comment_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Comment_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void commentCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 댓글("+"댓글번호 INTEGER PRIMARY KEY,"+"본문 VARCHAR2(100) NOT NULL CHECK (LENGTH (본문) BETWEEN 1 AND 100),"+"작성일 DATE DEFAULT SYSDATE NOT NULL,"+"공감수 INTEGER DEFAULT 0 CHECK (공감수 >= 0),"+"비공감수 INTEGER DEFAULT 0 CHECK (비공감수 >=0), "+"회원번호 INTEGER NOT NULL,"+"게시글번호 INTEGER NOT NULL,"+"FOREIGN KEY (회원번호) REFERENCES 회원 (회원번호),"+"FOREIGN KEY (게시글번호) REFERENCES 게시글 (게시글번호))";
            String createSequenceSQL = "CREATE SEQUENCE 댓글번호_SEQ "+" START WITH 1 "+" INCREMENT BY 1 "+" NOCACHE "+" NOCYCLE";
            String createTriggerSQL = "CREATE TRIGGER 댓글_트리거 "+" BEFORE INSERT ON 댓글 "+" FOR EACH ROW "+" BEGIN "+" :NEW.댓글번호 := 댓글번호_SEQ.NEXTVAL; "+" END;";
            stmt = conn.createStatement();
            stmt.executeUpdate(createTableSQL);
            stmt.executeUpdate(createSequenceSQL);
            stmt.executeUpdate(createTriggerSQL);
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
    // 댓글 작성자 본인 확인 기능 구현
    public boolean commentCheck(){
        return true;
    }
    
    // Comment Menu 따로 구현 -> 클래스 안에 포함?
    // SELECT(조회) 기능 구현
    // 댓글 테이블 확인 기능
    public List<Comment_VO> commentSelect(){
        List<Comment_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 댓글 ORDER BY 작성일"; // 댓글 테이블 쿼리문 구성 (작성일 정렬)
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int comment_Num = rs.getInt("댓글번호"); // 댓글번호 열 데이터 가져오기
                String comment_Content = rs.getString("본문"); // 본문 열 데이터 가져오기
                Date comment_Pub_Date = rs.getDate("작성일"); // 작성일 열 데이터 가져오기
                int comment_Like = rs.getInt("공감수"); // 공감수 열 데이터 가져오기
                int comment_Dislike = rs.getInt("비공감수"); // 비공감수 열 데이터 가져오기
                int member_Num = rs.getInt("회원번호"); // 회원번호 열 데이터 가져오기
                int post_Num = rs.getInt("게시글번호"); // 게시글번호 열 데이터 가져오기
                Comment_VO vo = new Comment_VO(comment_Num,comment_Content,comment_Pub_Date,comment_Like,comment_Dislike,member_Num,post_Num);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("댓글 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
    // 화면 결과 출력 구현
    public void commentSelect_Result(List<Comment_VO> list){
        System.out.println("---------------------------------");
        System.out.println("            댓글  정보            ");
        System.out.println("---------------------------------");
        for(Comment_VO e : list){
            System.out.print("댓글 번호 : "+ e.getComment_Num()+" ");
            System.out.print("댓글 내용 : "+e.getComment_Content()+" ");
            System.out.print("댓글 작성일 : "+e.getComment_Pub_Date()+" ");
            System.out.print("공감수 : "+e.getComment_Like());
            System.out.print("비공감수 : "+e.getComment_Dislike());
            System.out.print("회원번호 : "+e.getMember_Num());
            System.out.print("게시글번호 : "+e.getPost_Num());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // UPDATE 기능 구현 -> 댓글 작성자 전용 기능
    // 댓글 본문 수정 기능 / 날짜 업데이트 기능(?) X
    public boolean commentUpdate(Comment_VO vo){
        String sql = "UPDATE 댓글 SET 댓글번호 = ?, 게시판이름 = ? WHERE 게시판이름 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,vo.getComment_Num());
            psmt.setString(2,vo.getComment_Content());
            psmt.setString(3, vo.getComment_Content());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("댓글 UPDATE 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // UPDATE Input 데이터 받는 기능 -> 댓글 작성자 전용 기능
//    public static Comment_VO comment_Update_Input(){
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("변경할 댓글 번호 입력 : ");
//        int comment_Num = scanner.nextInt();
//        // 여기에 -> 권한 확인 코드
//        System.out.print("변경 후 댓글 본문 입력 : ");
//        String comment_Content_after = scanner.next();
//         Comment_VO vo = new Comment_VO();
//        return vo;
//    }
    // INSERT 기능 구현 -> 회원 전용 기능
    // 댓글 추가 기능
    public boolean commentInsert(Comment_VO vo){
        String sql = "INSERT INTO 댓글 (댓글번호, 본문, 작성일, 공감수, 비공감수, 회원번호, 게시글번호) VALUES (?,?,?,?,?,?,?)";
        try{
            conn = Common.getConnection();
            Date currentDate = new Date(System.currentTimeMillis());
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, vo.getComment_Num());
            psmt.setString(2,vo.getComment_Content());
            psmt.setDate(3,currentDate);
            psmt.setInt(4,vo.getComment_Like());
            psmt.setInt(5,vo.getComment_Dislike());
            psmt.setInt(6,vo.getMember_Num());
            psmt.setInt(7,vo.getPost_Num());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("댓글 INSERT 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
        // INSERT Input 데이터 받는 기능 -> ADMIN 전용 기능
        public static Comment_VO comment_Insert_Input(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("댓글 입력");
            System.out.print("댓글 내용 : ");
            String comment_Content = scanner.nextLine();
            System.out.print("회원 번호 : ");
            int member_Num = scanner.nextInt();
            System.out.print("게시글 번호 : ");
            int post_Num = scanner.nextInt();
            Comment_VO vo = new Comment_VO(comment_Content, member_Num, post_Num);
            return vo;
    }
}

