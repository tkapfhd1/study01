package com.the.cho.service.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;

import com.the.cho.dao.DAOManager;
import com.the.cho.dto.Notice;
import com.the.cho.service.Service;
import com.the.cho.vo.PageInfo;
import com.the.cho.vo.SearchInfo;

public class NoticeDeleteService implements Service {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		delete 처리를 위한 서비스 클래스
		//쿼리스트링의 파라미터 name으로 값을 읽어온다 예> delete.bo?noForDelete=116
		//noForDelete이름의 값 116을 얻을수있다.근데 문자열로 읽어온다.
		String noForDelete=request.getParameter("noForDelete");
		//borad DB에 no컬럼이 number로 되어있으니 숫자로 변환하자.
		//문자를 숫자로 변환하기위해서 Wrapperclass를 이용하자
		int no=Integer.parseInt(noForDelete);
//		DB에 보내서 처리
		//1. 먼저 DB로 접속하자. DAO클래스에 접속할수있는 메서드 해놨아.
		int result=DAOManager.getNoticeDAO().delete(no);
		System.out.println("삭제결과 : "+result);
		//삭제가 잘되었는지 체크
		if(result==1) {
			System.out.println("정상삭제완료");
		}
		

		response.sendRedirect("notice.bo?side=1");
		
//		페이지 이동 정보 리턴
		return null;
	}

}
