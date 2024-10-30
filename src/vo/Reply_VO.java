package vo;

import java.sql.Date;
// 댓글 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Reply_VO {
    private int reply_Num;
    // 댓글번호 / INTEGER / PRIMARY KEY
    private String reply_Content;
    // 본문 / VARCHAR2(100) / NOT NULL,
    // LENGTH(comment_Content) BETWEEN 1 AND 100
    private Date reply_Pub_Date;
    // 작성일 / DATE / NOT NULL
    private int reply_Like;
    // 공감수 / INTEGER / NOT NULL, CHECK comment_Like >= 0, DEFAULT 0
    private int reply_Dislike;
    // 비공감수 / INTEGER / NOT NULL, CHECK comment_Dislike >= 0, DEFAULT 0
    private int member_Num;
    // 회원번호 / INTEGER / FOREIGN KEY REFERENCES member(member_num),
    // NOT NULL
    private int post_Num;
    // 게시글 번호 / INTEGER / FOREIGN KEY REFERENCES post(post_num),
    // NOT NULL
    private String reply_Content_Aft;

    public Reply_VO(int reply_Num, String reply_Content, Date reply_Pub_Date, int reply_Like, int reply_Dislike, int member_Num, int post_Num) {
        this.reply_Num = reply_Num;
        this.reply_Content = reply_Content;
        this.reply_Pub_Date = reply_Pub_Date;
        this.reply_Like = reply_Like;
        this.reply_Dislike = reply_Dislike;
        this.member_Num = member_Num;
        this.post_Num = post_Num;
    }
    public Reply_VO(String reply_Content, int member_Num, int post_Num) {
        this.reply_Content = reply_Content;
        this.member_Num = member_Num;
        this.post_Num = post_Num;
    }
    public Reply_VO(int reply_Num, String reply_Content_Aft) {
        this.reply_Num = reply_Num;
        this.reply_Content_Aft = reply_Content_Aft;
    }
    public Reply_VO(int reply_Num) {
        this.reply_Num = reply_Num;
    }
    public int getReply_Num() {
        return reply_Num;
    }
    public void setReply_Num(int reply_Num) {
        this.reply_Num = reply_Num;
    }
    public String getReply_Content() {
        return reply_Content;
    }
    public void setReply_Content(String reply_Content) {
        this.reply_Content = reply_Content;
    }
    public Date getReply_Pub_Date() {
        return reply_Pub_Date;
    }
    public void setReply_Pub_Date(Date reply_Pub_Date) {
        this.reply_Pub_Date = reply_Pub_Date;
    }
    public int getReply_Like() {
        return reply_Like;
    }
    public void setReply_Like(int reply_Like) {
        this.reply_Like = reply_Like;
    }
    public int getReply_Dislike() {
        return reply_Dislike;
    }
    public void setReply_Dislike(int reply_Dislike) {
        this.reply_Dislike = reply_Dislike;
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
    public String getReply_Content_Aft() {
        return reply_Content_Aft;
    }
    public void setReply_Content_Aft(String reply_Content_Aft) {
        this.reply_Content_Aft = reply_Content_Aft;
    }
}
