package com.hfxt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hfxt.dao.ProductDao;
import com.hfxt.entity.Product;
import com.hfxt.service.ProductService;

@Service("productServiceImpl")
@Transactional(propagation=Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService{

	@Resource
	private ProductDao productDao;

	@Override
	public List<Product> getProducts() {
		return productDao.getProducts();
	}

	@Override
	public int addPro(Product pro) {
		return productDao.addPro(pro);
	}
}
