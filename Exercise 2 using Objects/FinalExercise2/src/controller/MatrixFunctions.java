package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

import model.KeyValues;
import model.Matrix;
import utility.Helper;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import view.MatrixView;

public class MatrixFunctions {
    ArrayList<ArrayList<KeyValues>> stringArray;
    Matrix matrix;
    MatrixView matrixView;
    Scanner sc = new Scanner(System.in);
    String filePath = "C:/Users/Ryne/Desktop/FILES/Internship Files/Exist/Exercise 2 using Objects/testing.txt";

    public MatrixFunctions()throws FileNotFoundException{
        this.stringArray = new ArrayList<ArrayList<KeyValues>>();
        this.matrix = new Matrix(stringArray);
        try {
            fileReader(filePath, matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inputMenuFunction(){
        String input;
        MatrixView matrixView = new MatrixView();
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
                    addStringRow();     
                }
                else if (input.toUpperCase().equals("F")){
                    addStringColumn();
                } 
                else if (input.toUpperCase().equals("G")){
                    sortString();
                }
                else if (input.toUpperCase().equals("H")){
                    System.exit(0);
                }
                else{
                    System.out.println("Invalid input! Please enter the correct choice: ");
                    input = sc.next();
                }
            }while (input!="H");
    }

    public void searchString (){
        System.out.println("Enter string to be searched: ");
        String input = sc.next();
        int counter = 0;

        for (int indexRow = 0; indexRow < this.matrix.getStringArray().size(); indexRow++) {
            for (int indexColumn = 0; indexColumn < this.stringArray.get(indexRow).size(); indexColumn++) {
                if(this.stringArray.get(indexRow).get(indexColumn).getValue().contains(input)){
                    int ctr = 0;
                    ctr++;
                    System.out.println("Search string " + input + " found at " + this.stringArray.get(indexRow).get(indexColumn).getKey() + " with " + 
                    ctr + " instances on key field");
                    ++counter;
            }
        }
    }
        System.out.println("Returned " + counter + " matches.");
        inputMenuFunction();   
    }

    public void editString(){
        String newValues;

        System.out.println("Input row number: ");
        int searchRow = sc.nextInt();
        System.out.println("Input column number: ");
        int searchColumn = sc.nextInt();

        for (int indexRow = 0; indexRow < this.matrix.getStringArray().size(); indexRow++) {
            for (int indexColumn = 0; indexColumn < this.stringArray.get(indexRow).size(); indexColumn++) {
                if(this.stringArray.get(indexRow).get(indexColumn).getKey().equals(searchRow + "," + searchColumn)){
                    System.out.println("Found " + this.stringArray.get(indexRow).get(indexColumn).getValue() + " on " + searchRow + "," + searchColumn);
                    System.out.print("Enter new values: ");
                    newValues = sc.next();
                    this.stringArray.get(indexRow).get(indexColumn).setValue(newValues);
                    System.out.println("The value is now " + newValues);
                }
            }
        }
        fileWriter();
        inputMenuFunction();
    }

    public void resetArray(){
        matrix.initializeArray(stringArray);
        
        fileWriter();
        inputMenuFunction();
    }

    public void printCharacters(){
        System.out.println("The current contents of the array are: ");
        for (int indexRow = 0; indexRow < this.matrix.getStringArray().size(); indexRow++) {
            for (int indexColumn = 0; indexColumn < this.stringArray.get(indexRow).size(); indexColumn++) {
                System.out.print("[" + this.matrix.getStringArray().get(indexRow).get(indexColumn).getKey() + " = " + 
                this.matrix.getStringArray().get(indexRow).get(indexColumn).getValue() + "] ");
            }
            System.out.println();
        }
        inputMenuFunction();
    }

    public void addStringRow(){
        int inputRows;
        int inputColumns;

        System.out.println("How many rows would you like to add: ");
        inputRows = sc.nextInt();
        System.out.print("How many columns would you like to add in: ");
        inputColumns = sc.nextInt();

        for (int indexRow = 0; indexRow < inputRows; indexRow++) {
            ArrayList<KeyValues> stringContent = new ArrayList<KeyValues>();
            for (int indexColumn = 0; indexColumn < inputColumns; indexColumn++) {
                stringContent.add(new KeyValues(this.matrix.getRows() + indexRow + "," + indexColumn, matrix.generateString()));
            }
            this.stringArray.add(stringContent);
        }
        fileWriter();
        inputMenuFunction();
    }

    public void addStringColumn(){
        int indexRow;
        int inputColumns;

        System.out.print("Which row would you like to add columns in: ");
        indexRow = sc.nextInt();
        System.out.print("How many columns would you like to add: ");
        inputColumns = sc.nextInt();

        for (int indexColumn = 0; indexColumn < inputColumns; indexColumn++) {
            int newColumns = this.matrix.getColumns() + indexColumn;
            this.stringArray.get(indexRow).add(new KeyValues(indexRow + "," + newColumns, matrix.generateString()));
        }
        fileWriter();
        inputMenuFunction();
    }

    public void sortString(){
        String choice;
        System.out.println("A - For Sorting in Ascending Order");
        System.out.println("D - For Sorting in Descending Order");
        System.out.println("Please Enter your choice for Sorting: ");
        choice = sc.next();

        ArrayList<ArrayList<String>> sortString = new ArrayList<ArrayList<String>>();
        Helper.sortHelper(sortString, this.matrix);
        
        for (int indexRow = 0; indexRow < this.matrix.getStringArray().size(); indexRow++) {
            if (choice.toUpperCase().equals("A")){
                Collections.sort(sortString.get(indexRow));
                Helper.saveSortData(sortString, this.matrix);
            }
            else if(choice.toUpperCase().equals("D")){
                Collections.sort(sortString.get(indexRow), Collections.reverseOrder());
                Helper.saveSortData(sortString, this.matrix);
            }
            else {
                System.out.println("Invalid input! Please enter a valid input");
                sortString();
            }
        }
        fileWriter();
        inputMenuFunction();
    }

    public void fileReader(String filePath, Matrix matrix){
        
        System.out.println("The contents of the file are: ");
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath)) ;
            Scanner myReader = new Scanner(br);
            ArrayList<KeyValues> arrayString = new ArrayList<KeyValues>();

