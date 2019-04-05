package config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// 필터 : 요청이 있을 경우 선처리되는 클래스
@WebFilter("/*")	// 모든 액션에 대해 이 필터를 경유하도록 처리함
public class EncodingFilter implements Filter {
	
	private String charset = "utf-8";

	// 서버가 멈출 때 실행됨
	public void destroy() {	
//		System.out.println("필터가 제거되었습니다.");
	}

	// 요청이 들어올 때 실행됨
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
//		System.out.println("필터가 실행되었습니다.");
		request.setCharacterEncoding(charset);
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	// 서버가 시작될 때 실행됨
	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println("필터가 초기화되었습니다.");
	}

}
