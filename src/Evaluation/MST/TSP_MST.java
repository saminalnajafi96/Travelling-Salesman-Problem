package Evaluation.MST;

import java.util.ArrayList;

/**
 * Created by Samin on 11/04/2017.
 */
public class TSP_MST
   {
      // Tour that MST generates
      public static ArrayList<Integer> MSTTour = new ArrayList<Integer>();
   
      /**
       * This method calculates the cost of the MST graph
       * @param distanceMatrix contains the distances between nodes/cities
       * @return the cost of the MST graph
       */
      public static double getMSTCost(double[][] distanceMatrix)
         {
            double tourLength = 0.0;
            double[][] MSTgraph = MST.PrimsMST(distanceMatrix, MSTTour);
            
            for(int i = 0; i < MSTgraph.length; i++)
               {
                  for(int j = 0; j < MSTgraph[i].length; j++)
                     {
                        if(MSTgraph[i][j] != 0.0)
                           {
                              tourLength += MSTgraph[i][j];
                           }
                     }
               }
            // Dividing by 2 to remove N*N error
            tourLength = tourLength/2.0;
            
            // Add distance between start and end nodes
            tourLength += distanceMatrix[MSTTour.get(MSTTour.size()-1)][MSTTour.get(0)];
            return tourLength;
         }
   }
