package sc;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
public class Debug {

    private static String path;

    private static FileWriter wr;
    private static File log_file;
    private static boolean isEnded;

    // debug

    /*public static void main(String[] args) {
        innit("C:\\Users\\admin\\Desktop\\StajZadacha-main\\Logs");
        Log("Hello");
        end();
    }*/

    public static void innit(String log_folder_path){
        if(path == null){
            path = log_folder_path;
            int log_file_index = 1;  
            log_file = new File(path + "\\log" + log_file_index + ".txt");
            while(log_file.exists()){
                log_file_index ++;
                log_file = new File(path + "\\log" + log_file_index + ".txt");
            }
            try {
                log_file.createNewFile();
                wr = new FileWriter(log_file,true);
            } catch (IOException e) {
            }
            isEnded = false;
        }
        

    }

    public static void Log(String arg){
        if(path != null && wr != null && !isEnded){
            try {
                wr.append("\n" + arg + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Log(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        Log(sw.toString());
    }
    
    public static void end(){
        try {
            if(log_file.length() == 0)
                log_file.delete();
            wr.close();
            isEnded = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
