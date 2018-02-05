# Travelling Salesman Problem
University project where I had to solve the Travelling Salesman Problem with 4 different local search algorithms:
* Random Mutation Hill Climber
* Random Restart Hill Climber
* Stochastic Hill Climber
* Simulated Annealing

# Cloning project
`git clone https://github.com/saminalnajafi96/Travelling-Salesman-Problem.git`

# src
* Algorithms package
    * Contains the java classes for the 4 algorithms as well as a TSPSolution class
* Evaluation
    * Uses a minimum spanning tree algorithm to evaluate the results from the algorithms
* Other
    * Tools and other general purpose functions (e.g. reading in files, generating random numbers, etc.)

# TSP Data Folder
This folder contains text files in the form of: <br/>
TSP_<N>.txt: distance file for N cities, N rows by N columns (real numbers), separated by a space, 31 files in total

The `algorithmTour` variable in main functions in all the algorithm java classes will contain the path to one of the files

NOTE: Files labeled 'OPT' are the optimum routes and can be used as another form of evaluation. I have not included the code to do so in this project.