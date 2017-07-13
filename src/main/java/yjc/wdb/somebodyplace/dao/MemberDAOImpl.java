package yjc.wdb.somebodyplace.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.somebodyplace.bean.Member;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	private static final String namespace =
			"yjc.wdb.memberMapper";
	
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void create(Member mem) throws Exception {
		sqlSession.insert(namespace + ".create", mem);
	}

	@Override
	public String read(int member_code) throws Exception {
		return sqlSession.selectOne(namespace + ".read", member_code);
	}

	@Override
	public void update(Member mem) throws Exception {
		sqlSession.update(namespace + ".update", mem);

	}

	@Override
	public void delete(int member_code) throws Exception {
		sqlSession.delete(namespace + ".delete", member_code);

	}

	@Override
	public List<Member> listAll() throws Exception {
		return sqlSession.selectList(namespace + ".listAll");
	}

	@Override
	public List<Member> login(Member member) {
		// TODO Auto-generated method stub
//		System.out.println(sqlSession.selectList(namespace+".login", member));
		return sqlSession.selectList(namespace+".login", member);
	}


	@Override
	public int read2(String member_email) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".read2", member_email);
	}

	@Override
	public List<Member> listAll(double lat, double lng, int radius) {
		// TODO Auto-generated method stub
		System.out.println("DAOIMpl"+lat+lng+radius);
		Map map = new HashMap();
	      map.put("lat", lat);
	      map.put("lng", lng);
	      map.put("radius",radius);
		
		
		   return sqlSession.selectList(namespace + ".listAll",map);
	}

	@Override
	public void requestupdate(Member mem) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".moneysuccess", mem);
	}

	@Override
	public List<Member> orderlist(int member_code) {
		// TODO Auto-generated method stub
		System.out.print("DAOIMPL"+member_code);
		return sqlSession.selectList(namespace+".orderlist", member_code);
	}

	@Override
	public List<Member> postRequest_listAll(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".postRequest_listAll", member_code);
	}

	@Override
	public void cartinsert(int member_code, int product_code) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.print("IMPL"+member_code);
		System.out.print(product_code);
		Map map = new HashMap();
	      map.put("member_code",member_code);
	      map.put("product_code",product_code);
	    
		
		
		 sqlSession.insert(namespace + ".cartinsert",map);
	}

	@Override
	public void cartoptioninsert(int cart_code, int detail_code) throws Exception {
		// TODO Auto-generated method stub
	
		
		
		Map map = new HashMap();
	      map.put("cart_code",cart_code);
	      map.put("detail_code",detail_code);
	    
		
		
		 sqlSession.insert(namespace + ".cartoptioninsert",map);
	}

	@Override
	public int searchcartcode(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".searchcartcode", member_code);
	}

	@Override
	public List<Member> cartlist(int member_code) {
		// TODO Auto-generated method stub
	return sqlSession.selectList(namespace+".cartlist", member_code);
	}

	@Override
	public String getMemberInterest(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getMemberInterest", member_code);
	}

	@Override
	public String getMemberEmail(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getMemberEmail", member_code);
	}

	@Override
	public void interestupdate(String member_interest, int member_code) throws Exception {
		// TODO Auto-generated method stub
		Map map = new HashMap();
	      map.put("member_interest",member_interest);
	      map.put("member_code",member_code);
	    
		
		
		 sqlSession.update(namespace + ".interestupdate",map);
	}
}
