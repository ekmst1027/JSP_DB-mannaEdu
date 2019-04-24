package shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import shop.vo.ProductVO;
import sqlmap.MybatisManager;

public class ProductDAO {
	
	// 상품목록을 리스트에 저장하여 리턴하는 코드
	public List<ProductVO> listProduct() {
		// mybatis 객체
		SqlSession session = MybatisManager.getInstance().openSession();
		// product 네임스페이스의 list_product 쿼리 실행
		List<ProductVO> list = session.selectList("product.list_product");
		// 사용한 리소스 정리
		session.close();
		
		return list;
	}
	
	// 상품 상세정보 리턴
	public ProductVO detailProduct(int product_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		ProductVO vo = session.selectOne("product.detail_product", product_id);
		session.close();
		return vo;
	}
	
	// 상품 등록
	public void insertProduct(ProductVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("product.insert_product", vo);
		session.commit();
		session.close();
	}
	
	// 상품 수정
	public void updateProduct(ProductVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("product.update_product", vo);
		session.commit();
		session.close();
		
	}
	
	// 상품 삭제
	public void deleteProduct(int product_id) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("product.delete_product", product_id);
		session.commit();
		session.close();
	}

}
