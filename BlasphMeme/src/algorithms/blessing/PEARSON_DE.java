package algorithms.blessing;

import  utils.algorithms.operators.DEOp;
import static utils.algorithms.Misc.toro;
import static utils.algorithms.Misc.Cov;
import static utils.algorithms.Misc.generateRandomSolution;

//import java.util.Vector; serve?

import interfaces.Algorithm;
import interfaces.Problem;
import utils.RunAndStore.FTrend;

/**
 * Differential Evolution (standard version, rand/1/bin)
 */
public class PEARSON_DE extends Algorithm
{
	@Override
	public FTrend execute(Problem problem, int maxEvaluations) throws Exception
	{
		int populationSize = getParameter("p0").intValue(); 
		double F = getParameter("p1").doubleValue();
		double CR = getParameter("p2").doubleValue();
		int mutationStrategy = getParameter("p3").intValue();
		int crossoverStrategy = getParameter("p4").intValue();
		double alpha = getParameter("p5").doubleValue();

		FTrend FT = new FTrend();
		int problemDimension = problem.getDimension(); 
		double[][] bounds = problem.getBounds();
		
		if(CR==-1)
			CR = 1.0/Math.pow(2.0,1.0/(problemDimension*alpha));
		
		double[][] population = new double[populationSize][problemDimension];
		double[] fitnesses = new double[populationSize];
		
		double[] best = new double[problemDimension];
		double fBest = Double.NaN;
		
		int i = 0;
		
		// evaluate initial population
		for (int j = 0; j < populationSize; j++)
		{
			double[] tmp = generateRandomSolution(bounds, problemDimension);
			for (int n = 0; n < problemDimension; n++)
				population[j][n] = tmp[n];
			fitnesses[j] = problem.f(population[j]);
			
			if (j == 0 || fitnesses[j] < fBest)
			{
				fBest = fitnesses[j];
				for (int n = 0; n < problemDimension; n++)
					best[n] = population[j][n];
					FT.add(i, fBest);
			}
			
			i++;
		}

		// temp variables
		double[] currPt = new double[problemDimension];
		double[] newPt = new double[problemDimension];
		double[] crossPt = new double[problemDimension];
		double currFit = Double.NaN;
		double crossFit = Double.NaN;

		// iterate
		while (i < maxEvaluations)
		{
		
			double[][] newPop = new double[populationSize][problemDimension];

			for (int j = 0; j < populationSize && i < maxEvaluations; j++)
			{
				for (int n = 0; n < problemDimension; n++)
					currPt[n] = population[j][n];
				currFit = fitnesses[j];
				
				// mutation
				switch (mutationStrategy)
				{
					case 1:
						// DE/rand/1
						newPt = DEOp.rand1(population, F);
						break;
					case 2:
						// DE/cur-to-best/1
						newPt = DEOp.currentToBest1(population, best, j, F);
						break;
					case 3:
						// DE/rand/2
						newPt = DEOp.rand2(population, F);
						break;
					case 4:
						// DE/current-to-rand/1
						crossPt = DEOp.currentToRand1(population, j, F);
						break;
					case 5:
						// DE/rand-to-best/2
						newPt = DEOp.randToBest2(population, best, F);
						break;
					default:
						break;
				}
		
				// crossover
				if (mutationStrategy != 4)
				{
					if (crossoverStrategy == 1)
						crossPt = DEOp.crossOverBin(currPt, newPt, CR);
					else if (crossoverStrategy == 2)
						crossPt = DEOp.crossOverExp(currPt, newPt, CR);
				}
				
				crossPt = toro(crossPt, bounds);
				crossFit = problem.f(crossPt);
				i++;


				// replacement
				if (crossFit < currFit)
				{
					for (int n = 0; n < problemDimension; n++)
						newPop[j][n] = crossPt[n];
					fitnesses[j] = crossFit;
					
					// best update
					if (crossFit < fBest)
					{
						fBest = crossFit;
						for (int n = 0; n < problemDimension; n++)
							best[n] = crossPt[n];
						//if(i==problemDimension)	
						FT.add(i, fBest);
					}
				}
				else
				{
					for (int n = 0; n < problemDimension; n++)
						newPop[j][n] = currPt[n];
					fitnesses[j] = currFit;
				}
				crossPt = null; newPt = null;
			}
			
			population = newPop;
			newPop = null;
		}
		
		finalBest = best;
		
		FT.add(i, fBest);
		
		
		
		
		double[][] cov = Cov(population); 
		double ci = 0;
		for(int c=0;c<cov.length;c++)
		{
			for(int r=c+1;r<cov[0].length;r++)
			{	
				ci += Math.abs(cov[r][c]);
			}
		}
		
		ci /= (Math.pow(problemDimension, 2) - problemDimension)/2; //bests.add(new Best(0, ci));
		
		
		FT.setExtraDouble(ci);
		
		System.out.println("DE: separability index="+ci);
		
		return FT;
		
		
	}

}