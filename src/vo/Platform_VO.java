package vo;

// 플랫폼 테이블 //
// VO(Value Object) : 데이터베이스에서 가져온 레코드(튜플)를 자바 객체로 매핑하는데 사용
public class Platform_VO {
    private int platform_Num;
    // 플랫폼번호 / INTEGER / PRIMARY KEY
    private String platform_Name;
    // 플랫폼이름 / VARCHAR2(30) / UNIQUE, NOT NULL, CHECK IN ('네이버', '카카오')
    private String platform_Name_Bef;
    private String platform_Name_Aft;

    public Platform_VO(int platform_Num, String platform_Name) {
        this.platform_Num = platform_Num;
        this.platform_Name = platform_Name;
    }
    public Platform_VO(int platform_Num, String platform_Name_Bef, String platform_Name_Aft) {
        this.platform_Num = platform_Num;
        this.platform_Name_Bef = platform_Name_Bef;
        this.platform_Name_Aft = platform_Name_Aft;
    }
    public int getPlatform_Num() {
        return platform_Num;
    }
    public void setPlatform_Num(int platform_Num) {
        this.platform_Num = platform_Num;
    }
    public String getPlatform_Name() {
        return platform_Name;
    }
    public void setPlatform_Name(String platform_Name) {
        this.platform_Name = platform_Name;
    }
    public String getPlatform_Name_Bef() {
        return platform_Name_Bef;
    }
    public void setPlatform_Name_Bef(String platform_Name_Bef) {
        this.platform_Name_Bef = platform_Name_Bef;
    }
    public String getPlatform_Name_Aft() {
        return platform_Name_Aft;
    }
    public void setPlatform_Name_Aft(String platform_Name_Aft) {
        this.platform_Name_Aft = platform_Name_Aft;
    }
}
