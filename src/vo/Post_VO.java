package vo;

import java.sql.Date;

// 게시글 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Post_VO {
    private int post_Num;
    // 게시글 번호 / INTEGER / PRIMARY KEY
    private String post_Title;
    // 제목 / VARCHAR2(100) / NOT NULL,
    // CHECK LENGTH(post_Title) BETWEEN 1 AND 100
    private String post_Content;
    // 본문 / VARCHAR2(1000) / NOT NULL,
    // CHECK LENGTH(post_Content) BETWEEN 1 AND 1000
    private Date post_Pub_Date;
    // 작성일 / DATE / NOT NULL
    private int post_Visit;
    // 조회수 / INTEGER / NOT NULL / DEFAULT 0 / CHECK 조회수 >= 0
    private int member_Num;
    // 회원번호 / INTEGER / NOT NULL, FK REFERENCES member(member_Num)
    private int board_Num;
    // 게시판유형번호 / INTEGER / NOT NULL, FK REFERENCES board(board_Num)
    private String post_Title_Aft;
    private String post_Content_Aft;


    public Post_VO(int post_Num, String post_Title, String post_Content, Date post_Pub_Date, int post_Visit, int member_Num, int board_Num) {
        this.post_Num = post_Num;
        this.post_Title = post_Title;
        this.post_Content = post_Content;
        this.post_Pub_Date = post_Pub_Date;
        this.post_Visit = post_Visit;
        this.member_Num = member_Num;
        this.board_Num = board_Num;
    }
    public Post_VO(int post_Num, String post_Title_Aft, String post_Content_Aft) {
        this.post_Num = post_Num;
        this.post_Title_Aft = post_Title_Aft;
        this.post_Content_Aft = post_Content_Aft;
    }
    public Post_VO(String post_Title, String post_Content, int member_Num, int board_Num) {
        this.post_Title = post_Title;
        this.post_Content = post_Content;
        this.member_Num = member_Num;
        this.board_Num = board_Num;
    }
    public int getPost_Num() {
        return post_Num;
    }
    public void setPost_Num(int post_Num) {
        this.post_Num = post_Num;
    }
    public String getPost_Title() {
        return post_Title;
    }
    public void setPost_Title(String post_Title) {
        this.post_Title = post_Title;
    }
    public String getPost_Content() {
        return post_Content;
    }
    public void setPost_Content(String post_Content) {
        this.post_Content = post_Content;
    }
    public Date getPost_Pub_Date() {
        return post_Pub_Date;
    }
    public void setPost_Pub_Date(Date post_Pub_Date) {
        this.post_Pub_Date = post_Pub_Date;
    }
    public int getMember_Num() {
        return member_Num;
    }
    public void setMember_Num(int member_Num) {
        this.member_Num = member_Num;
    }
    public int getBoard_Num() {
        return board_Num;
    }
    public void setBoard_Num(int board_Num) {
        this.board_Num = board_Num;
    }
    public int getPost_Visit() {
        return post_Visit;
    }
    public void setPost_Visit(int post_Visit) {
        this.post_Visit = post_Visit;
    }
    public String getPost_Title_Aft() {
        return post_Title_Aft;
    }
    public void setPost_Title_Aft(String post_Title_Aft) {
        this.post_Title_Aft = post_Title_Aft;
    }
    public String getPost_Content_Aft() {
        return post_Content_Aft;
    }
    public void setPost_Content_Aft(String post_Content_Aft) {
        this.post_Content_Aft = post_Content_Aft;
    }
}
