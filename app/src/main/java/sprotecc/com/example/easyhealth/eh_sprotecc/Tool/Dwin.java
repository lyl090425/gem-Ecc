package sprotecc.com.example.easyhealth.eh_sprotecc.Tool;

import java.io.DataOutputStream;
import java.io.FileInputStream;

import android.os.Build;
import android.util.Log;


/**
 * DWIN
 * <p/>
 * 使用本类前系统必须ROOT
 *
 * @author F
 */
public class Dwin {

    private static Dwin dwin;

    /**
     * @return Dwin
     */
    public static Dwin getInstance() {
        return dwin = dwin == null ? new Dwin() : dwin;
    }

    /**
     * 设置屏幕亮度，使用本方法甚至可以关闭背光，做休眠功能时可以只用本方法
     *
     * @param bright （0<=bright<=255）
     * @return 如果操作成功，则返回true
     */
    public boolean setBrightness(int bright) {
        try {
            String[] command = new String[]{
                    "su",
                    "-c",
                    "echo "
                            + bright
                            + " > /sys/devices/platform/pwm-backlight.0/backlight/pwm-backlight.0/brightness"};
            exec(command);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 隐藏导航
     *
     * @return 如果操作成功则返回ture.
     */
    public boolean hideNavigation() {
        try {
            Build.VERSION_CODES vc = new Build.VERSION_CODES();
            Build.VERSION vr = new Build.VERSION();
            String procId = vr.SDK_INT > vc.ICE_CREAM_SANDWICH ? "42" : "79";
            String[] command = new String[]{
                    "su",
                    "-c",
                    "service call activity " + procId
                            + " s16 com.android.systemui"};
            exec(command);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 显示导航
     *
     * @return 如果操作成功，返回true.
     */
    public boolean showNavigation() {

        try {      Log.i("测试","开始涉足");
            String[] command = new String[]{"am", "startservice", "-n",
                    "com.android.systemui/.SystemUIService"};
            exec(command);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * 动态设置IP，本方法使用前系统必须ROOT，且修改完成断电不保存
     *
     * @param IP
     * @return 如果设置成功，返回true
     */
    public boolean setIp(String IP) {
        try {
            String[] command = new String[]{"su", "-c",
                    "ifconfig eth0 " + IP + " netmask 255.255.255.0 up"};
            exec(command);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取屏幕唯一标识ChipID
     *
     * @return chipid
     */


    /**
     * 关机
     */
    public void shutDown() {
        execCommand("input keyevent 26");
    }

    /**
     * 重启
     */
    public void reboot() {
        execCommand("reboot");
    }

    /**
     * 返回 执行返回键按下操作
     */
    public void back() {
        execCommand("input keyevent 4");
    }

    /**
     * Home 执行Home键按下操作
     */
    public void home() {
        execCommand("input keyevent 3");
    }

    /**
     * execute command
     *
     * @param command
     * @throws Exception
     */
    private void exec(String[] command) throws Exception {
        Process proc;
        proc = Runtime.getRuntime().exec(command);
        proc.waitFor();
    }

    /**
     * execute command, the phone must be root,it can exctue the adb command
     *
     * @param command
     */
    private void execCommand(String command) {
        Process process = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec("su");//
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(command + "\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
