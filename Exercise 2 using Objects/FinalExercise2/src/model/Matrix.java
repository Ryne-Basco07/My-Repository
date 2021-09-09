package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Matrix {
    ArrayList<ArrayList<KeyValues>> stringArray;
    int rows;
    int columns;
    Scanner sc = new Scanner(System.in);

    public ArrayList<ArrayList<KeyValues>> getStringArray() {
        return stringArray;
    }

    public void setStringArray(ArrayList<ArrayList<KeyValues>> stringArray) {
        this.stringArray = stringArray;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Matrix(ArrayList<ArrayList<KeyValues>> stringArray){
        // System.out.print("Enter number of rows: ");
        // this.rows = sc.nextInt();
        // System.out.print("Enter number of columns: ");
        // this.columns = sc.nextInt();
        this.stringArray = stringArray;
        //initializeArray(stringArray);
    }



    public void initializeArray(ArrayList<ArrayList<KeyValues>> stringArray){
        this.stringArray.clear();
        System.out.print("Enter number of rows: ");
        this.rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        this.columns = sc.nextInt();
        for (int indexRow = 0; indexRow < this.rows; indexRow++) {
            ArrayList<KeyValues> stringContent = new ArrayList<KeyValues>();
            for (int indexColumn = 0; indexColumn < this.columns; indexColumn++) {
                stringContent.add(new KeyValues(indexRow + "," + indexColumn, generateString()));
            }
            this.stringArray.add(stringContent);
        }
    }
    
    public String generateString(){
        
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
}
