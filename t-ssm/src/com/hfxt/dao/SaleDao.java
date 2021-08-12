package com.hfxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfxt.entity.Sale;

public interface SaleDao {
	
	public int getcount(@Param("sale")Sale sale);
	public List<Sale> getSales(@Param("sale")Sale sale,
			@Param("choice")String choice,
			@Param("pageIndex")Integer pageIndex,
			@Param("pageSize")Integer pageSize);
	
	public int delSale(@Param("saleid")Integer saleid);
	
	public int addSale(@Param("sale")Sale sale);
	public int updateSale(@Param("sale")Sale sale);
	public Sale getSale(@Param("saleid")Integer saleid);
	
	public Sale getSaleProUser(@Param("saleid")Integer saleid);

}
