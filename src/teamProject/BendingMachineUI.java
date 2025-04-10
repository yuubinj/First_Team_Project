package teamProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BendingMachineUI {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Manager mg = new ManagerImpl();
	private User us = new UserImpl();

	public void menu() {
		int ch1;
		int ch2;
		int ch3;
		
		while(true) {
			try {
				System.out.println("1.관리자 2.사용자");
				System.out.print("선택 => ");
				
				ch1 = Integer.parseInt(br.readLine());
				
				if(ch1 == 1) {
					System.out.println("1.등록 2.상품수정 3.상품삭제 4.재고확인 5.재고수정 6.동전충전 7.동전개수확인 8.총금액회수 9.종료");
					System.out.print("선택 => ");
					ch2 = Integer.parseInt(br.readLine());
				} else if(ch1 == 2) {
					
				}
				
				System.out.println("1.등록 2.수정 3.삭제 4.학번검색 5.이름검색 6.총점내림차순 7.이름오름차순 8.학번순 9.종료");
				System.out.print("선택 => ");
				
			} catch (Exception e) {
				
			}
		}
	}

}
