package sprotecc.com.example.easyhealth.eh_sprotecc.Install;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruite.gem.modal.组织信息.Clazz;
import com.ruite.gem.modal.组织信息.Grade;

import java.util.List;

import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.ClazzAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Adapter.GradeAdapter;
import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Base.BaseActivity;
import sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase.DataDao;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.SoftInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.R;

public class InstallActivity extends BaseActivity<InstallView, InstallPresenter<InstallView>> implements InstallView {
    private InstallPresenter installPresenter;
    private ListView grade, clazz;
    private TextView gradeId, clazzId, gradeName, ClazzName;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_install);
        ViewInitialization();

    }

    @Override
    protected InstallPresenter<InstallView> createPresenter() {
        installPresenter = new InstallPresenterImpl(this);
        return installPresenter;
    }

    @Override
    protected MyApplication GetMyApplication() {
        return null;
    }

    @Override
    protected void ViewInitialization() {
        grade = (ListView) findViewById(R.id.install_grade);
        clazz = (ListView) findViewById(R.id.install_clazz);
        gradeId = (TextView) findViewById(R.id.install_grade_text_id);
        clazzId = (TextView) findViewById(R.id.install_clazz_text_id);
        gradeName = (TextView) findViewById(R.id.install_grade_text_name);
        ClazzName = (TextView) findViewById(R.id.install_clazz_text_name);

    }

    @Override
    public void setGradeListView(final List<Grade> listGrade) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                GradeAdapter gradeAdapter = new GradeAdapter(listGrade);
                grade.setDivider(new ColorDrawable(Color.rgb(187, 255, 255)));
                grade.setDividerHeight(1);
                grade.setAdapter(gradeAdapter);
                grade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ListView listView = (ListView) parent;
                        Grade grade = (Grade) listView.getItemAtPosition(position);
                        gradeId.setText(grade.getId()+"");
                        gradeName.setText(grade.getName());
                        clazzId.setText("");
                        ClazzName.setText("");

                        installPresenter.startClazzThread(grade.getId());
                    }
                });
            }
        });


    }

    @Override
    public void setClazzListView(final List<Clazz> listClazz) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                ClazzAdapter clazzAdapter = new ClazzAdapter(listClazz);
                clazz.setDivider(new ColorDrawable(Color.rgb(187, 255, 255)));
                clazz.setDividerHeight(1);
                clazz.setAdapter(clazzAdapter);
                clazz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ListView listView = (ListView) parent;
                        Clazz clazz = (Clazz) listView.getItemAtPosition(position);
                        clazzId.setText(clazz.getId()+"");
                        ClazzName.setText(clazz.getName());
                    }
                });
            }
        });

    }
    public void yesOrNo(View view){
        if(view.getId()==R.id.install_btn1){
            //存入数据库
            String schoolid=DataDao.getInstance().getSoftInfoByFlag().getSchoolId();
            String gradeid = gradeId.getText().toString();
            String clazzid=clazzId.getText().toString();
            SoftInfo softInfo=new SoftInfo();
            softInfo.setSchoolId(schoolid);
            softInfo.setGrade(gradeid);
            softInfo.setClazz(clazzid);

            softInfo.setVersion(getResources().getString(R.string.soft_versionId));
            if(softInfo.getClazz().equals("")||softInfo.getSchoolId().equals("")||softInfo.getGrade().equals("")) {
                Toast.makeText(InstallActivity.this, "年级或班级不能为空", Toast.LENGTH_LONG).show();
            }else {
                DataDao.getInstance().storeSoftInfo(softInfo);
                Toast.makeText(InstallActivity.this, "更改配置成功，单击取消退出该页面,更改后的配置将在2分钟后生效", Toast.LENGTH_LONG).show();

            }
        }else if(view.getId()==R.id.install_btn2){
          //返回主页面
            InstallActivity.this.finish();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InstallActivity.this.finish();
    }
}
