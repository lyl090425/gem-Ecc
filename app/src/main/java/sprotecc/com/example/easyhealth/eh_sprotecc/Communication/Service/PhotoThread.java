package sprotecc.com.example.easyhealth.eh_sprotecc.Communication.Service;

import android.util.Log;

import com.ruite.gem.action.公开.电子班牌.相册.获取班级相册;
import com.ruite.gem.comm.Rpc;
import com.ruite.gem.modal.班牌基础.PhotoAlbum;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Photo.PhotoView;

/**
 * Created by adminHjq on 2017/1/11.
 */
public class PhotoThread implements Runnable {
    PhotoView photoView;
    public PhotoThread(PhotoView photoView){
        this.photoView=photoView;
    }
    @Override
    public void run() {
        try {
            Rpc rpc=new Rpc(MyApplication.url);
            获取班级相册 action =new 获取班级相册();
            action.setClassId(Long.parseLong(DataDao.getInstance().getSoftInfoByFlag().getClazz()));
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -365);
            Date time = cal.getTime();//
            action.setBeginDate(time);
            action.setEndDate(new Date());
            获取班级相册 re=rpc.call(action);
            List<PhotoAlbum> list= re.getPhotoAlbums();
            Log.i("数据获取-相册","获取相册成功");
            photoView.showPhoto(list);
        } catch (Exception e) {
            Log.i("数据获取-相册","获取相册失败");
            photoView.showPhoto(null);
            e.printStackTrace();
        }

    }
}
