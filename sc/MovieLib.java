package sc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class MovieLib{

	private HashMap<String, Movie> movies;
	private final int maxMovieCount = 132;
	private String moviesFilePath;
    private ArrayList<String> sortedOrder; 

	public MovieLib(String moviesFilePath){

            sortedOrder = new ArrayList<>();

            this.moviesFilePath = moviesFilePath;

            movies = new HashMap<>();

            String loadingMoviesException = "Error with loading movies. Wrong entry format?";


            File movie_folder = new File(moviesFilePath);

			if(moviesFilePath.equals(""))
                Debug.Log(new RuntimeException(loadingMoviesException));

                for(File movieData: movie_folder.listFiles()){
                    
                        try (Scanner rd = new Scanner(movieData)) {
                            String line;
                            while(rd.hasNextLine() && movies.keySet().size() < maxMovieCount){
                                //pull the title out of txt file
                                line = rd.nextLine();
                                if(!line.contains("title")){
                                    rd.close();
                                    System.out.println(line);
                                   Debug.Log(new RuntimeException(loadingMoviesException));
                                }
                                String title = line.substring(line.indexOf("=") + 1).replaceAll("\\s+","");
                                //pull the genre out of txt file
                                line = rd.nextLine();
                                if(!line.contains("genre")){
                                    rd.close();
                                    Debug.Log(new RuntimeException(loadingMoviesException));
                                }
                                String genre = line.substring(line.indexOf("=") + 1).replaceAll("\\s+","");
                                //pull the release date out of txt file
                                line = rd.nextLine();
                                if(!line.contains("release_date")){
                                    rd.close();
                                    Debug.Log(new RuntimeException(loadingMoviesException));
                                }
                                String release_date = line.substring(line.indexOf("=") + 1).replaceAll("\\s+","");
                                line = rd.nextLine();
                                if(!line.contains("main_actors")){
                                    rd.close();
                                    Debug.Log(new RuntimeException(loadingMoviesException));
                                }
                                String[] main_actors = line.substring(line.indexOf("=") + 1).replaceAll("\\s+","").split(",");
                                Arrays.sort(main_actors);
                                movies.put(title, new Movie(title, genre, release_date, main_actors));
                                sortedOrder.add(title);
                            }
                            rd.close();
                        } catch (FileNotFoundException e) {
                           Debug.Log(e);
                        }
                        Debug.Log("Loaded " + movieData.getAbsolutePath());     
            }
            System.out.println("Loaded " + movies.size() + " movies succesfully.");          

	}

    private boolean newMovieFile(Movie movie){
        File movieFile = new File(moviesFilePath + "\\" + movie.getTitle() + ".txt");
        if(movieFile.exists()){
            System.out.println("File already exist! ");
            return false;
        }
        try{
            movieFile.createNewFile();
            FileWriter wr = new FileWriter(movieFile);
            wr.append(movie.toString());
            wr.close();
        }catch(IOException e){
            Debug.Log(e);
            return false;
        }
        System.out.println(85);
        return true;
    }
    public boolean addMovie(String title, String genre, String release_date, String[] main_actors){
        Movie newMovie = new Movie(title, genre, release_date, main_actors);
        return addMovie(newMovie);
    }

    public boolean addMovie(Movie newMovie){
        movies.put(newMovie.getTitle(), newMovie);
        sortedOrder.add(newMovie.getTitle());
        if(newMovieFile(newMovie)){
            System.out.println("Movie succesfully added to MovieLib! ");
            return true;
        }
        return false;
    }
    public boolean deleteMovie(String title){
        if(movies.keySet().contains(title)){
            movies.remove(title);
            sortedOrder.remove(title);
            File movieToRemove = new File(moviesFilePath + "\\" + title + ".txt");
            movieToRemove.delete();
            System.out.println("Movie succesfully deleted from MovieLib! ");
            return true;
        }else
            System.out.println("Such movie doesn't exist! ");
            return false;
    }

    //debug
	public void printList(){
		//prints all the movies in the previously sorted order
        if(!sortedOrder.isEmpty() || movies.isEmpty())
        for(String title: sortedOrder){
            System.out.println(movies.get(title) + "\n\n");
        }
        else
        System.out.println("No movies to be printed! ");
	}

    public Collection<Movie> getMovies(){
        return this.movies.values();
    }

    public ArrayList<String> getSortOrder(){
        return this.sortedOrder;
    }

	public void sortByActor(){
        this.sortedOrder = new ArrayList<>();
        ArrayList<String> sortedActors = new ArrayList<>();
        //We are adding the first actor from the array of actors, since he/she is always the first one alphabetically in the array.
        for(Movie movie: movies.values())
            sortedActors.add(movie.getMainActors()[0]);
        Collections.sort(sortedActors);
        for(String actor: sortedActors)
            for(Movie movie: movies.values()){
                if(movie.getMainActors()[0].equals(actor) && !this.sortedOrder.contains(movie.getTitle())){
                    if(movie.getTitle().contains("The Matrix") || movie.getTitle().contains("Матрицата"))
                        this.sortedOrder.add(0, movie.getTitle());
                    else
                        this.sortedOrder.add(movie.getTitle());
                    break;
                }
            }
	}

	public void sortByTitle(){
        this.sortedOrder = new ArrayList<>();
        for(String title: movies.keySet()){
            sortedOrder.add(title);
        }
        Collections.sort(sortedOrder);
        for(String title: movies.keySet()){
            if(movies.get(title).getTitle().contains("The Matrix") || movies.get(title).getTitle().contains("Матрицата")){
                this.sortedOrder.remove(title);
                this.sortedOrder.add(0,title);
            }
        }
	}

	public void sortByGenre(){
        this.sortedOrder = new ArrayList<>();
        ArrayList<String> sortedGenres = new ArrayList<>();
        for(Movie movie: movies.values())
            sortedGenres.add(movie.getGenre());
        Collections.sort(sortedGenres);
        for(String genre: sortedGenres)
            for(Movie movie: movies.values()){
                if(movie.getGenre().equals(genre) && !this.sortedOrder.contains(movie.getTitle())){
                    if(movie.getTitle().contains("The Matrix") || movie.getTitle().contains("Матрицата"))
                        this.sortedOrder.add(0, movie.getTitle());
                    else
                        this.sortedOrder.add(movie.getTitle());
                    break;
                }
            }

	}
    

}
