package Algorithms;

/**
 * Created by Samin on 11/04/2017.
 */

import java.io.IOException;

import static Evaluation.MST.TSP_MST.getMSTCost;

/**
 * This class implements the Random Mutation Hill Climbing algorithm to solve the Travelling Salesman Problem
 */
public class TSP_RMHC
   {
      /**
       * @param iter is the number of iterations
       * @param cityNum is the total number of cities (dependent on dataset)
       * @param distanceMatrix is a 2D array containing the distances between nodes
       * @return the best tour
       */
      public static TSPSolution RMHC(int iter, int cityNum, double[][] distanceMatrix)
         {
            double oldFitness = 0.0, newFitness = 0.0, bestFitness;
            
            // 1. Create initial permutation
            TSPSolution sol = new TSPSolution(cityNum);
            System.out.print("Initial solution: ");
            sol.print();
            System.out.println();
            
            for(int i = 0; i < iter; i++)
               {
                  System.out.println("==========Iteration = " + (i+1) + "==========");
                  
                  // 2. Get the fitness of old permutation
                  TSPSolution oldSol = new TSPSolution(sol.getSol());
                  System.out.print("Old solution: ");
                  oldSol.print();
                  System.out.println();
                  
                  oldFitness = oldSol.tourFitness(distanceMatrix);
                  System.out.println("Old fitness: " + oldFitness + "\n");
                  
                  // 3. Make a small change
                  sol.smallChange();
                  
                  // 4. Get the fitness of the new permutation
                  TSPSolution newSol = new TSPSolution(sol.getSol());
                  System.out.print("New solution: ");
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
            
            // Finding the best fitness
            if(oldFitness < newFitness)
               {
                  bestFitness = oldFitness;
               }
            else
               {
                  bestFitness = newFitness;
               }
            
            System.out.println("==================== SUMMARY ====================");
            
            System.out.println("========== RMHC ==========");
            
            System.out.print("Best tour is: ");
            sol.print();
            System.out.println();
            
            System.out.println("With a fitness of: " + bestFitness);
            
            System.out.println("========== MST ==========");
            double MSTCost = getMSTCost(distanceMatrix);
            System.out.println("With a fitness of: " + MSTCost);
            
            double MSTEff = (MSTCost / bestFitness) * 100.0;
            System.out.println("Efficiency (%): " + MSTEff);
            
            System.out.print(bestFitness + ", " + MSTEff);
            System.out.println();
            
            return sol;
         }
      
      public static void main(String args[]) throws IOException
         {
            String algorithmTour = "PATH_TO_FILE.txt";
            double[][] distanceMatrix = Other.TSP.ReadArrayFile(algorithmTour, " ");
            RMHC(10, distanceMatrix.length, distanceMatrix);
         }
   }
