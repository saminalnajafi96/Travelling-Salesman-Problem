package Other;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

//Some useful code for the CS2004 (2016-2017) Travelling Salesman Worksheet
public class TSP
	{
		//Shared random object
		static private Random rand;
		
		//Create a uniformly distributed random integer between aa and bb inclusive
		static public int UI(int aa,int bb)
			{
				int a = Math.min(aa,bb);
				int b = Math.max(aa,bb);
				if (rand == null)
					{
						rand = new Random();
						rand.setSeed(System.nanoTime());
					}
				int d = b - a + 1;
				int x = rand.nextInt(d) + a;
				return(x);
			}
		
		//Create a uniformly distributed random double between a and b inclusive
		static public double UR(Double a,Double b)
			{
				if (rand == null)
					{
						rand = new Random();
						rand.setSeed(System.nanoTime());
					}
				return((b-a)*rand.nextDouble()+a);
			}
		
		//Print a 2D double array to the console Window
		static public void PrintArray(double x[][])
			{
				for(int i=0;i<x.length;++i)
					{
						for(int j=0;j<x[i].length;++j)
							{
								System.out.print(x[i][j]);
								System.out.print(" ");
							}
						System.out.println();
					}
			}
		
		//This method reads in a text file and parses all of the numbers in it
		//This method is for reading in a square 2D numeric array from a text file
		//This code is not very good and can be improved!
		//But it should work!!!
		//'sep' is the separator between columns
		static public double[][] ReadArrayFile(String path,String sep) throws IOException
			{
				double[][] graph = null;
				FileReader fr = new FileReader(path);
				BufferedReader buf = new BufferedReader(fr);
				String line;
				int i = 0;
				
				while ((line = buf.readLine()) != null) {
					String splitA[] = line.split(sep);
					LinkedList<String> split = new LinkedList<String>();
					for (String s : splitA)
						if (!s.isEmpty())
							split.add(s);
					
					if (graph == null)
						graph = new double[split.size()][split.size()];
					int j = 0;
					
					for (String s : split)
						if (!s.isEmpty())
							graph[i][j++] = Double.parseDouble(s) + 1;
					
					i++;
					}
				return(graph);
			}
		
		//This method reads in a text file and parses all of the numbers in it
		//This code is not very good and can be improved!
		//But it should work!!!
		//It takes in as input a string filename and returns an array list of Integers
		static public ArrayList<Integer> ReadIntegerFile(String filename)
			{
				ArrayList<Integer> res = new ArrayList<Integer>();
				Reader r;
				try
					{
						r = new BufferedReader(new FileReader(filename));
						StreamTokenizer stok = new StreamTokenizer(r);
						stok.parseNumbers();
						stok.nextToken();
						while (stok.ttype != StreamTokenizer.TT_EOF)
							{
								if (stok.ttype == StreamTokenizer.TT_NUMBER)
									{
										res.add((int)(stok.nval));
									}
								stok.nextToken();
							}
					}
				catch(Exception E)
					{
						System.out.println("+++ReadIntegerFile: "+E.getMessage());
					}
				return(res);
			}
		
		public static double efficiencyTest(double algorithmFitness, double comparisonFitness)
			{
				double eff = 0.0;
				
				if(algorithmFitness < comparisonFitness)
					{
						eff = (algorithmFitness / comparisonFitness) * 100.0;
					}
				
				return eff;
			}
		
		public static void printArrl(ArrayList<Integer> arrl)
			{
				for(int i = 0; i < arrl.size(); i++)
					{
						System.out.print(arrl.get(i) + " ");
					}
			}
	}