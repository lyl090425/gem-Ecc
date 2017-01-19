package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2016/12/28.
 */
public class TempConductPoint {
    String name;//名字
    String score;//分数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public TempConductPoint(String name, String score){
        this.name=name;
        this.score=score;

    }
}
