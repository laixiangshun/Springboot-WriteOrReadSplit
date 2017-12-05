package com.sisyphe.framework.db.routing;

import javax.sql.DataSource;

/**
 * Created by lailai on 2017/12/5.
 */
public interface MasterDataSource {

    /**
     * 主库
     * @return
     */
    DataSource masterDataSource();
}
