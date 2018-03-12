package asia.dattel.androidtraining;

import android.app.Application;

import asia.dattel.androidtraining.entity.DaoMaster;
import asia.dattel.androidtraining.entity.DaoSession;

/**
 * Created by Shiburagi on 23/02/2018.
 */

public class BaseApplication extends Application {

    private DaoMaster.DevOpenHelper openHelper;
    private DaoSession session;

    @Override
    public void onCreate() {
        super.onCreate();

        openHelper = new DaoMaster.DevOpenHelper(this, "dummy-db");

        session = new DaoMaster(openHelper.getWritableDb()).newSession();
    }

    public DaoSession getSession() {
        return session;
    }
}
