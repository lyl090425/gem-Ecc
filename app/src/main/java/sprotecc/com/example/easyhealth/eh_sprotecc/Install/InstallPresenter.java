package sprotecc.com.example.easyhealth.eh_sprotecc.Install;

import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenter;

/**
 * Created by adminHjq on 2016/12/26.
 */
public interface InstallPresenter<T> extends BasePresenter<T> {
    public void Grade();
    public void Clazz();
    public void startClazzThread(long id);
}
