package yjc.wdb.somebodyplace.dao;

import java.sql.Timestamp;

public interface ServiceDAO {

	void setServiceOptionInfo(int service_option_code, Timestamp service_option_info_time);

	int getServiceOptionInfoCode(Timestamp service_option_info_time);

}
