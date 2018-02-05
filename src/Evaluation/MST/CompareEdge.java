package Evaluation.MST;/*
 * Created by Samin Alnajafi on 02/03/17 15:29
 * Copyright(c) 2017. All rights reserved
 *
 * Las modified on 05/12/16 15:08
 */

//Compare edge weights - used to sort an ArrayList of edges
public class CompareEdge implements java.util.Comparator 
{
	public int compare(Object a, Object b) 
	{
		if (((Edge)a).w < ((Edge)b).w) return(-1);
		if (((Edge)a).w > ((Edge)b).w) return(1);
		return(0);
	}
}
