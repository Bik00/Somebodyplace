package yjc.wdb.somebodyplace.dao;

import java.util.List;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Enable;
import yjc.wdb.somebodyplace.bean.Product;

public interface ProductDAO {
	public void insert(Product product) throws Exception;
	public List<Product> selectProductList(int place_code) throws Exception;
	public Product selectProduct(int product_code) throws Exception;
	public List<Product> selectAllProduct();
	public List<Product> selectProductByDcate(int dcate_code);
	public Detail selectDetailInfo(int parseInt);
	public int getProductNum(int member_code);
	public String getProductType(int cart_code);
	public int getProductCode(int cart_code);
	public List<Product> getProductInfo(int member_code);
	public int getNewProductCode();
	public List<Enable> getEnableTimes(int product_code);
}