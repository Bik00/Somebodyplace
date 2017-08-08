package yjc.wdb.somebodyplace;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import yjc.wdb.somebodyplace.bean.Board;
import yjc.wdb.somebodyplace.bean.Budget;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.bean.Place;
import yjc.wdb.somebodyplace.bean.Request;
import yjc.wdb.somebodyplace.service.BoardService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.ProductService;
import yjc.wdb.somebodyplace.service.RequestService;

@Controller
public class ManagerController {
	@Inject
	private RequestService requestservice;
	@Inject
	private PlaceService placeservice;
	@Inject
	private BoardService boardservice;
	@Inject
	private ProductService productservice;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Resource(name="placeAddFormPath")
	private String placeAddFormPath;
	
	
	// 플레이스 관리 페이지 메인
	@RequestMapping(value="placeManager", method=RequestMethod.GET)
	public String mypage(Model model, @ModelAttribute("member_code") String member_code) throws Exception {
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
		int place_code = placeservice.getPlaceCode(MemberController.member_code);
		List<Budget> budget = placeservice.getBudgetInfoForMain(place_code);
		model.addAttribute("budget", budget);
		model.addAttribute("placeMPage", "placeManagerStats.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",	PlaceController.place_name);
		return "index";
	}
	
	// 플레이스 수정
	@RequestMapping(value="placemodify", method=RequestMethod.GET)
	public String placemodify(Model model) throws Exception {
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
		
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
		System.out.println(myPlace.get(0).getPlace_logo());
		PlaceController.place_name = myPlace.get(0).getPlace_name();
		
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
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
	public String requestList(Model model) throws Exception {

		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
		
		List<Request> request_list = requestservice.readMyPlaceRequestList(MemberController.member_code);
		System.out.println(request_list);
		model.addAttribute("request_list", request_list);
		model.addAttribute("placeMPage", "requestList.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		return "index";
	}
	
	// 사업자 등록
	@RequestMapping(value="addBusiness", method=RequestMethod.GET)
	public String 	addBusiness(Model model) throws Exception {
		
		String place_busino = placeservice.getBusino(MemberController.member_code);
		System.out.println(place_busino);
		if(place_busino == null) {
			place_busino = "";
		}
		String arrayBusino[] = place_busino.split("-");
		for(int k = 0;k<arrayBusino.length;k++) {
			model.addAttribute("place_busino"+k+"", arrayBusino[k]);
		}
		
		Integer place_busino1=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino1!=0){
			model.addAttribute("place_busino","1");
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
		
		Integer place_busino1=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino1!=0){
			model.addAttribute("place_busino","1");
		}
		model.addAttribute("placeMPage", "placeManagerStats.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		model.addAttribute("member_code", MemberController.member_code);
		model.addAttribute("member_email", placeservice.readMember_email(MemberController.member_code));
		return "index";
	}
	
	@RequestMapping(value="deleteBusiness", method=RequestMethod.GET)
	public String deleteBusiness(Model model, HttpServletRequest req) throws Exception {
		int member_code = Integer.parseInt(req.getParameter("member_code"));
		
		placeservice.deleteplace_busino(member_code);
		
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
		model.addAttribute("placeMPage", "placeManagerStats.jsp");
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
	public String categorySetting(Model model) throws Exception{
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
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
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}		
		// 현재 회원의 플레이스 카테고리 리스트
		place_code = placeservice.getPlaceCode(MemberController.member_code);
		model.addAttribute("BoardList", boardservice.selectBoardList(place_code));
		// 현재 회원의 플레이스 로고와 플레이스 명
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		// 현재 회원의 상품 리스트
		model.addAttribute("ProductList", productservice.selectProductList(place_code));
		model.addAttribute("member_code", MemberController.member_code);
		model.addAttribute("Cmember_code", MemberController.member_code);
		return "index";
	}
	// 정산 관리
	@RequestMapping(value="currentBudget", method=RequestMethod.GET)
	public String 	currentBudget(Model model) throws Exception {
		int place_code = placeservice.getPlaceCode(MemberController.member_code);
		Integer place_busino=placeservice.searchplace_busino(MemberController.member_code);
		if(place_busino!=0){
			model.addAttribute("place_busino","1");
		}
		List<Budget> budget = placeservice.getBudgetInfo(place_code);
		List<Budget> budget2 = placeservice.getBudgetInfoByImpo(place_code);
		model.addAttribute("budget_info", budget);
		model.addAttribute("budget_info_impo", budget2);
		model.addAttribute("placeMPage", "currentBudget.jsp");
		model.addAttribute("placePage", "../manager/placeManager.jsp");
		model.addAttribute("cont", "place/place.jsp");
		model.addAttribute("place_logo",PlaceController.place_logo);
		model.addAttribute("place_name",PlaceController.place_name);
		return "index";
	}
	
	@ResponseBody
	@RequestMapping(value="setRequestType", method=RequestMethod.POST)
	public String setRequestType(Model model, HttpServletRequest req) throws Exception{
		String request_status = req.getParameter("request_status");
		int request_code = Integer.parseInt(req.getParameter("request_code"));
		int member_code = MemberController.member_code;
		
		requestservice.setRequestType(request_status, member_code, request_code);
		return request_status;
	}
	
	@ResponseBody
	@RequestMapping(value="calculateBudget", method=RequestMethod.POST)
	public String calculateBudget(Model model, HttpServletRequest req) throws Exception{
		int budget_month = Integer.parseInt(req.getParameter("budget_month"));
		int place_code = PlaceController.place_code;
		requestservice.calculateBudget(budget_month, place_code);
		return "뿡뿡";
	}
	
	@RequestMapping(value = "modifyPlaceLogo", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public Object modifyPlaceLogo(HttpServletRequest request, HttpServletResponse response,
            HttpSession session, MultipartHttpServletRequest req) throws IOException{
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
}