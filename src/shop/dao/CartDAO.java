package shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import shop.vo.CartVO;
import sqlmap.MybatisManager;

public class CartDAO {
	
	// 장바구니 목록
	public List<CartVO> cartList(String userid) {
		// mybatis 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		// 장바구니 목록 리턴
		List<CartVO> list = session.selectList("cart.list_cart", userid);
		// mybatis session 닫기
		session.close();
		// 리스트를 리턴
		return list;
	}
	
	// 장바구니에 담기
	public void cartInsert(CartVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("cart.insert_cart", vo);
		session.commit();
		session.close();
	}
	
	// 장바구니 금액 합계
	public int sumMoney(String userid) {
		int total = 0;
		SqlSession session = MybatisManager.getInstance().openSession();
		total = session.selectOne("cart.sum_money", userid);
		session.close();
		return total;
	}
	
	// 장바구니 비우기
	public void cartClear(String userid) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("cart.clear_cart", userid);
		session.commit();
		session.close();
	}
	
	// 장바구니 상품 개별 삭제
	public void cartDel(CartVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("cart.delete_cart", vo);
		session.commit();
		session.close();
	}
	
	// 장바구니 수정
	public void cartUpdate(CartVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("cart.update_cart", vo);
		session.commit();
		session.close();
	}
	
	// 상품별 장바구니 금액 통계
	public List<CartVO> cartMoney() {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<CartVO> items = session.selectList("cart.cart_money");
		session.close();
		return items;
	}
	
}









