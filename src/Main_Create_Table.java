import dao.*;


// 프라이머리 키도 설정안하고 Connection DAO를 따로 만들어서 맨 마지막에 한번에 하는 방법을
// 나중에 구현해볼 것
public class Main_Create_Table {
    public static void main(String[] args) {
        Member_Type_DAO mtdao = new Member_Type_DAO(); Member_DAO mdao = new Member_DAO();
        Platform_DAO pldao = new Platform_DAO(); Genre_DAO gdao = new Genre_DAO();
        Favorite_Genre_DAO fgdao = new Favorite_Genre_DAO(); Board_DAO bdao = new Board_DAO();
        Webtoon_DAO wdao = new Webtoon_DAO(); Post_DAO pdao = new Post_DAO();
        Reply_DAO cdao = new Reply_DAO(); Reply_Evaluation_DAO cedao = new Reply_Evaluation_DAO();
        mtdao.member_Type_Create();
        mdao.memberCreate();
        pldao.platformCreate();
        gdao.genreCreate();
        fgdao.favorite_Gen_Create();
        bdao.boardCreate();
        wdao.webtoonCreate();
        pdao.postCreate();
        cdao.replyCreate();
        cedao.reply_Eval_Create();
    }
}
