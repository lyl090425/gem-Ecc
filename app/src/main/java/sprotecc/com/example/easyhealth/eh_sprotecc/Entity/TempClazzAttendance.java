package sprotecc.com.example.easyhealth.eh_sprotecc.Entity;

/**
 * Created by adminHjq on 2016/12/27.
 */
public class TempClazzAttendance {
    private int total;//总人数
    private int arrived;//已到人数
    private int noArrived;//未到人数

    public TempClazzAttendance(int a, int b, int c) {
        this.total = a;
        this.arrived = b;
        this.noArrived = c;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getArrived() {
        return arrived;
    }

    public void setArrived(int arrived) {
        this.arrived = arrived;
    }

    public int getNoArrived() {
        return noArrived;
    }

    public void setNoArrived(int noArrived) {
        this.noArrived = noArrived;
    }
}
