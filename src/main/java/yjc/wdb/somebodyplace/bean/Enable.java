package yjc.wdb.somebodyplace.bean;

import java.sql.Timestamp;

public class Enable {

	private Timestamp enable_time;
	private int product_code;
	private String enable_result;
	
	public Timestamp getEnable_time() {
		return enable_time;
	}
	public void setEnable_time(Timestamp enable_time) {
		this.enable_time = enable_time;
	}
	public int getProduct_code() {
		return product_code;
	}
	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}
	public String getEnable_result() {
		return enable_result;
	}
	public void setEnable_result(String enable_result) {
		this.enable_result = enable_result;
	}
	
}
