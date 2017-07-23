package yjc.wdb.somebodyplace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Request;

import yjc.wdb.somebodyplace.dao.RequestDAO;
@Service
public class RequestServiceImpl implements RequestService {
	@Inject
	private RequestDAO dao;

	@Override
	public void insertRequest(int member_code) throws Exception {
		// TODO Auto-generated method stub
		dao.insertRequest(member_code);
	}

	@Override
	public List<Request> Requestlist(int request_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.Requestlist(request_code);
	}

	@Override
	public void insertRequestList(Request request) {
		// TODO Auto-generated method stub
		dao.insertRequestList(request);
	}

	@Override
	public int readRequestCode(int a) {
		// TODO Auto-generated method stub
		return dao.readRequestCode(a);
	}

	@Override
	public void insertRequestOption(Request request) {
		// TODO Auto-generated method stub
		dao.insertRequestOption(request);
	}

	@Override
	public int readRequestListCode(int request_code) {
		// TODO Auto-generated method stub
		return dao.readRequestListCode(request_code);
	}

	@Override
	public List<Request> readMyPlaceRequestList(int member_code) {
		// TODO Auto-generated method stub
		return dao.readMyPlaceRequestList(member_code);
	}

	@Override
	public void setRequestType(String request_status, int member_code, int request_code) {
		// TODO Auto-generated method stub
		dao.setRequestType(request_status, member_code, request_code);
	}

	@Override
	public String getRequestType(String request_status) {
		// TODO Auto-generated method stub
		return dao.getRequestType(request_status);
	}

	@Override
	public void calculateBudget(int budget_month, int place_code) {
		// TODO Auto-generated method stub
		dao.calculateBudget(budget_month, place_code);
	}

}
