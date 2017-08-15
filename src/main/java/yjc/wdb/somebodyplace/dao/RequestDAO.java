package yjc.wdb.somebodyplace.dao;

import java.sql.Timestamp;
import java.util.List;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Request;

public interface RequestDAO {
	public void insertRequest(int member_code) throws Exception;
	
	public List<Request> Requestlist(int request_code) throws Exception;

	public void insertRequestList(Request request);

	public int readRequestCode(int a);

	public void insertRequestOption(Request request);

	public int readRequestListCode(int request_code);

	public List<Request> readMyPlaceRequestList(int member_code);

	public void setRequestType(String request_status, int member_code, int request_code);

	public String getRequestType(String request_status);

	public void calculateBudget(int budget_month, int place_code);

	public void setReservation(Timestamp request_list_reserve);

	public void insertRequestListAsService(Request request);

	public void insertRequestOptionAsService(int request_list_code, int service_option_info_code);

}
