package controller;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import model.matrix;
import view.matrixView;

public class Exercise2 {

    public static void main(String[] args) throws IOException {
        
        Scanner sc = new Scanner (System.in);
        int rows;
        int columns;

        ArrayList<HashMap<String, String>> stringArray = new ArrayList<HashMap<String, String>>();

        System.out.print("Input number of rows: ");
        rows = sc.nextInt();
        System.out.print("Input number of columns: ");
        columns = sc.nextInt();

        matrix matrix = new matrix(stringArray, rows, columns);

        matrix.fileReader();
        matrix.inputFunction();
       
    }
}
