package teamProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainUI {
	private Manager mg = new ManagerImpl();
	private ManagerUI managerUI = new ManagerUI(mg);
	private UserUI userUI = new UserUI(mg);
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	

	public void menu() {
		int ch;
		while (true) {
			try {
				System.out.println("1.관리자 2.사용자");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
				System.out.println();
				if (ch == 1) {
					if(managerUI.managerAuthentication()) {
						managerUI.menu();
					} else {
						System.out.println("ID나 비밀번호가 잘못되었습니다.");
					}
				} else {
					userUI.menu();
				}
			
			} catch (NumberFormatException e) {
				System.out.println("메뉴 선택은 숫자로 입력되어야 합니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println();
		}
		
	}
}
