import java.util.*;
import java.io.*;

class Main{
  public static void main(String[] args) {
    String dataOfGraph = "";
    String topsOfGraph = "";
    try {
      File myObj = new File("GraphData.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine())
        dataOfGraph += myReader.nextLine();
      myReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    try {
      File myObj = new File("TopsOfGraph.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine())
        topsOfGraph += myReader.nextLine();
      myReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] split = dataOfGraph.split("\\s");
    String[] top = topsOfGraph.split("\\s");
    int[][] matrix = new int[split.length+1][split.length];
    for (int i = 0; i < matrix.length; i++) {
      int k = 0;
      for (int j = 1; j < matrix[i].length; j++) {
        matrix[0][j] = Integer.parseInt(split[k++]);
      }
      k=0;
      for (int j = 1; j <= matrix[i].length; j++) {
        matrix[j][0] = Integer.parseInt(split[k++]);
      }
    }
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }
}