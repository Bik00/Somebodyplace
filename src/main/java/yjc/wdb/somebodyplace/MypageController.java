package yjc.wdb.somebodyplace;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.SerialVersionUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import yjc.wdb.somebodyplace.bean.Auto;
import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Favorite;
import yjc.wdb.somebodyplace.bean.Issue;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.service.DetailService;
import yjc.wdb.somebodyplace.service.MemberService;
import yjc.wdb.somebodyplace.service.PlaceService;
import yjc.wdb.somebodyplace.service.ProductService;

@Controller
public class MypageController {
	
	@Inject
	private MemberService service;
	@Inject
	private PlaceService placeservice;
	@Inject
	private DetailService detailservice;
	@Inject
	private ProductService productservice;
	
	@RequestMapping(value="myPage", method=RequestMethod.GET)
	public String mypage(Model model,HttpSession session){
		Object x = session.getAttribute("applogin");
		if(x!=null){
			return "mypage/mypage";
		}
		else{
		
		List<Member> memberForDistance = service.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
			
		model.addAttribute("cont", "mypage/mypage.jsp");
		return "index";
		}
	
	}
	
	@RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public String modifyForm(Model model){
		
		List<Member> memberForDistance = service.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		model.addAttribute("cont", "mypage/modifyForm.jsp");
		return "index";
	}
	
	@RequestMapping(value="orderList", method=RequestMethod.GET)
	public String orderlist(Model model,String member_code,HttpSession session) throws Exception{
		int member_code2=MemberController.member_code;
		List<Member> list =service.orderlist(member_code2);
		model.addAttribute("orderlist",list);
		System.out.print(list);

		List<Member> memberForDistance = service.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		
		Object x = session.getAttribute("applogin");
		if(x!=null){
			return "mypage/orderList";
		}
		else{
		
		model.addAttribute("cont", "mypage/orderList.jsp");
		return "index";
		}
	}
	
	@RequestMapping(value="cartList", method=RequestMethod.GET)
	public String cart(Model model,HttpServletRequest req) throws Exception{
		
		int member_code=Integer.parseInt(req.getParameter("member_code"));
		
		List<Member> list=service.cartlist(member_code);
		model.addAttribute("cartlist",list);
		HashMap<Integer,List<Detail>> option=new HashMap<Integer,List<Detail>>();
		
		for(int z=0;z<list.size();z++){	
			System.out.println(list.get(z).getCart_code());
			List<Detail> detail = detailservice.getCartDetailInfo(list.get(z).getCart_code());
			option.put(z, detail);
		}
		model.addAttribute("cart_option", option);
		model.addAttribute("cont", "mypage/cart.jsp");
		
		List<Member> memberForDistance = service.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
	@RequestMapping(value="wishlist", method=RequestMethod.GET)
	public String wishlist(Model model){
		model.addAttribute("cont", "mypage/wishlist.jsp");
		return "index";
	}
	
	@RequestMapping(value="favorites", method=RequestMethod.GET)
	public String fovorties(Model model){
		int existence = placeservice.getMyfavoriteExistence(MemberController.member_code);
		
		if(existence != 0) {
			List<Favorite> favorite = placeservice.getFavoriteInfo(MemberController.member_code);
			model.addAttribute("favorite_info", favorite);
		}
		model.addAttribute("cont", "mypage/favorites.jsp");
		
		List<Member> memberForDistance = service.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
	@RequestMapping(value="interestSetting", method=RequestMethod.GET)
	public String interestSetting(Model model){
		String member_interest = service.getMemberInterest(MemberController.member_code);	
		model.addAttribute("member_interest", member_interest);		
		model.addAttribute("cont", "mypage/interestSetting.jsp");
		
		List<Member> memberForDistance = service.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
	@RequestMapping(value="delCart", method=RequestMethod.GET)
	public String delCart(Model model, HttpServletRequest req) throws Exception {
		
		int cart_code = Integer.parseInt(req.getParameter("cart_code"));
		service.delCartOption(cart_code);
		service.delCart(cart_code);
		
		int member_code=MemberController.member_code;
		
		List<Member> list=service.cartlist(member_code);
		model.addAttribute("cartlist",list);
		HashMap<Integer,List<Detail>> option=new HashMap<Integer,List<Detail>>();
		
		for(int z=0;z<list.size();z++){	
			System.out.println(list.get(z).getCart_code());
			List<Detail> detail = detailservice.getCartDetailInfo(list.get(z).getCart_code());
			option.put(z, detail);
		}
		model.addAttribute("cart_option", option);	
		
		model.addAttribute("cont", "mypage/cart.jsp");
		
		List<Member> memberForDistance = service.getMemberInfo(MemberController.member_code);
		MemberController.lng = memberForDistance.get(0).getMember_lat();
		MemberController.lat = memberForDistance.get(0).getMember_lng();
		model.addAttribute("lat", MemberController.lat);
		model.addAttribute("lng", MemberController.lng);
		return "index";
	}
	
    @ResponseBody
    @RequestMapping(value="getMyInfo", method=RequestMethod.GET)
    public JSONObject getMyInfo() throws Exception {
    	int member_code = MemberController.member_code;
    	List<Member> member = service.getMemberInfo(member_code);
    	JSONObject todoInfo = new JSONObject();
    	todoInfo.put("member_name", member.get(0).getMember_name());
    	todoInfo.put("member_addr", member.get(0).getMember_addr());
    	todoInfo.put("member_phone",member.get(0).getMember_phone());
    	return todoInfo;
    }
    
    @ResponseBody
    @RequestMapping(value="loadProductType", method=RequestMethod.GET)
    public JSONObject loadProductType(HttpServletRequest req) throws Exception {
    	int cart_code = Integer.parseInt(req.getParameter("cart_code"));
    	String cart_type = productservice.getProductType(cart_code);
    	JSONObject todoInfo = new JSONObject();
    	todoInfo.put("cart_type", cart_type);
    	return todoInfo;
    }
}
