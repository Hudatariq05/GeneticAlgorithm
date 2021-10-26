/* GeneticAlgorithm.java
 * J Scott Cameron
 * 
 * This class controls a population of Genotypes 
 * for regenration using a Genetic Algorithm
 *
 * It uses the Java Reflection API so that
 * it is possible to change the fitness function
 * for new GA problems without having to touch this 
 * class
 */
package ga;

import ga.GA;
import ga.Chromosome;
import ga.ChromosomeComparator;
import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import ta.Constants;

import ta.ProblemData;

public class GA implements GAInterface {
    /////////////////////////////////
    protected Chromosome population[];
    /* population of Chromosome */
    protected int populationSize;
    /* population size */
    protected Random random;
    /* random object to be passed to genotypes */
    private int fitness;
    /* sum of all the fitnesses in the population */
    private int lowestFitness;
    /* the worst fitness found */
    protected ProblemSpecificHeuristics GAheuristics;
    private FitnessCalculator FitnessObj;
    protected ProblemData problem;
    
    private ArrayList<String> allChromosomesList = new ArrayList<String>();
     public GA() {

    }

    /* initializes the population with random Genotypes */
    public GA(boolean mutation,boolean crossover,ProblemData problem){
        populationSize=Constants.POPULATION_SIZE;
        this.problem=problem;
        population=new Chromosome[Constants.POPULATION_SIZE];
        random=new Random();
        GAheuristics=new ProblemSpecificHeuristics(problem,mutation,crossover);
        FitnessObj=new FitnessCalculator(problem);

       
    }
    public void initpop(){
        random = new Random();
        int isAduplicate = 0;
        //this.population = new Chromosome[Constants.POPULATION_SIZE];

        for (int i = 0; i < Constants.POPULATION_SIZE; i++) {

            this.population[i] = new Chromosome(problem);

        }
        
               //this will check if a chromosme is identical to any other chormosome in populatiion  and it'll replace that chromose with new un identical chromosme
       for (int i = 0; i < Constants.POPULATION_SIZE; i++) //check duplicates and will replace them with non duplicates
        {
            this.checkDuplicatedInPop(population[i]);
        }
        
    }
    
    @Override
    public void checkDuplicatedInPop(Chromosome ch) {   
 
        
      //this will check if a chromosme is identical to any other chormosome in populatiion  and it'll replace that chromose with new un identical chromosme
        for (int i = 0; i < Constants.POPULATION_SIZE; i++) { 
        
            
            if (this.Duplicated(ch, population[i])) {
                ch = new Chromosome(problem);
                checkDuplicatedInPop(ch); //recursive call stop when the new created chromosome in place of identical will no longer have an identical bitset
            }
        }

    }

    @Override
    public void checkDuplicatedChild(Chromosome ch) {          //this will check if a chromosme is identical to any other chormosome in populatiion  and it'll replace that chromose with new un identical chromosme
        for (int i = 0; i < Constants.POPULATION_SIZE; i++) {
            if (this.Duplicated(ch, population[i])) {
                ch = BinaryTournament();
                GAheuristics.doMutation(ch);
                checkDuplicatedInPop(ch); //recursive call stop when the new created chromosome in place of identical will no longer have an identical bitset
            }

        }

    }

    @Override
    public boolean Duplicated(Chromosome ch1, Chromosome ch2) {  //it'll checknif two chromosomes have same bit set

        for (int k = 0; k < problem.getJobsCount(); k++) {
            if (ch1.getGenes(k) != ch2.getGenes(k)) {
                return false;
            }

        }

        return true;
    }

    @Override
   public Chromosome TournamentSelection(ProblemData problemList) {

        int selected_parent_1 = random.nextInt(Constants.POPULATION_SIZE);
        int selected_parent_2 = random.nextInt(Constants.POPULATION_SIZE);
        //  Chromosome parent=new Chromosome(jobsCount);
        if (population[selected_parent_1].getFitnessValue() < population[selected_parent_2].getFitnessValue()) {
            return population[selected_parent_2];
        } else if (population[selected_parent_1].getFitnessValue() > population[selected_parent_2].getFitnessValue()) {
            return population[selected_parent_1];

        } else {
            if (population[selected_parent_1].getUnfitnessValue() <= population[selected_parent_2].getUnfitnessValue()) {
                return population[selected_parent_1];
            } else {
                return population[selected_parent_2];
            }
        }
          }
    @Override
       public Chromosome BinaryTournament(){
              
          
        Chromosome parent1 = new Chromosome(problem.getJobsCount());
        parent1 = this.TournamentSelection(problem);
        Chromosome parent2 = new Chromosome(problem.getJobsCount());
        parent2 = this.TournamentSelection(problem);
        return  GAheuristics.doCrossover(parent1,parent2);
          }
       
    
   
    
    /* evaluates all the Chromosomes in the population and sorts them*/
    @Override
    public void evaluatePopulation() {

        for (int j = 0; j < Constants.POPULATION_SIZE; j++) // evaluate unfitness of all solutions in the population
        {
            FitnessObj.setUnfitnessOfPopulation(population[j]); //  evaluateChromosomeUnfitness for calculating Unfitness of each chromosome
        }
        for (int j = 0; j < Constants.POPULATION_SIZE; j++) // evaluate fitnesses of all solutions in the population
        {
            FitnessObj.setChromosomefitness(population[j]);      //  evaluateChromosomefitness for calculating fitness of each chromosome
        }

        /* sort the population */
        
    }
    @Override
       public void Applyheuristics(Chromosome child){
       
        GAheuristics.doMutation(child);
        //Chromosome Child = this.applyProblemSpecificHeuristics(child);
        this.checkDuplicatedChild(child);
        FitnessObj.setChromosomefitness(child);
        FitnessObj.setUnfitnessOfPopulation(child);
        //Will sort in descending of unfitnesses
        Arrays.sort(population, new ChromosomeComparator());
        ChromosomeComparator cmpr = new ChromosomeComparator();

        if (population[0].getUnfitnessValue() == population[1].getUnfitnessValue()) {
            if (population[0].getFitnessValue() < population[1].getFitnessValue()) {
                population[1] = child;
            } else {
                population[0] = child;
            }
        } else {
            population[0] = child;
        }

        Arrays.sort(population, new ChromosomeComparator());
      

    }

   
    /* returns a string wiith all the Bitsets printed out */
    @Override
    public String toString() {
        StringWriter sw = new StringWriter();

        for (int i = 0; i < populationSize; i++) {

            sw.write(population[i].toString());
            sw.write("\n");

        }
        return sw.toString();
    }
    @Override
         public Chromosome worst() {
          return population[0];
    }

    /* returns the best genotype of the current population */
    @Override
        public Chromosome best() {
        return population[population.length - 1];
    }
 



}
