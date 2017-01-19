package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2017/1/4.
 */
public class TempIngInfo {
    String name;//姓名
    String tContent;//老师简介
    String sContent;//课程简介
    String userid;//老师id

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public String getsContent() {
        return sContent;
    }

    public void setsContent(String sContent) {
        this.sContent = sContent;
    }

    public TempIngInfo(String name, String t, String s,String userid){
        this.name=name;
        this.tContent=t;
        this.sContent=s;
        this.userid=userid;
    }

}
