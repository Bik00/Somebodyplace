package yjc.wdb.somebodyplace.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Enable;
import yjc.wdb.somebodyplace.bean.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Inject
	private SqlSession sql;
	private static final String NAMESPACE = "yjc.wdb.somebodyplace.ProductMapper";
	
	@Override
	public void insert(Product product) throws Exception {
		sql.insert(NAMESPACE + ".insertProduct", product);
	}

	@Override
	public List<Product> selectProductList(int place_code) throws Exception {
		return sql.selectList(NAMESPACE + ".selectProductList", place_code);
	}

	@Override
	public Product selectProduct(int product_code) throws Exception {
		return sql.selectOne(NAMESPACE + ".selectProduct", product_code);
	}
	
	@Override
	public List<Product> selectAllProduct(double lat, double lng) {
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		
		map.put("lat", lat);
		map.put("lng", lng);
		
		return sql.selectList(NAMESPACE + ".selectAllProduct", map);
	}

	@Override
	public List<Product> selectProductByDcate(int dcate_code) {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE + ".selectProductByDcate", dcate_code);
	}

	@Override
	public Detail selectDetailInfo(int parseInt) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".selectDetailInfo", parseInt);
	}

	@Override
	public int getProductNum(int member_code) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".getProductNum", member_code);
	}

	@Override
	public String getProductType(int cart_code) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".getProductType", cart_code);
	}

	@Override
	public int getProductCode(int cart_code) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".getProductCode", cart_code);
	}

	@Override
	public List<Product> getProductInfo(int member_code) {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".getProductInfo", member_code);
	}

	@Override
	public int getNewProductCode() {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".getNewProductCode");
	}

	@Override
	public List<Enable> getEnableTimes(int product_code) {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".getEnableTimes", product_code);
	}

	@Override
	public List<Product> getRandomItem(double lat, double lng) {
		// TODO Auto-generated method stub
		
		Map map = new HashMap();
		map.put("lat", lat);
		map.put("lng", lng);
		
		return sql.selectList(NAMESPACE+".getRandomItem", map);
	}

	@Override
	public double getDistance(double place_lat, double place_lng, double member_lat, double member_lng) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("place_lat", place_lat);
		map.put("place_lng", place_lng);
		map.put("member_lat", member_lat);
		map.put("member_lng", member_lng);
		
		return sql.selectOne(NAMESPACE+".getDistance", map);
	}

	@Override
	public List<Product> getNewItem() {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".getNewItem");
	}

	@Override
	public List<Product> getBestItem() {
		// TODO Auto-generated method stub
		return sql.selectList(NAMESPACE+".getBestItem");
	}

	@Override
	public String getProductName(int product_code) {
		// TODO Auto-generated method stub
		return sql.selectOne(NAMESPACE+".getProductName", product_code);
	}
}
