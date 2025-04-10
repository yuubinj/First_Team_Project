package teamProject;

import java.util.List;

public interface User {
	public List<ProductVO> userViewProduct();
	public boolean userBuyProduct(String name);

	public boolean putMoney(int Money);
	public int viewMoney();
}
