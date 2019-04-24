package pdf;

import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import shop.dao.CartDAO;
import shop.vo.CartVO;

public class PdfService {
	public String createPdf() {
		CartDAO dao = new CartDAO();
		String result = "";
		try {
			// com.itextpdf.text.Document
			Document document = new Document();	// pdf 문서 객체 생성
			// pdf writer 객체
			PdfWriter writer = PdfWriter.getInstance(document, 
					new FileOutputStream("/Users/kyeongmin/Documents/Java_study/sample/sample.pdf"));
			document.open();	// pdf 문서 열기
			BaseFont baseFont = BaseFont.createFont(
					"/Library/Fonts/NanumGothicLight.otf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(baseFont, 12);	// 폰트 설정
			PdfPTable table = new PdfPTable(4);	// 4컬럼의 테이블
			Chunk chunk = new Chunk("장바구니", font);		// 출력할 내용
			Paragraph ph = new Paragraph(chunk);	// 문단
			ph.setAlignment(Element.ALIGN_CENTER);	// 가운데 정렬
			document.add(ph);
			
			document.add(Chunk.NEWLINE);	// 줄바꿈 처리
			document.add(Chunk.NEWLINE);
			// document.newPage();	// 페이지 나누기
			
			// 테이블의 타이틀 행 생성
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell1);
			PdfPCell cell2 = new PdfPCell(new Phrase("단가", font));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell2);
			PdfPCell cell3 = new PdfPCell(new Phrase("수량", font));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell3);
			PdfPCell cell4 = new PdfPCell(new Phrase("금액", font));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell4);
			
			List<CartVO> items = dao.cartList("kim");
			
			for(int i = 0; i < items.size(); i++) {
				CartVO vo = items.get(i);
				PdfPCell cellProductName = new PdfPCell(new Phrase(vo.getProduct_name(), font));
				table.addCell(cellProductName);	// 상품명
				
				PdfPCell cellPrice = new PdfPCell(new Phrase("" + vo.getPrice(), font));
				table.addCell(cellPrice);	// 단가
				
				PdfPCell cellAmount = new PdfPCell(new Phrase("" + vo.getAmount(), font));
				table.addCell(cellAmount);	// 수량
				
				PdfPCell cellMoney = new PdfPCell(new Phrase("" + vo.getMoney(), font));
				table.addCell(cellMoney);	// 금액
				
				// 날짜 자료를 처리할 경우 참조 :
				// Date date = vo.getRegdate();	// java.util.Date
				// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				// String strDate = sdf.format(date);
			}
			document.add(table);
			document.close();	// pdf 문서 닫기
			result = "pdf 파일이 생성되었습니다.";
			
		} catch (Exception e) {
			e.printStackTrace();
			result = "pdf 파일 생성 실패...";
		}
		
		return result;
	}
}
