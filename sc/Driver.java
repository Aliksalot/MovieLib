package sc;
/* Welcome to my Movie reading API;
The way movies are stored is in individual text files, where the name of the file represents the title of the movie that is being
described in said file. As the user you need to insert the path to the folder with the movies. After that you can chose between all the options that the MovieLib class offers.
That is the overview, now you can follow the instructions from the console when you click "run" */
public class Driver {
    //Napishi interfeis v konzolata, premesti logovete v papka logs. Napravi moviesFilePath da se getwa avtomatichno. Ako nqma movies papka da q suzdava
    public static void main(String[] args) {
        MovieLib ml = new MovieLib("C:\\Users\\admin\\Desktop\\Staj\\Movies");
        System.out.println(System.getProperty("user.dir"));
    }
    
}
