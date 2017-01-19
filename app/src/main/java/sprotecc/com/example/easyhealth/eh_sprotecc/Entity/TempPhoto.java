package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2017/1/11.
 */
public class TempPhoto {
    String time;
    String id;
    String type;
    public TempPhoto(){

    }
    public TempPhoto(String time , String id, String type){
        this.id=id;
        this.time=time;
        this.type=type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
