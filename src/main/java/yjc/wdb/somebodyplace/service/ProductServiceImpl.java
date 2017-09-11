package yjc.wdb.somebodyplace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Enable;
import yjc.wdb.somebodyplace.bean.Product;
import yjc.wdb.somebodyplace.dao.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO dao;
	
	@Override
	public void insert(Product product) throws Exception {
		dao.insert(product);
	}

	@Override
	public List<Product> selectProductList(int place_code) throws Exception {
		return dao.selectProductList(place_code);
	}

	@Override
	public Product selectProduct(int product_code) throws Exception {
		return dao.selectProduct(product_code);
	}

	@Override
	public List<Product> selectAllProduct(double lat, double lng) {
		// TODO Auto-generated method stub
		return dao.selectAllProduct(lat, lng);
	}

	@Override
	public List<Product> selectProductByDcate(int dcate_code) {
		// TODO Auto-generated method stub
		return dao.selectProductByDcate(dcate_code);
	}

	@Override
	public Detail selectDetailInfo(int parseInt) {
		// TODO Auto-generated method stub
		return dao.selectDetailInfo(parseInt);
	}

	@Override
	public int getProductNum(int member_code) {
		// TODO Auto-generated method stub
		return dao.getProductNum(member_code);
	}

	@Override
	public String getProductType(int cart_code) {
		// TODO Auto-generated method stub
		return dao.getProductType(cart_code);
	}

	@Override
	public int getProductCode(int cart_code) {
		// TODO Auto-generated method stub
		return dao.getProductCode(cart_code);
	}

	@Override
	public List<Product> getProductInfo(int member_code) {
		// TODO Auto-generated method stub
		return dao.getProductInfo(member_code);
	}

	@Override
	public int getNewProductCode() {
		// TODO Auto-generated method stub
		return dao.getNewProductCode();
	}

	@Override
	public List<Enable> getEnableTimes(int product_code) {
		// TODO Auto-generated method stub
		return dao.getEnableTimes(product_code);
	}

	@Override
	public List<Product> getRandomItem(double lat, double lng) {
		// TODO Auto-generated method stub
		return dao.getRandomItem(lat, lng);
	}

	@Override
	public double getDistance(double place_lat, double place_lng, double member_lat, double member_lng) {
		// TODO Auto-generated method stub
		return dao.getDistance(place_lat, place_lng, member_lat, member_lng);
	}

	@Override
	public List<Product> getNewItem() {
		// TODO Auto-generated method stub
		return dao.getNewItem();
	}

	@Override
	public List<Product> getBestItem() {
		// TODO Auto-generated method stub
		return dao.getBestItem();
	}

	@Override
	public String getProductName(int product_code) {
		// TODO Auto-generated method stub
		return dao.getProductName(product_code);
	}

}
