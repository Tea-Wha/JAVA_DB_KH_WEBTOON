package dao;

import common.Common;
import oracle.jdbc.proxy.annotation.Post;
import vo.Genre_VO;
import vo.Platform_VO;
import vo.Post_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Post_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;
    private int post_Num;
    private int member_Num;
    private int board_Num;
    private String free_Post_Search;
    private String notice_Post_Search;
    private String post_Search;
    private int post_Number;
    private int board_Number;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Post_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void postCreate(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 게시글 ("+"게시글번호 INTEGER PRIMARY KEY,"+"제목 VARCHAR2(100) NOT NULL CHECK (LENGTH (제목) BETWEEN 1 AND 100),"+"본문 VARCHAR2(1000) NOT NULL CHECK (LENGTH (본문) BETWEEN 1 AND 1000),"+"작성일 DATE NOT NULL,"+"조회수 INTEGER DEFAULT 0 CHECK (조회수 >= 0), "+"회원번호 INTEGER NOT NULL,"+"게시판유형번호 INTEGER NOT NULL,"+"FOREIGN KEY (회원번호) REFERENCES 회원 (회원번호),"+" FOREIGN KEY (게시판유형번호) REFERENCES 게시판유형 (게시판유형번호))";
            String createSequenceSQL = "CREATE SEQUENCE 게시글번호_SEQ "+" START WITH 1 "+" INCREMENT BY 1 "+" NOCACHE "+" NOCYCLE";
            String createTriggerSQL = "CREATE TRIGGER 게시글번호_자동증가 "+" BEFORE INSERT ON 게시글 "+" FOR EACH ROW "+" BEGIN "+" :NEW.게시글번호 := 게시글번호_SEQ.NEXTVAL; "+" END;";
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
    // SELECT(전체 조회) 기능 구현
    // 게시글 확인 기능
    public List<Post_VO> all_Post_Select(){
        List<Post_VO> list = new ArrayList<>();
        try{
            conn = Common.getConnection(); // 오라클 DB 연결
            stmt = conn.createStatement(); // statement 생성
            String query = "SELECT * FROM 게시글 ORDER BY 게시판유형번호, 게시글번호"; // 게시글 테이블 쿼리문 구성
            rs = stmt.executeQuery(query); // 쿼리문 실행
            while (rs.next()){
                int post_Num = rs.getInt("게시글번호"); // 게시글번호 열 데이터 가져오기
                String post_Title = rs.getString("제목"); // 제목 열 데이터 가져오기
                String post_Content = rs.getString("본문"); // 본문 열 데이터 가져오기
                Date post_Date = rs.getDate("작성일");
                int post_Visit = rs.getInt("조회수");
                int member_Num = rs.getInt("회원번호");
                int board_Num = rs.getInt("게시판유형번호");
                Post_VO vo = new Post_VO(post_Num,post_Title,post_Content,post_Date,post_Visit,member_Num,board_Num);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("게시글 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }
        return list; // 리스트 반환    
    }
    public List<Post_VO> all_Post_Select_Search(String post_Search){ // 공지 게시판 게시글 제목
        List<Post_VO> list = new ArrayList<>();
        this.post_Search = post_Search;
        try{
            String query = "SELECT * FROM 게시글 WHERE 제목 LIKE ? ORDER BY 게시글번호"; // 게시글 테이블 쿼리문 구성
            conn = Common.getConnection(); // 오라클 DB 연결
            psmt = conn.prepareStatement(query);// statement 생성
            psmt.setString(1,"%"+post_Search+"%");
            rs = psmt.executeQuery(); // 쿼리문 실행
            while (rs.next()){
                int post_Num = rs.getInt("게시글번호"); // 게시글번호 열 데이터 가져오기
                String post_Title = rs.getString("제목"); // 제목 열 데이터 가져오기
                String post_Content = rs.getString("본문"); // 본문 열 데이터 가져오기
                Date post_Date = rs.getDate("작성일");
                int post_Visit = rs.getInt("조회수");
                int member_Num = rs.getInt("회원번호");
                int board_Num = rs.getInt("게시판유형번호");
                Post_VO vo = new Post_VO(post_Num,post_Title,post_Content,post_Date,post_Visit,member_Num,board_Num);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("게시글 검색 실패");
        }
        finally{
            Common.close(rs);
            Common.close(psmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
    public List<Post_VO> post_Select(int board_Number){
        List<Post_VO> list = new ArrayList<>();
        this.board_Number = board_Number;
        try{
            String query = "SELECT * FROM 게시글 WHERE 게시판유형번호 = ? ORDER BY 게시글번호"; // 게시글 테이블 쿼리문 구성
            conn = Common.getConnection(); // 오라클 DB 연결
            psmt = conn.prepareStatement(query);// statement 생성
            psmt.setInt(1,board_Number);
            rs = psmt.executeQuery(); // 쿼리문 실행
            while (rs.next()){
                int post_Num = rs.getInt("게시글번호"); // 게시글번호 열 데이터 가져오기
                String post_Title = rs.getString("제목"); // 제목 열 데이터 가져오기
                String post_Content = rs.getString("본문"); // 본문 열 데이터 가져오기
                Date post_Date = rs.getDate("작성일");
                int post_Visit = rs.getInt("조회수");
                int member_Num = rs.getInt("회원번호");
                int board_Num = rs.getInt("게시판유형번호");
                Post_VO vo = new Post_VO(post_Num,post_Title,post_Content,post_Date,post_Visit,member_Num,board_Num);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("게시글 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(psmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
    public List<Post_VO> post_Single_Select(int post_Number){
        List<Post_VO> list = new ArrayList<>();
        this.post_Number = post_Number;
        try{
            String query = "SELECT * FROM 게시글 WHERE 게시글번호 = ?"; // 게시글 테이블 쿼리문 구성
            conn = Common.getConnection(); // 오라클 DB 연결
            psmt = conn.prepareStatement(query);// statement 생성
            psmt.setInt(1,post_Number);
            rs = psmt.executeQuery(); // 쿼리문 실행
            while (rs.next()){
                int post_Num = rs.getInt("게시글번호"); // 게시글번호 열 데이터 가져오기
                String post_Title = rs.getString("제목"); // 제목 열 데이터 가져오기
                String post_Content = rs.getString("본문"); // 본문 열 데이터 가져오기
                Date post_Date = rs.getDate("작성일");
                int post_Visit = rs.getInt("조회수");
                int member_Num = rs.getInt("회원번호");
                int board_Num = rs.getInt("게시판유형번호");
                Post_VO vo = new Post_VO(post_Num,post_Title,post_Content,post_Date,post_Visit,member_Num,board_Num);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("게시글 조회 실패");
        }
        finally{
            Common.close(rs);
            Common.close(psmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
    public List<Post_VO> post_Select_Search(int board_Number, String post_Search){ // 게시글 제목
        List<Post_VO> list = new ArrayList<>();
        this.board_Number = board_Number;
        this.post_Search = post_Search;
        try{
            String query = "SELECT * FROM 게시글 WHERE 게시판유형번호 = ? AND 제목 LIKE ? ORDER BY 게시글번호"; // 게시글 테이블 쿼리문 구성
            conn = Common.getConnection(); // 오라클 DB 연결
            psmt = conn.prepareStatement(query);// statement 생성
            psmt.setInt(1,board_Number);
            psmt.setString(2,"%"+post_Search+"%");
            rs = psmt.executeQuery(); // 쿼리문 실행
            while (rs.next()){
                int post_Num = rs.getInt("게시글번호"); // 게시글번호 열 데이터 가져오기
                String post_Title = rs.getString("제목"); // 제목 열 데이터 가져오기
                String post_Content = rs.getString("본문"); // 본문 열 데이터 가져오기
                Date post_Date = rs.getDate("작성일");
                int post_Visit = rs.getInt("조회수");
                int member_Num = rs.getInt("회원번호");
                int board_Num = rs.getInt("게시판유형번호");
                Post_VO vo = new Post_VO(post_Num,post_Title,post_Content,post_Date,post_Visit,member_Num,board_Num);
                list.add(vo); // 리스트 저장
            }
        }
        catch (Exception e){
            System.out.println("게시글 검색 실패");
        }
        finally{
            Common.close(rs);
            Common.close(psmt);
            Common.close(conn);
        }
        return list; // 리스트 반환
    }
    public void postSelect_Result(List<Post_VO> list){ // 단일 게시글 전체 조회 (본문 포함)
        System.out.println("---------------------------------");
        System.out.println("            게시글 정보            ");
        System.out.println("---------------------------------");
        for(Post_VO e : list){
            System.out.print("게시글 번호 : "+ e.getPost_Num()+" ");
            System.out.print("게시글 제목 : "+e.getPost_Title()+" ");
            System.out.print("게시글 본문 : "+ e.getPost_Content()+" ");
            System.out.print("게시글 작성일 : "+ e.getPost_Pub_Date()+" ");
            System.out.print("게시글 조회수 : "+ e.getPost_Visit()+" ");
            System.out.print("게시글 회원번호 : "+ e.getMember_Num()+" ");
            System.out.print("게시글 유형번호 : "+ e.getBoard_Num());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    public void postSelect_Result_Array(List<Post_VO> list){ // 게시판 게시글 전체 조회 (본문 불포함) (제목만)
        System.out.println("---------------------------------");
        System.out.println("            게시글 목록            ");
        for(Post_VO e : list){
            System.out.print("게시글 번호 : "+ e.getPost_Num()+" ");
            System.out.print("게시글 제목 : "+e.getPost_Title()+" ");
            System.out.print("게시글 작성일 : "+ e.getPost_Pub_Date()+" ");
            System.out.print("게시글 조회수 : "+ e.getPost_Visit()+" ");
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // UPDATE 기능 구현 -> 작성자 전용 기능
    // 게시글 이름 및 본문 변경 기능
    public boolean post_Update(Post_VO vo){
        String sql = "UPDATE 게시글 SET 제목 = ?, 본문 = ? WHERE 게시글번호 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,vo.getPost_Title_Aft());
            psmt.setString(2, vo.getPost_Content_Aft());
            psmt.setInt(3, vo.getPost_Num());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("게시글 수정 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // UPDATE Input 데이터 받는 기능 -> 작성자 전용 기능
    public static Post_VO postUpdate_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("변경할 게시글 번호 입력 : ");
        int post_Num = scanner.nextInt();
        scanner.nextLine();
        System.out.print("변경 후 게시글 제목 입력 : ");
        String post_Title_after = scanner.nextLine();
        System.out.print("변경 후 게시글 본문 입력 : ");
        String post_Content_after = scanner.nextLine();
        Post_VO vo = new Post_VO(post_Num, post_Title_after,post_Content_after);
        return vo;
    }
    // INSERT 기능 구현 -> 회원 전용 기능
    // 게시글 추가 기능
    public boolean post_Insert(Post_VO vo){
        String sql = "INSERT INTO 게시글 (제목, 본문, 작성일, 회원번호, 게시판유형번호) VALUES (?,?,SYSDATE,?,?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, vo.getPost_Title());
            psmt.setString(2, vo.getPost_Content());
            psmt.setInt(3,vo.getMember_Num());
            psmt.setInt(4,vo.getBoard_Num());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println("게시글 작성 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // INSERT Input 데이터 받는 기능 -> 회원 전용 기능
    public static Post_VO postInsert_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("게시글 정보 입력");
        System.out.print("게시글 제목 : ");
        String post_Title = scanner.nextLine();
        System.out.print("게시글 본문 내용 : ");
        String post_Content = scanner.nextLine();
        System.out.print("회원 번호 : ");
        int member_Num = scanner.nextInt();
        System.out.print("게시판 유형 번호 : ");
        int board_Num = scanner.nextInt();
        Post_VO vo = new Post_VO(post_Title, post_Content, member_Num, board_Num);
        return vo;
    }
    public Post_VO postInsert_Input_Auto(int member_Num, int board_Num){
        Scanner scanner = new Scanner(System.in);
        System.out.println("게시글 정보 입력");
        System.out.print("게시글 제목 : ");
        String post_Title = scanner.nextLine();
        System.out.print("게시글 본문 내용 : ");
        String post_Content = scanner.nextLine();
        this.member_Num = member_Num;
        this.board_Num = board_Num;
        Post_VO vo = new Post_VO(post_Title, post_Content, member_Num, board_Num);
        return vo;
    }
    public boolean post_Delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("삭제 게시글 번호 입력 : ");
        int post_Num = scanner.nextInt();
        System.out.print("회원번호 입력 : ");
        int member_Num = scanner.nextInt();
        if (post_Check(post_Num, member_Num)) {
            System.out.print("게시글을 삭제하시겠습니까? [1]예 [2]아니오 : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    String sql = "DELETE FROM 게시글 WHERE 게시글번호 = ?";
                    try {
                        conn = Common.getConnection();
                        psmt = conn.prepareStatement(sql);
                        psmt.setInt(1, post_Num);
                        psmt.executeUpdate();
                        return true;
                    } catch (Exception e) {
                        System.out.println("게시글 삭제 실패");
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
    public boolean post_Check(int post_Num, int member_Num){
        this.post_Num = post_Num;
        this.member_Num = member_Num;
        boolean check = false;
        String sql = "SELECT COUNT(*) FROM 게시글 WHERE 게시글번호 = ? AND 회원번호 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, post_Num);
            psmt.setInt(2, member_Num);
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
    public boolean post_Exist(int post_Num, int board_Num){
        this.post_Num = post_Num;
        this.board_Num = board_Num;
        boolean check = false;
        String sql = "SELECT COUNT(*) FROM 게시글 WHERE 게시글번호 = ? AND 게시판유형번호 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, post_Num);
            psmt.setInt(2, board_Num);
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
    public void post_Visit(int post_Num){
        this.post_Num = post_Num;
        String sql = "UPDATE 게시글 SET 조회수 = 조회수 + 1 WHERE 게시글번호 = ?";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, post_Num);
            psmt.executeUpdate();
        }
        catch (Exception e){
            System.out.print("오류 발생");
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
}
