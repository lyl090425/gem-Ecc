package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2016/12/27.
 */
public class TempSportRank {
    public String rank;//名次
    public String name;//姓名
    public String count;//总步数或者总运动量
    public  String userid;//id，用于请求头像
    public TempSportRank(String r,String n,String c,String userid){
        this.rank=r;
        this.name=n;
        this.count=c;
        this.userid=userid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
