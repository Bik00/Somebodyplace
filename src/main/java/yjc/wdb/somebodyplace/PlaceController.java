package yjc.wdb.somebodyplace;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import yjc.wdb.somebodyplace.bean.Board;
import yjc.wdb.somebodyplace.bean.Budget;
import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Enable;
import yjc.wdb.somebodyplace.bean.Favorite;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.bean.Option;
import yjc.wdb.somebodyplace.bean.Place;
import yjc.wdb.somebodyplace.bean.Post;
import yjc.wdb.somebodyplace.bean.PostContent;
import yjc.wdb.somebodyplace.bean.Product;
import yjc.wdb.somebodyplace.bean.Request;
import yjc.wdb.somebodyplace.service.BoardService;
import yjc.wdb.somebodyplace.service.CateService;
import yjc.wdb.somebodyplace.service.DetailService;
import yjc.wdb.somebodyplace.service.MemberService;
import yjc.wdb.somebodyplace.service.OptionService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.PostContentService;
import yjc.wdb.somebodyplace.service.PostService;
import yjc.wdb.somebodyplace.service.ProductService;
import yjc.wdb.somebodyplace.service.RequestService;
import yjc.wdb.somebodyplace.service.ServiceService;
import yjc.wdb.somebodyplace.util.MediaUtils;

@Controller
public class PlaceController {
	@Inject
	private PostService postservice;
	@Inject
	private ProductService productservice;
	@Inject
	private PostContentService postcontentservice;
	@Inject
	private OptionService optionservice;
	@Inject
	private DetailService detailservice;
	@Inject
	private CateService cateservice;
	@Inject
	private PlaceService placeservice;
	@Inject
	private MemberService memberservice;
	@Inject
	private BoardService boardservice;
	@Inject
	private RequestService requestservice;
	@Inject
	private ServiceService serviceservice;
	
	public static String place_name ;
	public static String place_logo ;
	public static int place_code;
	public static String memberEmail;		// 주소로 접속한 email 정보
	public static int member_code_for_email;	// 이메일로 알아낸 회원코드
	public static int Cmember_code; // 무조건 판매자 맴버코드
	private static int resizing_width;
	private static int resizing_height;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Resource(name="placeAddFormPath")
	private String placeAddFormPath;
	
	@Resource(name="postFormPath")
	private String postFormPath;
	
	@RequestMapping(value="placeHome", method=RequestMethod.GET)
	public String placeHome(Model model, HttpServletRequest req) throws Exception{
		model.addAttribute("placePage", "placeHome.jsp");
		model.addAttribute("cont", "place/place.jsp");
		
		// 현재 회원의 플레이스 로고, 플레이스 명
		int Cmember_code = Integer.parseInt(req.getParameter("member_code"));
		int member_code = MemberController.member_code;
		
		if(Integer.toString(member_code).matches("undefined")) {
			member_code = 0;
		}
		
		Integer place_busino=placeservice.searchplace_busino(Cmember_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}	
		
		
		String member_email = placeservice.readMember_email(Cmember_code);
		
		int board_code = 0;
		
		if(req.getParameter("board_code") != null) {
			board_code = Integer.parseInt(req.getParameter("board_code"));
		} 
		
		// 들어가려는 url의 플레이스 로고와 이름을 받아와서 넣어야 한다. - 본일
		place_logo= placeservice.searchlogo(Cmember_code);
		model.addAttribute("place_logo",place_logo);
		
		// 넘어오는 member_email이 널 값인 경우가 있다. 이때 따로 member_email을 찾는 메소드를 실행을 안하면
		// 500 에러가 발생한다. - 본일
		
		place_name = placeservice.readPlace_name(Cmember_code);
		model.addAttribute("place_name",place_name);

		// 현재 접속한 사람의 맴버코드와
		// 접속한 플레이스 주인의 맴버코드 값을 넘겨서
		// JSTL에서 비교를 통해 관리 버튼을 띄울지 정한다. - 본일
		
		model.addAttribute("member_code", member_code);
		model.addAttribute("Cmember_code", Cmember_code);

		// 현재 회원의 플레이스 카테고리 리스트
		place_code = placeservice.getPlaceCode(Cmember_code);
		model.addAttribute("place_code", place_code);
		List<Board> BoardList = boardservice.selectBoardList(place_code);
		model.addAttribute("BoardList", BoardList);
		model.addAttribute("dcate_code", placeservice.getPlaceDcate(place_code));
		if(board_code == 0){
			// 현재 회원의 상품 리스트
			String ec = BoardList.get(0).getBoard_type();	
			if(ec.equals("mainForm")) {
				model.addAttribute("mainFormTime", "tryIt");
				System.out.println(BoardList.get(0).getBoard_content() == null || BoardList.get(0).getBoard_content().length() == 0);
				
				if(BoardList.get(0).getBoard_content() == null || BoardList.get(0).getBoard_content().length() == 0) {
					model.addAttribute("main_type", ec);
				} else {
					model.addAttribute("board_content", BoardList.get(0).getBoard_content());
				}
				model.addAttribute("board_code", BoardList.get(0).getBoard_code());
			} else if(ec.equals("postForm")) {
				model.addAttribute("ProductList", productservice.selectProductList(place_code));
			}
		}else {
			List<Integer> product_code = postservice.selectProductCode(board_code);
			JSONArray pArray = new JSONArray();
			for(int i=0; i<product_code.size(); i++){
				JSONObject pjson = new JSONObject();
				Product pro = productservice.selectProduct(product_code.get(i));
				pjson.put("product_code", pro.getProduct_code());
				pjson.put("product_name", pro.getProduct_name());
				pjson.put("product_img", pro.getProduct_img());
				pjson.put("product_explanation", pro.getProduct_explanation());
				pjson.put("product_price", pro.getProduct_price());
				pArray.add(pjson);
			}
			model.addAttribute("ProductList", pArray);
		}		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		if(MemberController.member_code != 0) {
			MemberController.lng = memberForDistance.get(0).getMember_lat();
			MemberController.lat = memberForDistance.get(0).getMember_lng();
		}
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		return "index";	
	}
	
