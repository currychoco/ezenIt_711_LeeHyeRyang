package lms;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private int number;
	private String name;
	private String grade;
	private List<Subject> subjects;

	public Student(int number, String name) {
		this.number = number;
		this.name = name;
		this.grade = "F";
		this.subjects = new ArrayList<>();
	}
	
	public Student(int number, String name, String grade, List<Subject> subjects) {
		this.number = number;
		this.name = name;
		this.grade = grade;
		this.subjects = subjects;
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
	
	public List<Subject> getSubjects() {
		return this.subjects;
	}

	// 그 외 기능 메소드는 용도에 맞게 public 또는 private 처리

	// 성적 계산
	public void calculateGrade() {
		int sum = 0;
		for (Subject s : subjects) {
			sum += s.getScore();
		}
		
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

	// 학생이 수강하고 있는 과목 이름 가져오기
	public List<String> getSubjectsTitle() {
		List<String> subjectTitles = new ArrayList<>();
		for (Subject s : this.subjects) {
			subjectTitles.add(s.getTitle());
		}
		return subjectTitles;
	}

	// 수강하고 있는 과목 정보 넘겨주기
	public String printSubject() {
		List<String> prints = new ArrayList<>();
		for(Subject s : this.subjects) {
			prints.add(s.toString());
		}
		return String.join(", ", prints);
	}
	

	// 학생이 수강하고 있는 과목 점수 수정하기
	public void setSubjectScores(int idx, int score) {
		this.subjects.get(idx).setScore(score);
	}

	// 과목 추가
	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}
	
	//과목 삭제하기
	public void deleteSubject(int idx) {
		this.subjects.remove(idx);
	}
}
