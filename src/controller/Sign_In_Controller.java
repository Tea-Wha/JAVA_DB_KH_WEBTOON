package controller;

import dao.Member_DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Sign_In_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public boolean sign_In(){
        Scanner scanner = new Scanner(System.in);
        Member_DAO dao = new Member_DAO();
        System.out.print("ID 입력 : ");
        String member_ID = scanner.next();
        System.out.print("PW 입력 : ");
        String member_PW = scanner.next();
        return dao.member_Check(member_ID, member_PW);
    }

}
