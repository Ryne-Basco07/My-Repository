package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import view.matrixView;

public class matrix {
    static Scanner sc = new Scanner (System.in);
    public ArrayList<HashMap<String, String>> stringArray;
    Map<String, String> map = new HashMap<>();
    
    int rows, columns;

    public matrix(ArrayList<HashMap<String, String>> stringArray, int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.stringArray = stringArray;

        initializeArray(stringArray);
        System.out.println();
    }

    public String generateString(){
        
        //String SALTCHARS = "!#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~␡";
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 3) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public void inputFunction() throws IOException{
        Scanner sc = new Scanner(System.in); 
        String input;
        matrixView matrixView = new matrixView();
        matrixView.viewMenu();
        do{
            input = sc.next();
            
            if (input.toUpperCase().equals("A")){
                searchString();
            }
            else if (input.toUpperCase().equals("B")){
                editString();
            }
            else if (input.toUpperCase().equals("C")){
                printCharacters();
            }
            else if (input.toUpperCase().equals("D")){
                resetArray();
            }
            else if (input.toUpperCase().equals("E")){
                addStringRow(stringArray);          
            }
            else if (input.toUpperCase().equals("F")){
                addStringColumn(stringArray);
            } 
            else if (input.toUpperCase().equals("G")){
                sortString();
            }
            else if (input.toUpperCase().equals("H")){
                fileReader();
            }
            else if (input.toUpperCase().equals("I")){
                System.exit(0);
            }
            else{
                System.out.println("Invalid input! Please enter the correct choice: ");
                input = sc.next();
            }
        }while (input!="I");
    }

   public void initializeArray(ArrayList<HashMap<String, String>> stringArray){
        for (int i=0; i < rows; i++){
            Map <String, String> map = new HashMap<>();
            for (int j=0; j < this.columns; j++){
            map.put(i + ","+ j, generateString());
            }
            this.stringArray.add((HashMap<String, String>) map);
        }

        for (int i=0; i < stringArray.size(); i++){
            Iterator<Map.Entry<String, String>> itr = stringArray.get(i).entrySet().iterator();

                while(itr.hasNext()){
                    Map.Entry<String, String> iterate = (Map.Entry<String, String>)
                    itr.next();
                    System.out.print("[" + iterate.getKey() + " = " + iterate.getValue() + "]");
                }
                System.out.println();
            }
   }
  
