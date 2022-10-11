package lms;

import java.util.ArrayList;

public class Student {
	private int number;
	private String name;
	private String grade;
	private ArrayList<Subject> subjects;

	public Student(int number, String name) {
		this.number = number;
		this.name = name;
		this.grade = "F";
		this.subjects = new ArrayList<>();
	}

	// Getter & Setter
	public int getNumber() {
		return this.number;
	}

	public String getName() {
		return this.name;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setName(String name) {
		this.name = name;
	}

	// 그 외 기능 메소드는 용도에 맞게 public 또는 private 처리

	// 성적 계산
	public void calculateGrade() {
		int sum = 0;
		for (Subject s : subjects) {
			sum += s.getScore();
			int avg = sum / this.subjects.size();
			if (avg >= 90) {
				this.grade = "A";
			} else if (avg >= 80) {
				this.grade = "B";
			} else if (avg >= 70) {
				this.grade = "C";
			} else if (avg >= 60) {
				this.grade = "D";
			} else {
				this.grade = "F";
			}
		}
	}

	// 학생이 수강하고 있는 과목 이름 가져오기
	public ArrayList<String> getSubjectTitle() {
		ArrayList<String> subjectTitles = new ArrayList<>();
		for (Subject s : this.subjects) {
			subjectTitles.add(s.getTitle());
		}
		return subjectTitles;
	}

	// 수강하고 있는 과목 정보 출력하기
	public void printSubject() {
		for (int i = 0; i < this.subjects.size(); i++) {
			System.out.printf("%s(%d)", this.subjects.get(i).getTitle(), this.subjects.get(i).getScore());
			if (i < this.subjects.size() - 1) {
				System.out.print(", ");
			}
		}
	}

	// 학생이 수강하고 있는 과목 점수 수정하기
	public void setSubjectScores(int idx, int score) {
		this.subjects.get(idx).setScore(score);
	}

	// 과목 추가
	public void addSubject(String title) {
		this.subjects.add(new Subject(title));
	}
}
