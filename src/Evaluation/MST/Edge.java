package Evaluation.MST;/*
 * Created by Samin Alnajafi on 02/03/17 15:29
 * Copyright(c) 2017. All rights reserved
 *
 * Las modified on 05/12/16 15:07
 */

//Store an edge, from node i to j with weigh w
public class Edge extends Object
{
	public int i,j;
	public double w;
	Edge(int ii,int jj,double ww)
	{
		i = ii;
		j = jj;
		w = ww;
	};
	public void Print()
	{
		System.out.print("(");
		System.out.print(i);
		System.out.print(",");
		System.out.print(j);
		System.out.print(",");
		System.out.print(w);
		System.out.print(")");
	}
};