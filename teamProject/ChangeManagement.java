package teamProject;

public class ChangeManagement {
	private ChangeInVM ch = new ChangeInVM();
	int rechargeTotal;
	private int vmRecharge, salesProfit, totalProfit;

	public boolean recharge(int _10000, int _5000, int _1000, int _500, int _100) {

		try {
			ch.setCountOf10000won(_10000);
			ch.setCountOf5000won(_5000);
			ch.setCountOf1000won(_1000);
			ch.setCountOf500won(_500);
			ch.setCountOf100won(_100);

			int won = 10000 * _10000 + 5000 * _5000 + 1000 * _1000 + 500 * _500 + 100 * _100;
			rechargeTotal += won;

			return true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ChangeInVM howMuchManageHave() {
		return ch;
	}
// 총금액 회수
	public boolean getProfit() {
		
		 int total = ch.getTot();
		 vmRecharge = rechargeTotal;
		 salesProfit = total - rechargeTotal;
		 totalProfit = total;
		
		ch.setCountOf10000won(-ch.getCountOf10000won());
		ch.setCountOf5000won(-ch.getCountOf5000won());
		ch.setCountOf1000won(-ch.getCountOf1000won());
		ch.setCountOf500won(-ch.getCountOf500won());
		ch.setCountOf100won(-ch.getCountOf100won());

		rechargeTotal = 0;
		return true;
		}
	
	public int getVmRecharge() {
        return vmRecharge;
    }
    
    public int getSaleProfit() {
        return salesProfit;
    }
    
    public int getTotal() {
        return totalProfit;
    }
}