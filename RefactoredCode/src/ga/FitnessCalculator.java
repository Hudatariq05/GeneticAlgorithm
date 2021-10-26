/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import java.util.ArrayList;
import ta.ProblemData;

/**
 *
 * @author Hp
 */
public class FitnessCalculator implements FitnessCalulatorInterface {
    private ProblemData problem;
    private int PopulationFitness;
    private int PopulationUnfitness;
    private int sum;
   
    private ArrayList <Integer> Cost = new ArrayList<Integer>();
    public FitnessCalculator(){
        
    }
   public FitnessCalculator(ProblemData problem){
       this.problem=problem;
          PopulationFitness=0;
          PopulationUnfitness=0;
        
       
       
   }
    @Override
    public void setUnfitnessOfPopulation(Chromosome ch) {
       int sumagentsRc[] = new int[problem.getAgentsCount()];
        
        int sclear[] = new int[problem.getAgentsCount()];
        int difference = 0;

        for (int i = 0; i < problem.getAgentsCount(); i++) {
            sumagentsRc[i] = 0;
        }

         sum = 0  ;

        for (int i = 0; i < problem.getJobsCount(); i++) {
            sumagentsRc[ch.getGenes(i)] = problem.getResourceConsumed().get(ch.getGenes(i)).get(i) + sumagentsRc[ch.getGenes(i)];

        }

        for (int i = 0; i < problem.getAgentsCount(); i++) {
            difference = sumagentsRc[i] - problem.getResourceCapacity().get(ch.getGenes(i));

            if (difference <= 0) {
                sumagentsRc[i] = 0;

            } else {
                sumagentsRc[i] = difference;

            }
            sum = sumagentsRc[i] + sum;
            sumagentsRc[i] = 0;
        }
        ch.setUnfitnessValues(sum);

    }

    @Override
  
    public void setChromosomefitness(Chromosome ch) {   //Evaluate fitness of one chromosome
    
      
       sum = 0  ;
       
        for (int i = 0; i < problem.getJobsCount(); i++) {
            Cost =   problem.getJobAllocationCost().get(ch.getGenes(i));
            sum = Cost.get(i) + sum;


        ch.setFitnessValue(sum);
        PopulationFitness=ch.getFitnessValue();
    }
    }
    @Override
    
    public int getPopulationFitness(){
        return PopulationFitness;
    }
      @Override
    public int getPopulationUnfitness(){
        return PopulationUnfitness;
    }
    
}
