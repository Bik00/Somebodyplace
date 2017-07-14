package yjc.wdb.somebodyplace;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import yjc.wdb.somebodyplace.bean.Board;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.bean.Place;
import yjc.wdb.somebodyplace.service.BoardService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.ProductService;

@Controller
public class ManagerController {
	
	@Inject
	private PlaceService placeservice;
	@Inject
	private BoardService boardservice;
	@Inject
	private ProductService productservice;
	
	// 플레이스 관리 페이지 메인
	@RequestMapping(value="placeManager", method=RequestMethod.GET)
	public String mypage(Model model, @ModelAttribute("member_code") String member_code){
		model.addAttribute("placeMPage", "placeManagerStats.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",	PlaceController.place_name);
		return "index";
	}
	
	// 플레이스 수정
	@RequestMapping(value="placemodify", method=RequestMethod.GET)
	public String placemodify(Model model){
		List<Place> place = placeservice.getMyPlaceInfo(MemberController.member_code);
		model.addAttribute("place_mcate", place.get(0).getMcate_code());
		model.addAttribute("place_dcate", place.get(0).getDcate_code());
		model.addAttribute("placeMPage", "modifyPlace.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		model.addAttribute("member_code", MemberController.member_code);
		model.addAttribute("member_email", placeservice.readMember_email(MemberController.member_code));
		return "index";
	}
	
	@RequestMapping(value="modifyPlace", method=RequestMethod.POST)
	public String modifyPlace(HttpServletRequest req, Model model) throws Exception {
		String place_name = req.getParameter("place_name");
		String main_cate = req.getParameter("main_cate");
		int detail_cate = Integer.parseInt(req.getParameter("detail_cate"));
		String place_logo = req.getParameter("place_logo");
		Place place = new Place();
		place.setPlace_name(place_name);
		place.setMcate_name(main_cate);
		place.setDcate_code(detail_cate);
		place.setMember_code(MemberController.member_code);
		place.setPlace_logo(place_logo);
		placeservice.modify(place);
		List<Place> myPlace = placeservice.getMyPlaceInfo(MemberController.member_code);
		PlaceController.place_logo = myPlace.get(0).getPlace_logo();
		PlaceController.place_name = myPlace.get(0).getPlace_name();
		
		model.addAttribute("place_mcate", myPlace.get(0).getMcate_code());
		model.addAttribute("place_dcate", myPlace.get(0).getDcate_code());
		model.addAttribute("placeMPage", "placeManagerStats.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		model.addAttribute("member_code", MemberController.member_code);
		model.addAttribute("member_email", placeservice.readMember_email(MemberController.member_code));
		return "index";
	}
	
	// 신청내역
	@RequestMapping(value="requestList", method=RequestMethod.GET)
	public String requestList(Model model){
		model.addAttribute("placeMPage", "requestList.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		return "index";
	}
	
	// 사업자 등록
	@RequestMapping(value="addBusiness", method=RequestMethod.GET)
	public String 	addBusiness(Model model){
		
		String place_busino = placeservice.getBusino(MemberController.member_code);
		System.out.println(place_busino);
		if(place_busino == null) {
			place_busino = "";
		}
		String arrayBusino[] = place_busino.split("-");
		for(int k = 0;k<arrayBusino.length;k++) {
			model.addAttribute("place_busino"+k+"", arrayBusino[k]);
		}
		
		model.addAttribute("placeMPage", "addBusiness.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		return "index";
	}
	
	//사업자번호 DB insert 
	@RequestMapping(value="insertBusiness", method=RequestMethod.GET)
	public String 	insertBusiness(Model model, HttpServletRequest req) throws Exception{
		
		Place place = new Place();
		place.setMember_code(Integer.parseInt((String)req.getParameter("member_code")));
		String x = req.getParameter("place_busino");
		place.setPlace_busino(x);
		placeservice.updateplace_busino(place);
		String place_busino = placeservice.getBusino(MemberController.member_code);
		String arrayBusino[] = place_busino.split("-");
		for(int k = 0;k<arrayBusino.length;k++) {
			model.addAttribute("place_busino"+k+"", arrayBusino[k]);
		}
		model.addAttribute("placeMPage", "addBusiness.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		model.addAttribute("member_code", MemberController.member_code);
		model.addAttribute("member_email", placeservice.readMember_email(MemberController.member_code));
		return "index";
	}
	
	// 카테고리 설정
	@RequestMapping(value="categorySetting", method=RequestMethod.GET)
	public String 	categorySetting(Model model) throws Exception{
		model.addAttribute("placeMPage", "categorySetting.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		// 회원코드로 플레이스 코드 찾기 
		int place_code = placeservice.getPlaceCode(MemberController.member_code);
		// 플레이스 코드로 게시판 리스트 불러오기
		model.addAttribute("BoardList", boardservice.selectBoardList(place_code));
		model.addAttribute("place_code", place_code);
		return "index";
	}
	@RequestMapping(value="categoryChange", method=RequestMethod.POST)
	public String categoryChange(Model model, int[] del_code, int[] board_code, String[] board_name, int place_code) throws Exception {
		model.addAttribute("placePage", "placeHome.jsp");
		model.addAttribute("cont", "place/place.jsp");
		
		Board board = new Board();
		if(board_code != null && board_name !=null){
			for(int i=0; i<board_code.length; i++){
				board.setBoard_code(board_code[i]);
				board.setBoard_name(board_name[i]);
				board.setPlace_code(place_code);
				if(board_code[i] == 0){
					boardservice.insertBoard(board);
				}else {
					boardservice.updateBoard(board);
				}
			}
		}
		if(del_code != null){
			for(int i=0; i<del_code.length; i++){
				if(del_code[i] != 0){
					boardservice.deleteBoard(del_code[i]);
				}
			}
		}
		// 현재 회원의 플레이스 카테고리 리스트
		place_code = placeservice.getPlaceCode(MemberController.member_code);
		model.addAttribute("BoardList", boardservice.selectBoardList(place_code));
		// 현재 회원의 플레이스 로고와 플레이스 명
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		// 현재 회원의 상품 리스트
		model.addAttribute("ProductList", productservice.selectProductList(place_code));
		return "index";
	}
	// 정산 관리
	@RequestMapping(value="currentBudget", method=RequestMethod.GET)
	public String 	currentBudget(Model model){
		model.addAttribute("placeMPage", "currentBudget.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		return "index";
	}
	
	
}
