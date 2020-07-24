package com.the.cho.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;

import com.the.cho.service.Service;

@WebServlet("*.bo")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Map<String,Service> classMap=new Hashtable<String,Service>();
      
	@Override
	public void init() throws ServletException {
		
		
		String resource="com/the/cho/service/notice/boardClass-ingo.properties";
		try {
//			1.properties에서 데이터 읽어오기
			Properties prop=Resources.getResourceAsProperties(resource);
//			2.읽어온 prop에서 key만 set으로 저장
			Set<Object> sets=prop.keySet();
			
			for(Object obj : sets) {
//				키생성
				String key=(String)obj;
//			3.set에 저장된 key를 이용해서 className얻어오기
				String className=(String)prop.get(key);
				try {
//					4.className을이용해서 Service객체로 생성
					Service service=(Service)Class.forName(className)
//							Service객체생성
							.getDeclaredConstructor().newInstance();
					
					classMap.put(key, service);
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		
	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		String[] strs=uri.split("/");
		String key=strs[strs.length-1];
		
		Service service=classMap.get(key);
		
//		로직실행
		String path=null;
		if(service!=null)
			path=service.excute(request, response);
		
//		페이지이동
		if(path!=null)
			request.getRequestDispatcher(path).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
