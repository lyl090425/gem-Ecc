package sprotecc.com.example.easyhealth.eh_sprotecc.Standby.Fragemnt.ClassInfo.ClassinfoFragment.Photo;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.utils.L;
import com.ruite.gem.modal.班牌基础.AlbumAttachment;
import com.ruite.gem.modal.班牌基础.PhotoAlbum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseFragment;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempPhoto;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;
import sprotecc.com.example.easyhealth.eh_sprotecc.Tool.BitmapCache;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class PhotoFragment extends BaseFragment<PhotoView, PhotoPresenter<PhotoView>> implements PhotoView {
    private PhotoPresenter photoPresenter;
    private View view;
    private Handler handler = new Handler();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);//日期格式

    private LinearLayout mGallery, mGallery1;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    private List<String> stringList = new ArrayList<String>();
    private Map<String, List<TempPhoto>> stringListMap = new HashMap<>();
    private Drawable defauLt;
    private ImageView back;
    private TextView text;
    private RelativeLayout mainR1, childR2;
    //用于获取网络头像的加载队列
    private RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContext());
    //ImageLoader的参数，暂时为空，后续再开发
    ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
        @Override
        public Bitmap getBitmap(String s) {
            return null;
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.classinfo_photo, container, false);
        photoPresenter = new PhotoPresenterImpl(this);
        initView();
        photoPresenter.startThread();
        return view;
    }

    @Override
    protected PhotoPresenter<PhotoView> createPresenter() {

        return null;
    }

    public void initView() {
        mInflater = LayoutInflater.from(MyApplication.getContext());
        mGallery = (LinearLayout) view.findViewById(R.id.id_gallery);
        mGallery1 = (LinearLayout) view.findViewById(R.id.id_gallery1);
        back = (ImageView) view.findViewById(R.id.photo_back);
        mainR1 = (RelativeLayout) view.findViewById(R.id.photo_r1);
        childR2 = (RelativeLayout) view.findViewById(R.id.photo_r2);
        text = (TextView) view.findViewById(R.id.photo_text);
        defauLt = ContextCompat.getDrawable(view.getContext(), R.drawable.userhead);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainR1.setVisibility(View.VISIBLE);
                childR2.setVisibility(View.GONE);
            }
        });
    }

    public void showPhotoChild(List<TempPhoto> tempPhotoList) {
        Log.i("数据测试-相册", "子相册" + " 包含的相片数量：" + tempPhotoList.size());
        mGallery1.removeAllViews();
        for (int i = 0; i < tempPhotoList.size(); i++) {
            int photoid = Integer.parseInt(tempPhotoList.get(i).getId());
            View view = mInflater.inflate(R.layout.childphoto, mGallery1, false);
            ImageView img = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
            TextView txt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
            txt.setText(tempPhotoList.get(i).getType());
            ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(img, R.drawable.classsport_name1, R.drawable.userhead);
            String url = MyApplication.photourl + ((int) (photoid / 500)) + "/" + photoid;
            imageLoader.get(url, listener);
            mGallery1.addView(view);
        }

    }


    public void showPhoto2(final List<String> stringList, final Map<String, List<TempPhoto>> stringListMap) {
        Log.i("数据-相册", "开始设置一级相册" + stringList.size());
        if (stringList != null && stringListMap != null) {
            if (stringList.size() != 0) {
                for (int i = 0; i < stringList.size(); i++) {
                    Log.i("数据-相册", "开始设置一key" + stringList.get(i));
                    final String key1 = stringList.get(i);
                    List<TempPhoto> tempPhotoList = new ArrayList<TempPhoto>();
                    tempPhotoList = stringListMap.get(stringList.get(i));
                    String id = tempPhotoList.get(0).getId();
                    int photoid = Integer.parseInt(id);
                    View view = mInflater.inflate(R.layout.index_gallery_item, mGallery, false);
                    ImageView img = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
                    final List<TempPhoto> finalTempPhotoList = tempPhotoList;
                    Log.i("数据测试-相册", "名称：" + key1 + " 包含的相片数量：" + finalTempPhotoList.size());
                    img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //设置隐藏的背景的信息
                            showPhotoChild(finalTempPhotoList);
                            text.setText("班级相册：" + key1);
                            mainR1.setVisibility(View.GONE);
                            childR2.setVisibility(View.VISIBLE);
                        }
                    });
                    TextView txt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
                    txt.setText(stringList.get(i));
                    ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
                    ImageLoader.ImageListener listener = ImageLoader.getImageListener(img, R.drawable.classsport_name1, R.drawable.userhead);
                    String url = MyApplication.photourl + ((int) (photoid / 500)) + "/" + photoid;
                    imageLoader.get(url, listener);
                    mGallery.addView(view);
                }
            } else {
                View view = mInflater.inflate(R.layout.index_gallery_item, mGallery, false);
                ImageView img = (ImageView) view.findViewById(R.id.id_index_gallery_item_image);
                TextView txt = (TextView) view.findViewById(R.id.id_index_gallery_item_text);
                img.setImageDrawable(defauLt);
                txt.setText("暂无相册");
                mGallery.addView(view);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stringList = null;
        stringListMap = null;

    }

    @Override
    public void showPhoto(final List<PhotoAlbum> list) {

        if(handler!=null){
        handler.post(new Runnable() {
            @Override
            public void run() {
                stringList = new ArrayList<String>();
                stringListMap = new HashMap<>();
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        List<AlbumAttachment> list1 = list.get(i).getAlbumAttachments();
                        List<TempPhoto> tempPhotoList = new ArrayList<TempPhoto>();
                        for (int j = 0; j < list1.size(); j++) {
                            AlbumAttachment albumAttachment = list1.get(j);
                            long id = albumAttachment.getId();//id
                            Date time = albumAttachment.getUploadTime();//时间
                            String name = albumAttachment.getName();//相片名字
                            String date = sdf.format(time);
                            TempPhoto tempPhoto = new TempPhoto(date, id + "", name);
                            tempPhotoList.add(tempPhoto);
                            Log.i("数据获取", "图片id:" + id + "图片上传时间：" + time);
                        }
                        stringList.add(list.get(i).getName());//类型名字
                        stringListMap.put(list.get(i).getName(), tempPhotoList);
                    }
                    Log.i("数据获取", "相册-设置");
                    mGallery.removeAllViews();
                    showPhoto2(stringList, stringListMap);
                }
            }
        });
        }
    }

}
