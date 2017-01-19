package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2017/1/10.
 */
public class TempClazzInfo {
    private String name;
    private String text;
    public TempClazzInfo(String name,String text){
        this.name=name;
        this.text=text;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
