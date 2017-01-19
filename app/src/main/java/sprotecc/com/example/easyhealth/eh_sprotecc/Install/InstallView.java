package sprotecc.com.example.easyhealth.eh_sprotecc.Install;

import com.ruite.gem.modal.组织信息.Clazz;
import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

/**
 * Created by adminHjq on 2016/12/26.
 */
public interface InstallView {
    public void setGradeListView(List<Grade> listGrade);
    public void setClazzListView(List<Clazz> listClazz);

}
