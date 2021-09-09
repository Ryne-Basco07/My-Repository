import java.util.Random;
import java.util.Scanner;


public class Exercise1 {

    static String [][] array;

    public static String generateString(){
        String SALTCHARS = "!"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~␡";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

//    public static String[][] initializeArray(String [][]array){
//         for (int i=0; i < array.length; i++){
//             for (int j=0; j < array[i].length; j++){
//             array[i][j] = generateString();
//             }
//         }
//         return array;
//    }

    public static String menu(String [][] array){
        Scanner sc = new Scanner (System.in);
        String input;

        System.out.println("\nMenu");
        System.out.println("-----------");
        System.out.println("[A] - Search");
        System.out.println("[B] - Edit");
        System.out.println("[C] - Print");
        System.out.println("[D] - Reset");
        System.out.println("[E] - Exit");
        System.out.println("-----------");
        System.out.println("Please enter your choice: ");
        input = sc.next();
        
        if (input.toUpperCase().equals("A")){
            searchString(array);
        }
        else if (input.toUpperCase().equals("B")){
            editString(array);
        }
        else if (input.toUpperCase().equals("C")){
            printCharacters(array);
        }
        else if (input.toUpperCase().equals("D")){
            array = resetArray(array);
        }
        else if (input.toUpperCase().equals("E")){
            System.exit(0);
        }
        else{
            System.out.println("Invalid input! Please enter the correct choice: ");
            input = sc.next();
        }
        
        return menu(array);
    }

    public static void searchString (String [][] array){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string to be searched: ");
        String input = sc.nextLine();
        int counter = 0;
        for (int i=0; i < array.length; i++){
            for (int j=0; j < array[0].length; j++){
                if(array[i][j].contains(input)){
                    counter++;
                    System.out.println("Search String \""+ input+"\" found at " + "("+Integer.toString(i)+","+Integer.toString(j)+")");
                }
            }
        }    
        System.out.println("Returned "+counter+" matches.");
    }

    public static void editString (String [][] array){
        Scanner input = new Scanner(System.in);
        int searchRow;
        int searchColumn;
        String newValues;

        System.out.print("Input row number: ");
        searchRow =input.nextInt();
        System.out.print("Input column number: ");
        searchColumn = input.nextInt();
        System.out.println("Enter the new values: ");
        newValues = input.next();

        array[searchRow][searchColumn] = newValues;
        System.out.println("Value of " + searchRow +"," + searchColumn + " is now: " + newValues);
        input.close();
    }

	//prints the generated string currently stored in the array
    public static void printCharacters(String [][] array){
        System.out.println("The contents of the array are: ");
        for (int i=0; i < array.length; i++){
            for (int j=0; j < array[i].length; j++){
                System.out.print (array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String[][] resetArray(String [][] array){
        int rows = 0;
        int columns = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Input number of rows: ");
        rows = input.nextInt();
        System.out.print("Input number of columns: ");
        columns = input.nextInt();

        array = new String [rows][columns];

        for (int i=0; i < array.length; i++){
            for (int j=0; j < array[i].length; j++){
                array[i][j] = generateString();
                System.out.print (array[i][j] + " ");
            }
            System.out.println();
        }
        
        return array;
    }
    
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        int rows;
        int columns;

	/asks users for the rows and columns
        System.out.print("Input number of rows: ");
        rows = input.nextInt();
        System.out.print("Input number of columns: ");
        columns = input.nextInt();

	//declares the array with the rows and columns
        String [][] array = new String [rows][columns];
	
	//generates the string
        for (int i=0; i < rows; i++){
            for (int j=0; j < columns; j++){
                array[i][j] = generateString();
            }
        }

	//prints the generated string
        for (int i=0; i < rows; i++){
            for (int j=0; j < columns; j++){
                System.out.print (array[i][j] + " ");
            }
            System.out.println();
        }
        menu(array);
        
    }
}


