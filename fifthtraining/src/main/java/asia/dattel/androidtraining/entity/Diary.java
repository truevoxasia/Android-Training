package asia.dattel.androidtraining.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Shiburagi on 21/02/2018.
 */

@Entity(nameInDb = "ProjectTbl")
public class Diary {

    @Id(autoincrement = true)
    public Long projectId;

    public String projectName;
    public String code;
    public String startDate;
    public String endDate;

    public long version;
    public boolean isUploaded;

}
