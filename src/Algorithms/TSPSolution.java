package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Samin on 11/04/2017.
 */
public class TSPSolution
   {
      // ArrayList of cities visited
      private ArrayList<Integer> tour;
      
      // Default constructor
      public TSPSolution(ArrayList<Integer> t)
         {
            int n = t.size();
            boolean duplicate = hasDuplicate(t);
            
            if(duplicate)
               {
                  tour = randPermutation(n);
                  System.out.println("Duplicate has been found!");
               }
            else
               {
                  // Using clone to fix bug where oldSol would change if newSol was worse
                  tour = (ArrayList<Integer>) t.clone();
               }
         }
      
      
      public boolean hasDuplicate(ArrayList<Integer> t)
         {
            Set seenValues = new HashSet<Integer>();
            
            for(Integer value : t)
               {
                  if(!seenValues.add(value))
                     {
                        return true;
                     }
                  else
                     {
                        seenValues.add(value);
                     }
               }
            
            return false;
         }
      
      public TSPSolution(int cityNum)
         {
            tour = randPermutation(cityNum);
         }
      
      /**
       * This method creates a random tour as an initial starting point
       * @param cityNum is the number of cities
       * @return a random permutation of cities
       */
      private static ArrayList<Integer> randPermutation(int cityNum)
         {
            ArrayList<Integer> randomTour = new ArrayList<Integer>();
            for(int i = 0; i < cityNum; i++)
               {
                  randomTour.add(i);
               }
            
            Collections.shuffle(randomTour);
            
            return randomTour;
         }
   
      /**
       * This method calculates the fitness (sum of distances) of a tour
       * @param distanceMatrix is a 2D array containing distances between nodes/cities
       * @return the total distance travelled in a tour
       */
      public double tourFitness(double[][] distanceMatrix)
         {
            double tourLength = 0.0;
            int n = tour.size();
   
            /**
             * i < n-1 because at the last element of the ArrayList, it is not possible to look at i+1 (out
             * of bounds exception) so we stop the loop at the second last element in order to look at the last
             */
            for(int i = 0; i < n-1; i++)
               {
                  int node1 = tour.get(i);
                  int node2 = tour.get(i+1);
                  
                  tourLength += distanceMatrix[node1][node2];
               }
            
            int endCity = tour.get(n-1);
            int startCity = tour.get(0);
            
            tourLength += distanceMatrix[endCity][startCity];
            
            return tourLength;
         }
   
      /**
       * This method calculates the distance between 2 nodes
       * @param n1 A node/city
       * @param n2 The next node/city the salesman will be travelling to
       * @param distanceMatrix a 2D array containing distances between nodes/cities
       * @return the distance between 2 nodes/cities
       */
      private double getDistance(int n1, int n2, double[][] distanceMatrix)
         {
            double distance;
            int n1Row, n1Col, n2Row, n2Col, columnNum = distanceMatrix.length;
            
            // row = node / no. columns
            n1Row = Math.floorDiv(n1, columnNum);
            n2Row = Math.floorDiv(n2, columnNum);
            
            // column = node % no. columns
            n1Col = n1 % columnNum;
            n2Col = n2 % columnNum;
            
            distance = Math.abs(distanceMatrix[n1Row][n1Col] - distanceMatrix[n2Row][n2Col]);
            
            return distance;
         }
   
      /**
       * This method makes a small change to the tour by swapping 2 nodes/cities
       */
      public void smallChange()
         {
            int i,j;
            
            i = j = 0;
            
            while(i == j)
               {
                  i = Other.TSP.UI(0,Math.abs(tour.size()-1));
                  j = Other.TSP.UI(0,Math.abs(tour.size()-1));
               }
            
            Collections.swap(tour,i,j);
         }
   
      /**
       * This method returns the tour within the current instance of this class
       * @return a tour
       */
      public ArrayList<Integer> getSol()
         {
            return tour;
         }
      
      public void print()
         {
            int n = tour.size();
            
            for(int i = 0; i < n; i ++)
               {
                  System.out.print(tour.get(i) + " ");
               }
         }
   }