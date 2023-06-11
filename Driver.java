package sc;

import java.util.HashMap;
import java.util.Scanner;

public class Driver {

    private static final String PATH = System.getProperty("user.dir").replace("sc", "");
    private static final int OPTIONS_COUNT = 7;
    private static Scanner in;
    private static Scanner in_line;

    private static void printMenu(){
        System.out.println("1. Add movie \n2. Delete movie\n3. Print all movies\n4. Sort by title\n5. Sort by actor\n6. Sort by genre\n7. Quit");
    }
    
    private static boolean isDigit(String in){
        return !(in.length() != 1 || !Character.isDigit(in.charAt(0)));
    }

    private static String validateUserChoice(String option){
        //valid choice is represented by a single digit number between 1 and OPTIONS_COUNT.
        if(!isDigit(option)|| Character.getNumericValue(option.charAt(0)) <= 0 || Character.getNumericValue(option.charAt(0)) > OPTIONS_COUNT)
            return "";
        return option;
    }

    private static String input(String arg){
        System.out.println("enter " + arg + ": ");
        String value = in_line.nextLine();
        return value;
    }

    private static void exit(){
        System.out.println("Goodbye!");
        in.close();
        in_line.close();
        Debug.end();
        System.exit(0);
    }

    public static void main(String[] args) {

        Debug.innit(PATH + "\\Logs");

        MovieLib ml = new MovieLib(PATH + "\\Movies");

        in = new Scanner(System.in);
        in_line = new Scanner(System.in);

        String option; 
        boolean run = true;
        while(run){
            System.out.println("Choose option: ");
            printMenu();
            option = validateUserChoice(in.next());
            switch(option){
                //Add movie
                case "1": 
                    System.out.println("How many main actors are there? ");
                    String mainActorCntIn = in.next();
                    while(!isDigit(mainActorCntIn)){
                        System.out.println("Please enter a valid number! ");
                        mainActorCntIn = in.next();
                    }
                    int mainActorCnt = Character.getNumericValue(mainActorCntIn.charAt(0));
                    String[] mainActors = new String[mainActorCnt];
                    for(int i = 0; i < mainActorCnt; i ++)
                        mainActors[i] = input("actor #" + (i + 1));
                    if(ml.addMovie(input("title"), input("genre"), input("release date"), mainActors))
                        System.out.println("New movie succesfully added! ");
                    else
                        System.out.println("Couldn't add movie (duplicate title)! ");
                    break;
                case "2":
                    if(ml.deleteMovie(input("title")))
                        System.out.println("Movie succesfully deleted! ");
                    else
                        System.out.println("Problem with deleting movie (non existing title)!");
                    break;
                case "3":
                    System.out.println();
                    for(HashMap<String,String> movie : ml.printMovies()){
                        for(String arg: movie.keySet())
                            System.out.println(arg + " = " + movie.get(arg));        
                        System.out.println();      
                    }
                    break;
                case "4":
                    if(ml.sortByTitle()){
                        System.out.println("Sort succesful!");
                    }else{
                        System.out.println("Sort failed (check logs)!");
                    }
                    break;
                case "5":
                    if(ml.sortByActor()){
                        System.out.println("Sort succesful!");
                    }else{
                        System.out.println("Sort failed (check logs)!");
                    }
                    break;
                case "6":
                    if(ml.sortByGenre()){
                        System.out.println("Sort succesful!");
                    }else{
                        System.out.println("Sort failed (check logs)!");
                    }
                    break;
                case "7":
                    run = false;
                    exit();
                case "":break;
            }
            System.out.println("------------------------------");
        }


    }
    
}
