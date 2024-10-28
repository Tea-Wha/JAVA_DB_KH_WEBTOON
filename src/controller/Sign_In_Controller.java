package controller;

import common.Common;
import dao.Member_DAO;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Scanner;

public class Sign_In_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public boolean sign_In() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String sql = "SELECT 회원번호, 아이디 FROM 회원 WHERE 아이디 = ? AND 비밀번호 = ?";
        Member_DAO dao = new Member_DAO();
        System.out.print("ID 입력 : ");
        String member_ID = scanner.next();
        System.out.print("PW 입력 : ");
        String member_PW = scanner.next();
        if (dao.member_Check(member_ID, member_PW)){
            try {
                conn = Common.getConnection();
                psmt = conn.prepareStatement(sql);
                psmt.setString(1,member_ID);
                psmt.setString(2,member_PW);
                rs = psmt.executeQuery();
                rs.next();
                String user_ID = rs.getString("아이디");
                int user_Num = rs.getInt("회원번호");
                User_Session_Controller.getInstance().setUserInfo(user_ID, user_Num);
                return dao.member_Check(member_ID, member_PW);
            }
            catch (SQLException e){
                System.out.println(e);
                System.out.println("오류 발생");
                return false;
            }
        }
        else return dao.member_Check(member_ID, member_PW);
    }

}
