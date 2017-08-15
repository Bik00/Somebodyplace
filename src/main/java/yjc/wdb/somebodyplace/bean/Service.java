package yjc.wdb.somebodyplace.bean;

import java.sql.Timestamp;

public class Service {

	private int service_code;
	private int dcate_code;
	private String service_name;
	private int service_option_code;
	private String service_option_name;
	private int service_option_info_code;
	private Timestamp service_option_info_time;
	
	public int getService_code() {
		return service_code;
	}
	public void setService_code(int service_code) {
		this.service_code = service_code;
	}
	public int getDcate_code() {
		return dcate_code;
	}
	public void setDcate_code(int dcate_code) {
		this.dcate_code = dcate_code;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public int getService_option_code() {
		return service_option_code;
	}
	public void setService_option_code(int service_option_code) {
		this.service_option_code = service_option_code;
	}
	public String getService_option_name() {
		return service_option_name;
	}
	public void setService_option_name(String service_option_name) {
		this.service_option_name = service_option_name;
	}
	public int getService_option_info_code() {
		return service_option_info_code;
	}
	public void setService_option_info_code(int service_option_info_code) {
		this.service_option_info_code = service_option_info_code;
	}
	public Timestamp getService_option_info_time() {
		return service_option_info_time;
	}
	public void setService_option_info_time(Timestamp service_option_info_time) {
		this.service_option_info_time = service_option_info_time;
	}
}
