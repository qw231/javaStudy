package com.hfxt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.hfxt.common.Pager;
import com.hfxt.entity.Product;
import com.hfxt.entity.Sale;
import com.hfxt.entity.Users;

@Controller
@RequestMapping("/sale")
public class SaleController extends BaseController{
	
	@RequestMapping("/index")
	public String index(Pager<Sale> pager,Sale sale,@RequestParam(value="choice",defaultValue="totalprice")String choice,Model model){
		
		int total = saleService.getcount(sale);
		pager.setTotal(total);
		
		List<Sale> saleList=saleService.getSales(sale, choice, (pager.getCurrentPage()-1)*pager.getPageSize(), pager.getPageSize());
		pager.setPageRecords(saleList);
		
		List<Product> productlist=productService.getProducts();
		
		model.addAttribute("pager", pager);
		model.addAttribute("sale", sale);
		model.addAttribute("choice", choice);
		model.addAttribute("productlist", productlist);
		return "index";
	}
	
	@RequestMapping("del/{id}")
	@ResponseBody
	public String del(Model model,@PathVariable("id")Integer id,HttpSession session){
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			int result = saleService.delSale(id);
			if(result!=0){
				maps.put("code", 1);
				maps.put("msg", "删除成功");
			}else{
				maps.put("code", 0);
				maps.put("msg", "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return JSONArray.toJSONString(maps);
	}
	
	@RequestMapping("toadd")
	public String toadd(Model model){
		try {
			List<Product> productlist=productService.getProducts();
			model.addAttribute("productlist", productlist);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "edit";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(Model model,Sale sale,HttpSession session){
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			sale.setTotalprice(sale.getPrice()*sale.getQuantity());
			Users user = (Users) session.getAttribute("loginuser");
			sale.setUser(user);
			int result = saleService.addSale(sale);
			if(result!=0){
				maps.put("code", 1);
				maps.put("msg", "添加成功，销售编号是:"+sale.getSaleid());
			}else{
				maps.put("code", 0);
				maps.put("msg", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return JSONArray.toJSONString(maps);
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(Model model,Sale sale,HttpSession session){
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			sale.setTotalprice(sale.getPrice()*sale.getQuantity());
			Users user = (Users) session.getAttribute("loginuser");
			sale.setUser(user);
			int result = saleService.updateSale(sale);
			if(result!=0){
				maps.put("code", 1);
				maps.put("msg", "修改成功");
			}else{
				maps.put("code", 0);
				maps.put("msg", "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return JSONArray.toJSONString(maps);
	}
	
	@RequestMapping("toedit/{id}")
	public String toedit(Model model,@PathVariable("id")Integer id){
		try {
			List<Product> productlist=productService.getProducts();
			model.addAttribute("productlist", productlist);
			
			Sale sale = saleService.getSale(id);
			model.addAttribute("sale", sale);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "edit";
	}
	
	//@RequestMapping(value="show/{id}",produces="application/json;charset=UTF-8")
	@RequestMapping(value="show/{id}")
	@ResponseBody
	public String show(Model model,@PathVariable("id")Integer id){
		String result="";
		try {
			if(id==null || id.equals("")){
				return "nodata";
			}else{
				Sale sale = saleService.getSaleProUser(id);
				result=JSONArray.toJSONString(sale);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return result;		
	}
	
	@RequestMapping(value="show2/{id}")
	@ResponseBody
	public Sale show2(Model model,@PathVariable("id")Integer id){
		Sale sale = saleService.getSaleProUser(id);
		return sale;
	}
	
}
