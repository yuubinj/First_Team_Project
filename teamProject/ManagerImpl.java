package teamProject;

import java.util.List;

public class ManagerImpl implements Manager {
	private ProductManagement productMangement = new ProductManagement();
	ChangeManagement changeManagement = new ChangeManagement();

	@Override
	public void insertProduct(String name, int price, String expiredDate, int count) {
		productMangement.insertProduct(name, price, expiredDate, count);

	}

	@Override
	public boolean updateProduct(String oldName, String newName, int newPrice, String newExpiredDate, int newCount) {
		return productMangement.updateProduct(oldName, newName, newPrice, newExpiredDate, newCount);
	}

	@Override
	public boolean deleteProduct(String name) {
		return productMangement.deleteProduct(name);
		
	}

	@Override
	public List<ProductVO> viewProduct() {
		return productMangement.productInfo();
	}

	@Override
	public boolean recharge(int countOf10000won, int countOf5000won, int countOf1000won,
			int countOf500won, int countOf100won) {
		return changeManagement.recharge(countOf10000won, countOf5000won, countOf1000won, countOf500won, countOf100won);
	}
	
	public ProductVO findByName(String name) {
		List<ProductVO> list = productMangement.productInfo();
		
		for (ProductVO vo : list) {
			if (name.equals(vo.getName())) {
				return vo;
			}
		}
		return null;
	}

	@Override
	public ChangeInVM howMuchManagerHave() {
		return changeManagement.howMuchManageHave();
	}

	@Override
	public boolean getProfit() {
		return changeManagement.getProfit();
	}
	
	@Override
	public boolean isValidDate(String strDate) {
		return productMangement.isValidDate(strDate);
	}
	
	@Override
	public boolean isExpired(String strDate) {
		return productMangement.isExpired(strDate);
	}
	

}
