package yjc.wdb.somebodyplace;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.javassist.SerialVersionUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import yjc.wdb.somebodyplace.bean.Detail;
import yjc.wdb.somebodyplace.bean.Favorite;
import yjc.wdb.somebodyplace.bean.Issue;
import yjc.wdb.somebodyplace.bean.Member;
import yjc.wdb.somebodyplace.service.DetailService;
import yjc.wdb.somebodyplace.service.MemberService;
import yjc.wdb.somebodyplace.service.PlaceService;

@Controller
public class MypageController {
	
	@Inject
	private MemberService service;
	@Inject
	private PlaceService placeservice;
	@Inject
	private DetailService detailservice;
	
	@RequestMapping(value="myPage", method=RequestMethod.GET)
	public String mypage(Model model){
		model.addAttribute("cont", "mypage/mypage.jsp");
		return "index";
	}
	
	@RequestMapping(value="modifyForm", method=RequestMethod.GET)
	public String modifyForm(Model model){
		model.addAttribute("cont", "mypage/modifyForm.jsp");
		return "index";
	}
	
	@RequestMapping(value="orderList", method=RequestMethod.GET)
	public String orderlist(Model model,String member_code) throws Exception{
		int member_code2=Integer.parseInt(member_code);
	
		
		List<Member> list =service.orderlist(member_code2);
		model.addAttribute("orderlist",list);
		System.out.print(list);
		model.addAttribute("cont", "mypage/orderList.jsp");
		return "index";
	}
	
	@RequestMapping(value="cartList", method=RequestMethod.GET)
	public String cart(Model model,String member_code) throws Exception{
		
		int member_code2=Integer.parseInt(member_code);
		System.out.println(member_code2);
		
		List<Member> list=service.cartlist(member_code2);
		model.addAttribute("cartlist",list);
		
		System.out.println("맴버코드 : "+list.size());
		HashMap<Integer,List<Detail>> option=new HashMap<Integer,List<Detail>>();
		
		for(int z=0;z<list.size();z++){
			
			System.out.println(list.get(z).getCart_code());
			List<Detail> detail = detailservice.getCartDetailInfo(list.get(z).getCart_code());
			option.put(z, detail);
/*			for(int k=z+1;k<list.size();k++){
				System.out.print(list.get(z).getCart_code());
				System.out.println(list.get(k).getCart_code());
				if(list.get(z).getCart_code()==list.get(k).getCart_code()){
					
					a=list.get(z).getDetail_name();
					a+=list.get(k).getDetail_name();
					tt.add(a);
				}
			}	*/
		
		}
		model.addAttribute("cart_option", option);
		
		model.addAttribute("cont", "mypage/cart.jsp");
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
		return "index";
	}
	
	@RequestMapping(value="interestSetting", method=RequestMethod.GET)
	public String interestSetting(Model model){
		String member_interest = service.getMemberInterest(MemberController.member_code);	
		model.addAttribute("member_interest", member_interest);		
		model.addAttribute("cont", "mypage/interestSetting.jsp");
		return "index";
	}
	
	
}
