package experiments;

import interfaces.Experiment;
import interfaces.Algorithm;
import benchmarks.CEC2013LSGO;
import algorithms.ISPO; 



public class CEC13LSGO extends Experiment
{
	
	public CEC13LSGO(int probDim) throws Exception
	{
		//super(probDim,"cec2015allDim");
		super(probDim,5,"testCEC15");
		setNrRuns(1);


		Algorithm a;// ///< A generic optimiser.
	    //Problem p;// ///< A generic problem.

		a = new ISPO();
		a.setParameter("p0", 1.0);
		a.setParameter("p1", 10.0);
		a.setParameter("p2", 2.0);
		a.setParameter("p3", 4.0);
		a.setParameter("p4", 1e-5);
		a.setParameter("p5", 30.0);
		add(a);
	
		for(int i = 1; i<=15; i++)
			add(new CEC2013LSGO(probDim, i));

	}
}
