package sprotecc.com.example.easyhealth.eh_sprotecc.Tool;

/**
 * Created by adminHjq on 2017/1/3.
 */
public class SportDataUtil {

    public static double getsportlength(int bs, double height) {
        double s1 = height * 0.43 * bs;
        double s2 = height * 0.46 * bs;
        double s3 = height * 0.453 * bs;
        double length = (s1 + s2 + s3) / 3;
        return length / 100;
    }

    //获取用户运动时间（min）(一秒2步，感觉不是很科学，无法分辨是跑步还是走路，暂时这样计算)
    public static double getsporttime(int bs) {

        double time = (double) bs / 2 / 60;
        return time;

    }

    //获取用户的运动速度 (m/min)
    public static double getsportspeed(double length, double time) {
        if (time == 0) {
            return 0;
        } else {
            return length / time;
        }

    }

    //获取用户卡路里（kcal）1千卡=4186焦耳(因为手环只能提供运动步数，不能提供运动方式及其他，所以采用计算慢跑，散步，轻松骑车的模式来计算)
    public static double getsporthot(double height, double weight, double time) {
        double hot = 0;
        double etr1 = 4.801;//低运动量,每消耗一升的氧气产生的能量
        double etr2 = 4.924;//中等运动，每消耗一升的氧气产生的能量
        double etr3 = 5.047;//长时间运动，每消耗一升的氧气产生的能量
        double o21 = 4.5 * 3.5 * weight * time;//耗氧量1（与体重和运动时间有关）
        double o22 = 5 * 3.5 * weight * time;//耗氧量2（与体重和运动时间有关）
        double o23 = 5.5 * 3.5 * weight * time;//耗氧量3（与体重和运动时间有关）
        if (time == 0) {
            hot = 0;
        } else if (0 < time && time < 30) {//小于3600步数
            hot = etr1 * o21;
        } else if (time >= 30 && time < 60) {
            hot = etr2 * o22;
        } else if (time >= 60) {
            hot = etr3 * o23;
        }

        return hot;

    }
}
