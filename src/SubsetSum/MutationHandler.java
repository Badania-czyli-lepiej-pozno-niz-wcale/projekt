package SubsetSum;

import java.util.ArrayList;
import java.util.Random;

public class MutationHandler {
    
    private ArrayList<Chromosome> population;
    private final boolean consoleDebug;
    private final double mutateprobability;
    private Random rnd;
    
    MutationHandler(ArrayList<Chromosome> pop, double prob, Random rnd, boolean debug) {
        
        this.population = pop;
        this.mutateprobability = prob;
        this.consoleDebug = debug;
        this.rnd = rnd;
        
    }
    
    void mutate() {
        
        double rndD;
        int chromosomeLength = population.get(0).getChromosomeLength();
        int bitToReverse;
        
        print("Mutation");
        
        // every chrom. has a 'mutateProbability' chance to be mutated, chosen ones are in chosenToMutation structure
        for (Chromosome ch : population) {
            
            rndD = rnd.nextDouble();
            if (rndD <= mutateprobability) {
                // chosen to mutate, so mutate him:
                bitToReverse = rnd.nextInt(chromosomeLength);
                if (ch.arrayOfGens.get(bitToReverse) == 1) 
                    ch.arrayOfGens.set(bitToReverse, 0);
                else
                    ch.arrayOfGens.set(bitToReverse, 1);
            }
            
        }

        print("\tAfter mutation:\n");
        for (Chromosome ch : population) 
            printChromosomeWithoutDetails(ch,population);
        
    }
    
    private void print(String s) {
        
        if (consoleDebug)
            System.out.print("\n"+s);
    }
    
    private void printChromosomeWithoutDetails(Chromosome ch, ArrayList<Chromosome> tmpPopulation) {
        
        if (consoleDebug) {
            
            System.out.print("\t");
            for (Integer i : ch.arrayOfGens) {
                System.out.print(i);
            }
            System.out.println();
            
        }
    }
}
