package lms;

public class Subject {
	private String title;
	private int score;
	
	public Subject(String title) {
		this.title = title;
	}
	
	public Subject(String title, int score) {
		this.title = title;
		this.score = score;
	}
	
	
	//getter & setter
	public String getTitle() {
		return this.title;
	}
	public int getScore() {
		return this.score;
	}
	
	//점수 수정
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return this.title + "(" + this.score + ")";
	}
}
