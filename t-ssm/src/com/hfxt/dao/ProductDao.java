package com.hfxt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hfxt.entity.Product;

public interface ProductDao {
	public List<Product> getProducts();
	
	public int addPro(@Param("pro")Product pro);
}
