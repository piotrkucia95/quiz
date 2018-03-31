import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class QuestionReader {
	
	private FileReader file = null;
	private String pytanie 	= "";
	private String a 		= "";
	private String b 		= "";
	private String c 		= "";
	private String poprawna = "" ;
	
	//QuestionReader constructor
	public QuestionReader(int nr_pytania) {
		
		//file opening
		try {
			file = new FileReader("test.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Nie odnaleziono pliku");
			System.exit(1);
		}
	
		BufferedReader bfr = new BufferedReader(file);
	
		//following lines of file are question, a, b, c and correct answer
		try {
			for (int i=0; i<5*(nr_pytania-1); i++) {
				bfr.readLine();
			}
				pytanie = bfr.readLine();
				a = bfr.readLine();
				b = bfr.readLine();
				c = bfr.readLine();
				poprawna = bfr.readLine();
		} catch(IOException e1){
			System.out.println("Nie odnaleziono pliku");
			System.exit(2);
		}
		
		//file closing
		try {
			file.close();
		} catch(IOException e2){
			System.out.println("Błąd przy zamykaniu pliku");
			System.exit(3);
		}
	}
	
	//question, a, b, c and correct answer's getters
	public String getPytanie() {
		return pytanie;
	}
	
	public String getA() {
		return a;
	}
	
	public String getB() {
		return b;
	}
	
	public String getC() {
		return c;
	}
	
	public String getPoprawna() {
		return poprawna;
	}
}
