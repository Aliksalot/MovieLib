package sc;

import java.util.Scanner;

public class Driver {

    private static final String PATH = System.getProperty("user.dir").replace("sc", "");
    private static final int OPTIONS_COUNT = 7;
    private static Scanner in;

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
        String value = in.next();
        return value;
    }

    private static void exit(){
        System.out.println("Goodbye!");
        in.close();
        Debug.end();
        System.exit(0);
    }

    public static void main(String[] args) {

        Debug.innit(PATH + "\\Logs");

        MovieLib ml = new MovieLib(PATH + "\\Movies");

        in = new Scanner(System.in);

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
                    if(!isDigit(mainActorCntIn)){
                        System.out.println("Please enter a valid number! ");
                        break;
                    }
                    int mainActorCnt = Character.getNumericValue(mainActorCntIn.charAt(0));
                    String[] mainActors = new String[mainActorCnt];
                    for(int i = 0; i < mainActorCnt; i ++)
                        mainActors[i] = input("actor #" + (i + 1));
                    ml.addMovie(input("title"), input("genre"), input("release date"), mainActors);
                    break;
                case "2":
                    ml.deleteMovie(input("title"));
                    break;
                case "3":
                    ml.printList();
                    break;
                case "4":
                    ml.sortByTitle();
                    break;
                case "5":
                    ml.sortByActor();
                    break;
                case "6":
                    ml.sortByGenre();
                    break;
                case "7":
                    run = false;
                    exit();
                case "":break;
            }
        }


    }
    
}
