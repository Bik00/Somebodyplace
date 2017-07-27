package yjc.wdb.somebodyplace.dao;

import java.util.List;

import yjc.wdb.somebodyplace.bean.Detail;

public interface DetailDAO {
   public void insert(Detail detail) throws Exception;
   public List<Detail> selectDetail(int option_code) throws Exception;
   List<Detail> getCartDetailInfo(int cart_code);
   public int getDetailPrice(int detail_code);
}