package teamProject;

import java.util.List;

public interface Manager {
	public boolean deleteProduct(String name);
	
	public List<ProductVO> viewProduct();

	public boolean getProfit();
	void insertProduct(String name, int price, String expiredDate, int count);

	boolean updateProduct(String oldName, String newName, int newPrice, String newExpiredDate, int newCount);

	public ProductVO findByName(String name);
	boolean recharge(int countOf10000won, int countOf5000won, int countOf1000won, int countOf500won, int countOf100won);

	public ChangeInVM howMuchManagerHave();
	
	public boolean isValidDate(String strdate);
	
	public boolean isExpired(String strDate);
}
