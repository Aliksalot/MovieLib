package sc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class innit{
	
	public static void main(String args[]){
		String path = System.getProperty("user.dir").replace("\\sc", "");
		File logs_folder = new  File(path + "\\" + "Logs");
		if(!logs_folder.exists()){
			logs_folder.mkdirs();
			System.out.println("logs_folder succesfully created! ");
		}

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
			System.out.println(e);
		}
		File movies_folder = new File(path + "\\" + "Movies");
		if(!movies_folder.exists()){
			movies_folder.mkdirs();
			System.out.println("movies_folder succesfully created! ");
		}

		
		System.exit(0);
	}
}
