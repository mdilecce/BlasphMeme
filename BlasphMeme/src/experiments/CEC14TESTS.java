package experiments;

import interfaces.Experiment;
import interfaces.Algorithm;
import benchmarks.CEC2014;
import algorithms.*;

public class CEC14TESTS extends Experiment
{
	
	public CEC14TESTS(int probDim) throws Exception
	{
		//super(probDim,"cec2015allDim");
		super(probDim,5000,"testCEC14");
		setNrRuns(30);


		Algorithm a;// ///< A generic optimiser.
	    //Problem p;// ///< A generic problem.
		
//		a = new DE();
//		a.setID("rDEr1bin");
//		a.setParameter("p0", (double)probDim);
//		a.setParameter("p1", 0.7);//F
//		a.setParameter("p2", -1.0); //CR
//		a.setParameter("p3", 1.0);
//		a.setParameter("p4", 1.0);
//		a.setParameter("p5", 0.3);//ALPHA
//		add(a);
//		
		a = new DE();
		a.setID("rDEr1exp");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.7);//F
		a.setParameter("p2", -1.0); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 2.0);
		a.setParameter("p5", 0.3);//ALPHA
		add(a);
		
		a = new EigenRIDE();
		a.setID("EigenRIDEbin");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", 0.3); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 4.0);
		a.setParameter("p5", Double.NaN);
		add(a);
		
		a = new EigenRIDE();
		a.setID("EigenRIDEexp");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", -1.0); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 5.0);
		a.setParameter("p5", 0.3);
		add(a);
		
		a = new RIDE();
		a.setID("rRIDErand1bin");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", 0.3); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 4.0);
		a.setParameter("p5", Double.NaN);
		add(a);
		
		a = new RIDE();
		a.setID("rRIDErand1exp");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.4); //F
		a.setParameter("p2", -1.0); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 5.0);
		a.setParameter("p5", 0.3);
		add(a);
		
		a = new EigenDE();
		a.setID("rEigenDEr1bin");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.7);//F
		a.setParameter("p2", 0.3); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 1.0);
		a.setParameter("p5", Double.NaN);//ALPHA
		a.setParameter("p6", 1.0);//PR
		add(a);
		
		a = new EigenDE();
		a.setID("rEigenDEr1exp");
		a.setParameter("p0", (double)probDim);
		a.setParameter("p1", 0.7);//F
		a.setParameter("p2", -1.0); //CR
		a.setParameter("p3", 1.0);
		a.setParameter("p4", 2.0);
		a.setParameter("p5", 0.3);//ALPHA
		a.setParameter("p6", 1.0);//PR
		add(a);

		for(int i = 1; i<=30; i++)
			add(new CEC2014(probDim, i));




	}
}
