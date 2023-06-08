package sc;
import java.util.Arrays;



public class Movie{
	private String title;
	private String genre;
	private String releaseDate;
	private String[] mainActors;

	public Movie(String title, String genre, String releaseDate, String[] mainActors){
		this.title = title;
		this.genre = genre;
		this.releaseDate = releaseDate;
        Arrays.sort(mainActors);
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
	
	String[] getMainActors(){
		return this.mainActors;
	}
    
    @Override
    public String toString(){

        String mainActorsAsString = mainActors[0];
    
        for(int i = 1; i < mainActors.length; i ++)
            mainActorsAsString += "," + mainActors[i];

        return "title = " + this.title + "\ngenre = " + this.genre + "\nrelease_date = " + this.releaseDate + "\nmain_actors = " + mainActorsAsString;
    }

}

