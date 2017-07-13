package yjc.wdb.somebodyplace.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Favorite;
import yjc.wdb.somebodyplace.bean.Place;
import yjc.wdb.somebodyplace.dao.PlaceDAO;

@Service
public class PlaceServiceImpl implements PlaceService  {
	
	@Inject
	private PlaceDAO dao;

	@Override
	public void regist(Place b) throws Exception {
		// TODO Auto-generated method stub
		dao.create(b);
	}

	@Override
	public String read(String member_email) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(member_email);
	}

	@Override
	public void modify(Place b) throws Exception {
		// TODO Auto-generated method stub
		dao.update(b);
	}

	@Override
	public void remove(int place_code) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(place_code);
	}

	@Override
	public List<Place> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public int getTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String searchlogo(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchlogo(member_code);
	}

	@Override
	public int makechoice(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.makechoice(member_code);
	}

	@Override
	public String searchcategori1(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchcategori1(member_code);
	}

	@Override
	public String searchcategori2(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchcategori2(member_code);
	}

	@Override
	public List<Place> MainPlacelist(int dcate_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.MainPlacelist(dcate_code);
	}

	@Override
	public List<Place> getPlaceInfo(int place_code) {
		// TODO Auto-generated method stub
		return dao.getPlaceInfo(place_code);
	}

	@Override
	public String readPlace_name(int member_code) {
		// TODO Auto-generated method stub
		return dao.readPlace_name(member_code);
	}

	@Override
	public String readMember_email(int member_code) {
		// TODO Auto-generated method stub
		return dao.readMember_email(member_code);
	}

	@Override
	public int getPlaceCode(int member_code) {
		// TODO Auto-generated method stub
		return dao.getPlaceCode(member_code);
	}

	@Override
	public Integer hasPlaceCode(int member_code) {
		// TODO Auto-generated method stub
		return dao.hasPlaceCode(member_code);
	}

	@Override
	public int searchPlaceCode(int member_code) {
		// TODO Auto-generated method stub
		return dao.searchPlaceCode(member_code);
	}

	@Override
	public void addFavorite(Place place) {
		// TODO Auto-generated method stub
		dao.addFavorite(place);
	}

	@Override
	public int getMemberCode(int place_code) {
		// TODO Auto-generated method stub
		return dao.getMemberCode(place_code);
	}

	@Override
	public void delFavorite(Place place) {
		// TODO Auto-generated method stub
		dao.delFavorite(place);
	}

	@Override
	public int getFavoriteExistence(Place place) {
		// TODO Auto-generated method stub
		return dao.getFavoriteExistence(place);
	}

	@Override
	public List<Favorite> getFavoriteInfo(int member_code) {
		// TODO Auto-generated method stub
		return dao.getFavoriteInfo(member_code);
	}

	@Override
	public void updateplace_busino(int member_code, int place_busino) throws Exception {
		dao.updateplace_busino(member_code, place_busino);
		
	}

	@Override
	public int searchplace_busino(int member_code) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchplace_busino(member_code);
	}
}
