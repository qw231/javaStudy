package com.hfxt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hfxt.dao.SaleDao;
import com.hfxt.entity.Sale;
import com.hfxt.service.SaleService;

@Service("saleServiceImpl")
@Transactional(propagation=Propagation.REQUIRED)
public class SaleServiceImpl implements SaleService{

	@Resource
	private SaleDao saleDao;

	@Override
	public int getcount(Sale sale) {
		return saleDao.getcount(sale);
	}

	@Override
	public List<Sale> getSales( Sale sale,
			String choice,
			Integer pageIndex,
			Integer pageSize) {
		
		return saleDao.getSales(sale, choice, pageIndex, pageSize);
	}

	@Override
	public int delSale(Integer saleid) {
		return saleDao.delSale(saleid);
	}

	@Override
	public int addSale(Sale sale) {
		return saleDao.addSale(sale);
	}

	@Override
	public int updateSale(Sale sale) {
		return saleDao.updateSale(sale);
	}

	@Override
	public Sale getSale(Integer saleid) {
		return saleDao.getSale(saleid);
	}

	@Override
	public Sale getSaleProUser(Integer saleid) {
		return saleDao.getSaleProUser(saleid);
	}
}
