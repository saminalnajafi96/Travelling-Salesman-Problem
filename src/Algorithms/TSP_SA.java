package Algorithms;

/**
 * Created by Samin on 11/04/2017.
 */

import java.io.IOException;

import static Evaluation.MST.TSP_MST.getMSTCost;

/**
 * This class implements the Simulated Annealing algorithm
 */
public class TSP_SA
   {
      /**
       * This method calculates the acceptance probability
       * @param oldFitness is the fitness of the old solution
       * @param newFitness is the fitness of the new solution
       * @param currentTemp is the temperature in the current iteration (Titer)
       * @return the probability of accepting a solution
       */
      public static double acceptanceProbability(double oldFitness, double newFitness, double currentTemp)
         {
            double fitDiff = Math.abs(newFitness - oldFitness);
            return Math.exp((-fitDiff) / currentTemp);
         }
      
      /**
       *
       * @param startTemp is the starting temperature
       * @param iter is the number of iterations
       * @param coolRate (a.k.a lambda) is the rate at which the simulated annealing process cools
       * @param cityNum is the total number of cities (dependent on dataset)
       * @param distanceMatrix is a 2D array containing the distances between nodes
       * @return
       */
      public static TSPSolution SA(double startTemp, int iter, double coolRate, int cityNum, double[][] distanceMatrix)
         {
            double oldFitness = 0.0, newFitness = 0.0, bestFitness;
            double currentTemp = startTemp;
            
            // 1. Create a random solution
            TSPSolution sol = new TSPSolution(cityNum);
            System.out.print("Initial solution: ");
            sol.print();
            System.out.println();
            
            for(int i = 0; i < iter; i++)
               {
                  System.out.println("==========Iteration = " + (iter+1) + "==========");
                  
                  // 2. Evaluate old permutation
                  TSPSolution oldSol = new TSPSolution(sol.getSol());
                  System.out.print("Old solution: ");
                  oldSol.print();
                  System.out.println();
                  
                  oldFitness = oldSol.tourFitness(distanceMatrix);
                  System.out.println("Old fitness: " + oldFitness + "\n");
                  
                  // 3. Make a small change
                  sol.smallChange();
                  
                  // 4. Evaluate new solution
                  TSPSolution newSol = new TSPSolution(sol.getSol());
                  System.out.print("New solution: ");
                  newSol.print();
                  System.out.println();
                  
                  newFitness = newSol.tourFitness(distanceMatrix);
                  System.out.println("New fitness: " + newFitness + "\n");
                  
                  /**
                   * 5. If new fitness is worse
                   * Calculate probability from acceptance function
                   * if p < UR(0,1) reject change
                   * else accept change
                   */
                  if(newFitness > oldFitness)
                     {
                        if(acceptanceProbability(oldFitness, newFitness, currentTemp) < Other.TSP.UR(0.0, 1.0))
                           {
                              sol = new TSPSolution(oldSol.getSol());
                           }
                     }
                  // 6. Ti+1 = coolRate * Ti
                  currentTemp *= coolRate;
               }
   
            // Finding the best fitness
            if(newFitness < oldFitness)
               {
                  bestFitness = newFitness;
               }
            else
               {
                  bestFitness = oldFitness;
               }
            
            System.out.println("==================== SUMMARY ====================");
            
            System.out.println("========== SA ==========");
            
            System.out.print("Best tour is: ");
            sol.print();
            System.out.println();
            
            System.out.println(bestFitness);
            
            System.out.println("========== MST ==========");
            double MSTCost = getMSTCost(distanceMatrix);
            System.out.println("With a fitness of: " + MSTCost);
            
            double MSTEff = (MSTCost / bestFitness) * 100.0;
            System.out.println("Efficiency (%): " + MSTEff);
            
            return sol;
         }
      
      public static void main(String args[]) throws IOException
         {
            String algorithmTour = "/Users/Samin/Documents/CS2004 Labs/1540781_TSP/CS2004 TSP Data (2016-2017)/TSP_48.txt";
            
            double[][] distanceMatrix = Other.TSP.ReadArrayFile(algorithmTour, " ");
            
            int iter = 10000;
            double coolRate;
            double startTemp = 10000.0;
            double Titer = 0.0001;
            
            double divide = Titer / startTemp;
            double pow = (double) 1 / iter;
            coolRate = Math.pow(divide, pow);
            
            
            SA(startTemp, iter, coolRate, distanceMatrix.length, distanceMatrix);
            
         }
   }