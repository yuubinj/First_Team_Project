package teamProject;

public class Purchase implements UserPurchase {
	public boolean userBuyProduct(VendingMachineVO bVo, ChangeInVM ch, ProductVO vo, int count) {
		
		// 거스름돈 계산
		int change10000 = (bVo.getMoney() - vo.getPrice() * count) / 10000;
		int change5000 = ((bVo.getMoney() - vo.getPrice() * count) % 10000) / 5000;
		int change1000 = ((bVo.getMoney() - vo.getPrice() * count) % 5000) / 1000;
		int change500 = ((bVo.getMoney() - vo.getPrice() * count) % 1000) / 500;
		int change100 = ((bVo.getMoney() - vo.getPrice() * count) % 500) / 100;
		ch.setCountOf10000won(bVo.get10000Count());
		ch.setCountOf5000won(bVo.get5000Count());
		ch.setCountOf1000won(bVo.get1000Count());
		ch.setCountOf500won(bVo.get500Count());
		ch.setCountOf100won(bVo.get100Count());

		// 관리자가 동전 가지고 있는지 확인
		if (ch.getCountOf10000won() >= change10000 && ch.getCountOf5000won() >= change5000
				&& ch.getCountOf1000won() >= change1000 && ch.getCountOf500won() >= change500
				&& ch.getCountOf100won() >= change100) {
			System.out.printf("\n거스름돈 : %d\n", bVo.getMoney() - vo.getPrice() * count);

			// 관리자 동전개수 수정
			ch.setCountOf10000won(-change10000);
			ch.setCountOf5000won(-change5000);
			ch.setCountOf1000won(-change1000);
			ch.setCountOf500won(-change500);
			ch.setCountOf100won(-change100);
			vo.setCount(vo.getCount() - count);
		} else {
			System.out.println("현재 자판기 내에 거슬러 드릴 동전 수가 부족하여 구매가 불가능합니다. 죄송합니다.\n");
			ch.setCountOf10000won(-bVo.get10000Count());
			ch.setCountOf5000won(-bVo.get5000Count());
			ch.setCountOf1000won(-bVo.get1000Count());
			ch.setCountOf500won(-bVo.get500Count());
			ch.setCountOf100won(-bVo.get100Count());
			return false;
		}

		return true;
	}
}
