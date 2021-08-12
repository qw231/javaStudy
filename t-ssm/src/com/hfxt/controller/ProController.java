package com.hfxt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.scripting.bsh.BshScriptUtils.BshExecutionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.hfxt.entity.Product;

@Controller
@RequestMapping("/pro")
public class ProController extends BaseController{
	
	@RequestMapping("add")
	@ResponseBody
	public String add(Model model,Product product,
			@RequestParam(value="files")MultipartFile multipartFile,HttpServletRequest request){
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			if(!multipartFile.isEmpty()){
				//注意，idea编译器动态获取项目路径在out\artifacts\t97ssm_war_exploded
				String classpath = request.getSession().getServletContext().getRealPath("/statics/images");
				//System.out.println(classpath);
				//String classpath01="C:\\srbzCode";
				String oldname = multipartFile.getOriginalFilename();
				String type = FilenameUtils.getExtension(oldname);
				int fileSize = 5000000;
				//此处对上传文件大小和类型的验证，在前端页面，用js如何实现？？？
				if(multipartFile.getSize()>fileSize){
					maps.put("code", 0);
					maps.put("msg", "上传文件过大");
				}else if(!type.equals("jpg")&&!type.equals("png")&&!type.equals("bmp")){
					maps.put("code", 0);
					maps.put("msg", "上传文件类型不正确");
				}else{
					Random random = new Random();
					String newfilename = System.currentTimeMillis()+random.nextInt()+"."+type;
					File myfile = new File(classpath, newfilename);
					System.out.println(myfile.getAbsolutePath());
					multipartFile.transferTo(myfile);
					product.setImage(newfilename);
				}
				int result = productService.addPro(product);
				if(result!=0){
					maps.put("code", 1);
					maps.put("msg", "添加成功，商品编号是:"+product.getProductid());
				}else{
					maps.put("code", 0);
					maps.put("msg", "添加商品失败");
				}
				return JSONArray.toJSONString(maps);
			}else{
				maps.put("code", 0);
				maps.put("msg", "上传文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return JSONArray.toJSONString(maps);
	}
	
	@RequestMapping("add2")
	@ResponseBody
	public String add2(Model model,Product product,
			@RequestParam(value="files")MultipartFile[] multipartFiles,HttpServletRequest request){
		Map<String, Object> maps = new HashMap<String, Object>();
		for(MultipartFile multipartFile:multipartFiles){
			try {
				if(!multipartFile.isEmpty()){
					//注意，idea编译器动态获取项目路径在out\artifacts\t97ssm_war_exploded
					String classpath = request.getSession().getServletContext().getRealPath("/statics/images");
					//System.out.println(classpath);
					//String classpath01="C:\\srbzCode";
					String oldname = multipartFile.getOriginalFilename();
					String type = FilenameUtils.getExtension(oldname);
					int fileSize = 5000000;
					//此处对上传文件大小和类型的验证，在前端页面，用js如何实现？？？
					if(multipartFile.getSize()>fileSize){
						maps.put("code", 0);
						maps.put("msg", "上传文件过大");
					}else if(!type.equals("jpg")&&!type.equals("png")&&!type.equals("bmp")){
						maps.put("code", 0);
						maps.put("msg", "上传文件类型不正确");
					}else{
						Random random = new Random();
						String newfilename = System.currentTimeMillis()+random.nextInt()+"."+type;
						File myfile = new File(classpath, newfilename);
						System.out.println(myfile.getAbsolutePath());
						multipartFile.transferTo(myfile);
						product.setImage(newfilename);
					}
					int result = productService.addPro(product);
					if(result!=0){
						maps.put("code", 1);
						maps.put("msg", "添加成功，商品编号是:"+product.getProductid());
					}else{
						maps.put("code", 0);
						maps.put("msg", "添加商品失败");
					}
					return JSONArray.toJSONString(maps);
				}else{
					maps.put("code", 0);
					maps.put("msg", "上传文件不存在");
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		return JSONArray.toJSONString(maps);
	}
	
	@RequestMapping("down")
	public String down(String filename,HttpServletRequest request,HttpServletResponse response){
		//输入输出流
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//文件地址
		String filePath = "D:\\apache-tomcat-8.5.42\\webapps\\t97ssm\\statics\\images";
		//要下载的文件的具体路径
		String path = filePath+File.separator+filename;
		//封装成文件对象
		File file = new File(path);
		
		//设置下载参数
		long filelength = file.length();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Length",String.valueOf(filelength));
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		
		try {
			bis = new BufferedInputStream(new FileInputStream(path));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte []buf = new byte[1024];
			int len;
			while((len=bis.read(buf))!=-1){
				bos.write(buf,0,len);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			try {
				if(bos!=null) bos.close();
				if(bis!=null) bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
