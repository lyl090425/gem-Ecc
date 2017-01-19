package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Photo;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.PhotoThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class PhotoPresenterImpl extends BasePresenterImpl<PhotoView> implements PhotoPresenter<PhotoView> {

private PhotoView photoView;
    public PhotoPresenterImpl(PhotoView photoView){
        this.photoView=photoView;
    }

    @Override
    public void startThread() {
        MyThreadPool.getInstance().createLongPool().execute(new PhotoThread(photoView));
    }
}
