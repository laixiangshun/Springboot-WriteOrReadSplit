package com.sisyphe.framework.db.routing;

import javax.sql.DataSource;

/**
 * Created by lailai on 2017/12/5.
 */
public interface SlaveDataSource {

    /**
     * 子库
     * @return
     */
    DataSource salveDataSource();
}
