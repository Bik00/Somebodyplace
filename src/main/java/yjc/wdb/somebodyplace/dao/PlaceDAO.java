package yjc.wdb.somebodyplace.dao;

import java.util.List;

import yjc.wdb.somebodyplace.bean.*;

public interface PlaceDAO {
	public void create(Place vo) throws Exception;
	public void updateplace_busino(int member_code,int place_busino) throws Exception;
	public String read(String member_email) throws Exception;
	public String searchlogo(int member_code) throws Exception;
	public int searchplace_busino(int member_code) throws Exception;
	public int makechoice(int member_code) throws Exception;
	public String searchcategori1(int member_code) throws Exception;
	public String searchcategori2(int member_code) throws Exception;
	public void update(Place vo) throws Exception;
	public void delete(int bno) throws Exception;
	public List<Place> listAll() throws Exception;
	public List<Place> MainPlacelist(int dcate_code) throws Exception;
	public List<Place> getPlaceInfo(int place_code);
	public String readPlace_name(int member_code);
	public String readMember_email(int member_code);
	public int getPlaceCode(int member_code);
	public Integer hasPlaceCode(int member_code);
	public int searchPlaceCode(int member_code);
	public void addFavorite(Place place);
	public int getMemberCode(int place_code);
	public void delFavorite(Place place);
	public int getFavoriteExistence(Place place);
	public List<Favorite> getFavoriteInfo(int member_code);
	public int getMyfavoriteExistence(int member_code);
	public int getProductNum(int member_code);
}