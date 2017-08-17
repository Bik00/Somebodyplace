package yjc.wdb.somebodyplace.service;

import java.sql.Timestamp;
import java.util.List;

import yjc.wdb.somebodyplace.bean.*;

public interface PlaceService {
	public void regist(Place b) throws Exception;
	public String read(String member_email) throws Exception;
	public String searchlogo(int member_code) throws Exception;
	public int makechoice(int member_code) throws Exception;
	public void updateplace_busino(Place place) throws Exception;
	public void deleteplace_busino(int member_code);
	public int searchplace_busino(int member_code) throws Exception;
	public String searchcategori1(int member_code) throws Exception;
	public String searchcategori2(int member_code) throws Exception;
	public void modify(Place b) throws Exception;
	public void remove(int place_code) throws Exception;
	public List<Place> listAll() throws Exception;
	public List<Place> MainPlacelist(int dcate_code) throws Exception;
	public int getTotalCount() throws Exception;
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
	public List<Place> getMyPlaceInfo(int member_code);
	public String getBusino(int member_code);
	public List<Budget> getBudgetInfo(int place_code);
	public List<Budget> getBudgetInfoByImpo(int place_code);
	public List<Budget> getBudgetInfoForMain(int place_code);
	public int getPlaceDcate(int place_code);
	public void addEnableTime(int product_code, Timestamp enable_time);
}