package teamProject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement {
	private List<ProductVO> list = new ArrayList<ProductVO>();

	public void insertProduct(String name, int price, String expiredDate, int count) {
		ProductVO vo = new ProductVO();
		vo.setName(name);
		vo.setPrice(price);
		vo.setExpiredDate(expiredDate);
		vo.setCount(count);
		list.add(vo);
	}

	public boolean updateProduct(String oldName, String newName, int newPrice, String newExpiredDate, int newCount) {
		for (ProductVO vo : list) {
			if (vo.getName().equals(oldName)) {
				vo.setName(newName);
				vo.setPrice(newPrice);
				vo.setExpiredDate(newExpiredDate);
				vo.setCount(newCount);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteProduct(String name) {
		for (ProductVO vo : list) {
			if (vo.getName().equals(name)) {
				list.remove(vo);
				return true;
			}
		}
		return false;
	}
	
	public List<ProductVO> productInfo() {
		return list;
	}
	
	// 날짜 형식 검사
	public boolean isValidDate(String strDate) {
		try {
			if (strDate.matches("\\d{4}[-./]\\d{2}[-./]\\d{2}") == false) {
				return false;
			}

			strDate = strDate.replaceAll("(\\-|\\.|\\/)", "");
			String s;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
			LocalDate localDate = LocalDate.parse(strDate, formatter);
			s = localDate.format(formatter);
			if (strDate.equals(s)) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}

		return false;
	}
	
	// 유통기한 검사
	public boolean isExpired(String strDate) {
		strDate = strDate.replaceAll("(\\-|\\.|\\/)", "");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate inputLocalDate = LocalDate.parse(strDate, formatter);
		LocalDate now = LocalDate.now();
	
		return inputLocalDate.isBefore(now);
	}
	
	
}