package ta;


import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

import ga.Chromosome;
import ga.GA;
import ta.Constants;
import ta.DataReader;
import ta.ProblemData;

public class TaskAssignmentMain {

    private GA gaObj;
    private Chromosome bestChromosome;/*best chromosome found*/
    private int generation;/* current generation */
    private Random random;
    private DataReader dataReaderObj;
    private ProblemData problemDataObj;

    /* bools to control behavior of GA */
    private boolean crossoverBool = true;
    private boolean mutationBool = true;
    private double startTime = 0;
    private double endTime = 0;
    double bestChromosomeFoundTime = 0;

    public TaskAssignmentMain(String dataFilePath, int problemNumber) {
        dataReaderObj = new DataReader();
        //Read the file and  get required ProblemData object, 
        //Debug: you can insert a breakpoint after executing these two lines of code 
        //and inspect the "problemDataObj" object to get the idea about the structure of the read data
        
        DataReader.readFile(new File(dataFilePath));
        //problemDataObj=new probemDataObj()
        problemDataObj = (ProblemData) DataReader.problemList.get(problemNumber - 1);

        random = new Random();
        gaObj = new GA( true, true, problemDataObj);
        startTime = System.currentTimeMillis();
        gaObj.initpop();

        for (long i = 0; i < Constants.ITERATIONS_COUNT; i++) {

            gaObj.evaluatePopulation();
            Chromosome child = gaObj.BinaryTournament();
            gaObj.Applyheuristics(child);

        }
        System.out.println("Best child after Evolution(after 499999 generations)");
        System.out.println(gaObj.best().getFitnessValue());

       
    }

    public static void main(String args[]) {
        TaskAssignmentMain obj1 = new TaskAssignmentMain("D:/test/gap-data/gapa.txt", 1);//pass gap file path and problem number as parameter
        
    }

}
