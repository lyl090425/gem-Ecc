package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.RedFlag;

import com.ruite.gem.modal.班牌基础.ClassRedFlag;

import java.util.List;

/**
 * Created by adminHjq on 2016/12/23.
 */
public interface RedFlagView {
    //显示班级流动红旗
    public void showMyClassRedFlag(List<ClassRedFlag> list);

    //显示年级流动红旗排名
    public void showGradeRedFlagRank(List<ClassRedFlag> list);
}
