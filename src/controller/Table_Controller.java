package controller;

import dao.*;

public class Table_Controller {
    Member_Type_DAO mtdao = new Member_Type_DAO();
    Member_DAO mdao = new Member_DAO();
    Platform_DAO pldao = new Platform_DAO();
    Genre_DAO gdao = new Genre_DAO();
    Favorite_Genre_DAO fgdao = new Favorite_Genre_DAO();
    Board_DAO bdao = new Board_DAO();
    Webtoon_DAO wdao = new Webtoon_DAO();
    Post_DAO pdao = new Post_DAO();
    Reply_DAO rdao = new Reply_DAO();
    Reply_Evaluation_DAO redao = new Reply_Evaluation_DAO();

    public void createTable(){
        mtdao.member_Type_Create();
        mdao.memberCreate();
        pldao.platformCreate();
        gdao.genreCreate();
        fgdao.favorite_Gen_Create();
        bdao.boardCreate();
        wdao.webtoonCreate();
        pdao.postCreate();
        rdao.replyCreate();
        redao.reply_Eval_Create();
    }
    public void defaultValue(){
        bdao.boardDefault_Value();
        gdao.genreDefault_Value();
        mtdao.memberTypeDefault_Value();
        pldao.platformDefault_Value();
    }
}
