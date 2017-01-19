package sprotecc.com.example.easyhealth.eh_sprotecc.Tool;


import com.ruite.gem.modal.基础数据.Day;
import com.ruite.shell.exception.UserException;

import java.util.Calendar;
import java.util.Date;

public class DayManager  {
	public long getDiffDay(Date date, Calendar calendar) throws Exception {
		if (date == null && calendar == null) throw new UserException("获取day参数不能为空");

		Calendar calen2000 = Calendar.getInstance();
		calen2000.set(2000, 0, 1);
		calen2000.set(Calendar.HOUR_OF_DAY, 0);
		calen2000.set(Calendar.MINUTE, 0);
		calen2000.set(Calendar.SECOND, 0);
		calen2000.set(Calendar.MILLISECOND, 0);

		long milliseconds0 = calen2000.getTimeInMillis();

		if (calendar == null) {
			calendar = Calendar.getInstance();
			calendar.setTime(date);
		}

//		SimpleDateFormat mf = new SimpleDateFormat("yyyy-MM-dd");
//		String s = mf.format(calendar.getTime());
//		System.out.println("getDiffDay日期：" + s);

		long milliseconds1 = calendar.getTimeInMillis();
		if (milliseconds1 < milliseconds0) throw new UserException("初始日期不能早于2000年1月1日");
		long diff = milliseconds1 - milliseconds0;
		long diffDays = diff / (24L * 60 * 60 * 1000);
		return diffDays;
	}



}
