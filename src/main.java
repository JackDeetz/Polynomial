import java.util.* ;
import java.util.Scanner ;
import java.util.InputMismatchException ;



public class main
{
    //menu withing polynomial creation, term adding, clearing, polynomial adding
    public static void main(String[] args)
    {

        System.out.println("~~~Welcome to the polynomial program (-1 to quit)~~~~") ;
        Scanner keyboard = new Scanner(System.in) ; //get keyboard
        int userMenuSelection1 = 0 ;                 //variable to hold the user menu maneuvering
        ArrayList<Polynomial> arrayL = new ArrayList<>();   //ArrayList holds the polynomials the user will create


        do {    //main menu and user action loop
 //selfCheck           System.out.println("Begining of the do While loop, arrayList is size" + arrayL.size());
            userMenuSelection1 = menu(arrayL, keyboard) ;       //prompts the user for their desired action, validates their input.
 //           keyboard.nextLine() ;
            userAction(userMenuSelection1, arrayL, keyboard) ;  //carries out the actions defined by the user

//selfCheck            System.out.println("in the do while in main");
        } while (userMenuSelection1 > -1) ;
    keyboard.close();
    }

        //guides the user through their chosen action path
    public static void userAction(int userMenuSelection, ArrayList<Polynomial>  arrayL, Scanner keyboard)
    {
        int userPolynomialChoice = 0 ;
        switch(userMenuSelection)
        {
            case 0:     //creating a new Polynomial
                arrayL.add(new Polynomial()) ;
//selfCheck                System.out.println("here in case 0");
                break ;
            case 1:     //create a Term and add it to a already created polynomial
                printCreatedPolynomials(arrayL) ;
                userPolynomialChoice = validateInt("", "Not a valid input.", 0, arrayL.size()-1, keyboard) ;     //user defines the polynomial to add a Term to
//                keyboard.nextLine() ;   //empty Scanner buffer
                System.out.println("Coefficient for new term to add:") ;    //prompt for new term coefficient
                int coefficient = keyboard.nextInt() ;
                System.out.println("Exponent for new term to add:") ;       //prompt for a new term exponent
                int exponent = keyboard.nextInt() ;
                arrayL.get(userPolynomialChoice).addTerm(new Term(coefficient, exponent)) ;     //creates the new term and adds it to the user defined polynomial
                break ;
            case 2:     //clear(remove all terms) from a user defined, already created, polynomial
                printCreatedPolynomials(arrayL) ;
                userPolynomialChoice = validateInt("", "Not a valid input.", 0, arrayL.size()-1, keyboard) ;    //user chooses which polynomial to empty (clear)
                keyboard.nextLine() ;
                arrayL.get(userPolynomialChoice).clear() ; //polynomials cleared
                break ;
            case 3:     //choose two already created polynomials to print the results of adding them together
                int userPolynomialChoiceb = 0 ;        //polynomial b
                printCreatedPolynomials(arrayL) ;
                System.out.println("Choose Polynomial \'a\' to add to Polynomial \'b\'") ;
                userPolynomialChoice = validateInt("", "Not a valid input.", 0, arrayL.size()-1, keyboard) ;
                System.out.println("Choose Polynomial \'b\'") ;
                userPolynomialChoiceb = validateInt("", "Not a valid input.", 0, arrayL.size()-1, keyboard) ;
                keyboard.nextLine() ;
                Polynomial temp = new Polynomial(arrayL.get(userPolynomialChoice)) ;    //create a temporary polynomial and assign it a deep copy of polynomial 'a'
                temp.add(arrayL.get(userPolynomialChoiceb)) ;          //so we can call the add(polynomial) function without altering the stored polynomials
                System.out.println("Polynomial " +  arrayL.get(userPolynomialChoice) + " plus " + arrayL.get(userPolynomialChoiceb) + " =\n" + temp) ;
                break ;
        }
//selfCheck            System.out.println("leaving userAction()");
    }

    public static void printCreatedPolynomials(ArrayList<Polynomial> arrayL)    //prints the polynomials
    {
        System.out.println("Available Polynomials:");
        for (int i = 0 ; i < arrayL.size() ; i++)       //prints all the already created polynomials
        {
            System.out.println( i + ") " + arrayL.get(i));
        }
    }

    public static int menu(ArrayList<Polynomial> arrayL, Scanner keyboard)      //prompts the user to their options, validates their input, and returns an int for userAction() to use
    {
        int userMenuSelection = 0 ;
        System.out.println("~~~Available Actions~~~");
        System.out.println("0 - Create a new polynomial") ; //always an available option
        if (arrayL.size() > 0)
        {
            System.out.println("1 - Add new term to polynomial");   //only available
            System.out.println("2 - clear a polyniomal");           //when a polynomials exists
            if (arrayL.size() > 1)
            {
                System.out.println("3 - add polynomials together"); //only when more than 1 polynomial exists
            }
        }
        switch(arrayL.size())   //prompts the user with the input validation appropriate for the number of already created polynomials
        {
            case 0: //no polynomials have been created, only option is to create a new one
                userMenuSelection = validateInt("Your menu choice: ", "This is not an acceptable input", 0, 0, keyboard) ;
                break ;
            case 1: //1 polynomial have been created, can only create more or add terms
                userMenuSelection = validateInt("Your menu choice: ", "This is not an acceptable input", 0, 2, keyboard) ;
                break ;
            default:    //otherwise you can do any of the options, create, addTerms, or addPolynomials
                userMenuSelection = validateInt("Your menu choice: ", "This is not an acceptable input", 0, 3, keyboard) ;
        }
        return userMenuSelection ;
    }

    public static int validateInt(String prompt, String errorMessage, int min, int max, Scanner keyboard)       //a 'bullet proof' function to validate user Input
    {
        int number = 0 ;  //to hold the user input
//        Scanner keyboard = new Scanner(System.in) ; //keyboard access
        boolean validInput = false ;  //conditional variable for if the input is valid(within range and an int)

        while (!(validInput)) //runs while the input is not valid
        {
            try //any exceptions found in here will send the flow to the catch
            {
                System.out.print(prompt) ;  //show the parameter promp
                number = keyboard.nextInt() ; //users input
//                keyboard.nextLine() ;
                validInput = (number >= min && number <= max) ; //bool of if user input in within range
            }
            catch(InputMismatchException e) //exceptions sent here
            {
                validInput = false ;  //set to false, because input was way invalid, whole types away from valid
                keyboard.nextLine() ; //empty the keyboard buffer otherwise it loops infinatly
            }
            if (!validInput)  //only get here if input is right type
            {
                System.out.println(errorMessage + "\n") ; //prints parameters error message if outside range
            }
        }//end of while loop

 //       keyboard.close() ;
        return number ; //returning the validated user input

    }
}
