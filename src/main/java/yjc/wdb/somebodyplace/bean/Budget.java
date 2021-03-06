package yjc.wdb.somebodyplace.bean;

import java.sql.Timestamp;

public class Budget {
	
	private String budget_month;
	private int budget_count;
	private int budget_totalprice;
	private int budget_possibleprice;
	private int budget_charge;
	private String budget_period;
	private String request_status;
	private Timestamp budget_date;
	private String result_date;
	private String product_name;
	
	public String getBudget_month() {
		return budget_month;
	}
	public void setBudget_month(String budget_month) {
		this.budget_month = budget_month;
	}
	public int getBudget_count() {
		return budget_count;
	}
	public void setBudget_count(int budget_count) {
		this.budget_count = budget_count;
	}
	public int getBudget_totalprice() {
		return budget_totalprice;
	}
	public void setBudget_totalprice(int budget_totalprice) {
		this.budget_totalprice = budget_totalprice;
	}
	public int getBudget_possibleprice() {
		return budget_possibleprice;
	}
	public void setBudget_possibleprice(int budget_possibleprice) {
		this.budget_possibleprice = budget_possibleprice;
	}
	public int getBudget_charge() {
		return budget_charge;
	}
	public void setBudget_charge(int budget_charge) {
		this.budget_charge = budget_charge;
	}
	public String getBudget_period() {
		return budget_period;
	}
	public void setBudget_period(String budget_period) {
		this.budget_period = budget_period;
	}
	public String getRequest_status() {
		return request_status;
	}
	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}
	public Timestamp getBudget_date() {
		return budget_date;
	}
	public void setBudget_date(Timestamp budget_date) {
		this.budget_date = budget_date;
	}
	public String getResult_date() {
		return result_date;
	}
	public void setResult_date(String result_date) {
		this.result_date = result_date;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}