            int indexRow = 0;

            //https://www.javatpoint.com/post/java-matcher-group-method (source)

            while (myReader.hasNext()) {
              String line = myReader.nextLine();
              int indexColumn = 0;
                Matcher matchPair = Pattern.compile("(?<java>[A-Za-z]+)").matcher(line);
                while(matchPair.find()){
                    String key = indexRow + "," + indexColumn;
                    String value = matchPair.group();
                    arrayString.add(new KeyValues(key, value));
                    indexColumn++;
                }

                if(myReader.hasNextLine()){
                    this.matrix.getStringArray().add(arrayString);
                    indexRow++;
                    arrayString = new ArrayList<KeyValues>();
                }
              System.out.println(line);
            }
            myReader.close();
    
          } catch (Exception e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
       }
    }

    public void fileWriter(){
        try {
            FileWriter writeFile = new FileWriter(filePath);
            Writer writer = new BufferedWriter(writeFile);

            for (int indexRow = 0; indexRow < this.matrix.getStringArray().size(); indexRow++) {
                for (int indexColumn = 0; indexColumn < this.matrix.getStringArray().get(indexRow).size(); indexColumn++) {
                    writer.write("[" + this.matrix.getStringArray().get(indexRow).get(indexColumn).getKey() + "=" 
                    + this.matrix.getStringArray().get(indexRow).get(indexColumn).getValue() + "]");
                }
                writer.write("\n");
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Error. File was not found");
            e.printStackTrace();
        }
    }

}
