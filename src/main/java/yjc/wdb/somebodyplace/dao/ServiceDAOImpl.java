package yjc.wdb.somebodyplace.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceDAOImpl implements ServiceDAO {

	   @Inject
	   private SqlSession sql;
	   private static final String NAMESPACE = "yjc.wdb.somebodyplace.ServiceMapper";
	@Override
	public void setServiceOptionInfo(int service_option_code, Timestamp service_option_info_time) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		
		map.put("service_option_code", service_option_code);
		map.put("service_option_info_time", service_option_info_time);
		
		sql.insert(NAMESPACE+".setServiceOptionInfo", map);
		
	}
	@Override
	public int getServiceOptionInfoCode(Timestamp service_option_info_time) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".getServiceOptionInfoCode", service_option_info_time);
	}
}