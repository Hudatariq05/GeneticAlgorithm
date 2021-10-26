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
public interface GAInterface  {

    void Applyheuristics(Chromosome child);

    Chromosome BinaryTournament();

    boolean Duplicated(Chromosome ch1, Chromosome ch2);

    Chromosome TournamentSelection(ProblemData problemList);

    /* returns the best genotype of the current population */
    Chromosome best();

    void checkDuplicatedChild(Chromosome ch);

    void checkDuplicatedInPop(Chromosome ch);
    /* evaluates all the Chromosomes in the population and sorts them*/
    void evaluatePopulation();

    /* returns a string wiith all the Bitsets printed out */
    String toString();

    Chromosome worst();
    
}
