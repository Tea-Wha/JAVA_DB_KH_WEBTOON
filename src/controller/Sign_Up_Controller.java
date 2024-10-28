package controller;

import dao.Member_DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Sign_Up_Controller {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    Scanner scanner = null;
    FileInputStream fileInputStream = null;

    public boolean sign_Up(){
        Member_DAO dao = new Member_DAO();
        return dao.member_Insert(Member_DAO.memberInsert_Input());
    }
}
