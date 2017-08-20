package yjc.wdb.somebodyplace.bean;

import java.sql.Timestamp;

public class Timeline {

	private int timeline_sender;
	private int timeline_receiver;
	private int timeline_product;
	private int timeline_request;
	private Timestamp timeline_time;
	private int timeline_status;
	private int timeline_issue;
	private int timeline_command_code;
	private String product_img;
	private String product_name;
	private int product_code;
	private String member_nickname;
	private String member_profile;
	
	public int getTimeline_sender() {
		return timeline_sender;
	}
	public void setTimeline_sender(int timeline_sender) {
		this.timeline_sender = timeline_sender;
	}
	public int getTimeline_receiver() {
		return timeline_receiver;
	}
	public void setTimeline_receiver(int timeline_receiver) {
		this.timeline_receiver = timeline_receiver;
	}
	public int getTimeline_product() {
		return timeline_product;
	}
	public void setTimeline_product(int timeline_product) {
		this.timeline_product = timeline_product;
	}
	public int getTimeline_request() {
		return timeline_request;
	}
	public void setTimeline_request(int timeline_request) {
		this.timeline_request = timeline_request;
	}
	public Timestamp getTimeline_time() {
		return timeline_time;
	}
	public void setTimeline_time(Timestamp timeline_time) {
		this.timeline_time = timeline_time;
	}
	public int getTimeline_status() {
		return timeline_status;
	}
	public void setTimeline_status(int timeline_status) {
		this.timeline_status = timeline_status;
	}
	public int getTimeline_issue() {
		return timeline_issue;
	}
	public void setTimeline_issue(int timeline_issue) {
		this.timeline_issue = timeline_issue;
	}
	public int getTimeline_command_code() {
		return timeline_command_code;
	}
	public void setTimeline_command_code(int timeline_command_code) {
		this.timeline_command_code = timeline_command_code;
	}
	public String getProduct_img() {
		return product_img;
	}
	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_code() {
		return product_code;
	}
	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}
	public String getMember_nickname() {
		return member_nickname;
	}
	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}
	public String getMember_profile() {
		return member_profile;
	}
	public void setMember_profile(String member_profile) {
		this.member_profile = member_profile;
	}
}
