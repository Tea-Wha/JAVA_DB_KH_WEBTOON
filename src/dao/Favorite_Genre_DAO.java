package dao;

import common.Common;
import vo.Comment_VO;
import vo.Member_VO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Favorite_Genre_DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    // Scanner 기능이 필요할 때 켜짐(?)
    public void Favorite_Genre_DAO_Scanner(){
        scanner = new Scanner(System.in);
        try{
            fileInputStream = new FileInputStream(".csv");
        }
        catch (FileNotFoundException e){
            System.out.println("파일을 찾지 못했습니다.");
        }
    }
    // 테이블 CREATE 기능 구현 (참조 기능 없음)
    public void favorite_Gen_Create(){
        try{
            conn = Common.getConnection();
            stmt = null;
            String createTableSQL = "CREATE TABLE 선호장르 ("+"회원번호 INTEGER,"+"장르번호 INTEGER,"+"PRIMARY KEY (회원번호, 장르번호),"+"FOREIGN KEY (회원번호) REFERENCES 회원 (회원번호),"+"FOREIGN KEY (장르번호) REFERENCES 장르 (장르번호))";
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
    // 화면 결과 출력 구현
    public void MemberSelect_Result(List<Member_VO> list){
        System.out.println("---------------------------------");
        System.out.println("            회원  정보            ");
        System.out.println("---------------------------------");
        for(Member_VO e : list){
            System.out.print("회원 번호 : "+ e.getMember_Num()+" ");
            System.out.print("아이디 : "+e.getMember_ID()+" ");
            System.out.print("비밀번호 : "+e.getMember_PW()+" ");
            System.out.print("이메일 : "+e.getMember_Email()+" ");
            System.out.print("생년월일 : "+e.getMember_Birth_Date()+" ");
            System.out.print("닉네임 : "+e.getMember_Num()+" ");
            System.out.print("가입일 : "+e.getMember_Reg_Date()+" ");
            System.out.print("탈퇴여부 : "+e.getMember_Exist()+" ");
            System.out.print("회원종류번호 : "+e.getMember_Type_Num());
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    // INSERT 기능 구현 -> 회원 가입 기능
    // 회원 가입 기능
    public boolean memberInsert(Member_VO vo){
        String sql = "INSERT INTO 회원 (회원번호, 아이디, 비밀번호, 이메일, 생년월일, 닉네임, 가입일, 탈퇴여부, 회원종류번호) VALUES (?,?,?,?,?,?,SYSDATE,?,?)";
        try{
            conn = Common.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, vo.getMember_Num());
            psmt.setString(2,vo.getMember_ID());
            psmt.setString(3,vo.getMember_PW());
            psmt.setString(4,vo.getMember_Email());
            psmt.setDate(5,vo.getMember_Birth_Date());
            psmt.setString(6,vo.getMember_Nickname());
            psmt.setInt(7,vo.getMember_Exist());
            psmt.setInt(8, vo.getMember_Type_Num());
            psmt.executeUpdate();
            return true;
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println("회원 INSERT 실패");
            return false;
        }
        finally {
            Common.close(psmt);
            Common.close(conn);
        }
    }
    // INSERT Input 데이터 받는 기능 -> ADMIN 전용 기능
    public static Member_VO member_Insert_Input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("회원 정보 입력");
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
