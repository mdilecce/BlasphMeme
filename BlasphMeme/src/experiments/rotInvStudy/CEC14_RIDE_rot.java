package experiments.rotInvStudy;

import interfaces.Experiment;
import interfaces.Algorithm;
import benchmarks.CEC2014RotInvStudy;
import algorithms.RIDE;



public class CEC14_RIDE_rot extends Experiment
{
	
	public CEC14_RIDE_rot(int probDim) throws Exception
	{
		//super(probDim,"cec2015allDim");
		super(probDim,5000,"testCEC14RIDE");
		setNrRuns(30);


		Algorithm a;// ///< A generic optimiser.
	    //Problem p;// ///< A generic problem.
		
//		a = new CMAES();
//		a.setID("rotation");//N.B. this algorithm makes use of "generateRandomSolution" that has still to be implemented.
//		add(a);
		
		a = new RIDE();
		a.setID("rRIDErand1bin");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", 0.3); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 4.0);
		add(a);
		
		a = new RIDE();
		a.setID("rRIDErand1exp");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", 0.3); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 5.0);
		add(a);
		
		for(int i = 1; i<=30; i++)
				add(new CEC2014RotInvStudy(probDim, i));




	}
}