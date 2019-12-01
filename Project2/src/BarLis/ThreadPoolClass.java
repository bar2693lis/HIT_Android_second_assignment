package BarLis;

import java.util.ArrayList;
import java.util.Random;

public class ThreadPoolClass implements Runnable {

    private static ArrayList<int[][]> matricesList = new ArrayList<int[][]>();
    private int matrixDimension;

    public ThreadPoolClass(int matrixDimension) {
        this.matrixDimension = matrixDimension;
    }

    @Override
    public void run() {

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        addMatToList(matrixDimension);
    }

    private static synchronized void addMatToList(int matrixDimension) {

        Random rand = new Random();
        int[][] tempMatrix = new int[matrixDimension][matrixDimension];

        for(int i = 0; i < matrixDimension ; i++) {
            for(int j = 0; j < matrixDimension ; j++) {
                int k = rand.nextInt(10);
                tempMatrix[i][j] = k ;
            }
        }
        matricesList.add(tempMatrix);
    }

    public static void print() {

        int[][] result = resetMatrix(matricesList.get(0).length);

        for(int i = 1; i < matricesList.size(); i++) {
            int j = i-1;
            result = multiplicar(matricesList.get(j), matricesList.get(i));
        }

        for(int i = 0; i < matricesList.get(0).length; i++) {
            for(int j = 0; j < matricesList.get(0).length; j++) {
                System.out.println("[" + result[i][j] + "] ");
            }
            System.out.println("\n");
        }
        matricesList.clear();
    }

    private static int[][] resetMatrix(int matrixDimension) {

        int[][] tempMatrix = new int[matrixDimension][matrixDimension];

        for(int i = 0; i < matrixDimension; i++) {
            for(int j = 0; j < matrixDimension; j++) {
                tempMatrix[i][j] = 0;
            }
        }
        return tempMatrix;
    }

    private static int[][] multiplicar(int[][] A, int[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if(aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        int[][] C = resetMatrix(A.length);

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

}
