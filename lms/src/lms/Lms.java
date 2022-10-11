package lms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Lms {
	private Scanner sc;
	private String brand;
	private int menu;
	private int sel;
	private ArrayList<Student> students;
	private FileManager fileManager;

	public Lms(String brand) {
		this.brand = brand;
		this.sc = new Scanner(System.in);
		this.students = new ArrayList<>();
		fileManager = new FileManager();
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
		System.out.print("학생이 수강할 기본 과목 : ");
		String title = this.sc.next();
		this.students.get(this.students.size() - 1).addSubject(new Subject(title));
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
		this.students.get(idx).addSubject(new Subject(title));
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

		List<String> subjectTitles = this.students.get(idx).getSubjectsTitle();

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
	public void deleteStudent() {
		System.out.print("탈퇴할 학생 학번 : ");
		int studentNumber = this.sc.nextInt();
		int idx = isExistStudentNumber(studentNumber);
		if (idx == -1) {
			System.out.println("해당 학번의 학생이 존재하지 않습니다.");
			return;
		}
		this.students.remove(idx);
	}

	// 과목 삭제
	public void deleteSubject() {
		System.out.print("과목을 삭제할 학생의 학번 : ");
		int studentNumber = this.sc.nextInt();
		int idx = isExistStudentNumber(studentNumber);
		if (idx == -1) {
			System.out.println("해당 학번의 학생이 존재하지 않습니다.");
			return;
		}
		System.out.print("삭제할 과목 이름 : ");
		String title = this.sc.next();
		List<String> subjectTitle = this.students.get(idx).getSubjectsTitle();

		boolean isExistSubjectTitle = false;
		int subjectIdx = -1;
		for (int i = 0; i < subjectTitle.size(); i++) {
			if (subjectTitle.get(i).equals(title)) {
				isExistSubjectTitle = true;
				subjectIdx = i;
				break;
			}
		}
		if (!isExistSubjectTitle) {
			System.out.println("해당 학생이 수강하고 있지 않은 과목입니다.");
			return;
		}
		this.students.get(idx).deleteSubject(subjectIdx);
	}

	// 정렬
	public void sort() {
		for (int i = 0; i < this.students.size() - 1; i++) {
			for (int j = i + 1; j < this.students.size(); j++) {
				if (this.students.get(i).getNumber() > this.students.get(j).getNumber()) {
					Collections.swap(this.students, i, j);
				}
			}
		}
	}

	// 출력
	public void printStudents() {
		System.out.printf("-----%s-----\n", this.brand);
		for (Student s : this.students) {
			System.out.printf("학번 : %d, 이름 : %s, 성적 : %s, 과목 : ", s.getNumber(), s.getName(), s.getGrade());
			System.out.println(s.printSubject());
		}
	}

	// 저장
	public void save() {
		String data = "";
		for (int i = 0; i < this.students.size(); i++) {
			data += this.students.get(i).getNumber() + "/" + this.students.get(i).getName() + "/"
					+ this.students.get(i).getGrade() + "/";
			List<String> titles = this.students.get(i).getSubjectsTitle();
			for (int j = 0; j < titles.size(); j++) {
				data += titles.get(j);
				if (j < titles.size() - 1) {
					data += ",";
				} else {
					data += "/";
				}
			}
			for (int j = 0; j < titles.size(); j++) {
				data += this.students.get(i).getSubjects().get(j).getScore();
				if (j < titles.size() - 1) {
					data += ",";
				}
			}

			if (i < this.students.size() - 1) {
				data += "\n";
			}
		}
		this.fileManager.save(data);
	}

	// 로드
	public void load() {
		String data = this.fileManager.load();
		if (data.equals("실패")) {
			System.out.println("로드 실패");
			return;
		}

		this.students = new ArrayList<>();

		String[] temp = data.split("\n");
		for (int i = 0; i < temp.length; i++) {
			String[] arr = temp[i].split("/");

			String numStr = arr[0];
			String name = arr[1];
			String grade = arr[2];
			String[] subjectArr = arr[3].split(",");
			String[] scoreStrArr = arr[4].split(",");

			List<Subject> subjects = new ArrayList<>();
			for (int j = 0; j < subjectArr.length; j++) {
				String title = subjectArr[j];
				int score = Integer.parseInt(scoreStrArr[j]);

				Subject subject = new Subject(title, score);
				subjects.add(subject);
			}

			int num = Integer.parseInt(numStr);
			Student student = new Student(num, name, grade, subjects);
			this.students.add(student);
			this.students.get(i).calculateGrade();
		}
	}

	// 실행
	public void run() {
		
		this.load();
		
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
				this.printDeleteMenu();

				if (sel == 1) {
					this.deleteStudent();
				} else if (sel == 2) {
					this.deleteSubject();
				}
			} else if (menu == 3) {
				this.sort();
			} else if (menu == 4) {
				this.printStudents();
			} else if (menu == 5) {
				this.save();
			} else if (menu == 6) {
				this.load();
			} else if (menu == 7) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}
	}
}
