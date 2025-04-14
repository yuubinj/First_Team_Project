package teamProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManagerUI {
	private Manager mg = null;
	private ChangeManagement cm;
	private ManagerAuthentication ma = new ManagerAuthentication();
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public ManagerUI(Manager mg){
		this.mg = mg;
		cm = ((ManagerImpl)mg).changeManagement;
	}
	
	public void menu() {
			while(true) {
				try {
				System.out.println("1.등록 2.상품수정 3.상품삭제 4.재고확인 5.동전충전 6.동전개수확인 7.총금액회수 8.종료 9.이전으로");
				System.out.print("선택 => ");
				int ch = Integer.parseInt(br.readLine());
				if (ch == 8) {
					System.out.println("\n종료");
					System.exit(0);
				}
				switch (ch) {
				case 1: insertProduct();break;
				case 2: updateProduct();break;
				case 3: deleteProduct();break;
				case 4: viewProduct();break;
				case 5: recharge();break;
				case 6: viewMoney();break;
				case 7: getProfit();break;
				case 9: return;
				}	
			
				} catch (NumberFormatException e) {
					System.out.println("메뉴 선택은 숫자로 입력되어야 합니다.\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	}

	// 관리자 로그인
	public boolean managerAuthentication() {
		try {
			System.out.print("아이디 : ");
			String id = br.readLine();

			System.out.print("비밀번호 : ");
			String pw = br.readLine();
			
			boolean loginSuccess = ma.authentication(id, pw);
			if(loginSuccess) {
				System.out.println("로그인 완료!\n");
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// 관리자 상품 등록
	public void insertProduct() {
		System.out.println("\n[상품 등록]");
		String name, expiredDate;
		int price, count;
		try {
			System.out.print("상품의 이름을 입력해주세요. => ");
			name = br.readLine();
			System.out.print("상품의 가격을 입력해주세요. => ");
			price = Integer.parseInt(br.readLine());
			if (price <= 0) {
				System.out.println("상품의 가격은 0보다 커야합니다.");
				return;
			}

			System.out.println("[형식 : yyyy-MM-dd, yyyy.MM.dd, yyyy/MM/dd]");
			System.out.print("상품의 유통기한을 입력해주세요. => ");
			expiredDate = br.readLine();
			if (mg.isValidDate(expiredDate) == false) {
				System.out.println("잘못된 날짜 형식입니다.\n");
				return;
			}
			
			if(mg.isExpired(expiredDate)) {
				System.out.println("유통기한이 지난 상품은 등록할 수 없습니다.");
				return;
			}
			
			System.out.print("상품의 재고를 입력해주세요. => ");
			count = Integer.parseInt(br.readLine());
			mg.insertProduct(name, price, expiredDate, count);

			System.out.println("상품 등록이 완료되었습니다.");
		} catch (IOException e) {
			System.out.println("입출력에 문제가 발생했습니다.");
		} catch (NumberFormatException e) {
			System.out.println("가격, 재고는 숫자로 입력해야 합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 관리자 상품 수정
	public void updateProduct() {
		System.out.println("\n[상품 수정]");
		String oldName, newName, newExpiredDate;
		int newPrice, newCount;
		boolean updateSuccess;
		try {
			System.out.print("수정할 상품의 이름을 입력해주세요. => ");
			oldName = br.readLine();
			if(mg.findByName(oldName)==null) {
				System.out.println("등록되지 않은 상품입니다.\n");
				return;
			}
			
			System.out.print("새로운 상품의 이름을 입력해주세요. => ");
			newName = br.readLine();

			System.out.print("새로운 상품의 가격을 입력해주세요. => ");
			newPrice = Integer.parseInt(br.readLine());
			if (newPrice <= 0) {
				System.out.println("상품의 가격은 0보다 커야합니다.\n");
				return;
			}
			System.out.println("[형식 : yyyy-MM-dd, yyyy.MM.dd, yyyy/MM/dd]");
			System.out.print("새로운 상품의 유통기한을 입력해주세요. => ");
			newExpiredDate = br.readLine();
			if (mg.isValidDate(newExpiredDate) == false) {
				System.out.print("잘못된 날짜 형식입니다.\n");
				return;
			}
			if(mg.isExpired(newExpiredDate)) {
				System.out.println("유통기한이 지난 상품은 등록할 수 없습니다.");
				return;
			}
			System.out.print("새로운 상품의 재고를 입력해주세요. => ");
			newCount = Integer.parseInt(br.readLine());
			updateSuccess = mg.updateProduct(oldName, newName, newPrice, newExpiredDate, newCount);
			if (updateSuccess) {
				System.out.println("상품이 수정되었습니다.");
			} else {
				System.out.println("등록되지 않은 상품입니다.");
			}
		} catch (IOException e) {
			System.out.println("입출력에 문제가 발생했습니다.");
		} catch (NumberFormatException e) {
			System.out.println("가격은 숫자로 입력해야 합니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 관리자 상품 삭제
	private void deleteProduct() {
		System.out.println("\n[상품 삭제]");
		String name;
		try {
			System.out.print("삭제할 상품 ? ");
			name = br.readLine();
			boolean b = mg.deleteProduct(name);
			if (!b) {
				System.out.println("등록된 상품이 아닙니다.\n");
				return;
			}
			System.out.println("상품이 삭제되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 상품 보기
	private void viewProduct() {
		try {
			System.out.println("\n[전체 리스트]");
			List<ProductVO> list = mg.viewProduct();
			System.out.println("상품명\t가격\t수량\t유통기한");
			System.out.println("-------------------------------------");
			Collections.sort(list, new Comparator<ProductVO>() {
				
				@Override
				public int compare(ProductVO o1, ProductVO o2) {
					return o1.getExpiredDate().compareTo(o2.getExpiredDate());
				}
			});
			for (ProductVO vo : list) {
				System.out.print(vo.getName() + "\t");
				System.out.print(vo.getPrice() + "\t");
				System.out.print(vo.getCount() + "\t");
				System.out.println(vo.getExpiredDate());
				
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 관리자 동전 충전
	private void recharge() {
		System.out.println("\n[관리자 돈 충전]");
		System.out.println("얼마를 충전하시겠습니까? ");
		try {
			System.out.print("만원권 : ");
			int countOf10000won = Integer.parseInt(br.readLine());
			System.out.print("오천원권 : ");
			int countOf5000won = Integer.parseInt(br.readLine());
			System.out.print("천원권 : ");
			int countOf1000won = Integer.parseInt(br.readLine());
			System.out.print("오백원권 : ");
			int countOf500won = Integer.parseInt(br.readLine());
			System.out.print("백원권 : ");
			int countOf100won = Integer.parseInt(br.readLine());

			boolean b = mg.recharge(countOf10000won, countOf5000won, countOf1000won, countOf500won, countOf100won);
			if (b) {
				// 얼마 충전되었는지 보여주기
				int amountCharge = 10000 * countOf10000won + 5000 * countOf5000won + 1000 * countOf1000won
						+ 500 * countOf500won + 100 * countOf100won;
				System.out.println(amountCharge + "원이 충전되었습니다.");
			}
		} catch (NumberFormatException e) {
			System.out.println("동전은 숫자로 입력하십시오.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}

	// 관리자 돈 보기
	private void viewMoney() {
		ChangeInVM ch = mg.howMuchManagerHave();
		System.out.println("\n[현재 자판기 동전 수]");
		System.out.print("현재 자판기에 남아있는 잔돈은 " + ch.getTot() + "원으로, ");
		System.out.println("지폐별 개수는 ");
		System.out.println("만원권 : " + ch.getCountOf10000won());
		System.out.println("오천원권 : " + ch.getCountOf5000won());
		System.out.println("천원권 : " + ch.getCountOf1000won());
		System.out.println("오백원권 : " + ch.getCountOf500won());
		System.out.println("백원권 : " + ch.getCountOf100won());
		System.out.println();
	}

	// 관리자 자판기 돈(총금액) 회수
	public void getProfit() {
		System.out.println("[총금액 회수]");
		mg.getProfit();
		System.out.println("자판기에 충전한 금액 : " + cm.getVmRecharge() + "원");
        System.out.println("수익: " + cm.getSaleProfit() + "원");
        System.out.println("총 회수금액 : " + cm.getTotal()+ "원");
        System.out.println();
    }
		
}
