package Algorithms;

/**
 * Created by Samin on 11/04/2017.
 */

import java.io.IOException;

import static Evaluation.MST.TSP_MST.getMSTCost;

/**
 * This class implements the Stochastic Hill Climbing algorithm
 */
public class TSP_SHC
   {
      /**
       * @param iter is the number of iterations
       * @param cityNum is the total number of cities (dependent on dataset)
       * @param T is a constant
       * @param distanceMatrix is a 2D array containing the distances between nodes
       * @return the best tour
       */
      public static TSPSolution SHC(int iter, int cityNum, double T, double[][] distanceMatrix)
         {
            double oldFitness = 0.0, newFitness = 0.0, bestFitness;
            
            // 1. Create initial permutation
            TSPSolution sol = new TSPSolution(cityNum);
            System.out.println("Initial solution: ");
            sol.print();
            System.out.println();
            
            for(int i = 0; i < iter; i++)
               {
                  System.out.println("==========Iteration = " + (i+1) + "==========");
                  
                  // 2. Get the fitness of old permutation
                  TSPSolution oldSol = new TSPSolution(sol.getSol());
                  System.out.println("Old solution: ");
                  oldSol.print();
                  System.out.println();
                  
                  oldFitness = oldSol.tourFitness(distanceMatrix);
                  System.out.println("Old fitness: " + oldFitness + "\n");
                  
                  // 3. Make a small change
                  sol.smallChange();
                  
                  // 4. Get the fitness of the new permutation
                  TSPSolution newSol = new TSPSolution(sol.getSol());
                  System.out.println("New solution: ");
                  newSol.print();
                  System.out.println();
                  
                  newFitness = newSol.tourFitness(distanceMatrix);
                  System.out.println("New fitness: " + newFitness + "\n");
                  
                  // 5. Calculate probability of accepting new solution
                  double prAccept = 1.0 / (1.0 + Math.exp((newFitness - oldFitness) / T));
                  double rand = Other.TSP.UR(0.0,1.0);
                  if(prAccept < rand)
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

            System.out.println("========== SHC ==========");

            System.out.print("Best tour is: ");
            sol.print();
            System.out.println();
            System.out.println("With a fitness of: " + bestFitness);
            
            System.out.println("========== MST ==========");
            double MSTCost = getMSTCost(distanceMatrix);
            System.out.println("With a fitness of: " + MSTCost);
            
            double MSTEff = (MSTCost / bestFitness) * 100.0;
            System.out.println("Efficiency (%): " + MSTEff);
            
            return sol;
         }
      
      public static void main(String args[]) throws IOException
         {
            String algorithmTour = "PATH_TO_FILE.txt";
            
            double[][] distanceMatrix = Other.TSP.ReadArrayFile(algorithmTour, " ");
            
            SHC(10, distanceMatrix.length, 100.0, distanceMatrix);
            
         }
   }