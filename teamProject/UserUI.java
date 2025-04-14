package teamProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class UserUI {
	private Manager mg = null;
	private UserPurchase up = new Purchase(); 
	private VendingMachineVO bVo = new VendingMachineVO();
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public UserUI(Manager mg){
		this.mg = mg;
	}

	public void menu() {
		while (true) {
			try {
				System.out.println("1.상품목록보기 2.구매하기 3.금액투입 4.투입금액확인 5.종료 6.이전으로");
				System.out.print("선택 => ");
				int ch = Integer.parseInt(br.readLine());
				
				if (ch == 5) {
					System.out.println("\n종료");
					System.exit(0);
				}
				switch (ch) {
				case 1: userViewProduct();break;
				case 2: userBuyProduct();break;
				case 3: putMoney();break;
				case 4: countMoney();break;
				case 6: return;
				}
			} catch (NumberFormatException e) {
				System.out.println("메뉴 선택은 숫자로 입력되어야 합니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	}

	// 사용자 상품 목록 보기
	private void userViewProduct() {
		try {
			List<ProductVO> list = mg.viewProduct();
			System.out.println("\n[상품 목록]");
			
			System.out.println("상품명\t가격");
			System.out.println("---------------------");
			for (ProductVO vo : list) {
				System.out.println(vo.getName() + "\t" + vo.getPrice());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 사용자 상품 구매
	private void userBuyProduct() {
		userViewProduct();
		ChangeInVM ch = mg.howMuchManagerHave();

		try {
			System.out.print("구매할 상품 이름 ? ");
			String name = br.readLine();

			ProductVO vo = mg.findByName(name);
			if (vo == null) {
				System.out.println("상품이 존재하지 않습니다.\n");
				return;
			}

			System.out.print("구매할 수량 ? ");
			int count = Integer.parseInt(br.readLine());

			if (vo.getPrice() * count > bVo.getMoney()) {
				// 사용자의 금액이 부족할 경우
				System.out.printf("상품 가격: %d / 투입한 금액: %d\n", vo.getPrice(), bVo.getMoney());
				System.out.println("투입한 금액이 부족합니다.\n");
				return;
			} else if (count > vo.getCount()) {
				// 재고 수량이 부족한 경우
				System.out.printf("%s의 현재 구매 가능한 개수 : %d\n", name, vo.getCount());
				System.out.println("현재 재고 수량이 부족합니다. 죄송합니다.\n");
				return;
			}

			System.out.println("\n구매 진행 중입니다...");
			
			if(up.userBuyProduct(bVo, ch, vo, count)) {
				System.out.println("구매가 완료되었습니다. 맛있게 드세요!\n");
				bVo.setMoney(0);	
			}
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 사용자 금액 투입
	protected void putMoney() {

		System.out.println("\n[금액을 투입합니다.]");
		int money;

		try {
			System.out.print("얼마를 투입하시겠습니까? => ");

			money = Integer.parseInt(br.readLine());
			if (money <= 0) {
				System.out.println("금액은 0 이상의 값을 입력하세요.");
			} else {
				bVo.setMoney(bVo.getMoney() + money);
				System.out.println("[금액이 충전되었습니다.]");
			}
		} catch (NumberFormatException e) {
			System.out.println("금액은 숫자만 입력 가능합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 투입금액 확인
	protected void countMoney() {
		System.out.println("\n[투입 금액을 확인합니다.]");
		System.out.println("[투입 금액은 " + bVo.getMoney() + "원 입니다.]");
		System.out.println();
	}
}