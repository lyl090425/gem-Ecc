package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.RedFlag;

import com.ruite.gem.modal.班牌基础.DutyUser;

import java.util.List;
import java.util.Map;

/**
 * Created by adminHjq on 2016/12/23.
 */
public interface RedFlagModel {
    //流动红旗表
    public void startClassRedFlagThread();

    public void callbackClassRdeFlag();

    public void storeTempClassRedFlag();

    public void getTempClassRedFlag();

    //流动红旗排名
    public void startGradeRedFlagThread();

    public void callbackGradeRdeFlag();

    public void storeTempGradeRedFlag();

    public void getTempGradeRedFlag();
}
