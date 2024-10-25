package vo;

import java.sql.Date;
// 댓글 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Comment_VO {
    private int comment_Num;
    // 댓글번호 / INTEGER / PRIMARY KEY
    private String comment_Content;
    // 본문 / VARCHAR2(100) / NOT NULL,
    // LENGTH(comment_Content) BETWEEN 1 AND 100
    private Date comment_Pub_Date;
    // 작성일 / DATE / NOT NULL
    private int comment_Like;
    // 공감수 / INTEGER / NOT NULL, CHECK comment_Like >= 0, DEFAULT 0
    private int comment_Dislike;
    // 비공감수 / INTEGER / NOT NULL, CHECK comment_Dislike >= 0, DEFAULT 0
    private int member_Num;
    // 회원번호 / INTEGER / FOREIGN KEY REFERENCES member(member_num),
    // NOT NULL
    private int post_Num;
    // 게시글 번호 / INTEGER / FOREIGN KEY REFERENCES post(post_num),
    // NOT NULL

    public Comment_VO(int comment_Num, String comment_Content, Date comment_Pub_Date, int comment_Like, int comment_Dislike, int member_Num, int post_Num) {
        this.comment_Num = comment_Num;
        this.comment_Content = comment_Content;
        this.comment_Pub_Date = comment_Pub_Date;
        this.comment_Like = comment_Like;
        this.comment_Dislike = comment_Dislike;
        this.member_Num = member_Num;
        this.post_Num = post_Num;
    }
    public Comment_VO(String comment_Content, Date comment_Pub_Date, int comment_Like, int comment_Dislike, int member_Num, int post_Num) {
        this.comment_Content = comment_Content;
        this.comment_Pub_Date = comment_Pub_Date;
        this.comment_Like = comment_Like;
        this.comment_Dislike = comment_Dislike;
        this.member_Num = member_Num;
        this.post_Num = post_Num;
    }

    public Comment_VO(String comment_Content, int member_Num, int post_Num) {
        this.comment_Content = comment_Content;
        this.member_Num = member_Num;
        this.post_Num = post_Num;
    }

    public int getComment_Num() {
        return comment_Num;
    }
    public void setComment_Num(int comment_Num) {
        this.comment_Num = comment_Num;
    }
    public String getComment_Content() {
        return comment_Content;
    }
    public void setComment_Content(String comment_Content) {
        this.comment_Content = comment_Content;
    }
    public Date getComment_Pub_Date() {
        return comment_Pub_Date;
    }
    public void setComment_Pub_Date(Date comment_Pub_Date) {
        this.comment_Pub_Date = comment_Pub_Date;
    }
    public int getComment_Like() {
        return comment_Like;
    }
    public void setComment_Like(int comment_Like) {
        this.comment_Like = comment_Like;
    }
    public int getComment_Dislike() {
        return comment_Dislike;
    }
    public void setComment_Dislike(int comment_Dislike) {
        this.comment_Dislike = comment_Dislike;
    }
    public int getMember_Num() {
        return member_Num;
    }
    public void setMember_Num(int member_Num) {
        this.member_Num = member_Num;
    }
    public int getPost_Num() {
        return post_Num;
    }
    public void setPost_Num(int post_Num) {
        this.post_Num = post_Num;
    }
}
