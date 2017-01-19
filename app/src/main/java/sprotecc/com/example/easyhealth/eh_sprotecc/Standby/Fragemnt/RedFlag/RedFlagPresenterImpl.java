package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.RedFlag;

import android.os.Handler;

import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BasePresenterImpl;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.GradeRedFlagRankThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service.MyClassRedFlagThread;
import sprotecc.com.example.easyhealth.eh_sprotecc.ThreadPoolExecutor.MyThreadPool;

/**
 * Created by adminHjq on 2016/12/23.
 */
public class RedFlagPresenterImpl extends BasePresenterImpl<RedFlagView> implements  RedFlagPresenter<RedFlagView>{
    private  RedFlagView redFlagView;
    private  RedFlagModelImpl redFlagModel=new RedFlagModelImpl(this);
    private Handler handler;
    public RedFlagPresenterImpl(RedFlagView redFlagView, Handler handler){
         this.redFlagView=redFlagView;
        this.handler=handler;
    }

    @Override
    public void myClassredFlag() {
        MyThreadPool.getInstance().createLongPool().execute(new MyClassRedFlagThread(redFlagView));
    }

    @Override
    public void gradeRedFlagRank() {
        MyThreadPool.getInstance().createLongPool().execute(new GradeRedFlagRankThread(redFlagView));

    }
}
