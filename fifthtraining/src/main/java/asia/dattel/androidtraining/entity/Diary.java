package asia.dattel.androidtraining.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

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
    @Generated(hash = 634618174)
    public Diary(Long projectId, String projectName, String code, String startDate,
            String endDate, long version, boolean isUploaded) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.version = version;
        this.isUploaded = isUploaded;
    }
    @Generated(hash = 112123061)
    public Diary() {
    }
    public Long getProjectId() {
        return this.projectId;
    }
    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return this.projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getStartDate() {
        return this.startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public long getVersion() {
        return this.version;
    }
    public void setVersion(long version) {
        this.version = version;
    }
    public boolean getIsUploaded() {
        return this.isUploaded;
    }
    public void setIsUploaded(boolean isUploaded) {
        this.isUploaded = isUploaded;
    }

}
