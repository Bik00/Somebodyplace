package yjc.wdb.somebodyplace.dao;

import java.util.List;

import yjc.wdb.somebodyplace.bean.Member;

public interface MemberDAO {
	// CRUD : Create, Read, Update, Delete 
	public void create(Member mem) throws Exception;
	public void cartinsert(int member_code,int product_code, int total_price) throws Exception;
	public void cartoptioninsert(int cart_code,int detail_code) throws Exception;
	public int searchcartcode(int member_code) throws Exception;
	public String read(int member_code) throws Exception;
	public int read2(String member_email) throws Exception;
	public void update(Member mem) throws Exception;
	public void interestupdate(Member member) throws Exception;
	public void requestupdate(Member mem) throws Exception;
	public void delete(int member_code) throws Exception;
	public List<Member> postRequest_listAll(int member_code) throws Exception;
	public List<Member> listAll() throws Exception;
	public List<Member> login(Member member);
	public String eLogin(String member_email) throws Exception;
	public List<Member> listAll(double lat, double lng, int radius);
	public List<Member> orderlist(int member_code);
	public List<Member> cartlist(int member_code);
	public String getMemberInterest(int member_code);
	public String getMemberEmail(int member_code);
	public void delCartOption(int cart_code);
	public void delCart(int cart_code);
	public List<Member> getMemberInfo(int member_code);
	public String getMemberAddr(int member_code);
}