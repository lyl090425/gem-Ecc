package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2016/12/29.
 */
public class TempSportDataHistory {
    //日期
    String time;
    //步数
    String bushu;

    public TempSportDataHistory(String time,String bs){
        this.time=time;
        this.bushu=bs;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBushu() {
        return bushu;
    }

    public void setBushu(String bushu) {
        this.bushu = bushu;
    }
}
