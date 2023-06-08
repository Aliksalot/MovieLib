import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class innit{
	
	public static void main(String args[]){
		String path = System.getProperty("user.dir");
		File pathFile = new File(path + "\\" + "path.txt");
		try{
			if(!pathFile.exists()){
				pathFile.createNewFile();
				System.out.println("path_file succesfully created! ");
			}
			FileWriter wr = new FileWriter(pathFile);
			wr.append(path);
			wr.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		File movies_folder = new File(path + "\\" + "Movies");
		File logs_folder = new  File(path + "\\" + "Logs");
		if(!movies_folder.exists()){
			movies_folder.mkdirs();
			System.out.println("movies_folder succesfully created! ");
		}
		if(!logs_folder.exists()){
			logs_folder.mkdirs();
			System.out.println("logs_folder succesfully created! ");
		}
		System.out.println("write something to continue");
		Scanner kb = new Scanner(System.in);
		kb.next();
		kb.close();
		System.exit(0);
	}
}
