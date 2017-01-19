package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2017/1/9.
 */
public class TempMySport {
    String time;//时间段
    String bushu;

    public String getBushu() {
        return bushu;
    }

    public void setBushu(String bushu) {
        this.bushu = bushu;
    }

    public TempMySport(String time, String bushu){
      this.time=time;
      this.bushu=bushu;

  }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
