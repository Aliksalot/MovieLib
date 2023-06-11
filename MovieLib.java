package sc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MovieLib {
    
    private String movieFilesPath;
    private ArrayList<String> moviePaths;

    public MovieLib(String movieFilesPath){

        this.movieFilesPath = movieFilesPath;

        this.moviePaths = new ArrayList<>();

        RuntimeException wrongFileFormatException = new RuntimeException("Error when loading movie. Wrong entry format or wrong file type?");

        File moviesFodler = new File(movieFilesPath);
        
        for(File movie: moviesFodler.listFiles()){
            if(movie.isDirectory() || !movie.getAbsolutePath().contains(".txt")){
                Debug.Log(wrongFileFormatException, " at " + movie.getAbsolutePath());
                continue;
            }
            moviePaths.add(movie.getAbsolutePath());
            Debug.Log("Loaded = " + movie.getAbsolutePath());
        }
    }

    private String createFilePath(String movie_title){
        return this.movieFilesPath + "\\" + movie_title + ".txt";
    }

    private boolean checkNotNull(Object object){
        if(object == null){
            Debug.Log("Null = " + object);
            return false;
        }
        return true;
    }

    private boolean createMovieFile(String title, String genre, String release_date, String[] actors){
        
        try{
            File movieFile = new File(createFilePath(title));
            if(movieFile.exists()){
                Debug.Log("Error when adding new movie to file: Title " + title + " already exists! ");
                return false;
            }
            movieFile.createNewFile();
            FileWriter wr = new FileWriter(movieFile, true);
            wr.append("title = " + title + "\n");
            wr.append("genre = " + genre + "\n");
            wr.append("release_date = " + release_date + "\n");
            Arrays.sort(actors);
            wr.append("main_actors = " + Arrays.toString(actors).replace("[", "").replace("]", ""));
            wr.flush();
            wr.close();
        }catch(IOException e){
            Debug.Log(e);
            return false;
        }
        return true;
    }

    // "arg" can be title/genre/main_actors/date or can be left empty to get the full file
    private String getFromFile(String movie_path, String arg) throws IOException{
        if(!checkNotNull(arg) || !checkNotNull(movie_path) || (!arg.equals("title") && !arg.equals("genre") && !arg.equals("main_actors") && !arg.equals("release_date") && !arg.equals(""))){
            Debug.Log("Wrong argument when getting info from file = " + arg);
            return null;
        }
        File movieFile = new File(movie_path);
        Scanner rd = new Scanner(movieFile);

        if(!arg.equals("")){
            while(rd.hasNextLine()){
                String line = rd.nextLine();
                if(line.contains(arg)){
                    rd.close();
                    return line.replace(arg + " = ", "");   
                }
            }
        }else{
            String fullFile = "";
            while(rd.hasNextLine())
                fullFile += rd.nextLine() + "\n";  
            rd.close();
            return fullFile;
        }

        rd.close();

        Debug.Log("Problem with reading movie file. Wrong format perhaps? ");
        return null;
    }

    public boolean addMovie(String title, String genre, String release_date, String[] actors){
        if(!checkNotNull(title) || !checkNotNull(genre) || !checkNotNull(release_date) || !checkNotNull(actors)){
            Debug.Log("Error when creating new movie: " + "\n" + title + "\n" + genre + "\n" + release_date + "\n" + actors);
            return false;
        }
        moviePaths.add(createFilePath(title));
        return createMovieFile(title, genre, release_date, actors);
    }

    public boolean deleteMovie(String title){
        if(!checkNotNull(title))
            return false;
        moviePaths.remove(createFilePath(title));
        File toDelete = new File(createFilePath(title));
        try {
            FileWriter wr = new FileWriter(toDelete);
            wr.close();
        } catch (IOException e) {
            Debug.Log(e);
        }
        return toDelete.delete();
    }

    public ArrayList<HashMap<String,String>> printMovies(){
        ArrayList<HashMap<String,String>> movies = new ArrayList<>();
        for(int i = 0; i < moviePaths.size(); i ++){
            try {
                movies.add(new HashMap<String,String>());
                movies.get(i).put("title", getFromFile(moviePaths.get(i),"title"));
                movies.get(i).put("genre", getFromFile(moviePaths.get(i),"genre"));
                movies.get(i).put("release_date", getFromFile(moviePaths.get(i),"release_date"));
                movies.get(i).put("main_actors", getFromFile(moviePaths.get(i),"main_actors"));
            } catch (IOException e) {
                Debug.Log(e);
            }
        }

        return movies;
    }

    private void putMatrixFirst(){
        String matrixPath = "";
        for(String path: this.moviePaths){
            if(path.contains("The Matrix") || path.contains("Матрицата")){
                matrixPath = path;
                Debug.Log("found the matrix");
            }
        }
        this.moviePaths.remove(matrixPath);
        this.moviePaths.add(0, matrixPath);
    }

    public boolean sortByTitle(){
        Collections.sort(this.moviePaths);
        this.putMatrixFirst();
        return true;
    }

    public boolean sortByActor(){
        ArrayList<String> actorsOrder = new ArrayList<>();
        for(String path: this.moviePaths){
            try {
                //we are adding the first actor from the main_actors, since he/she should always be the first one alphabetically
                actorsOrder.add(getFromFile(path, "main_actors").split(" ")[0]);
            } catch (IOException e) {
                Debug.Log(e);
                return false;
            }
        }
        Collections.sort(actorsOrder);
        ArrayList<String> newOrder = new ArrayList<>();
        for(String actor: actorsOrder){
            for(String path: moviePaths){
                try {
                    if(getFromFile(path, "main_actors").split(" ")[0].equals(actor)){
                        newOrder.add(path);
                        break;
                    }
                } catch (IOException e) {
                    Debug.Log(e);
                    return false;
                }
            }
        }
        this.moviePaths = newOrder;
        this.putMatrixFirst();
        return true;
    }

    public boolean sortByGenre(){
        ArrayList<String> genresOrder = new ArrayList<>();
        for(String path: this.moviePaths){
            try {
                genresOrder.add(getFromFile(path, "genre").split(" ")[0]);
            } catch (IOException e) {
                Debug.Log(e);
                return false;
            }
        }
        ArrayList<String> newOrder = new ArrayList<>();
        for(String genre: genresOrder){
            for(String path: moviePaths){
                try {   
                    if(getFromFile(path, "genre").split(" ")[0].equals(genre)){
                        newOrder.add(path);
                        break;
                    }
                } catch (IOException e) {
                    Debug.Log(e);
                    return false;
                }
            }
        }
        this.moviePaths = newOrder;
        this.putMatrixFirst();
        return true;
    }

}
