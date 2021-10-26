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
public class Heuristics {
       protected boolean crossover;  //specularitve generality
    /* boolean that control crossover */
       protected boolean mutation;// specularitve generality
    /* boolean that controls mutation */
       protected int sumagentsRc[];
       protected int sumagentsCost[];
       protected ProblemData problem;
    /** 
     * @param args the command line arguments
     */
      public Heuristics(){
           
      }
      public Heuristics(ProblemData problem,boolean mutation, boolean crossover ){
          this.problem=problem;
          this.mutation=mutation;
          this.crossover=crossover;
          sumagentsRc = new int[this.problem.getAgentsCount()];
          sumagentsCost = new int[this.problem.getAgentsCount()];
         
         for (int i = 0; i < problem.getAgentsCount(); i++) {
            sumagentsRc[i] = 0;
        }
      }
     public Chromosome doCrossover(Chromosome parent1,Chromosome parent2) {
        //select two parents using two binary tournaments

     
        Chromosome child = new Chromosome(parent1, parent2, problem);

        return child;

    }
       public void doMutation(Chromosome child) {   //doMutatiation will call mutate in class chromosome for swapping agents at two randomly selected indexes
        child.mutate(problem);
    }
 
}
