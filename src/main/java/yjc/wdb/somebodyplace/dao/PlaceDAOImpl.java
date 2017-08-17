package yjc.wdb.somebodyplace.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.somebodyplace.bean.Budget;
import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Favorite;
import yjc.wdb.somebodyplace.bean.Place;

@Repository
public class PlaceDAOImpl implements PlaceDAO {

	//BoardMapper.xml에 접근하기위해서 namespace를 멤버변수로 지정
	private static final String namespace = "yjc.wdb.placeMapper";				
	
	//root-context.xml 파일의 SqlSession Spring Bean에 지정한 sqlSession
	// 스프링빈은 객체를 생성하지 않고 프레임�p이 생성해줌(제어의 역전) >> 어노테이션지정(필수)
	@Inject
	private SqlSession sqlSession;	

	@Override
	public void create(Place vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".create", vo);
	}

	@Override
	public String read(String member_email) throws Exception {
		// TODO Auto-generated method stub
	
		 return sqlSession.selectOne(namespace + ".read",member_email);
	}

	@Override
	public void update(Place vo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(namespace + ".update", vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace + ".delete", bno);
	}

	@Override
	public List<Place> listAll() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".listAll");
	}

	@Override
	public String searchlogo(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".searchlogo",member_code);
	}

	@Override
	public int makechoice(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".makechoice",member_code);
	}

	@Override
	public String searchcategori1(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".searchcategori1",member_code);
	}

	@Override
	public String searchcategori2(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".searchcategori2",member_code);
	}

	@Override
	public List<Place> MainPlacelist(int dcate_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".MainPlacelist",dcate_code);
	}

	@Override
	public List<Place> getPlaceInfo(int place_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getPlaceInfo", place_code);
	}

	@Override
	public String readPlace_name(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".readPlace_name", member_code);
	}

	@Override
	public String readMember_email(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".readMember_email", member_code);
	}

	@Override
	public int getPlaceCode(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getPlaceCode", member_code);
	}

	@Override
	public Integer hasPlaceCode(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".hasPlaceCode", member_code);
	}

	@Override
	public int searchPlaceCode(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".searchPlaceCode", member_code);
	}

	@Override
	public void addFavorite(Place place) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace+".addFavorite", place);
	}

	@Override
	public int getMemberCode(int place_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getMemberCode", place_code);
	}

	@Override
	public void delFavorite(Place place) {
		// TODO Auto-generated method stub
		sqlSession.delete(namespace+".delFavorite", place);
	}

	@Override
	public int getFavoriteExistence(Place place) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getFavoriteExistence", place);
	}

	@Override
	public List<Favorite> getFavoriteInfo(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getFavoriteInfo", member_code);
	}

	@Override
	public void updateplace_busino(Place place) throws Exception {
		sqlSession.update(namespace + ".updateplace_busino",place);
	}

	@Override
	public void deleteplace_busino(int member_code) {
		// TODO Auto-generated method stub
		sqlSession.update(namespace+".deleteplace_busino", member_code);
	}
	
	@Override
	public int searchplace_busino(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".searchplace_busino", member_code);
	}
	
	@Override
	public int getMyfavoriteExistence(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getMyfavoriteExistence", member_code);
	}

	@Override
	public List<Place> getMyPlaceInfo(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getMyPlaceInfo", member_code);
	}

	@Override
	public String getBusino(int member_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getBusino", member_code);
	}

	@Override
	public List<Budget> getBudgetInfo(int place_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getBudgetInfo", place_code);
	}

	@Override
	public List<Budget> getBudgetInfoByImpo(int place_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getBudgetInfoByImpo", place_code);
	}

	@Override
	public List<Budget> getBudgetInfoForMain(int place_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".getBudgetInfoForMain", place_code);
	}

	@Override
	public int getPlaceDcate(int place_code) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".getPlaceDcate", place_code);
	}

	@Override
	public void addEnableTime(int product_code, Timestamp enable_time) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("product_code", product_code);
		map.put("enable_time", enable_time);
		sqlSession.insert(namespace+".addEnableTime", map);
	}
}
