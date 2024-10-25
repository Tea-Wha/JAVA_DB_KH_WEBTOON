package vo;

// 게시판유형 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Board_VO {
    private int boardNum;
    // 게시판 유형번호 / INTEGER / PRIMARY KEY
    private String boardName;
    // 게시판 이름 / VARCHAR2(20) / NOT NULL, UNIQUE, CHECK IN('공지', '자유')

    public Board_VO(int boardNum, String boardName) {
        this.boardNum = boardNum;
        this.boardName = boardName;
    }
    public int getBoardNum() {
        return boardNum;
    }
    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }
    public String getBoardName() {
        return boardName;
    }
    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }
}
