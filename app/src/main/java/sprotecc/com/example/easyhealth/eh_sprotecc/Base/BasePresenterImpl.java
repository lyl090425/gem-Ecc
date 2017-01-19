package sprotecc.com.example.easyhealth.eh_sprotecc.Base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by adminHjq on 2016/12/8.
 */
public abstract class BasePresenterImpl<T> implements BasePresenter<T> {
    protected Reference<T> mViewRef;

    /**
     * 建立关联
     * @param view
     */
    public void attachView(T view){
        mViewRef=new WeakReference<T>(view);
    }
    public T getView(){
        return mViewRef.get();
    }

    /**
     * 判断是否是否已经建立关联
     * @return
     */
    public boolean isViewAttached(){
        return  mViewRef !=null&&mViewRef.get()!=null;
    }

    /**
     * 接触关联
     */
    public void detachView(){
        if(mViewRef!=null){
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
