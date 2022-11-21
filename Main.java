import java.util.*;
import java.io.*;

class Main{
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    String dataOfGraph = "";
    String topsOfGraph = "";
    String weightOfGraphs = "";
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
    try {
      File myObj = new File("WeightOfGraph.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine())
        weightOfGraphs += myReader.nextLine();
      myReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String[] split = dataOfGraph.split("\\s");
    String[] top = topsOfGraph.split("\\s");
    String[] weight = weightOfGraphs.split("\\s");
    int[][] connectedGraph = new int[split.length+1][split.length+1];
    int[][] graph = new int[split.length+1][split.length+1];
    int[][] weightOfGraph = new int[split.length+1][split.length+1];
    int[] splitInt = new int[split.length];
    for (int i = 0; i < split.length; i++)
      splitInt[i] = Integer.parseInt(split[i]);
    for (int i = 0; i < 1; i++) {
      int k = 0;
      for (int j = 1; j < graph[i].length; j++) {
        graph[0][j] = splitInt[k++];
      }
      k=0;
      for (int j = 1; j < graph[i].length; j++) {
        graph[j][0] = splitInt[k++];
      }
      for (int j = 1; j < graph[i].length; j++) {
        for (int l = 1; l < graph[i].length; l++) {
          for (int m = 0; m < top.length; m++) {
            if(graph[j][0] == Character.getNumericValue(top[m].charAt(0)) && 
               graph[0][l] == Character.getNumericValue(top[m].charAt(2)))
              graph[j][l] = 1;
          }
        }
      }
    }
    for (int i = 0; i < 1; i++) {
      int k = 0;
      for (int j = 1; j < connectedGraph[i].length; j++) {
        connectedGraph[0][j] = splitInt[k++];
      }
      k=0;
      for (int j = 1; j < connectedGraph[i].length; j++) {
        connectedGraph[j][0] = splitInt[k++];
      }
      for (int j = 1; j < connectedGraph[i].length; j++) {
        for (int l = 1; l < connectedGraph[i].length; l++) {
          for (int m = 0; m < top.length; m++) {
            if((connectedGraph[j][0] == Character.getNumericValue(top[m].charAt(0)) && 
               connectedGraph[0][l] == Character.getNumericValue(top[m].charAt(2))) ||
              connectedGraph[j][0] == Character.getNumericValue(top[m].charAt(2)) &&
              connectedGraph[0][l] == Character.getNumericValue(top[m].charAt(0)))
              connectedGraph[j][l] = 1;
          }
        }
      }
    }
    for (int i = 0; i < 1; i++) {
      int k = 0;
      for (int j = 1; j < weightOfGraph[i].length; j++) {
        weightOfGraph[0][j] = splitInt[k++];
      }
      k=0;
      for (int j = 1; j < weightOfGraph[i].length; j++) {
        weightOfGraph[j][0] = splitInt[k++];
      }
      k=0;
      int n = 0;
      for (int j = 1; j < weightOfGraph[i].length; j++) {
        for (int l = 1; l < weightOfGraph[i].length; l++) {
          for (int m = 0; m < weight.length; m++) {
            if(weightOfGraph[j][0] == Character.getNumericValue(weight[m].charAt(0)) && 
               weightOfGraph[0][l] == Character.getNumericValue(weight[m].charAt(2)))
              weightOfGraph[j][l] = Character.getNumericValue(weight[m].charAt(4));
          }
        }
      }
    }
    System.out.println("\n"+"Graph");
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[i].length; j++) {
        System.out.print(graph[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("\n"+"Connected Graph");
    for (int i = 0; i < connectedGraph.length; i++) {
      for (int j = 0; j < connectedGraph[i].length; j++) {
        System.out.print(connectedGraph[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println("\n"+"Weight Of Graph");
    for (int i = 0; i < weightOfGraph.length; i++) {
      for (int j = 0; j < weightOfGraph[i].length; j++) {
        System.out.print(weightOfGraph[i][j] + " ");
      }
      System.out.println();
    }
    System.out.print("\n"+"Enter two peaks of graph ");
    int firstTop = input.nextInt();
    int secondTop = input.nextInt();
    int[] shortPath = new int[3];
    Map<String,Integer> path = new HashMap<>();
    boolean checkPeaks = false;
    for(int i = 0; i<split.length; i++){
      if(Integer.parseInt(split[i]) == firstTop){
        for(int j = 0; j<split.length; j++){
          if(Integer.parseInt(split[j]) == secondTop)
            checkPeaks = true;
        }
      }
    }
    if(checkPeaks){
      for(int i = 0; i < weight.length; i++){
        if(firstTop == Character.getNumericValue(weight[i].charAt(0))){
          String pathOfGraph = "";
          int weights = 0;
          pathOfGraph += Character.getNumericValue(weight[i].charAt(0));
          pathOfGraph += Character.getNumericValue(weight[i].charAt(2));
          weights += Character.getNumericValue(weight[i].charAt(4));
          if(!pathOfGraph.isEmpty())
            path.put(pathOfGraph,weights);
          for(int j = 0; j < weight.length; j++){
            if(Character.getNumericValue(weight[i].charAt(2)) == 
                Character.getNumericValue(weight[j].charAt(0))){
              pathOfGraph = "";
              weights = 0;
              pathOfGraph += Character.getNumericValue(weight[i].charAt(0));
              pathOfGraph += Character.getNumericValue(weight[i].charAt(2));
              pathOfGraph += Character.getNumericValue(weight[j].charAt(2));
              weights += Character.getNumericValue(weight[i].charAt(4));
              weights += Character.getNumericValue(weight[j].charAt(4));
              if(!pathOfGraph.isEmpty())
                path.put(pathOfGraph,weights);
              for(int k = 0; k < weight.length; k++){
                pathOfGraph = "";
                weights = 0;
                if(Character.getNumericValue(weight[j].charAt(2)) == 
                    Character.getNumericValue(weight[k].charAt(0)) &&
                  secondTop == Character.getNumericValue(weight[k].charAt(2))){
                  pathOfGraph += Character.getNumericValue(weight[i].charAt(0));
                  pathOfGraph += Character.getNumericValue(weight[i].charAt(2));
                  pathOfGraph += Character.getNumericValue(weight[j].charAt(2));
                  pathOfGraph += Character.getNumericValue(weight[k].charAt(2));
                  weights += Character.getNumericValue(weight[i].charAt(4));
                  weights += Character.getNumericValue(weight[j].charAt(4));
                  weights += Character.getNumericValue(weight[k].charAt(4));
                  if(!pathOfGraph.isEmpty())
                    path.put(pathOfGraph,weights);
                  for(int l = 0; l < weight.length; l++){
                    pathOfGraph = "";
                    weights = 0;
                    if(Character.getNumericValue(weight[k].charAt(2)) == 
                        Character.getNumericValue(weight[l].charAt(0)) &&
                      secondTop == Character.getNumericValue(weight[l].charAt(2))){
                      pathOfGraph += Character.getNumericValue(weight[i].charAt(0));
                      pathOfGraph += Character.getNumericValue(weight[i].charAt(2));
                      pathOfGraph += Character.getNumericValue(weight[j].charAt(2));
                      pathOfGraph += Character.getNumericValue(weight[k].charAt(2));
                      pathOfGraph += Character.getNumericValue(weight[l].charAt(2));
                      weights += Character.getNumericValue(weight[i].charAt(4));
                      weights += Character.getNumericValue(weight[j].charAt(4));
                      weights += Character.getNumericValue(weight[k].charAt(4));
                      weights += Character.getNumericValue(weight[l].charAt(4));
                      if(!pathOfGraph.isEmpty())
                        path.put(pathOfGraph,weights);
                    }
                  }
                }
              }
            }
          }
        }
      }
      boolean noWay = false; 
      if(!path.isEmpty()){
        String p = "";
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<String, Integer> i : path.entrySet()) {
          if(String.valueOf(i.getKey().charAt(i.getKey().length()-1)).equals(
            Integer.toString(secondTop))){
            noWay = true;
            list.add(i.getValue());
          } 
        }
        int minOfWeight = 0;
        String s = "";
        if(!list.isEmpty()){
          minOfWeight = Collections.min(list);
          for (Map.Entry<String, Integer> i : path.entrySet()) {
            if(i.getValue() == minOfWeight && 
              String.valueOf(i.getKey().charAt(i.getKey().length()-1)).equals(
              Integer.toString(secondTop))){
              int result = Integer.parseInt(i.getKey());
              while(result != 0){
                int module = result % 10;
                s = module + " -> " + s;
                result /= 10;
              }
            }
          }
        }
        if(!s.isEmpty()){
          StringBuffer str = new StringBuffer(s);
          str.deleteCharAt(str.length()-1);
          str.deleteCharAt(str.length()-1);
          str.deleteCharAt(str.length()-1);
          System.out.print(str + "(" + minOfWeight + ")");
        }
      }
      if(!noWay)
        System.out.println("There is no way!");
    }
    else
      System.out.println("Entered peak(s) doesn't match peaks of graph!");
  }
}