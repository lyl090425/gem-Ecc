package sprotecc.com.example.easyhealth.eh_sprotecc.Data.SqliteDatabase;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ruite.gem.modal.人员信息.ClassStudent;
import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.modal.班牌基础.ClassSchedule;
import com.ruite.gem.modal.班牌基础.StudentEvaluate;
import com.ruite.gem.modal.班牌基础.StudentHonour;
import com.ruite.gem.modal.组织信息.Clazz;
import com.ruite.gem.modal.运动健康.运动.Sport;
import com.ruite.gem.modal.运动健康.运动.SportRank;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import sprotecc.com.example.easyhealth.eh_sprotecc.Application.MyApplication;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzAttendance;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.Hardware;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.SoftInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempClazzInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempConductPoint;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempHonour;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempIngInfo;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportDataHistory;
import sprotecc.com.example.easyhealth.eh_sprotecc.Entity.TempSportRank;

/**
 * Created by adminHjq on 2016/12/13.
 */
public class DataDao {
    private static Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);//日期格式
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private SQLiteDatabase db;
    private static DataDao mInstance;
    private Date date;

    private DataDao() {
        DataDao.context = MyApplication.getContext();
        DbHelper helper = new DbHelper(context);
        db = helper.getWritableDatabase();
        date = new Date();
    }

    public synchronized static DataDao getInstance() {
        if (mInstance == null) {
            synchronized (DataDao.class) {
                if (mInstance == null) {
                    mInstance = new DataDao();
                }
            }

        }
        return mInstance;
    }


    /**
     * 存储软件信息
     *
     * @return
     */
    public void storeSoftInfo(SoftInfo softInfo) {
        if (softInfo != null) {
            //先清空列表(本表只储存一条)
            db.delete(DbHelper.TABLE_SOFTINFO, null, null);
            String version = softInfo.getVersion();
            //软件所在班级
            String clazz = softInfo.getClazz();
            //软件所在年级
            String grade = softInfo.getGrade();
            //软件所在学校编号
            String schoolId = softInfo.getSchoolId();

            ContentValues values = new ContentValues();
            values.putNull(DbHelper.SOFTINFO_ID);//自动增长
            values.put(DbHelper.SOFTINFO_VERSION, version);//软件版本号.String
            values.put(DbHelper.SOFTINFO_CLAZZ, clazz);//软件所在班级.int
            values.put(DbHelper.SOFTINFO_GRADE, grade);//软件所在年级.int
            values.put(DbHelper.SOFTINFO_SCHOOLID, schoolId);//软件所在班级.string
            values.put(DbHelper.SOFTINFO_FLAG, 1);//只要是插入都注入为1，代表已经初始化
            db.insert(DbHelper.TABLE_SOFTINFO, null, values);
            Log.i("软件信息更新","更改了班级配置-版本号："+version+"学校："+schoolId+"年级："+grade+"班级："+clazz);
        } else {

        }


    }

    /**
     * 获取是否已经插入有配置软件信息
     *db
     * @return
     */
    public SoftInfo getSoftInfoByFlag() {
        String fl = 1 + "";
        //查询标记，是否已经为true
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_SOFTINFO + " where flag=?;", new String[]{fl});
        //获取调试，如果大于0则表明有数据
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            {
                //0-内部ID 1-软件版本号String 2:班级号int 3:年级号int 4：学校ID string
                String version = cursor.getString(1);
                String clazz = cursor.getString(2);
                String grade = cursor.getString(3);
                String schoolCode = cursor.getString(4);
                SoftInfo softinfo = new SoftInfo();
                softinfo.setVersion(version);
                softinfo.setClazz(clazz + "");
                softinfo.setGrade(grade + "");
                softinfo.setSchoolId(schoolCode);
                Log.i("数据测试","本地软件信息：学校ID"+schoolCode+",年级ID:"+grade+",班级ID："+clazz);
                Log.i("测试", "查询已有软件基本信息,不在进行初始化软件信息");
                cursor.close();
                return softinfo;
            }
        } else {
            return null;
        }
    }

    /**
     * 存入硬件信息
     *
     * @param hardware
     */
    public void storeHardware(Hardware hardware) {
        if (hardware != null) {
            String address = hardware.getSchoolAddress();
            String schoolCode = hardware.getSchoolId();
            String schoolName = hardware.getSchoolName();

            ContentValues values = new ContentValues();
            values.putNull(DbHelper.HARDWAREINFO_ID);//自动增长
            values.put(DbHelper.HARDWAREINFO_ADDRESS, address);//软件版本号.String
            values.put(DbHelper.HARDWAREINFO_SCHOOLID, schoolCode);//软件所在年级.int
            values.put(DbHelper.HARDWAREINFO_SCHOOLNAME, schoolName);//软件所在班级.int
            values.put(DbHelper.HARDWAREINFO_FLAG, 1);//只要是插入都注入为1，代表已经初始化
            db.insert(DbHelper.TABLE_HARdWAREINFO, null, values);
        } else {

        }

    }

    /**
     * 获取硬件信息
     *
     * @return
     */
    public Hardware getHardwareInfoByflag() {
        String flag = 1 + "";
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_HARdWAREINFO + " where flag=?;", new String[]{flag});
        //获取调试，如果大于0则表明有数据
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            {
                //0-内部ID 1-地址 2:学校ID 3:名字
                String address = cursor.getString(1);
                String schoolCode = cursor.getString(2);
                String schoolName = cursor.getString(3);
                Hardware hardware = new Hardware();
                hardware.setSchoolAddress(address);
                hardware.setSchoolId(schoolCode);
                hardware.setSchoolName(schoolName);
                Log.i("测试", "查询已有硬件基本信息,不在进行初始化硬件信息");
                cursor.close();
                return hardware;
            }
        } else {
            return null;
        }
    }

    /**
     * 存储UserInfo
     *
     * @param list
     */
    public void storeUserInfo(List<UserInfo> list) {
        if (list != null) {
            if(list.size()!=0){
            int i = 1;
            for (UserInfo userInfo : list) {
                String name = userInfo.getName();//姓名
                double weight = userInfo.getWeight();//体重
                int height = userInfo.getHeight();//身高
                long userId = userInfo.getId();//服务器用户唯一ID
                Log.i("用户信息","存储数据库的ID:"+userId);
                String userCode = userInfo.getCode();//用户识别码
                ContentValues values = new ContentValues();
                values.putNull(DbHelper.USERINFO_ID);//自动增长
                values.put(DbHelper.USERINFO_USERID, userId+"");//用户ID-int
                values.put(DbHelper.USERINFO_USERCODE, userCode);//用户身份证号码-string
                values.put(DbHelper.USERINFO_USERNAME, name);//姓名-string
                values.put(DbHelper.USERINFO_USERHEIGHT, height + "");//身高-string
                values.put(DbHelper.USERINFO_USERWEIGHT, weight + "");//体重-string
                //先删除
                db.delete(DbHelper.TABLE_USERINFO, " usercode=? ", new String[]{userCode});
                //后插入
                db.insert(DbHelper.TABLE_USERINFO, null, values);
                Log.i("数据库", "插入用户信息,第" + i + "条");
                i++;
            }
        }
        }else{
            Log.i("数据库", "没用户信息数据可插入");
        }

    }

    /**
     * 更具UserCode查询用户信息
     *
     * @param userCode
     * @return
     */
    public UserInfo getUserInfoByCode(String userCode) {
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_USERINFO + " where usercode=?;", new String[]{userCode});
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            String userid = cursor.getString(1);
            String username = cursor.getString(3);
            String userheight = cursor.getString(4);
            String userweight = cursor.getString(5);
            UserInfo userinfo = new UserInfo();
            userinfo.setId(Long.parseLong(userid));
            userinfo.setCode(userCode);
            userinfo.setName(username);
            userinfo.setHeight(Integer.parseInt(userheight));
            userinfo.setWeight(Double.parseDouble(userweight));
            Log.i("数据库操作", "查询存在该用户-用户名："+username+"  用户id:"+userid);
            cursor.close();
            return userinfo;
        } else {
            Log.i("数据库操作", "查询不存该用户");
            cursor.close();
            return null;

        }


    }

    /**
     * 存入临时考勤信息
     */
    public void storeTempAttendance(TempClazzAttendance clazzAttendance) {
        if (clazzAttendance != null) {
            //先清空列表
            db.delete(DbHelper.TABLE_TEMPORARY_ATTENDANCE, null, null);
            //应到人数
            int total = clazzAttendance.getTotal();
            //实到人数
            int ar = clazzAttendance.getArrived();
            //未到人数
            int noar = clazzAttendance.getNoArrived();
            Log.i("数据库：考勤",total+":"+ar+":"+noar);
            ContentValues values = new ContentValues();
            values.putNull(DbHelper.TEMPORARY_ATTENDANCE_ID);//自动增长
            values.put(DbHelper.TEMPORARY_ATTENDANCE_TOTAL, total + "");//
            values.put(DbHelper.TEMPORARY_ATTENDANCE_AR, ar + "");//
            values.put(DbHelper.TEMPORARY_ATTENDANCE_NO, noar + "");//
            db.insert(DbHelper.TABLE_TEMPORARY_ATTENDANCE, null, values);
            Log.i("数据库", "存储考勤信息成功，存储时间为:" + new Date() + "");
        } else {
            Log.i("数据库", "存储考勤信信息失败");
        }

    }

    /**
     * 取出临时存储的考勤消息
     *
     * @return
     */
    public TempClazzAttendance getTempClazzAttendance() {
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_TEMPORARY_ATTENDANCE, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            {
                String t, a, n;
                t = cursor.getString(1);
                a = cursor.getString(2);
                n = cursor.getString(3);
                int total, arrived, noArrived;
                total = Integer.parseInt(t);
                arrived = Integer.parseInt(a);
                noArrived = Integer.parseInt(n);
                TempClazzAttendance clazzattendance = new TempClazzAttendance(total, arrived, noArrived);
                cursor.close();
                return clazzattendance;
            }

        } else {
            return null;
        }
    }

    /**
     * 存入临时的运动步数排名信息
     */
    public void storeTempSportRank(List<SportRank> list) {

        if (list != null) {
            int i = 1;
            //先清空列表
            db.delete(DbHelper.TABLE_TEMPORARY_SPORTRANK, null, null);
            for (SportRank s : list) {
                int rank=0;
                if( s.getRank()==null){
                    rank = 0;
                }else {
                 rank = s.getRank();//名次
                     }
                String name = s.getUser().getName();//姓名
                int step = s.getSport().getStep();//运动步数
                long userid = s.getUser().getId();//用户ID
                ContentValues values = new ContentValues();
                values.putNull(DbHelper.TEMPORARY_SPORTRANK_ID);//自动增长
                values.put(DbHelper.TEMPORARY_SPORTRANK_RANK, rank + "");
                values.put(DbHelper.TEMPORARY_SPORTRANK_NAME, name + "");
                values.put(DbHelper.TEMPORARY_SPORTRANK_COUNT, step + "");
                values.put(DbHelper.TEMPORARY_SPORTRANK_USERID, userid + "");
                db.insert(DbHelper.TABLE_TEMPORARY_SPORTRANK, null, values);
                Log.i("数据库", "存储运动排名信息成功：" + i + "条：" + step + "步数  " + "-ID:" + userid + "");
                i++;
            }
            Log.i("数据库", "存储运动排名信息成功，存储时间为:" + new Date() + "");

        }

    }

    /**
     * 取出临时存储的运动排名
     */
    public List<TempSportRank> getTempSportRank() {
        List<TempSportRank> list1 = new ArrayList<TempSportRank>();
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_TEMPORARY_SPORTRANK, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String r = cursor.getString(1);
            String n = cursor.getString(2);
            String s = cursor.getString(3);
            String i=cursor.getString(4);
            TempSportRank tempSportRank = new TempSportRank(r, n, s,i);
            list1.add(tempSportRank);
            cursor.moveToNext();
        }
        cursor.close();
        return list1;
    }

    /**
     * 临时操行分的存入
     *
     * @param list
     */
    public void storeTempConductPiont(List<ClassStudent> list) {
        if (list != null) {
            if(list.size()!=0){
            //先清空表
            db.delete(DbHelper.TABLE_TEMPORARY_CONDUCTPOINT, null, null);
            int i = 1;
            for (ClassStudent s : list) {
                String name = s.getStudent().getUser().getName();//名字
                String score = null;
                score= s.getEvalusteScore()+"";

                ContentValues values = new ContentValues();
                values.putNull(DbHelper.TEMPORARY_CONDUCTPOINT_ID);//自动增长
                values.put(DbHelper.TEMPORARY_CONDUCTPOINT_NAME, name);//名字
                values.put(DbHelper.TEMPORARY_CONDUCTPOINT_SCORE, score);//操行分
                db.insert(DbHelper.TABLE_TEMPORARY_CONDUCTPOINT, null, values);
                Log.i("数据库", "存储临时操行分表:第" + i + "条");
                i++;

            }
        }
        }

    }

    /**
     * 临时操行分成绩的取出
     *
     * @return
     */
    public List<TempConductPoint> getTempStudentEvaluate() {
        List<TempConductPoint> list1 = new ArrayList<TempConductPoint>();
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_TEMPORARY_CONDUCTPOINT, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String r = cursor.getString(1);
            String n = cursor.getString(2);
            TempConductPoint tempConductPoint = new TempConductPoint(r, n);
            list1.add(tempConductPoint);
            cursor.moveToNext();
        }
        cursor.close();
        return list1;
    }


    /**
     * 存储临时班级运动历史
     *
     * @param list
     */
    public void storeClassSportDataHistory(List<Sport> list) {
        if (list != null&&!list.isEmpty()) {
            //先清空列表
            db.delete(DbHelper.TABLE_TEMPORARY_CLASSHISTORY, null, null);
            for (Sport s : list) {
                int step = s.getStep();//步数
                Date date = s.getDate();//日期
                ContentValues values = new ContentValues();
                values.putNull(DbHelper.TEMPORARY_CLASSHISTORY_ID);//自动增长
                values.put(DbHelper.TEMPORARY_CLASSHISTORY_DATE, sdf.format(date));
                values.put(DbHelper.TEMPORARY_CLASSHISTORY_STEP, step);
                db.insert(DbHelper.TABLE_TEMPORARY_CLASSHISTORY, null, values);
            }
            Log.i("数据库", "存储班级运动历史，存储时间为:" + new Date() + "");
        }
        Log.i("数据库", "没有获取到班级历史数据" + new Date() + "");
    }

    /**
     * 获取一周的班级运动历史
     *
     * @return
     */
    public List<TempSportDataHistory> getTempWeekClassSportDataHistory() {
        List<TempSportDataHistory> list = new ArrayList<TempSportDataHistory>();
        //七天前
        String s = sdf.format(new Date(date.getTime() - 7 * 24 * 60 * 60 * 1000));
        String sql = "select *from " + DbHelper.TABLE_TEMPORARY_CLASSHISTORY + " where date>'" + s + "' order by date ASC";
//        String sql = "select * from t_sport where date>'" + s + "' and user_id=" + userID + " order by date ASC";
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Log.i("数据库", "查询班级周历史数据一共有：" + count + "条运动数据");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String date = cursor.getString(1);
            int step = cursor.getInt(2);
            TempSportDataHistory tempSportDataHistory = new TempSportDataHistory(date, step + "");
            list.add(tempSportDataHistory);
            cursor.moveToNext();
        }
        cursor.close();
        return list;

    }

    /**
     * 获取一月的班级运动历史
     *
     * @return
     */
    public List<TempSportDataHistory> getTempMonthClassSportDataHistory() {
        List<TempSportDataHistory> list = new ArrayList<TempSportDataHistory>();
        //30天前
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        Date time = cal.getTime();//
        String s = sdf.format(time);
        String sql = "select *from " + DbHelper.TABLE_TEMPORARY_CLASSHISTORY + " where date>'" + s + "' order by date ASC";
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Log.i("数据库", "查询班级月历史数据一共有：" + count + "条运动数据");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String date = cursor.getString(1);
            int step = cursor.getInt(2);
            TempSportDataHistory tempSportDataHistory = new TempSportDataHistory(date, step + "");
            list.add(tempSportDataHistory);
            cursor.moveToNext();
        }
        cursor.close();
        return list;

    }

    /**
     * 获取一年的班级运动历史
     *
     * @return
     */
    public List<TempSportDataHistory> getTempYearClassSportDataHistory() {
        List<TempSportDataHistory> list = new ArrayList<>();
        for (int i = 12; i > 0; i--) {
            String sql = "select avg(count) AS avgstep from " + DbHelper.TABLE_TEMPORARY_CLASSHISTORY + " where date>date('now','start of month','" + (-(i - 1)) + " month') and date<date('now','start of month','" + (-(i - 2)) + " month')";
            //从日期最前排列到当前月
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                int avgstep = cursor.getInt(cursor.getColumnIndex("avgstep"));
                String date = 12 - i + 1 + "";//描述为第几个月
                TempSportDataHistory tempSportDataHistory = new TempSportDataHistory("第"+date+"月", avgstep + "");
                list.add(tempSportDataHistory);
                System.out.println("第" + (12 - i) + "个运动数据为：" + avgstep);
            }
            cursor.close();
        }

        return list;
    }

    /**
     * 存储我的临时运动历史
     *
     * @param list
     */
    public void storeMySportDataHistory(List<Sport> list) {
        if (list != null) {
            //先清空列表
            Log.i("数据库", "存储我的运动历史，存储时间为数据长度:" + list.size()+ "");
            db.delete(DbHelper.TABLE_TEMPORARY_MYHISTORY, null, null);
            for (Sport s : list) {
                int step = s.getStep();//步数
                Date date = s.getDate();//日期
                ContentValues values = new ContentValues();
                values.putNull(DbHelper.TEMPORARY_MYHISTORY_ID);//自动增长
                values.put(DbHelper.TEMPORARY_MYHISTORY_DATE, sdf.format(date));
                values.put(DbHelper.TEMPORARY_MYHISTORY_STEP, step);
                db.insert(DbHelper.TABLE_TEMPORARY_MYHISTORY, null, values);
            }
            Log.i("数据库", "存储我的运动历史，存储时间为:" + new Date() + "");
        }
        Log.i("数据库", "返回的我的运动数据为空");

    }

    /**
     * 获取一周的我的运动历史
     *
     * @return
     */
    public List<TempSportDataHistory> getTempWeekMySprotdataHistory() {
        List<TempSportDataHistory> list = new ArrayList<>();
        //七天前
        String s = sdf.format(new Date(date.getTime() - 7 * 24 * 60 * 60 * 1000));
        String sql = "select *from " + DbHelper.TABLE_TEMPORARY_MYHISTORY + " where date>'" + s + "' order by date ASC";
//        String sql = "select * from t_sport where date>'" + s + "' and user_id=" + userID + " order by date ASC";
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Log.i("数据库", "查询个人周历史数据一共有：" + count + "条运动数据");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String date = cursor.getString(1);//日期
            int step = cursor.getInt(2);//步数
            TempSportDataHistory tempSportDataHistory = new TempSportDataHistory(date, step + "");
            list.add(tempSportDataHistory);
            cursor.moveToNext();
        }
        return list;
    }

    /**
     * 获取一月的我的运动历史
     *
     * @return
     */
    public List<TempSportDataHistory> getTempMonthMySprotdataHistory() {
        List<TempSportDataHistory> list = new ArrayList<TempSportDataHistory>();
        //30天前
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        Date time = cal.getTime();//
        String s = sdf.format(time);
        String sql = "select *from " + DbHelper.TABLE_TEMPORARY_MYHISTORY + " where date>'" + s + "' order by date ASC";
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        Log.i("数据库", "查询我的月历史数据一共有：" + count + "条运动数据");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String date = cursor.getString(1);
            int step = cursor.getInt(2);
            TempSportDataHistory tempSportDataHistory = new TempSportDataHistory(date, step + "");
            list.add(tempSportDataHistory);
            cursor.moveToNext();
        }
        return list;
    }

    /**
     * 获取一年的我的运动历史
     *
     * @return
     */
    public List<TempSportDataHistory> getTempYearMySprotdataHistory() {
        List<TempSportDataHistory> list = new ArrayList<>();
        for (int i = 12; i > 0; i--) {
            String sql = "select avg(count) AS avgstep from " + DbHelper.TABLE_TEMPORARY_MYHISTORY + " where date>date('now','start of month','" + (-(i - 1)) + " month') and date<date('now','start of month','" + (-(i - 2)) + " month')";
            //从日期最前排列到当前月
            Cursor cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                int avgstep = cursor.getInt(cursor.getColumnIndex("avgstep"));
                String date =12 - i + 1 + "";//描述为第几个月
                TempSportDataHistory tempSportDataHistory = new TempSportDataHistory("第"+date+"月", avgstep + "");
                list.add(tempSportDataHistory);
                System.out.println("第" + (12 - i) + "个运动数据为：" + avgstep);
            }
        }
        return list;
    }

    /**
     * 存储临时荣誉数据
     *
     * @param list
     */
    public void storeMyHonour(List<StudentHonour> list) {
           if (list != null) {
            if(list.size()!=0){
            //先清空列表
                db.delete(DbHelper.TABLE_TEMPORARY_MYHONOUR, null, null);
            for (StudentHonour s : list) {
                String name = s.getType().getName();//荣誉名字
                String typeid = s.getTypeId() + "";//荣誉ID
                String count = s.getCount()+"";//荣誉数量
                Log.i("荣誉数量",count);
                ContentValues values = new ContentValues();
                values.putNull(DbHelper.TEMPORARY_MYHONOUR_ID);//自动增长
                values.put(DbHelper.TEMPORARY_MYHONOUR_NAME, name);
                values.put(DbHelper.TEMPORARY_MYHONOUR_TYPEID, typeid);
                values.put(DbHelper.TEMPORARY_MYHONOUR_COUNT, count);
                db.insert(DbHelper.TABLE_TEMPORARY_MYHONOUR, null, values);
            }
            Log.i("数据库", "存储我的荣誉，存储时间为:" + new Date() + "");
        }
        }else {
        Log.i("数据库", "返回的我的荣誉数据为空");
        }

    }

    /**
     * 获取临时存储的荣誉信息
     *
     * @return
     */
    public List<TempHonour> getMyHonnor() {
        List<TempHonour> list1 = new ArrayList<TempHonour>();
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_TEMPORARY_MYHONOUR, null);
        cursor.moveToFirst();
        Log.i("数据库", "查询我的荣誉为" + cursor.getCount() + "条");
        while (!cursor.isAfterLast()) {
            String r = cursor.getString(1);
            String n = cursor.getString(2);
            String c = cursor.getString(3);
            Log.i("数据库-荣誉",cursor.getString(3));
            TempHonour tempHonour = new TempHonour(r, n,c);
            list1.add(tempHonour);
            cursor.moveToNext();
        }
        cursor.close();
        return list1;

    }

    /**
     * 存入临时活动度
     *
     * @param list
     */
    public void storeTempActivityRank(List<SportRank> list) {

        if (list != null) {
            if(list.size()!=0) {
                //先清空列表
                db.delete(DbHelper.TABLE_TEMPORARY_ACTIVITYRANK, null, null);
                for (SportRank s : list) {
                    int rank=0;
                    if(s.getRank()==null){
                        rank=0;
                    }else {
                         rank = s.getRank();//名次
                    }

                    String name = s.getUser().getName();//姓名
                    int step = s.getSport().getActivity();//活动度
                    long userid = s.getUser().getId();//学生ID
                    ContentValues values = new ContentValues();
                    values.putNull(DbHelper.TEMPORARY_ACTIVITYRANK_ID);//自动增长
                    values.put(DbHelper.TEMPORARY_ACTIVITYRANK_RANK, rank + "");
                    values.put(DbHelper.TEMPORARY_ACTIVITYRANK_NAME, name + "");
                    values.put(DbHelper.TEMPORARY_ACTIVITYRANK_COUNT, step + "");
                    values.put(DbHelper.TEMPORARY_ACTIVITYRANK_USERID, userid + "");
                    db.insert(DbHelper.TABLE_TEMPORARY_ACTIVITYRANK, null, values);

                }
                Log.i("数据库", "存储活动度排名信息成功，存储时间为:" + new Date() + "");
            }
        }

    }

    /**
     * 取出临时活动度排名
     *
     * @return
     */
    public List<TempSportRank> getTempActivityRank() {
        List<TempSportRank> list1 = new ArrayList<TempSportRank>();
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_TEMPORARY_ACTIVITYRANK, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String r = cursor.getString(1);
            String n = cursor.getString(2);
            String s = cursor.getString(3);
            String l = cursor.getString(4);
            TempSportRank tempSportRank = new TempSportRank(r, n, s,l);
            list1.add(tempSportRank);
            cursor.moveToNext();
        }
        cursor.close();
        return list1;
    }

    /**
     * 存入临时课中信息
     *
     * @param list
     */
    public void storeTempIng(ClassSchedule list) {
        db.delete(DbHelper.TABLE_TEMPORARY_ING, null, null);
        if (list != null) {
            //先清空列表

            String tcontent="0";
            String name="未设置";
            String scontent ="0";
            long id=0;
           if(list.getTeacher()!=null){
               if(list.getTeacher().getIntroduction()!=null){
                   if(list.getTeacher().getIntroduction().getContent()!=null){
                        tcontent = list.getTeacher().getIntroductionId()+"";
                   }
               }
           }
            if(list.getTeacher()!=null){
                if(list.getTeacher().getUser()!=null){
                    if(list.getTeacher().getUser().getName()!=null){
                         name = list.getTeacher().getUser().getName();//老师名字
                    }
                }
            }
            if(list.getCourse()!=null){
                if(list.getCourse().getIntroduction()!=null){
                    if(list.getCourse().getIntroduction().getContent()!=null){
                      scontent = list.getCourse().getIntroductionId()+"";
                    }
                }
            }
            if(list.getTeacher()!=null){
                    id=  list.getTeacher().getId();//老师ID
            }



            ContentValues values = new ContentValues();
            values.putNull(DbHelper.TEMPORARY_ING_ID);//自动增长
            values.put(DbHelper.TEMPORARY_ING_NAME, name + "");
            values.put(DbHelper.TEMPORARY_ING_TCONTENT, tcontent + "");
            values.put(DbHelper.TEMPORARY_ING_SCONTENT, scontent + "");
            values.put(DbHelper.TEMPORARY_ING_TEACHERID, id + "");
            db.insert(DbHelper.TABLE_TEMPORARY_ING, null, values);
            Log.i("数据库", "存储课中信息成功，存储内容为:课程简介：" + scontent +"；老师简介："+tcontent+";老师名字："+name);
        }else {
        Log.i("数据库", "没有课中信息");
        }
    }

    /**
     * 取出临时存储的课中信息
     * @param
     */
    public TempIngInfo getTempIng( ) {
        Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_TEMPORARY_ING, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String r = cursor.getString(1);//姓名
            String n = cursor.getString(2);//老师简介
            String s = cursor.getString(3);//班级简介
            String sl=cursor.getString(4);//老师ID
            TempIngInfo tempIngInfo = new TempIngInfo(r, n, s,sl);
            Log.i("数据库", "提取课中信息成功，存储内容为:课程简介：" + s +"；老师简介："+n+";老师名字："+r);
            cursor.moveToNext();
            cursor.close();
            Log.i("数据库","提取课中信息成功" );
            return tempIngInfo;
        }
        return null;
    }

    /**
     * 存储临时班级信息
     * @param clazz
     */
    public void storeTempClazzInfo(Clazz clazz){
        if (clazz != null) {
            int i = 1;
            //先清空列表
            db.delete(DbHelper.TABLE_TEMPORARY_CLAZZINFO, null, null);

                String name = clazz.getName();
            String content="";
            if(clazz.getDescription()==null){
                content="暂无班级格言";
            }else {
               content=clazz.getDescription();
            }

                ContentValues values = new ContentValues();
                values.putNull(DbHelper.TTEMPORARY_CLAZZINFO_ID);//自动增长
                values.put(DbHelper.TTEMPORARY_CLAZZINFO_NAME, name + "");
                values.put(DbHelper.TTEMPORARY_CLAZZINFO_TEXT, content + "");
                db.insert(DbHelper.TABLE_TEMPORARY_CLAZZINFO, null, values);
            Log.i("数据库", "存储班级信息成功:" + new Date() + "");

        }else {Log.i("数据库", "存储班级信息失败" );}
    }

    /**
     * 获取临时班级信息
     * @return
     */
 public TempClazzInfo getTempClazzInfo(){
     Cursor cursor = db.rawQuery("select * from " + DbHelper.TABLE_TEMPORARY_CLAZZINFO, null);
     cursor.moveToFirst();
     while (!cursor.isAfterLast()) {
         String r = cursor.getString(1);//姓名
         String n = cursor.getString(2);//班级格言
         TempClazzInfo clazzInfo = new TempClazzInfo(r, n);
         cursor.moveToNext();
         cursor.close();
         Log.i("数据库","提取班级信息成功" );
         return clazzInfo;
     }
     return null;
 }

}
