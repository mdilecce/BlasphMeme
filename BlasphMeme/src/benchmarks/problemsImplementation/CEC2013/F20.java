package benchmarks.problemsImplementation.CEC2013;


public class F20 extends AbstractCEC2013
{
	protected final double bias = 600.0;
	
	public F20 (int dimension) {super(dimension);}

	// Function body
	public double f(double[] x) {return CEC2013TestFunctions.escaffer6_func(x,nx,this.OShift,this.M,1)+this.bias;}
}
