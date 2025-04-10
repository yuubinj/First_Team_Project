package teamProject;

import java.util.List;

public interface Manager {
	public void insertProduct(ProductVO vo);
	public void updateProduct(String name);
	public boolean deleteProduct(String name);
	
	public List<String> viewProduct();
	public void updateCount(String name);
	public int recharge(int money);
	
	public int viewMoney();
	public boolean getProfit();
}
