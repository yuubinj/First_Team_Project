package teamProject;

public class ManagerAuthentication {
	private String setId = "빈과그외2명";
	private String setPw = "54321";
	
	public boolean authentication(String id, String pw) {
		
		if(setId.equals(id) && setPw.equals(pw)) {
			return true;
		}
		return false;
	}
	
}
