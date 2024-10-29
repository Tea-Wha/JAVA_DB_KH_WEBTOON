package controller;

import dao.Member_DAO;

public class Sign_Up_Controller {

    public boolean sign_Up(){
        Member_DAO dao = new Member_DAO();
        return dao.member_Insert(Member_DAO.memberInsert_Input());
    }
}
