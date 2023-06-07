import java.io.*;
import java.util.Scanner;
/* Welcome to my Movie reading API;
The way movies are stored is in individual text files, where the name of the file represents the title of the movie that is being
described in said file. As the user you need to insert the path to the folder with the movies and type out the titles of all the movies
that you want loaded in the program individualy. After that you can chose between all the options that the MovieLib class offers.
That is the overview, now you cna follow the instructions from the console when you click "run" */

public class Movie{
	private String title;
	private String genre;
	private String releaseDate;
	private ArrayList<String> mainActors;

	public Movie(String title, String genre, String releaseDate, ArrayList<String> mainActors){
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.mainActors = mainActors;
	}
	
	String getTitle(){
		return this.title;
	}
	
	String getGenre(){
		return this.genre;
	}
	
	String getReleaseDate(){
		return this.releaseDate;
	}
	
	ArrayList<String> getMainActors(){
		return this.mainActors;
	}
		

}

public class MovieLib{
	private ArrayList<Movie> movies;
	private final int maxMovieCount = 138;
	
	public MoiveLib(String[] movieTitles, String moviesFilePath){
			if(movieTitles.length() > maxMovieCount || movieTitles == null || movieFilePath.equals(""))
				throw new RuntimeException("Error when loading movies");
			for(String title: movieTitles){
				File movieData = new File(moviesFilePath + "\\" + title);
				if(!movieData.exists()){
					System.out.println("Movie with the title " + title + " does not exis! ");
					continue;
				}
				Scanner rd = new Scanner(moviesFilePath + "\\" + title);
				while(rd.hasNextLine()){
					
				}
				
				
			}
			

	}

	public void printList(){
		//prints all the movies in the previously sorted order
	}

	public void sortByName(){

	}

	public void sortByTitle(){
	
	}

	public void sortByGenre(){

	}

}

public class Driver{



}