	@RequestMapping(value="place", method=RequestMethod.GET)
	public String place(Place place,Model model,@ModelAttribute("member_code") int member_code,@ModelAttribute("member_email") String member_email){
		
		try {
			model.addAttribute("McateList", cateservice.McateList());  // 메인 카테고리 리스트
			model.addAttribute("DcateList", cateservice.DcateList());	// 세부 카테고리 리스트
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		try {
			System.out.println(member_code);
			
			placeservice.makechoice(member_code);
			place_logo= placeservice.searchlogo(member_code);
			model.addAttribute("place_logo",place_logo);
			place_code = placeservice.searchPlaceCode(member_code);
			model.addAttribute("place_code", place_code);
			
			int productNum = productservice.getProductNum(member_code);
			model.addAttribute("productNum", productNum);
			
			Integer place_busino=placeservice.searchplace_busino(member_code);
			if(place_busino!=0){
				model.addAttribute("place_busino","1");
			}			
			if(member_email.length() == 0) {
				member_email = placeservice.readMember_email(member_code);
			}
			
			place_name = placeservice.read(member_email);	
			model.addAttribute("place_name",place_name);
			
			String categori1=placeservice.searchcategori1(member_code);
			model.addAttribute("mcate_code",categori1);
			
			String categori2=placeservice.searchcategori2(member_code);
			
			List<Request> request_list = requestservice.readMyPlaceRequestList(member_code);
			System.out.println(request_list);
			model.addAttribute("request_list", request_list);
			List<Budget> budget = placeservice.getBudgetInfoForMain(place_code);
			model.addAttribute("budget", budget);
			model.addAttribute("writeBtn",1);
			model.addAttribute("dcate_code",categori2);
			model.addAttribute("placeMPage", "placeManagerStats.jsp");
			model.addAttribute("placePage", "../manager/placeManager.jsp");
			model.addAttribute("cont", "place/place.jsp");
		
			List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
			MemberController.lng = memberForDistance.get(0).getMember_lat();
			MemberController.lat = memberForDistance.get(0).getMember_lng();
			model.addAttribute("lat", MemberController.lat);
			model.addAttribute("lng", MemberController.lng);
			
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("PlaceX",3);
			model.addAttribute("cont", "place/placeAddForm.jsp");
			
			List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
			MemberController.lng = memberForDistance.get(0).getMember_lat();
			MemberController.lat = memberForDistance.get(0).getMember_lng();
			model.addAttribute("lat", MemberController.lat);
			model.addAttribute("lng", MemberController.lng);
			return "index";
		}
	}
	
	
	@RequestMapping(value="m_place", method=RequestMethod.GET)
	public String m_place(Place place,Model model, HttpSession session, @ModelAttribute("member_code") int member_code,@ModelAttribute("member_email") String member_email){
		
		Object x = session.getAttribute("applogin");
		
		try {
			model.addAttribute("McateList", cateservice.McateList());  // 메인 카테고리 리스트
			model.addAttribute("DcateList", cateservice.DcateList());	// 세부 카테고리 리스트
		} catch (Exception e1) {
			e1.printStackTrace();
		}	
		try {
			placeservice.makechoice(member_code);
			place_logo= placeservice.searchlogo(member_code);
			model.addAttribute("place_logo",place_logo);
			place_code = placeservice.searchPlaceCode(member_code);
			model.addAttribute("place_code", place_code);
			
			int productNum = productservice.getProductNum(member_code);
			model.addAttribute("productNum", productNum);
			
			Integer place_busino=placeservice.searchplace_busino(member_code);
			if(place_busino!=0){
				model.addAttribute("place_busino","1");
			}			
			if(member_email.length() == 0) {
				member_email = placeservice.readMember_email(member_code);
			}
			
			place_name = placeservice.read(member_email);	
			model.addAttribute("place_name",place_name);
			
			String categori1=placeservice.searchcategori1(member_code);
			model.addAttribute("mcate_code",categori1);
			
			String categori2=placeservice.searchcategori2(member_code);
			
			List<Request> request_list = requestservice.readMyPlaceRequestList(member_code);
			System.out.println(request_list);
			model.addAttribute("request_list", request_list);

			
			model.addAttribute("writeBtn",1);
			
			model.addAttribute("dcate_code",categori2);
			model.addAttribute("placeMPage", "placeManagerStats.jsp");
			model.addAttribute("placePage", "../manager/placeManager.jsp");
		
			
			List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
			MemberController.lng = memberForDistance.get(0).getMember_lat();
			MemberController.lat = memberForDistance.get(0).getMember_lng();
			model.addAttribute("lat", MemberController.lat);
			model.addAttribute("lng", MemberController.lng);	
			return "/place/place";
		} catch (Exception e) {
			
			
			model.addAttribute("PlaceX",3);
			model.addAttribute("cont", "place/placeAddForm.jsp");
			return "index";
		}
	}
	
	
	
	
	@RequestMapping(value="postForm", method=RequestMethod.GET)
	public String postForm(Model model) throws Exception{
		model.addAttribute("placePage", "postForm.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo", place_logo);
		model.addAttribute("place_code", place_code);
		model.addAttribute("place_name", place_name);
		model.addAttribute("McateList", cateservice.McateList());	// 메인 카테고리 리스트
		model.addAttribute("DcateList", cateservice.DcateList());	// 세부 카테고리 리스트
		int dcate_code = placeservice.getPlaceDcate(place_code);
		model.addAttribute("dcate_code", dcate_code);
		// 현재 회원의 플레이스 카테고리 리스트
		place_code = placeservice.getPlaceCode(MemberController.member_code);
		List<Board> board = boardservice.selectBoardList(place_code);
		model.addAttribute("BoardList", board);
		// 플레이스 카테고리 중 첫번째 카테고리의 코드
		Board minboardcode = Collections.min(board, new BoardCodeAsc());
		model.addAttribute("minBoardCode", minboardcode.getBoard_code());
		// 현재 회원의 플레이스 코드
		model.addAttribute("placeCode", place_code);
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		return "index";
	}
	
	// 게시판 코드 오름차순 정렬
	class BoardCodeAsc implements Comparator<Board> {
	    @Override
	    public int compare(Board b1, Board b2) {
	        return b1.getBoard_code() < b2.getBoard_code() ? -1 : b1.getBoard_code() > b2.getBoard_code() ? 1:0;      
	    }
	}

	@RequestMapping(value="addPost", method=RequestMethod.POST)
	public String addPost(Model model, String[] content, String[] option_name 
			, Post post, Product product, String[] detail_name, int[] additional_price
			, int[] detailLength, HttpServletRequest req) throws Exception{

		model.addAttribute("placePage", "placeHome.jsp");
		model.addAttribute("cont", "place/place.jsp");
		productservice.insert(product);		//플레이스코드, 상품명, 상품이미지, 가격, 설명 insert
		postservice.insert(post);	// 게시판코드, 상품코드, 해시태그, 메인카테, 세부카테 insert
		
		// 카테고리가 공연이면 예매 가능한 날짜를 추가해준다.
		String[] result_enable_time;
		if(req.getParameter("enable_time").length() != 0) {
			String result_time = req.getParameter("enable_time");
			System.out.println(result_time);
			result_enable_time = result_time.split(";");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			for(int i = 0;i<result_enable_time.length;i++) {
				Date each_date = dateFormat.parse(result_enable_time[i]);
				long each_time = each_date.getTime();
				Timestamp result_timestamp = new Timestamp(each_time);
				
				placeservice.addEnableTime(productservice.getNewProductCode(), result_timestamp);
			}
		} else {
		}
		
		if(content != null) {
			PostContent postcontent = new PostContent();
			for(String postcon : content){
				postcontent.setContent(postcon);
				postcontentservice.insert(postcontent);	// 게시글코드, 게시글 내용 insert
			}
		}
		if(option_name != null && detail_name != null){
			Option option = new Option();
			Detail detail = new Detail();
			int j = 0;	// detailLength (각 옵션별 세부옵션 개수) 의 인덱스 
			int index = 0; // 세부옵션명과 추가가격의 인덱스 (배열 크기만큼 계속 증가시켜야하기때문) i를 사용하면 반복문 돌때마다 0으로 초기화하기때문에 안됨
			for(String optionName : option_name){
				option.setOption_name(optionName);
				optionservice.insert(option);		// 상품코드, 옵션명 insert    
				int optionCode = optionservice.selectOptionCode();	// 가장 마지막에 넣은 옵션코드를 읽어옴
				detail.setOption_code(optionCode);	// 세부옵션 테이블에 참조되는 옵션코드
				for(int i=0; i<detailLength[j]; i++){	
					// 각 옵션별 세부옵션 개수만큼 반복하면서 데이터를 집어넣음
					// ex) 옵션명 : 사이즈 , 세부옵션 : S, M, L 이면
					// 사이즈 코드 동일에 S, M, L 따로 insert 해야함
					detail.setDetail_name(detail_name[index]);
					detail.setAdditional_price(additional_price[index]);
					detailservice.insert(detail);		// 옵션코드, 세부옵션명, 추가가격 insert
					if(index < detail_name.length)
						index++;
				}
				if(j < detailLength.length){
					j++;
				}
			}
		}
		// 현재 회원의 플레이스 카테고리 리스트
		place_code = placeservice.getPlaceCode(MemberController.member_code);
		model.addAttribute("place_code", place_code);
		model.addAttribute("BoardList", boardservice.selectBoardList(place_code));
		// 현재 회원의 플레이스 로고와 플레이스 명
		model.addAttribute("place_logo",place_logo);
		model.addAttribute("place_name",place_name);
		// 현재 회원의 상품 리스트
		model.addAttribute("ProductList", productservice.selectProductList(place_code));
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
	@RequestMapping(value="placeMain", method=RequestMethod.GET)
	public String placeMain(Model model){
		model.addAttribute("cont", "place/placeMain.jsp");
		return "index";
	}
	
	// 플레이스 생성 폼
	@RequestMapping(value="placeAddForm", method=RequestMethod.GET)
	public String placeAddForm(Model model) throws Exception{
		model.addAttribute("cont", "place/placeAddForm.jsp");
		model.addAttribute("McateList", cateservice.McateList());	// 메인 카테고리 리스트
		model.addAttribute("DcateList", cateservice.DcateList());	// 세부 카테고리 리스트
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
	// 플레이스 생성
	@RequestMapping(value="create", method=RequestMethod.POST)	
	public String postBoardForm(Board board, Place place, RedirectAttributes rttr,Model model) throws Exception{
		placeservice.regist(place);
		
		boardservice.insertInit(board);
		
		model.addAttribute("place_name",place.getPlace_name());
		model.addAttribute("place_logo",place.getPlace_logo());
		place_code = placeservice.getPlaceCode(MemberController.member_code);
		model.addAttribute("place_code", place_code);
		model.addAttribute("mcate_code", place.getMcate_code());
		model.addAttribute("dcate_code", place.getDcate_code());
		model.addAttribute("placeMPage", "placeManagerStats.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		place_name = place.getPlace_name();
		return "index";
	}
   
	@SuppressWarnings("static-access")
	@RequestMapping(value="/{member_email}")
	public String page(@PathVariable("member_email")String member_email,Model model,Place place) throws Exception {

		memberEmail = member_email;

		if(member_email.matches("undefined")) {
			return "index";
		} else {
			member_code_for_email= memberservice.read2(memberEmail+".com");
			
			String place_logo= placeservice.searchlogo(member_code_for_email);
			model.addAttribute("place_logo",place_logo);
			
			String place_name = placeservice.read(memberEmail+".com");	
			model.addAttribute("place_name",place_name);
			
			place_code = placeservice.searchPlaceCode(member_code_for_email);
			model.addAttribute("place_code", place_code);
			
			String categori1=placeservice.searchcategori1(member_code_for_email);
			model.addAttribute("mcate_code",categori1);
			
			String categori2=placeservice.searchcategori2(member_code_for_email);
			model.addAttribute("dcate_code",categori2);
			
			model.addAttribute("placePage", "placeHome.jsp");
			model.addAttribute("cont", "place/place.jsp");
			// 현재 플레이스의 플레이스 카테고리 리스트
			place_code = placeservice.getPlaceCode(member_code_for_email);
			model.addAttribute("BoardList", boardservice.selectBoardList(place_code));

			// 현재 플레이스의 상품 리스트
			model.addAttribute("ProductList", productservice.selectProductList(place_code));
		
			List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
			MemberController.lng = memberForDistance.get(0).getMember_lat();
			MemberController.lat = memberForDistance.get(0).getMember_lng();
			model.addAttribute("lat", MemberController.lat);
			model.addAttribute("lng", MemberController.lng);	
			return "index";
		} 
		
	}
	
	// 게시글 폼화면
	@RequestMapping(value="postDefault")
	public String postDefault(Model model, int product_code,Member member, HttpSession session, HttpServletRequest req) throws Exception{
		//상품클릭시 위에 플레이스명 그대로 가져오기
		int member_code = Integer.parseInt(req.getParameter("member_code"));
		
		model.addAttribute("placePage", "postDefault.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("product_code",product_code); //광민
		//로그인한 회원의 회원 코드 

		model.addAttribute("member_code",member_code);
		// 상품 정보 가져오기
		Product product = productservice.selectProduct(product_code);
		model.addAttribute("product", product);
		//타입 가져오기(광민)
		String type=postservice.searchType(product_code);
		model.addAttribute("type",type);
		// 옵션 정보 가져오기
		List<Option> option = optionservice.selectOption(product_code);
		model.addAttribute("option", option);
		// 판매자 플레이스 정보 가져오기 (본일)
		List<Place> place = placeservice.getPlaceInfo(product.getPlace_code());
		model.addAttribute("dcate_code", place.get(0).getDcate_code());
		
		List<Enable> enable_time = productservice.getEnableTimes(product_code);
		if(!enable_time.isEmpty()) {
			String c = "";
			for(int i = 0;i<enable_time.size();i++) {
				if(i > 0 && i<enable_time.size()) {
					c +=",";
				}
				Date x = enable_time.get(i).getEnable_time();//책갈피
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
				c += transFormat.format(x);

			}
			model.addAttribute("enable_time", c);
		} else {}
		
		// 세부옵션 정보 가져오기
		JSONArray detailArray = new JSONArray();
		for(int i=0; i<option.size(); i++){
			 List<Detail> detail = detailservice.selectDetail(option.get(i).getOption_code());
			 for(int j=0; j<detail.size(); j++){
				 JSONObject detailJson = new JSONObject();
				 detailJson.put("detail_code", detail.get(j).getDetail_code());
				 detailJson.put("detail_name", detail.get(j).getDetail_name());
				 detailJson.put("option_code", detail.get(j).getOption_code());
				 detailJson.put("add_price", detail.get(j).getAdditional_price());
				 detailArray.add(detailJson);
			 }
		}
		model.addAttribute("detailArray", detailArray);
		// 게시글 내용 정보 가져오기
		int post_code = postservice.selectPostCode(product_code);
		model.addAttribute("post_code", post_code);
		List<PostContent> post_content = postcontentservice.selectPostContent(post_code);
		model.addAttribute("postContent", post_content);
		// 게시글의 플레이스 정보 가져오기
		model.addAttribute("place_logo", product.getPlace_logo());
		model.addAttribute("place_name", product.getPlace_name());
		model.addAttribute("place_code", product.getPlace_code());
		// 판매자의 정보 넘기기
		
		//판매자 맴버 코드로 그 플레이스가 사업자 플레이스인지 확인 
		Integer place_busino=placeservice.searchplace_busino( place.get(0).getMember_code());
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}	
		
		PlaceController.Cmember_code = place.get(0).getMember_code(); // 상품을 클릭할 때마다 PlaceController.java의 판매자 회원코드가 선언된다.
		model.addAttribute("Cmember_code", place.get(0).getMember_code());
		System.out.println(place.get(0).getMember_email());
		model.addAttribute("Cmember_email", place.get(0).getMember_email());
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		if(MemberController.member_code != 0) {
			MemberController.lng = memberForDistance.get(0).getMember_lat();
			MemberController.lat = memberForDistance.get(0).getMember_lng();
		}
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		return "index";
	}
	
	//신청하기 버튼 클릭시 
	@RequestMapping(value="postRequest", method=RequestMethod.POST)
	public String postRequest(Model model, HttpServletRequest req,Post post) throws Exception{
	
		int product_code = Integer.parseInt(req.getParameter("product_code"));
		int member_code = MemberController.member_code;
		int Cmember_code = PlaceController.Cmember_code;
		//여기서부터 광민 
		//상품을 신청한 회원의 멤버코드로 이름,주소,폰번호를 모델에 담아서 같이 넘겨줌
		List<Member> postRequest_listAll =memberservice.postRequest_listAll(member_code);
		model.addAttribute("tpostRequest_listAll",postRequest_listAll);
		
		String type2=req.getParameter("type");
		String type[]=type2.split(",");
		for(int k=0; k<type.length;k++){
			System.out.println(type[k]);
		}
		model.addAttribute("type",type);
		//광민
		String[] detail_code = req.getParameterValues("detail_code");
		List<Detail> detail_info = new ArrayList<Detail>();
		
		if(detail_code != null) {
			for(int k = 0; k<detail_code.length;k++) {
				Detail detail = productservice.selectDetailInfo(Integer.parseInt(detail_code[k]));
				detail_info.add(k, detail);
			}
		}

		model.addAttribute("detail_info", detail_info);
		model.addAttribute("product_price", Integer.parseInt(req.getParameter("product_price")));
		model.addAttribute("product_Total", Integer.parseInt(req.getParameter("product_Total")));
		model.addAttribute("type", type);
		
		model.addAttribute("placePage", "postRequest.jsp");
		model.addAttribute("cont", "place/place.jsp");
		
		
		// 상품 정보 가져오기
		Product product = productservice.selectProduct(product_code);
		model.addAttribute("product", product);
		// 옵션 정보 가져오기
		List<Option> option = optionservice.selectOption(product_code);
		model.addAttribute("option", option);
		// 판매자 플레이스 정보 가져오기 (본일)
		List<Place> place = placeservice.getPlaceInfo(product.getPlace_code());
		// 세부옵션 정보 가져오기

		// 게시글의 플레이스 정보 가져오기
		model.addAttribute("place_logo", product.getPlace_logo());
		model.addAttribute("place_name", product.getPlace_name());
		
		// 자신의 정보 넘기기
		model.addAttribute("member_code", member_code);
		// 판매자의 정보 넘기기
		model.addAttribute("Cmember_code", Cmember_code);
		
		model.addAttribute("member_email", placeservice.readMember_email(member_code));
		
		model.addAttribute("dcate_code", req.getParameter("dcate_code"));
		
		// 예약시 예약 정보 넘기기
		if(req.getParameter("request_list_reserve") != null) {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date to = transFormat.parse(req.getParameter("request_list_reserve"));
			
			SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
			String result_time = changeFormat.format(to);
			
			model.addAttribute("request_list_reserve", result_time);
		}
		
		if(req.getParameter("service_option_info_time") != null) {
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date to = transFormat.parse(req.getParameter("service_option_info_time"));
			
			SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
			String result_time = changeFormat.format(to);
			
			model.addAttribute("request_list_reserve", result_time);
		}
		
		if(req.getParameter("selected_out_date") != null) {
			System.out.println("오케이");
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date to1 = transFormat.parse(req.getParameter("selected_out_date"));
			Date to2 = transFormat.parse(req.getParameter("selected_enter_date"));
			
			SimpleDateFormat changeFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
			String selected_out_date = changeFormat.format(to1);
			String selected_enter_date = changeFormat.format(to2);
			
			model.addAttribute("selected_out_date", selected_out_date);
			model.addAttribute("selected_enter_date", selected_enter_date);
		}
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		return "index";
	}
	
	//장바구니 버튼 클릭 시 
	@RequestMapping(value="postcart", method=RequestMethod.POST)
	public String postcart(Model model, HttpServletRequest req,Post post,Member member,Request request) throws Exception{
	
	
		int product_code = Integer.parseInt(req.getParameter("product_code"));
		int member_code = Integer.parseInt(req.getParameter("member_code"));
		int total_price = Integer.parseInt(req.getParameter("product_Total"));
		
		//여기서부터 광민 
		System.out.println("맴버코드"+member_code);
		System.out.println("상품코드"+product_code);
		
		memberservice.cartinsert(member_code, product_code, total_price);
		
		//맴버코드로 회원의 카트코드를 찾음 
		int cart_code=memberservice.searchcartcode(member_code);
		System.out.println("카트코드"+cart_code);
		
		
		//광민
		String[] detail_code = req.getParameterValues("detail_code");		
		List<Detail> detail_info = new ArrayList<Detail>();
		if(detail_code != null) {
			for(int k = 0; k<detail_code.length;k++) {
				System.out.println(detail_code[k]);
				request.setDetail_code(Integer.parseInt(detail_code[k]));
				memberservice.cartoptioninsert(cart_code,Integer.parseInt(detail_code[k]));
			}
		}
		
		model.addAttribute("detail_info", detail_info);
		model.addAttribute("product_price", Integer.parseInt(req.getParameter("product_price")));
		model.addAttribute("product_Total", Integer.parseInt(req.getParameter("product_Total")));
	
		//다시 상품 내역 페이지로  리턴 하기 위에서 그대로 들고옴  
		model.addAttribute("placePage", "postDefault.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("product_code",product_code); //광민
		//로그인한 회원의 회원 코드 
		model.addAttribute("member_code",member.getMember_code());
		// 상품 정보 가져오기
		Product product = productservice.selectProduct(product_code);
		model.addAttribute("product", product);
		//타입 가져오기(광민)
		String type=postservice.searchType(product_code);
		model.addAttribute("type",type);
		// 옵션 정보 가져오기
		List<Option> option = optionservice.selectOption(product_code);
		model.addAttribute("option", option);
		// 판매자 플레이스 정보 가져오기 (본일)
		List<Place> place = placeservice.getPlaceInfo(product.getPlace_code());
		
		// 세부옵션 정보 가져오기
		JSONArray detailArray = new JSONArray();
		for(int i=0; i<option.size(); i++){
			 List<Detail> detail = detailservice.selectDetail(option.get(i).getOption_code());
			 for(int j=0; j<detail.size(); j++){
				 JSONObject detailJson = new JSONObject();
				 
				 
				 
				 
				 
				 detailJson.put("detail_code", detail.get(j).getDetail_code());
				 detailJson.put("detail_name", detail.get(j).getDetail_name());
				 detailJson.put("option_code", detail.get(j).getOption_code());
				 detailJson.put("add_price", detail.get(j).getAdditional_price());
				 detailArray.add(detailJson);
			 }
		}
		model.addAttribute("detailArray", detailArray);
		// 게시글 내용 정보 가져오기
		int post_code = postservice.selectPostCode(product_code);
		List<PostContent> post_content = postcontentservice.selectPostContent(post_code);
		model.addAttribute("postContent", post_content);
		// 게시글의 플레이스 정보 가져오기
		model.addAttribute("place_logo", product.getPlace_logo());
		model.addAttribute("place_name", product.getPlace_name());
		
		// 판매자의 정보 넘기기
		model.addAttribute("member_code", MemberController.member_code);
		System.out.println(place.get(0).getMember_email());
		model.addAttribute("member_email", place.get(0).getMember_email());

		model.addAttribute("placePage", "postDefault.jsp");
		model.addAttribute("cont", "place/place.jsp");

		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		return "index";
	}
	
	//결제하기 버튼 클릭시 
	@RequestMapping(value="moneysuccess", method=RequestMethod.POST)
	public String moneysuccess(Model model, Member member, HttpServletRequest req,String request_type,Request request) throws Exception{
			
		// 총가격
		int total_price = Integer.parseInt(req.getParameter("productTotalPrice"));
		
		System.out.println(member.getMember_code());
		
		//회원 db업데이트!! 
		memberservice.requestupdate(member);
		
		int a= member.getMember_code();
		//신청 테이블 인설트 
		requestservice.insertRequest(a);
		
		// Request request = new Request();
		int product_code = Integer.parseInt(req.getParameter("productCodeNum"));
		System.out.print("선택한타입"+request_type);
		request.setRequest_type(request_type);
		request.setProduct_code(product_code);
		request.setRequest_code(requestservice.readRequestCode(a));
		request.setRequest_list_totalprice(total_price);
		request.setRequest_content(request.getRequest_content());
		request.setRequest_addr(request.getRequest_addr());
		request.setRequest_phone(request.getRequest_phone());
		System.out.print(request.getRequest_content());
		requestservice.insertRequestList(request);
		
		// 예약시 예약 정보 넘기기
		if(req.getParameter("request_listReserve") != null) {
			SimpleDateFormat transFormat;
			System.out.println(req.getParameter("request_listReserve").length());
			if(req.getParameter("request_listReserve").length() == 13) {
				transFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
			} else {
				transFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
			}
			Date to = transFormat.parse(req.getParameter("request_listReserve"));
			System.out.println(to);
			Timestamp sq = new Timestamp(to.getTime());  
			System.out.println("타임스탬프는 : "+sq);
			requestservice.setReservation(sq);
		}

		request.setRequest_list_code(requestservice.readRequestListCode(request.getRequest_code()));

		if(req.getParameterValues("detailCodeNum") != null) {
			String[] detail_code = req.getParameterValues("detailCodeNum");
			for(int k = 0; k<detail_code.length;k++) {
				System.out.println(detail_code[k]);
				request.setDetail_code(Integer.parseInt(detail_code[k]));
				requestservice.insertRequestOption(request);
			}
		}		
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
	    model.addAttribute("cont", "main.jsp");
	    return "index";
	}
	
	// 카트에서 결제하기 클릭 시
	@RequestMapping(value="payByCart", method=RequestMethod.POST)
	public String payByCart(HttpServletRequest req, Model model) throws Exception {
		// 총가격
		
		int total_price = Integer.parseInt(req.getParameter("request_list_totalprice"));
		
		Member member = new Member();
		
		member.setMember_name(req.getParameter("member_name"));
		member.setMember_addr(req.getParameter("member_addr"));
		member.setMember_phone(Integer.parseInt(req.getParameter("member_phone")));
		
		//회원 db업데이트!! 
		memberservice.requestupdate(member);
		
		int a= MemberController.member_code;
		//신청 테이블 인설트 
		requestservice.insertRequest(a);
		
		int cart_code = 0;
		int product_code = 0;
		if(req.getParameter("cart_code")!= null) {
			cart_code = Integer.parseInt(req.getParameter("cart_code"));
			product_code = productservice.getProductCode(cart_code);
		} else {
			product_code = Integer.parseInt(req.getParameter("product_code"));
		}

		int request_code = requestservice.readRequestCode(a);
		
		Request request = new Request();
		
		request.setRequest_type(req.getParameter("request_type"));
		request.setProduct_code(product_code);
		request.setRequest_code(requestservice.readRequestCode(a));
		request.setRequest_list_totalprice(total_price);		
		request.setRequest_content(req.getParameter("request_content"));
		requestservice.insertRequestList(request);
		
		request.setRequest_list_code(requestservice.readRequestListCode(request_code));
		
		String detail_code = req.getParameter("detail_code");
		
		String[] detail_each = detail_code.split(",");
		
		if(req.getParameter("detail_code") != null ) {
			for(int k = 0; k<detail_each.length-1;k++) {
				request.setDetail_code(Integer.parseInt(detail_each[k]));
				requestservice.insertRequestOption(request);
			}
		}
		
		memberservice.delCartOption(cart_code);
		memberservice.delCart(cart_code);
		
		List<Member> list =memberservice.orderlist(MemberController.member_code);
		model.addAttribute("orderlist",list);
		System.out.print(list);
		model.addAttribute("cont", "mypage/orderList.jsp");
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="hasPlaceCode", method=RequestMethod.POST)
	public int hasPlaceCode(HttpServletRequest req) throws Exception {
		int x = Integer.parseInt(req.getParameter("member_code"));
		Integer y= placeservice.hasPlaceCode(x);
		return y;
	}
	
	@ResponseBody
    @RequestMapping(value="addFavorite", method=RequestMethod.POST)	
	public void addFavorite(HttpServletRequest req) throws Exception{
    	int place_code = Integer.parseInt(req.getParameter("place_code"));
    	int member_code = Integer.parseInt(req.getParameter("member_code"));
    	Place place = new Place();
    	place.setPlace_code(place_code);
    	place.setMember_code(member_code);
    	placeservice.addFavorite(place);
	}
    
    @ResponseBody
    @RequestMapping(value="delFavorite", method=RequestMethod.POST)
    public void delFavorite(HttpServletRequest req) throws Exception {
    	int place_code = Integer.parseInt(req.getParameter("place_code"));
    	int member_code = Integer.parseInt(req.getParameter("member_code"));
    	Place place = new Place();
    	place.setPlace_code(place_code);
    	place.setMember_code(member_code);
    	placeservice.delFavorite(place);
    }
    
    @ResponseBody
    @RequestMapping(value="getFavoriteExistence", method=RequestMethod.POST)
    public int getFavoriteExistence(HttpServletRequest req) throws Exception {
    	int place_code = Integer.parseInt(req.getParameter("place_code"));
    	int member_code = Integer.parseInt(req.getParameter("member_code"));
    	Place place = new Place();
    	place.setPlace_code(place_code);
    	place.setMember_code(member_code);
    	int result = placeservice.getFavoriteExistence(place);
    	return result;
    }
    
	@RequestMapping(value="delFavorite2", method={RequestMethod.GET})	
	public String delFavorite2(int place_code,int member_code,Model model) throws Exception{
		Place place = new Place();
		
    	place.setPlace_code(place_code);
    	place.setMember_code(member_code);
    	placeservice.delFavorite(place);
  
    	int existence = placeservice.getMyfavoriteExistence(MemberController.member_code);
		System.out.println(existence);
	
		if(existence != 0) {
			List<Favorite> favorite = placeservice.getFavoriteInfo(MemberController.member_code);
			model.addAttribute("favorite_info", favorite);
		}

		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		model.addAttribute("cont", "mypage/favorites.jsp");
		return "index";
	}
	
	@RequestMapping(value="writePostForMain", method=RequestMethod.GET)
	public String writePostForMain(Model model, HttpServletRequest req) throws Exception {
		
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
		
		List<Place> place = placeservice.getMyPlaceInfo(MemberController.member_code);
		model.addAttribute("placePage", "writePostForMain.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_code",PlaceController.place_code);
		model.addAttribute("place_name",PlaceController.place_name);
		model.addAttribute("member_code", MemberController.member_code);
		model.addAttribute("member_email", placeservice.readMember_email(MemberController.member_code));
		model.addAttribute("board_code", req.getParameter("board_code"));
		model.addAttribute("hideBootStrap", "오케이");
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
	@RequestMapping(value="writePostAsMain", method=RequestMethod.POST)
	public String writePostAsMain(Model model, HttpServletRequest req) throws Exception {
		
		String board_content = req.getParameter("writePostForMain_content");
		int board_code = Integer.parseInt(req.getParameter("board_code"));
		System.out.println(board_content);
		System.out.println(board_code);
		boardservice.writeBoardContent(board_code, board_content);
		
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
		
		List<Place> place = placeservice.getMyPlaceInfo(MemberController.member_code);
		model.addAttribute("placePage", "placeHome.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_code",PlaceController.place_code);
		model.addAttribute("place_name",PlaceController.place_name);
		model.addAttribute("member_code", MemberController.member_code);
		model.addAttribute("Cmember_code", MemberController.member_code);
		model.addAttribute("member_email", placeservice.readMember_email(MemberController.member_code));
		model.addAttribute("mcate_code", place.get(0).getMcate_code());
		model.addAttribute("dcate_code", place.get(0).getDcate_code());
		model.addAttribute("mainFormTime", "tryIt");
		model.addAttribute("board_content", board_content);
		model.addAttribute("board_code", board_code);
		List<Board> BoardList = boardservice.selectBoardList(PlaceController.place_code);
		model.addAttribute("BoardList", BoardList);
		
		List<Member> memberForDistance = memberservice.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
	
	@RequestMapping(value = "cropPlaceLogo", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public Object cropPlaceLogo(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, MultipartHttpServletRequest req) throws IOException{
		String sibal = request.getParameter("sibal");
        int x1=Integer.parseInt(request.getParameter("x"));
        int y1=Integer.parseInt(request.getParameter("y"));
        int x2=Integer.parseInt(request.getParameter("x2"));
        int y2=Integer.parseInt(request.getParameter("y2"));
        int w=Integer.parseInt(request.getParameter("w"));
        int h=Integer.parseInt(request.getParameter("h"));
        System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+w+" "+" "+h);

        
        String image = request.getParameter("imageName");
        Iterator<String> itr = req.getFileNames();
        File result = new File(placeAddFormPath);
        System.out.println("imageName"+image);
		if (itr.hasNext()) {
			MultipartFile mpf = req.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded!");
			
			try {
				// just temporary save file info into ufile
				System.out.println("file length : " + mpf.getBytes().length);
				System.out.println("file name : " + mpf.getOriginalFilename());
				String pathSet = request.getSession().getServletContext().getRealPath("/");
				String zx = pathSet.substring(0,17);
				System.out.println(zx+"\\Somebodyplace\\src\\main\\webapp\\resources\\img\\"+mpf.getOriginalFilename());
				mpf.transferTo(new File(zx+"\\Somebodyplace\\src\\main\\webapp\\resources\\img\\"+mpf.getOriginalFilename())); 
				
				BufferedImage bi = ImageIO.read(new File(zx+"\\Somebodyplace\\src\\main\\webapp\\resources\\img\\"+mpf.getOriginalFilename()));
				BufferedImage out = bi.getSubimage(x1, y1, w, h);
				
/*				BufferedImage out = resizeImage(bi, bi.getType());*/
				String savedPath = calculatePath(placeAddFormPath);
				UUID uid = UUID.randomUUID();
				result = new File(placeAddFormPath+savedPath, uid+mpf.getOriginalFilename());
				ImageIO.write(out,"jpg", result);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			result = new File(placeAddFormPath);
		}
		String er = result.getPath();
		System.out.println(er);
        response.setContentType("text/html");
        
		return new ResponseEntity<>(er, HttpStatus.CREATED);
	}
		
	private static String calculatePath(String placeAddFormPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		DecimalFormat df = new DecimalFormat("00");
		String monthPath = df.format(cal.get(Calendar.MONTH)+1);
		/*/2017/05 */
		monthPath = yearPath + File.separator + monthPath;
		
		/*/2017/05/12 */
		
		String datePath = File.separator+ df.format(cal.get(Calendar.DATE));
		datePath = monthPath+datePath;
		
		File folder = new File(placeAddFormPath+datePath);
		if(folder.exists()==false) {
			folder.mkdirs();
		}
		
		return datePath;
	}
	
	@ResponseBody  //占쏙옙환占싹깍옙占쏙옙占쌔쇽옙 (占쏙옙占쏙옙占쏙옙)
	@RequestMapping("displayPlaceLogo")
	public ResponseEntity<byte[]> displayPlaceLogo(String fileName)throws Exception{

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		//logger.info("File name : " + fileName);
      
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		String fileFormat = fileName.substring(fileName.lastIndexOf("."));
		//logger.info("ext:"+ext);
      
		MediaType mediaType = MediaUtils.getMediaType(ext.toLowerCase());

		HttpHeaders headers = new HttpHeaders();
		//uploadPath : resources/upload
		//fileName : /2017/05/18/ThumNail_rose_XXXXXXXX.jpg
      
		try{
			in = new FileInputStream(placeAddFormPath+fileName);
         
			System.out.println(in);
			if (mediaType != null) {
				headers.setContentType(mediaType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				System.out.println(fileName);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				String fN = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + fN + "\"");
			}

			byte[] data = IOUtils.toByteArray(in);
			entity = new ResponseEntity<byte[]>(data, headers, HttpStatus.CREATED);
         
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}

		return entity;
	}
	
	@RequestMapping(value = "cropPost", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public Object cropPost(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, MultipartHttpServletRequest req) throws IOException{
        double x1x=Double.parseDouble(request.getParameter("x"));
        double y1x=Double.parseDouble(request.getParameter("y"));
        double x2x=Double.parseDouble(request.getParameter("x2"));
        double y2x=Double.parseDouble(request.getParameter("y2"));
        double wx=Double.parseDouble(request.getParameter("w"));
        double hx=Double.parseDouble(request.getParameter("h"));
        
        int x1 = (int)Math.round(x1x);
        int y1 = (int)Math.round(y1x);
        int x2 = (int)Math.round(x2x);
        int y2 = (int)Math.round(y2x);
        int w = (int)Math.round(wx);
        int h = (int)Math.round(hx);
        int oriWidth = Integer.parseInt(request.getParameter("oriWidth"));
        int oriHeight = Integer.parseInt(request.getParameter("oriHeight"));
        double ratio = 0.0;
        double maxWidth = 800.0;
        double maxHeight = 800.0;
        
        if(oriWidth > 800) {
        	if(oriWidth>oriHeight) {
            	ratio = maxWidth / oriWidth;
            	resizing_width = 800;
            	resizing_height = (int)Math.round(oriHeight * ratio);       		
        	} else {
        	}
        }
        if(oriHeight > 800) {
        	if(oriWidth<oriHeight) {
            	ratio = maxHeight / oriHeight;
            	resizing_height = 800;
            	resizing_width = (int)Math.round(oriWidth * ratio);        		
        	} else {
        	}
        }
        if(oriWidth>800 && oriHeight>800 && (oriWidth-oriHeight)==0) {
        	resizing_height = 800;
        	resizing_width = 800;
        }
        
/*        System.out.println(x1+" "+y1+" "+x2+" "+y2+" "+w+" "+" "+h);*/
        System.out.println("원래 이미지의 크기는 : "+oriWidth+", "+oriHeight);
        System.out.println("리사이징 된 크기는 : "+resizing_width+", "+resizing_height);
        String image = request.getParameter("imageName");
        Iterator<String> itr = req.getFileNames();
        File result = new File(postFormPath);
        System.out.println("imageName"+image);
		if (itr.hasNext()) {
			MultipartFile mpf = req.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded!");
			try {
				// just temporary save file info into ufile
				System.out.println("file length : " + mpf.getBytes().length);
				System.out.println("file name : " + mpf.getOriginalFilename());
				String pathSet = request.getSession().getServletContext().getRealPath("/");
				System.out.println(pathSet);
				String zx = pathSet.substring(0,17);
				mpf.transferTo(new File(zx+"\\Somebodyplace\\src\\main\\webapp\\resources\\img\\"+mpf.getOriginalFilename())); 
				BufferedImage bi = ImageIO.read(new File(zx+"\\Somebodyplace\\src\\main\\webapp\\resources\\img\\"+mpf.getOriginalFilename()));
				BufferedImage out = bi.getSubimage(x1, y1, w, h);
				
				if(oriWidth > 800) {
					bi = resizeImage(bi, bi.getType());
					out = bi.getSubimage(x1, y1, w, h);
				}
				
				String savedPath = calculatePostPath(postFormPath);
				UUID uid = UUID.randomUUID();
				result = new File(postFormPath+savedPath, uid+mpf.getOriginalFilename());
				ImageIO.write(out,"jpg", result);
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} else {
			result = new File(postFormPath);
		}
		String er = result.getPath();
		System.out.println(er);
        response.setContentType("text/html");
        
		return new ResponseEntity<>(er, HttpStatus.CREATED);
	}
	
	private static String calculatePostPath(String postFormPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		DecimalFormat df = new DecimalFormat("00");
		String monthPath = df.format(cal.get(Calendar.MONTH)+1);
		/*/2017/05 */
		monthPath = yearPath + File.separator + monthPath;
		
		/*/2017/05/12 */
		
		String datePath = File.separator+ df.format(cal.get(Calendar.DATE));
		datePath = monthPath+datePath;
		
		File folder = new File(postFormPath+datePath);
		if(folder.exists()==false) {
			folder.mkdirs();
		}
		
		return datePath;
	}
	
	
	@ResponseBody  //占쏙옙환占싹깍옙占쏙옙占쌔쇽옙 (占쏙옙占쏙옙占쏙옙)
	@RequestMapping("displayProduct")
	public ResponseEntity<byte[]> displayProduct(String fileName)throws Exception{

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		//logger.info("File name : " + fileName);
      
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);
		String fileFormat = fileName.substring(fileName.lastIndexOf("."));
		//logger.info("ext:"+ext);
      
		MediaType mediaType = MediaUtils.getMediaType(ext.toLowerCase());

		HttpHeaders headers = new HttpHeaders();
		//uploadPath : resources/upload
		//fileName : /2017/05/18/ThumNail_rose_XXXXXXXX.jpg
      
		try{
			in = new FileInputStream(postFormPath+fileName);
         
			System.out.println(in);
			if (mediaType != null) {
				headers.setContentType(mediaType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				System.out.println(fileName);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				String fN = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				headers.add("Content-Disposition", "attachment; filename=\"" + fN + "\"");
			}

			byte[] data = IOUtils.toByteArray(in);
			entity = new ResponseEntity<byte[]>(data, headers, HttpStatus.CREATED);
         
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int type){
	    BufferedImage resizedImage = new BufferedImage(resizing_width, resizing_height, type);
	    Graphics2D g = resizedImage.createGraphics();
	    g.drawImage(originalImage, 0, 0, resizing_width, resizing_height, null);
	    g.dispose();

	    return resizedImage;
	}
	
	@RequestMapping(value="mainRequest", method=RequestMethod.GET)
	public String mainRequest(Model model, HttpServletRequest req) throws Exception {
		int dcate_code = Integer.parseInt(req.getParameter("dcate_code"));
		model.addAttribute("dcate_code", dcate_code);
		model.addAttribute("cont", "place/mainRequest.jsp");
		return "index";
	}
	
	@RequestMapping(value="payAboutDry", method=RequestMethod.POST)
	public String payAboutDry(Model model, HttpServletRequest req) throws Exception {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date first_date = dateFormat.parse(req.getParameter("request_firstDate"));
		Date second_date = dateFormat.parse(req.getParameter("request_secondDate"));
		
		long first_time = first_date.getTime();
		long second_time = second_date.getTime();
		Timestamp first_result = new Timestamp(first_time);
		Timestamp second_result = new Timestamp(second_time);
		
		System.out.println(first_result+", "+second_result);
			
		Member member = new Member();
		
		member.setMember_name(req.getParameter("member_name"));
		member.setMember_addr(req.getParameter("member_addr"));
		member.setMember_phone(Integer.parseInt(req.getParameter("member_phone")));
		
		//회원 db업데이트!! 
		memberservice.requestupdate(member);
		
		int a= MemberController.member_code;
		//신청 테이블 인설트 
		requestservice.insertRequest(a);
		
		int request_code = requestservice.readRequestCode(a);

		Request request = new Request();
		
		request.setRequest_type("예약");
		request.setRequest_code(request_code);
		request.setRequest_content(req.getParameter("request_content"));
		request.setService_code(1);
		request.setRequest_addr(req.getParameter("member_addr"));
		request.setRequest_phone(Integer.parseInt(req.getParameter("member_phone")));
		requestservice.insertRequestListAsService(request);

		for(int i =1;i<3;i++) {
			
			int service_option_code = i;
			
			if(i == 1) {
				serviceservice.setServiceOptionInfo(service_option_code, first_result);
				int service_option_info_code = serviceservice.getServiceOptionInfoCode(first_result);
				int request_list_code = requestservice.readRequestListCode(request_code);
				
				requestservice.insertRequestOptionAsService(request_list_code, service_option_info_code);
				
			} else if(i ==2) {
				serviceservice.setServiceOptionInfo(service_option_code, second_result);
				int service_option_info_code = serviceservice.getServiceOptionInfoCode(second_result);
				int request_list_code = requestservice.readRequestListCode(request_code);
				
				requestservice.insertRequestOptionAsService(request_list_code, service_option_info_code);
			}
			
		}
		
/*		
		
		if(req.getParameter("detail_code") != null ) {
			for(int k = 0; k<detail_each.length-1;k++) {
				request.setDetail_code(Integer.parseInt(detail_each[k]));
				requestservice.insertRequestOption(request);
			}
		}
		
		memberservice.delCartOption(cart_code);
		memberservice.delCart(cart_code);*/
		
		List<Member> list =memberservice.orderlist(MemberController.member_code);
		model.addAttribute("orderlist",list);
		System.out.print(list);
		model.addAttribute("cont", "mypage/orderList.jsp");
		return "index";
	}
}