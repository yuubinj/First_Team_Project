package teamProject;

public class ChangeInVM {

// 잔돈 충전 /잔돈 보기 / 총금액 회수			
	private int countOf10000won;
	private int countOf5000won;
	private int countOf1000won;
	private int countOf500won;
	private int countOf100won;

	public int getCountOf10000won() {
		return countOf10000won;
	}

	public void setCountOf10000won(int countOf10000won) {
		this.countOf10000won += countOf10000won;
	}

	public int getCountOf5000won() {
		return countOf5000won;
	}

	public void setCountOf5000won(int countOf5000won) {
		this.countOf5000won += countOf5000won;
	}

	public int getCountOf1000won() {
		return countOf1000won;
	}

	public void setCountOf1000won(int countOf1000won) {
		this.countOf1000won += countOf1000won;
	}

	public int getCountOf500won() {
		return countOf500won;
	}

	public void setCountOf500won(int countOf500won) {
		this.countOf500won += countOf500won;
	}

	public int getCountOf100won() {
		return countOf100won;
	}

	public void setCountOf100won(int countOf100won) {
		this.countOf100won += countOf100won;
	}

	public int getTot() {
		return 10000 * countOf10000won + 5000 * countOf5000won + 1000 * countOf1000won + 500 * countOf500won
				+ 100 * countOf100won;
	}
	
	
}