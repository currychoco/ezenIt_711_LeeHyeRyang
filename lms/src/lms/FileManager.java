package lms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileManager {
	private String fileName;
	private File file;
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	public FileManager() {
		this.fileName = "lms.txt";
		this.file = new File(this.fileName);
	}
	
	//저장
	public void save(String data) {
		try {
			this.fw = new FileWriter(file);
			fw.write(data);
			fw.close();
			System.out.println("저장완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("저장실패");
		}
	}
	
	//로드
	public String load() {
		String data = "";
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while(br.ready()) {
				data += br.readLine();
				data+="\n";
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return "실패";
		}
	}
}
