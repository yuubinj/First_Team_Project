package teamProject;

public class VendingMachineVO {
	private ChangeInVM changeInVM = new ChangeInVM();
	
	private int money;

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public int get10000Count() {
		return money / 10000;
	}
	public int get5000Count() {
		return (money % 10000) / 5000;
	}
	public int get1000Count() {
		return (money % 5000) / 1000;
	}
	public int get500Count() {
		return (money % 1000) / 500;
	}
	public int get100Count() {
		return (money % 500) / 100;
	}
	
	public void sendToManager(int count10000, int count5000, int count1000, int count500, int count100) {
		changeInVM.setCountOf10000won(count10000);
		changeInVM.setCountOf5000won(count10000);
		changeInVM.setCountOf1000won(count10000);
		changeInVM.setCountOf500won(count10000);
		changeInVM.setCountOf100won(count10000);
	}
	
	
}
