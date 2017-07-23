package yjc.wdb.somebodyplace.service;

import java.util.List;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Product;

public interface ProductService {
	public void insert(Product product) throws Exception;
	public List<Product> selectProductList(int place_code) throws Exception;
	public Product selectProduct(int product_code) throws Exception;
	public List<Product> selectAllProduct();
	public List<Product> selectProductByDcate(int dcate_code);
	public Detail selectDetailInfo(int parseInt);
	public int getProductNum(int member_code);
	public String getProductType(int cart_code);
	public int getProductCode(int cart_code);
}