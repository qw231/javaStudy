package com.hfxt.controller;

import com.hfxt.service.ProductService;
import com.hfxt.service.SaleService;
import com.hfxt.service.UsersService;

import javax.annotation.Resource;

public class BaseController {

    @Resource
    protected UsersService usersService;

    @Resource
    protected ProductService productService;
    
    @Resource
    protected SaleService saleService;
   
}
