import com.opencsv.exceptions.CsvValidationException;
import common.Common;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import com.opencsv.CSVReader;

public class Main_DATA_Input_Test {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\user1\\Desktop\\webtoon.csv";
        List<Webtoon> webtoons = readWebtoonFromCSV(filePath);

        for (Webtoon webtoon : webtoons) {
            System.out.println(webtoon);
        }
    }
    public static List<Webtoon> readWebtoonFromCSV(String filePath){
        List<Webtoon> webtoons = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "CP949"))){
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null){
                Webtoon webtoon = new Webtoon(
                        line[0] != null ? line[0].trim() : "",
                        line[1] != null ? line[1].trim() : "",
                        parseIntOrDefault(line[2].trim(),0),
                        parseIntOrDefault(line[3].trim(),0),
                        parseBigDecimalOrDefault(line[4],BigDecimal.ZERO),
                        parseBigDecimalOrDefault(line[5],BigDecimal.ZERO),
                        parseIntOrDefault(line[6],0),
                        parseIntOrDefault(line[7],0),
                        line[8] != null ? line[8].trim() : "",
                        line[9] != null ? line[9].trim() : "",
                        parseIntOrDefault(line[10],0)
                );
                webtoons.add(webtoon);
            }
        }
        catch (IOException | CsvValidationException | NumberFormatException e){
            e.printStackTrace();
        }
        return webtoons;
    }
    private static int parseIntOrDefault (String value, int defaultValue){
        try{
            return (value == null || value.trim().isEmpty()) ? defaultValue : Integer.parseInt(value.trim());
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }
    private static BigDecimal parseBigDecimalOrDefault (String value, BigDecimal defaultValue){
        try{
            return (value == null || value.trim().isEmpty()) ? defaultValue : new BigDecimal(value.trim());
        }
        catch (NumberFormatException e){
            return defaultValue;
        }
    }
}
class Webtoon implements Comparable<Webtoon> {
    private String webtoon_Name;
    private String webtoon_Author;
    private int genre_Num;
    private int webtoon_Ava_Age;
    private BigDecimal webtoon_Rating;
    private BigDecimal webtoon_Sub_Num;
    private int webtoon_View_Num;
    private int webtoon_Rel_Day;
    private String webtoon_Page_Url;
    private String webtoon_Thu_Url;
    private int platform_Num;

    // 생성자
    public Webtoon(String webtoon_Name, String webtoon_Author, int genre_Num,
                   int webtoon_Ava_Age, BigDecimal webtoon_Rating, BigDecimal webtoon_Sub_Num,
                   int webtoon_View_Num, int webtoon_Rel_Day, String webtoon_Page_Url,
                   String webtoon_Thu_Url, int platform_Num) {
        this.webtoon_Name = webtoon_Name;
        this.webtoon_Author = webtoon_Author;
        this.genre_Num = genre_Num;
        this.webtoon_Ava_Age = webtoon_Ava_Age;
        this.webtoon_Rating = webtoon_Rating;
        this.webtoon_Sub_Num = webtoon_Sub_Num;
        this.webtoon_View_Num = webtoon_View_Num;
        this.webtoon_Rel_Day = webtoon_Rel_Day;
        this.webtoon_Page_Url = webtoon_Page_Url;
        this.webtoon_Thu_Url = webtoon_Thu_Url;
        this.platform_Num = platform_Num;
    }

    @Override
    public int compareTo(Webtoon o) {
        return Integer.compare(this.webtoon_Rel_Day, o.webtoon_Rel_Day);
    }

    @Override
    public String toString() {
        return "Webtoon{" +
                " webtoon_Name='" + webtoon_Name + '\'' +
                ", webtoon_Author='" + webtoon_Author + '\'' +
                ", genre_Num=" + genre_Num +
                ", webtoon_Ava_Age=" + webtoon_Ava_Age +
                ", webtoon_Rating=" + webtoon_Rating +
                ", webtoon_Sub_Num=" + webtoon_Sub_Num +
                ", webtoon_View_Num=" + webtoon_View_Num +
                ", webtoon_Rel_Day=" + webtoon_Rel_Day +
                ", webtoon_Page_Url='" + webtoon_Page_Url + '\'' +
                ", webtoon_Thu_Url='" + webtoon_Thu_Url + '\'' +
                ", platform_Num=" + platform_Num +
                '}';
    }
}