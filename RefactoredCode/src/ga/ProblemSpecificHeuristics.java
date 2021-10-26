/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

import ta.ProblemData;

/**
 *
 * @author Hp
 */
public class ProblemSpecificHeuristics extends Heuristics{
    public ProblemSpecificHeuristics(ProblemData problem,boolean mutation, boolean crossover){
        super(problem,mutation,crossover);
    }
          
    public Chromosome ResourceAdequaty(Chromosome childChromosome) {
       
         for (int i = 0; i < problem.getJobsCount(); i++) {  //sumagentRc Will have some of agents resources of child
            sumagentsRc[childChromosome.getGenes(i)] = problem.getResourceConsumed().get(childChromosome.getGenes(i)).get(i) + sumagentsRc[childChromosome.getGenes(i)];
        } //This loop is for having sum of each agent resources consumed so that after minimizing cost we can check if resources are not exceeding
         for (int j = 0; j < problem.getJobsCount(); j++) { //This loop is for checking cost for performing job j for agent i 
            if (sumagentsRc[childChromosome.getGenes(j)] > problem.getResourceCapacity().get(childChromosome.getGenes(j))) {
                for (int i = 0; i < problem.getAgentsCount(); i++) { //check  Cost for each agents
                     if (sumagentsRc[i] <= problem.getResourceCapacity().get(i)) {
                        childChromosome.genes[j] = i;     
                    }
                }
            } 
        }
        return childChromosome;
    }
    public void CostEfficency(Chromosome childChromose){
    }
   

    
}
