package SubsetSum;

import static java.lang.Math.abs;
import java.util.ArrayList;

public class SelectionHandler {
    
    private ArrayList<Integer> inputNumbers;
    private ArrayList<Chromosome> population;
    private final boolean consoleDebug;
    
    SelectionHandler(ArrayList<Chromosome> pop, ArrayList<Integer> inNum, boolean debug) {
        this.inputNumbers = inNum;
        this.population = pop;
        this.consoleDebug = debug;
        
    }
    
    // calculating every chromosome's fitness value AND a sum of max fitness value of a given chromosome
    void select() {
        int sumOfFitVals = 0;
        print("\nSelection\n");
            
        for (Chromosome ch : population) {
            calculateFitness(ch,inputNumbers);
            sumOfFitVals += ch.fitVal;
        }

        for (Chromosome ch : population) {
            ch.crossProbability = 1- (double)ch.fitVal/(double)sumOfFitVals;
            printChromosome(ch);
        }
    }
    
    private void print(String s) {
        
        if (consoleDebug)
            System.out.print("\n"+s);
    }
    
    private void printChromosome(Chromosome ch) {

        if (consoleDebug) {
            System.out.print("\t");
            for (Integer i : ch.arrayOfGens) {
                System.out.print(i);
            }
            System.out.print(" fit:"+ch.fitVal);
            System.out.print(" crProb:"+ch.crossProbability);
            System.out.println();
        }
    }  
    
    private void calculateFitness(Chromosome ch, ArrayList<Integer> inputNumbers) {
        
        ch.fitVal = 0;
        int count = 0;
        
        for (Integer i : ch.arrayOfGens) {
            if (i == 1) {
                ch.fitVal += abs(inputNumbers.get(count));
            }
            count++;
        }
    }

}
