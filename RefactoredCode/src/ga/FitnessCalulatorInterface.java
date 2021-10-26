/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

/**
 *
 * @author Hp
 */
public interface FitnessCalulatorInterface {

    void setChromosomefitness(Chromosome ch);
    void setUnfitnessOfPopulation(Chromosome ch);
    int  getPopulationFitness();
    int getPopulationUnfitness();
    
}
