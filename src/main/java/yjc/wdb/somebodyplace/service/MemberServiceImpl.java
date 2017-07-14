package yjc.wdb.somebodyplace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.dao.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	@Override
	public void modify(Member m) throws Exception {
		dao.update(m);
	}

	@Override
	public void regist(Member m) throws Exception {
		dao.create(m);
	}


	@Override
	public String read(int member_code) throws Exception {
		return dao.read(member_code);
	}

	@Override
	public void remove(int member_code) throws Exception {
		dao.delete(member_code);		
	}

	@Override
	public List<Member> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<Member> login(Member member) {
		return dao.login(member);
	}

	@Override
	public int read2(String member_email) throws Exception {
		return dao.read2(member_email);
	}
	@Override
	public List<Member> listAll(double Lat, double Lng, int Radius) throws Exception {
		return dao.listAll(Lat,Lng,Radius);
	}

	@Override
	public void requestupdate(Member mem) throws Exception {
		dao.requestupdate(mem);
		
	}

	@Override
	public List<Member> orderlist(int member_code) {
		// TODO Auto-generated method stub
		return dao.orderlist(member_code);
	}

	@Override
	public List<Member> postRequest_listAll(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.postRequest_listAll(member_code);
	}

	@Override
	public void cartinsert(int member_code, int product_code, int total_price) throws Exception {
		// TODO Auto-generated method stub
		dao.cartinsert(member_code,product_code,total_price);
	}

	@Override
	public void cartoptioninsert(int cart_code, int detail_code) throws Exception {
		// TODO Auto-generated method stub
		
		dao.cartoptioninsert(cart_code, detail_code);
	}

	@Override
	public int searchcartcode(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchcartcode(member_code);
	}

	@Override
	public List<Member> cartlist(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.cartlist(member_code);
	}

	@Override
	public String getMemberInterest(int member_code) {
		// TODO Auto-generated method stub
		return dao.getMemberInterest(member_code);
	}

	@Override
	public String getMemberEmail(int member_code) {
		// TODO Auto-generated method stub
		return dao.getMemberEmail(member_code);
	}

	@Override
	public void interestupdate(Member member) throws Exception {
		// TODO Auto-generated method stub
		dao.interestupdate(member);
	}

}