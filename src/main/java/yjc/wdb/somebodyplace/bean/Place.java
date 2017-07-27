package yjc.wdb.somebodyplace.bean;

public class Place {
	
	private int place_code;
	private int member_code;
	private String member_email;
	private String place_name;
	private String place_logo;
	private int mcate_code;
	private int dcate_code;
	private String place_busino;
	private String mcate_name;
	private String dcate_name;
	private double place_lat;
	private double place_lng;
	private String place_addr;
	
	public String getPlace_addr() {
		return place_addr;
	}
	public void setPlace_addr(String place_addr) {
		this.place_addr = place_addr;
	}
	public double getPlace_lat() {
		return place_lat;
	}
	public void setPlace_lat(double place_lat) {
		this.place_lat = place_lat;
	}
	public double getPlace_lng() {
		return place_lng;
	}
	public void setPlace_lng(double place_lng) {
		this.place_lng = place_lng;
	}

	
	public int getPlace_code() {
		return place_code;
	}
	public void setPlace_code(int place_code) {
		this.place_code = place_code;
	}
	public int getMember_code() {
		return member_code;
	}
	public void setMember_code(int member_code) {
		this.member_code = member_code;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getPlace_logo() {
		return place_logo;
	}
	public void setPlace_logo(String place_logo) {
		this.place_logo = place_logo;
	}
	public int getMcate_code() {
		return mcate_code;
	}
	public void setMcate_code(int mcate_code) {
		this.mcate_code = mcate_code;
	}
	public int getDcate_code() {
		return dcate_code;
	}
	public void setDcate_code(int dcate_code) {
		this.dcate_code = dcate_code;
	}
	public String getMcate_name() {
		return mcate_name;
	}
	public void setMcate_name(String mcate_name) {
		this.mcate_name = mcate_name;
	}
	public String getDcate_name() {
		return dcate_name;
	}
	public void setDcate_name(String dcate_name) {
		this.dcate_name = dcate_name;
	}
	public String getPlace_busino() {
		return place_busino;
	}
	public void setPlace_busino(String place_busino) {
		this.place_busino = place_busino;
	}
}
