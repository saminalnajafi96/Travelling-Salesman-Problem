package Algorithms;

/**
 * Created by Samin on 11/04/2017.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static Evaluation.MST.TSP_MST.getMSTCost;

/**
 * This class implements the Random Restart Hill Climbing algorithm
 */
public class TSP_RRHC
   {
      /**
       * @param iter is the number of iterations
       * @param restarts is the number of times the algorithm will restart
       * @param cityNum is the total number of cities (dependent on dataset)
       * @param distanceMatrix is a 2D array containing the distances between nodes
       * @return
       */
      public static TSPSolution RRHC(int iter, int restarts, int cityNum, double[][] distanceMatrix)
         {
            // An arrl which contains all best solutions for every restart
            ArrayList<TSPSolution> tours = new ArrayList<TSPSolution>();
            
            // An arrl which contains all the best fitness' for every restart
            ArrayList<Double> fitnessList = new ArrayList<Double>();
            
            
            double oldFitness = 0.0, newFitness = 0.0, bestFitness;
            
            // Calling outside to be able to use variables inside loops
            TSPSolution sol = null, oldSol, newSol;
            
            for(int i = 0; i < restarts; i++)
               {
                  System.out.println("====================Restart: " + (i+1) + "====================");
                  
                  // 1. Create initial permutation
                  sol = new TSPSolution(cityNum);
                  System.out.println("Initial solution: ");
                  sol.print();
                  System.out.println();
                  
                  for(int j = 0; j < iter; j++)
                     {
                        System.out.println("==========Iteration = " + (j + 1) + "==========");
                        
                        // 2. Get the fitness of old permutation
                        oldSol = new TSPSolution(sol.getSol());
                        System.out.println("Old solution: ");
                        oldSol.print();
                        System.out.println();
                        
                        oldFitness = oldSol.tourFitness(distanceMatrix);
                        System.out.println("Old fitness: " + oldFitness + "\n");
                        
                        // 3. Make a small change
                        sol.smallChange();
                        
                        // 4. Get the fitness of the new permutation
                        newSol = new TSPSolution(sol.getSol());
                        System.out.println("New solution: ");
                        newSol.print();
                        System.out.println();
                        
                        newFitness = newSol.tourFitness(distanceMatrix);
                        System.out.println("New fitness: " + newFitness + "\n");
                        
                        // 5. If worse, go back to old permutation (minimisation problem)
                        if(newFitness > oldFitness)
                           {
                              sol = new TSPSolution(oldSol.getSol());
                           }
                     }
                  if(newFitness < oldFitness)
                     {
                        bestFitness = newFitness;
                     }
                  else
                     {
                        bestFitness = oldFitness;
                     }
                  System.out.print("Best solution for restart " + i + ": ");
                  sol.print();
                  System.out.println();
                  
                  // Adding sol (best solution) to arrl
                  tours.add(sol);
                  fitnessList.add(bestFitness);
               }
            
            System.out.println("==================== SUMMARY ====================");
            
            System.out.println("========== RRHC ==========");
            
            // Best solution for all restarts will be written to this variable
            TSPSolution bestTour = compareTours(tours, fitnessList);
            
            System.out.print("Best tour is: ");
            bestTour.print();
            System.out.println();
            bestFitness = bestTour.tourFitness(distanceMatrix);
            System.out.println("With a fitness of:" + bestTour.tourFitness(distanceMatrix));
            
            System.out.println("========== MST ==========");
            double MSTCost = getMSTCost(distanceMatrix);
            System.out.println("With a fitness of: " + MSTCost);
            
            double MSTEff = (MSTCost / bestFitness) * 100.0;
            System.out.println("Efficiency (%): " + MSTEff);
            
            return sol;
         }
      
      /**
       * This method compares all the best tours from each restart by looking at which has the smallest fitness value
       * @param allTours is an ArrayList which contains all tours
       * @param fitnessList
       * @return the best tour
       * */
      public static TSPSolution compareTours(ArrayList<TSPSolution> allTours, ArrayList<Double> fitnessList)
         {
            int minIndex = fitnessList.indexOf(Collections.min(fitnessList));
            //System.out.println("Restart " + minIndex + " has the best tour");
            
            return allTours.get(minIndex);
         }
      
      public static void main(String args[]) throws IOException
         {
            String algorithmTour = "PATH_TO_FILE.txt";
            
            double[][] distanceMatrix = Other.TSP.ReadArrayFile(algorithmTour, " ");
            RRHC(1000, 10, distanceMatrix.length, distanceMatrix);
         }
   }