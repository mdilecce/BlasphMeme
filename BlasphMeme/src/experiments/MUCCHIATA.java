package experiments;

import interfaces.Experiment;
import interfaces.Algorithm;
//import interfaces.Problem;
import algorithms.singleSolution.*;
import algorithms.*;
//import benchmarks.BaseFunctions.Alpine;
import benchmarks.CEC2014;
//import benchmarks.BaseFunctions.Ackley;
//import benchmarks.BaseFunctions.Rosenbrock;

public class MUCCHIATA extends Experiment
{
	
	public MUCCHIATA(int probDim) throws Exception
	{
		//super(probDim,"DESIGN");
		super(probDim,5000,"DESIGN");
		setNrRuns(30);
		
		Algorithm a;// ///< A generic optimiser.
//	    Problem p;// ///< A generic problem.

	    a = new PMS();
	    a.setParameter("p0",0.5);
	    a.setParameter("p1", 0.4);
	    a.setParameter("p2", 150.0);
	    a.setParameter("p3", 2.0);
	    a.setParameter("p4", 0.5);
	    a.setParameter("p5", 0.5);
	    add(a);
	    
	    a = new SPAM();
	    a.setParameter("p0",0.95);
	    a.setParameter("p1", 140.0);
	    a.setParameter("p2", 0.4);
	    a.setParameter("p3", 10e-5);
	    a.setParameter("p4", 2.0);
	    a.setParameter("p5", 0.00001);
	    add(a);

//		a = new ISPO();
//		a.setParameter("p0", 1.0);
//		a.setParameter("p1", 10.0);
//		a.setParameter("p2", 2.0);
//		a.setParameter("p3", 4.0);
//		a.setParameter("p4", 1e-5);
//		a.setParameter("p5", 30.0);
//		add(a); //add it to the list
//
//		a = new CMAES(); //N.B. this algorithm makes use of "generateRandomSolution" that has still to be implemented.
//		add(a);
//		
	    
		for(int i = 1; i<=30; i++)
			add(new CEC2014(probDim, i));

	}
}