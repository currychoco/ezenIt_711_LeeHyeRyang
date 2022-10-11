package lms;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lms {
	private Scanner sc;
	private String brand;
	private int menu;
	private int sel;
	private ArrayList<Student> students;

	public Lms(String brand) {
		this.brand = brand;
		this.sc = new Scanner(System.in);
		this.students = new ArrayList<>();
	}

	// 메뉴 출력
	public void printMenu() {
		this.menu = -1;
		System.out.printf("=====%s=====\n", this.brand);
		System.out.println("[1] 추가");
		System.out.println("[2] 삭제");
		System.out.println("[3] 정렬");
		System.out.println("[4] 출력");
		System.out.println("[5] 저장");
		System.out.println("[6] 로드");
		System.out.println("[7] 종료");
		while (this.menu < 1 || this.menu > 7) {
			System.out.print("메뉴 : ");
			this.menu = this.sc.nextInt();
		}
	}

	// 추가 메뉴 출력
	public void printAddMenu() {
		this.sel = -1;
		System.out.printf("-----%s-----\n", this.brand);
		System.out.println("(1) 학생 추가");
		System.out.println("(2) 과목 추가");
		System.out.println("(3) 성적 수정");
		while (this.sel < 1 || this.sel > 3) {
			System.out.print("메뉴 : ");
			this.sel = this.sc.nextInt();
		}
	}

	// 삭제 메뉴 출력
	public void printDeleteMenu() {
		this.sel = -1;
		System.out.printf("-----%s-----\n", this.brand);
		System.out.println("(1) 학생 탈퇴");
		System.out.println("(2) 과목 삭제");
		while (this.sel < 1 || this.sel > 3) {
			System.out.print("메뉴 : ");
			this.sel = this.sc.nextInt();
		}
	}

	// 랜덤 학번 생성
	private int makeRandomNumber() {
		Random random = new Random();
		boolean isExistNumber = true;
		int number = -1;
		while (isExistNumber) {
			number = random.nextInt(9000) + 1000;
			isExistNumber = false;
			for (int i = 0; i < this.students.size(); i++) {
				if (this.students.get(i).getNumber() == number) {
					isExistNumber = true;
					break;
				}
			}
		}
		return number;
	}

	// 해당 학생이 존재하는지 확인
	private int isExistStudentNumber(int number) {
		int idx = -1;
		for (int i = 0; i < this.students.size(); i++) {
			if (this.students.get(i).getNumber() == number) {
				idx = i;
				break;
			}
		}
		return idx;
	}

	// 학생 추가
	public void addStudent() {
		System.out.print("학생 이름 입력 : ");
		String name = this.sc.next();
		int number = makeRandomNumber();
		this.students.add(new Student(number, name));
	}

	// 과목 추가
	public void addSubject() {
		System.out.print("과목을 추가할 학생 학번 : ");
		int studentNumber = this.sc.nextInt();
		int idx = isExistStudentNumber(studentNumber);
		if (idx == -1) {
			System.out.println("해당 학번의 학생이 존재하지 않습니다.");
			return;
		}

		System.out.print("추가할 과목 이름 : ");
		String title = this.sc.next();
		this.students.get(idx).addSubject(title);
		this.students.get(idx).calculateGrade();
	}

	// 점수 수정
	public void modificationScore() {
		System.out.print("점수를 수정할 학생 학번 : ");
		int studentNumber = this.sc.nextInt();
		int idx = isExistStudentNumber(studentNumber);
		if (idx == -1) {
			System.out.println("해당 학번의 학생이 존재하지 않습니다.");
			return;
		}

		System.out.print("성적을 수정할 과목 이름 : ");
		String title = this.sc.next();

		ArrayList<String> subjectTitles = this.students.get(idx).getSubjectTitle();

		boolean isExistSubject = false;
		int subjectIdx = -1;
		for (int i = 0; i < subjectTitles.size(); i++) {
			if (subjectTitles.get(i).equals(title)) {
				isExistSubject = true;
				subjectIdx = i;
				break;
			}
		}

		if (!isExistSubject) {
			System.out.println("해당 과목이 존재하지 않습니다.");
			return;
		}

		int score = -1;
		while (score < 0 || score > 100) {
			System.out.print("성적 입력 : ");
			score = this.sc.nextInt();
		}
		this.students.get(idx).setSubjectScores(subjectIdx, score);
		this.students.get(idx).calculateGrade();
	}

	// 학생 탈퇴

	// 과목 삭제

	// 정렬

	// 출력
	public void printStudents() {
		System.out.printf("-----%s-----", this.brand);
		for (Student s : this.students) {
			System.out.printf("학번 : %d, 이름 : %s, 성적 : %s, 과목 : ", s.getNumber(), s.getGrade(), s.getName());
			s.printSubject();
			System.out.println();
		}
	}

	// 저장

	// 로드
	public void run() {
		// 메뉴출력
		while (true) {

			this.printMenu();

			if (menu == 1) {
				this.printAddMenu();

				if (sel == 1) {
					this.addStudent();
				} else if (sel == 2) {
					this.addSubject();
				} else if (sel == 3) {
					this.modificationScore();
				}
			} else if (menu == 2) {

			} else if (menu == 3) {

			} else if (menu == 4) {
				this.printStudents();
			} else if (menu == 5) {

			} else if (menu == 6) {

			} else if (menu == 7) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}
