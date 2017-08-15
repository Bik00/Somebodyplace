package yjc.wdb.somebodyplace.service;

import java.sql.Timestamp;

public interface ServiceService {

	void setServiceOptionInfo(int service_option_code, Timestamp service_option_info_time);

	int getServiceOptionInfoCode(Timestamp service_option_info_time);

}