    public void searchString () throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string to be searched: ");
        String input = sc.nextLine();
        int counter = 0;
        for (int i=0; i < this.stringArray.size(); i++){
                Iterator<Map.Entry<String, String>> iterate = stringArray.get(i).entrySet().iterator();
                while (iterate.hasNext()) {
                    Map.Entry<String, String> entry = iterate.next();
                    if(entry.getValue().contains(input)){
                    counter++;
                    System.out.println("Search String \""+ input +"\" found at " + "("+ entry.getKey() + ")" + counter + " instances");
                    }
                }
        }    
        System.out.println("Returned "+counter+" matches.");
        inputFunction();
    }

    public void editString () throws IOException{
        Scanner input = new Scanner(System.in);
        int searchRow;
        int searchColumn;
        String newValues;
    
        System.out.print("Input row number: ");
        searchRow =input.nextInt();
        System.out.print("Input column number: ");
        searchColumn = input.nextInt();
 
            for (int indexRow = 0; indexRow < this.stringArray.size(); indexRow++) {
                Iterator<Map.Entry<String, String>> iterate = stringArray.get(indexRow).entrySet().iterator();
                while(iterate.hasNext()){
                    Map.Entry<String, String> entry = iterate.next();
                    if(entry.getKey().equals(searchRow +","+searchColumn)){
                        System.out.println("Found " + entry.getValue() + " on (" + searchRow +"," + searchColumn + ")");
                        System.out.print("Enter new values: ");
                        newValues = input.next();
                        
                        this.stringArray.get(searchRow).put(entry.getKey(), newValues);
                        System.out.println("The value is now " + newValues);
                      
                    }
                }
            }

            // System.out.println("Enter new values: ");
            // newValues = input.next();

            // ArrayList<HashMap<String, String>> stringArray = new ArrayList<HashMap<String, String>>();
            // map.replace(searchRow + "," + searchColumn, newValues);

            try{
                FileWriter writer = new FileWriter("test.txt"); 
                for(HashMap<String, String> str: stringArray) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
           }
           inputFunction();
        }

    public void printCharacters() throws IOException{
        //System.out.println("The contents of the array are: ");
        for (int i=0; i < stringArray.size(); i++){
            Iterator<Map.Entry<String, String>> itr = stringArray.get(i).entrySet().iterator();

                while(itr.hasNext()){
                    Map.Entry<String, String> iterate = (Map.Entry<String, String>)
                    itr.next();
                    System.out.print("[" + iterate.getKey() + " = " + iterate.getValue() + "]");
                }
                System.out.println();
            }
            inputFunction();
        }
    
    public void resetArray() throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.print("Input number of rows: ");
        this.rows = input.nextInt();
        System.out.print("Input number of columns: ");
        this.columns = input.nextInt();

        this.stringArray = new ArrayList<HashMap<String, String>>();

        initializeArray(stringArray);

        try{
            FileWriter writer = new FileWriter("test.txt"); 
            for(HashMap<String, String> str: stringArray) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
       }
       inputFunction();
    }

    public void addStringRow(ArrayList<HashMap<String, String>> stringArray) throws IOException{
        int inputRows;

            System.out.println("How many rows would you like to add: ");
            inputRows = sc.nextInt();

            for (int rows=0; rows < inputRows; rows++){
            Map <String, String> map = new HashMap<>();
            for (int columns=0; columns < this.columns; columns++){
                // int newRows = this.rows + inputRows;
                int newRows = this.rows + rows;
                map.put(newRows + ","+ columns, generateString());
                }
                this.stringArray.add((HashMap<String, String>) map);
            }

            try{
                FileWriter writer = new FileWriter("test.txt"); 
                for(HashMap<String, String> str: stringArray) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error has occurred.");
                e.printStackTrace();
           }
           inputFunction();
    }

    public void addStringColumn(ArrayList<HashMap<String, String>> stringArray) throws IOException{
        Scanner input = new Scanner(System.in);
        int inputColumns;
        int rowIndex;

        System.out.print("Which row would you like to add columns in: ");
        rowIndex = input.nextInt();

        System.out.print("How many columns would you like to add: ");
        inputColumns = input.nextInt();

        for (int columnIndex = 0; columnIndex < inputColumns; columnIndex++){
            // int newColumns = inputColumns + 1 + columnIndex;
            int newColumns = this.columns + columnIndex;
            this.stringArray.get(rowIndex).put(rowIndex + "," + newColumns, generateString()); 
        }

        try{
            FileWriter writer = new FileWriter("test.txt"); 
            for(HashMap<String, String> str: stringArray) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
       }
       inputFunction();
            
    }   

    public void sortString() throws IOException{
        String choice;
        System.out.println("A - For Sorting in Ascending Order");
        System.out.println("B - For Sorting in Descending Order");
        System.out.println("Please Enter your choice for Sorting: ");
        choice = sc.nextLine();

        if (choice.toUpperCase().equals("A")){
            System.out.println("Sorting in Ascending order");
            for (int i = 0; i < stringArray.size(); i++) {
                Iterator<Map.Entry<String, String>> iterate = stringArray.get(i).entrySet().iterator();
    
                List<String> sortedAscending = new ArrayList<>(stringArray.get(i).values());
                Collections.sort(sortedAscending);
    
                for (String sortString : sortedAscending) {
                    while(iterate.hasNext()){
                        Map.Entry<String, String> entry = iterate.next();
                        stringArray.get(i).replace(entry.getKey(), sortString);
                        break;
                    } 
                }
            }
        } else if (choice.toUpperCase().equals("B")){
            System.out.println("Sorting in Descending order");
            for (int i = 0; i < stringArray.size(); i++) {
                Iterator<Map.Entry<String, String>> iterate = stringArray.get(i).entrySet().iterator();
    
                List<String> sortedDescending = new ArrayList<>(stringArray.get(i).values());
                Collections.reverse(sortedDescending);
    
                for (String sortString : sortedDescending) {
                    while(iterate.hasNext()){
                        Map.Entry<String, String> entry = iterate.next();
                        stringArray.get(i).replace(entry.getKey(), sortString);
                        break;
                    } 
                }
            }
        } 
        try{
            FileWriter writer = new FileWriter("test.txt"); 
            for(HashMap<String, String> str: stringArray) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
       }
        inputFunction();
     }

    public void fileReader(){
        System.out.println("The previously saved contents in the file are: ");
        try {
            BufferedReader br = new BufferedReader(new FileReader("test.txt")) ;
            Scanner myReader = new Scanner(br);
    
            while (myReader.hasNextLine()) {
              String line = myReader.nextLine();
              System.out.println(line);
            }
            myReader.close();
    
          } catch (FileNotFoundException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
       }
    }
}
