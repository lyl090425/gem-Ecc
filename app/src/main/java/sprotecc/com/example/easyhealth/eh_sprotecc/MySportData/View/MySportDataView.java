package sprotecc.com.example.easyhealth.eh_sprotecc.MySportData.View;

import com.ruite.gem.modal.人员信息.UserInfo;
import com.ruite.gem.spdtp.ECC_SPORTDATA;
import com.ruite.gem.spdtp.SPDTP_HW_ZSLYEYDATA;

import java.util.Date;

/**
 * Created by adminHjq on 2016/12/8.
 */
public interface MySportDataView {
    public  void showSportData(UserInfo userInfo,ECC_SPORTDATA spdtp_hw_zslyeydata);
    public  void showSportDataHTTP(UserInfo userInfo,ECC_SPORTDATA spdtp_hw_zslyeydata);
   public void showSportDataMoshengren(ECC_SPORTDATA spdtp_hw_zslyeydata);
    public void showPushCard(UserInfo userInfo, Date date);
    public void showUpload(int b);
}
