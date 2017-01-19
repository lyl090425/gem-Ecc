package sprotecc.com.example.easyhealth.eh_sprotecc.Base;

/**
 * Created by adminHjq on 2016/12/8.
 */
public interface BasePresenter<T>{
    public void attachView(T view);//绑定View
    public boolean isViewAttached();//判读是否绑定View
    public void detachView();//接触绑定
}
