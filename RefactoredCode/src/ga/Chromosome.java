/** Genotype.java
 *J Scott Cameron
 *This class contains a bit-vector with the "genes"
 *and a rating to be determined by the fitness
 *function of the Genetic Algorithm.
 *
 *Note: All the contructors take a Random as a parameter
 *because when creating a whole slew of Genetypes very quickly
 *if each Genotype creates its own Random object then there are a lot
 *of identical Random objects since they're all initialized using the
 *current time in milliseconds. It takes less than a millisecond to create
 *a Genotype so the Random objects are initialized with the same time.
 *This is very bad if you want a population of unique Genotypes.
 *By including a Random object in the constructor the Genetic Algorithm
 *sends all the Genotypes a reference to the same Random object. This way
 *each objects gets the object at a different point in its progression.
 */
package ga;

import java.io.*;
import java.util.*;
import ta.Constants;
import ta.DataReader;
import ta.ProblemData;

public class Chromosome {
    private ProblemData pr;
    public int genes[];
    //DataReader Obj_for_accesing;
    private int fitnessValue;
    private int length;
    private int unfitnessValues;
    private Random random;
    public Chromosome(){
    }
    
    public Chromosome(int chromosomeSize) {
        random = new Random();
     
        length = chromosomeSize;

        genes = new int[chromosomeSize];

        for (int i = 0; i < chromosomeSize; i++) {
            this.genes[i] = 0;

        }

    }

    /* creates a Random chromosome */
    public Chromosome(ProblemData prblmdata) {   //removed long parametres
        //implementation to create a random solutionl
        pr=prblmdata;
      
        genes = new int[pr.getJobsCount()];
      
        unfitnessValues = 0;
        random = new Random();
         
        for (int i = 0; i < pr.getJobsCount(); i++) {

            this.genes[i] = random.nextInt(pr.getAgentsCount());

        }

    }

    /* Crossover Constructor
     */
    public Chromosome(Chromosome g1, Chromosome g2, ProblemData prblmdata) {
        //implement code for crossover
        pr=prblmdata;
        genes = new int[pr.getJobsCount()];
        length = g1.length;
        int check = (pr.getJobsCount()) / 2;
        for (int i = 0; i < check; i++) {
            this.genes[i] = g1.getGenes(i);
        }
        for (int i = check; i < pr.getJobsCount(); i++) {
            this.genes[i] = g2.getGenes(i);
        }

    }

    /* mutates Chromosome*/
    public void mutate(ProblemData problem) {
        random = new Random();
        //implement code for mutation
       
        int index1 = random.nextInt(problem.getJobsCount());
        int index2 = random.nextInt(problem.getJobsCount());
  
        this.genes[index1] = random.nextInt(problem.getAgentsCount());
        this.genes[index2] = random.nextInt(problem.getAgentsCount());

    }

    /**
     * @return the genes
     */
    public int getGenes(int i) {
        return genes[i];
    }

    /**
     * @param genes the genes to set
     */
    public void setGenes(int value, int jobNum) {

        this.genes[jobNum] = value;   //will set a gene according to a job number in a chromosome of size number of jobs
    }

    /**
     * @return the fitnessValue
     */
    public int getFitnessValue() {
        return fitnessValue;
    }

    /**
     * @param fitnessValue the fitnessValue to set
     */
    public void setFitnessValue(int fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    /**
     * @return the unfitnessValue
     */
    public int getUnfitnessValue() {
        return unfitnessValues;
    }

    /**
     * @param unfitnessValue the unfitnessValue to set
     */
    public void setUnfitnessValues(int val) {

        this.unfitnessValues = val;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

}
