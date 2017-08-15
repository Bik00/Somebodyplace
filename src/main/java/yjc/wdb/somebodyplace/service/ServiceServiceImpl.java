package yjc.wdb.somebodyplace.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.somebodyplace.dao.ServiceDAO;


@Service
public class ServiceServiceImpl implements ServiceService {

	@Inject
	private ServiceDAO dao;

	@Override
	public void setServiceOptionInfo(int service_option_code, Timestamp service_option_info_time) {
		// TODO Auto-generated method stub
		dao.setServiceOptionInfo(service_option_code, service_option_info_time);
	}

	@Override
	public int getServiceOptionInfoCode(Timestamp service_option_info_time) {
		// TODO Auto-generated method stub
		return dao.getServiceOptionInfoCode(service_option_info_time);
	}
}
