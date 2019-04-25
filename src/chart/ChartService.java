package chart;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import shop.dao.CartDAO;
import shop.vo.CartVO;

public class ChartService {
	// db에서 자료를 읽어서 json 형식으로 리턴하는 코드
	public JSONObject getChartData() {
		CartDAO cartDao = new CartDAO();
		List<CartVO> items = cartDao.cartMoney();
		
		// 리턴할 json 객체
		JSONObject data = new JSONObject();
		
		// 컬럼 구성
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		JSONArray title = new JSONArray();
		col1.put("label", "상품평");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		title.add(col1);
		title.add(col2);
		data.put("cols", title);
		
		JSONArray body = new JSONArray();	// 실제 데이터
		for(CartVO vo : items) {
			JSONObject name = new JSONObject();
			name.put("v", vo.getProduct_name());
			JSONObject money = new JSONObject();
			money.put("v", vo.getMoney());
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		return data;
	}

}
