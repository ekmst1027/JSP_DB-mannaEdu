package common.Util;

import javax.servlet.http.Cookie;

public class Util {
	// 쿠키변수명을 입력받아 쿠키값을 리턴하는 method
	public static String getCookie(Cookie[] cookies, String name) {
		String result = "";
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				// 쿠키변수명이 같으면
				if(cookies[i].getName().equals(name)) {
					result = cookies[i].getValue();	// 쿠키값을 저장
					break;
				}
			}
		}
		return result;
	}
}
