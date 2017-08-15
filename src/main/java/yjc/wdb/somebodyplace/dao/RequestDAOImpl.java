package yjc.wdb.somebodyplace.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Request;
@Repository
public class RequestDAOImpl implements RequestDAO {
	
	@Inject
	private SqlSession sql;
	private static final String NAMESPACE = "yjc.wdb.somebodyplace.RequestMapper";

	@Override
	public void insertRequest(int member_code) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE + ".insertRequest",member_code);
	}

	@Override
	public List<Request> Requestlist(int request_code) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".Requestlist", request_code);
	}

	@Override
	public void insertRequestList(Request request) {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE+".insertRequestList", request);
	}

	@Override
	public int readRequestCode(int a) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".readRequestCode", a);
	}

	@Override
	public void insertRequestOption(Request request) {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE+".insertRequestOption", request);
	}

	@Override
	public int readRequestListCode(int request_code) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".readRequestListCode", request_code);
	}

	@Override
	public List<Request> readMyPlaceRequestList(int member_code) {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".readMyPlaceRequestList", member_code);
	}

	@Override
	public void setRequestType(String request_status, int member_code, int request_code) {
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
	    map.put("request_status", request_status);
	    map.put("member_code", member_code);
	    map.put("request_code", request_code);
	    
	    System.out.println("신청 상태는 : "+request_status+", 맴버 코드는 : "+member_code+", 신청 코드는 : "+request_code);
		
		sql.update(NAMESPACE+".setRequestType", map);
	}

	@Override
	public String getRequestType(String request_status) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".getRequestType", request_status);
	}

	@Override
	public void calculateBudget(int budget_month, int place_code) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
	    map.put("budget_month", budget_month);
	    map.put("place_code", place_code);
		sql.update(NAMESPACE+".calculateBudget", map);
	}

	@Override
	public void setReservation(Timestamp request_list_reserve) {
		// TODO Auto-generated method stub
		sql.update(NAMESPACE+".setReservation", request_list_reserve);
	}

	@Override
	public void insertRequestListAsService(Request request) {
		// TODO Auto-generated method stub
		sql.insert(NAMESPACE+".insertRequestListAsService", request);
	}

	@Override
	public void insertRequestOptionAsService(int request_list_code, int service_option_info_code) {
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		map.put("request_list_code", request_list_code);
		map.put("service_option_info_code", service_option_info_code);
		
		sql.insert(NAMESPACE+".insertRequestOptionAsService", map);
	}
}
