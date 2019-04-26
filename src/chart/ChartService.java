package chart;

import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

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
	
	public void makeImage(List<CartVO> list, HttpServletResponse response) throws ServletException, IOException {
		// 파이 차트가 아닌 경우
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(CartVO vo : list) {
			dataset.setValue(vo.getMoney(), "과일", vo.getProduct_name());
		}
		
		JFreeChart chart = null;
		String title = "장바구니 통계";
		
		try {
			// 막대 그래프
			chart = ChartFactory.createBarChart(title, "상품명", "금액", dataset, 
					PlotOrientation.VERTICAL, true, true, false);

			Font font = new Font("돋움", Font.PLAIN, 12);
			Color color = new Color(0, 0, 0);
			
			// 제목
			chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15));
 			
			// 범례
			chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10));
			
			StandardChartTheme chartTheme =
					(StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);
			
			chartTheme.setAxisLabelPaint(color);
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			chartTheme.apply(chart);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ChartUtilities.writeChartAsPNG(response.getOutputStream(), chart, 900, 550);
		
	}
	 
	// 차트를 pdf로 저장
	public String makePdf() {
		String message = "";
		try {
			JFreeChart chart = createChart();	// 차트 생성
			Document document = new Document();	// itextpdf 파일
			try {
				PdfWriter.getInstance(document, new FileOutputStream("/Users/kyeongmin/Documents/Java_study/sample/sample.pdf"));
				document.open();
				// 차트를 png 이미지로
				Image png = Image.getInstance(ChartUtilities.encodeAsPNG(chart.createBufferedImage(500, 500)));
				document.add(png);	// pdf에 이미지 추가
				document.close();	// pdf 저장 완료
				message = "pdf 파일이 생성되었습니다.";
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "pdf 파일을 만들지 못했습니다.";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	public JFreeChart createChart() {
		CartDAO cartDao = new CartDAO();
		List<CartVO> list = cartDao.cartMoney();
		
		// 파이 차트가 아닌 경우
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(CartVO vo : list) {
			dataset.setValue(vo.getMoney(), "과일", vo.getProduct_name());
		}
		
		JFreeChart chart = null;
		String title = "장바구니 통계";
		
		try {
			// 막대 그래프
			chart = ChartFactory.createBarChart(title, "상품명", "금액", dataset, 
					PlotOrientation.VERTICAL, true, true, false);

			Font font = new Font("돋움", Font.PLAIN, 12);
			Color color = new Color(0, 0, 0);
			
			// 제목
			chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15));
 			
			// 범례
			chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10));
			
			StandardChartTheme chartTheme =
					(StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
			chartTheme.setExtraLargeFont(font);
			chartTheme.setLargeFont(font);
			chartTheme.setRegularFont(font);
			chartTheme.setSmallFont(font);
			
			chartTheme.setAxisLabelPaint(color);
			chartTheme.setLegendItemPaint(color);
			chartTheme.setItemLabelPaint(color);
			chartTheme.apply(chart);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return chart; 
	}
	

}
