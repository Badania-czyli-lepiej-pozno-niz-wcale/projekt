package SubsetSum;

import java.util.ArrayList;
import java.util.Random;

public class CrossoverHandler {

    // index of the chromosomes chosen to crossover
    // for example: we have 4 chromosomes, thanks to `crossProbability` we know that
    // first and third chromosome are going to be crossed, so indexList contains [0,2]
    private ArrayList<Integer> indexList;
    
    private ArrayList<Chromosome> population; 
    private Random rnd;
    private final boolean consoleDebug;
    
    CrossoverHandler(ArrayList<Chromosome> pop, Random rnd, boolean debug) {
        
        this.population = pop;
        this.rnd = rnd;
        this.consoleDebug = debug;
        indexList = new ArrayList<>();
    }
    
    void cross() {
        
        double rndD;
        print("Crossover");
        
        // every chromosome has a 'crossProbability' chance to be crossed, 
        // here is calculated which chromosomes will be crossed
        for (int i=0;i<population.size();i++){
            rndD = rnd.nextDouble();
            if (rndD <= population.get(i).crossProbability) {
                indexList.add(i);
            }
        }

        //if number of chosen ones are odd (there is 1 chromosome without a pair to crossover)
        if (indexList.size() % 2 != 0) {
            print("\n\tNumber of chosen chromosomes: "+indexList.size());
            deleteLowestFit(indexList,population);
            print("-1 = "+indexList.size()+" (can't be odd)\n");
        }else
            print("\n\tNumber of chosen chromosomes: "+indexList.size()+"\n");

        for (Integer i : indexList) 
            printChromosome(population.get(i));

        // actual crossover - first from `indexList` is crossing with second, third with fourth and so on
        new Crossover(indexList,population);
         
        print("\n\tAfter crossover:\n");
        for (Chromosome ch : population) 
            printChromosomeWithoutDetails(ch);
        print("\n");
    }
    
    private void print(String s) {
        
        if (consoleDebug)
            System.out.print(s);
    }
    
    private void deleteLowestFit(ArrayList<Integer> indexes, ArrayList<Chromosome> population) {
        
        int min = population.get(indexes.get(0)).fitVal;
        int toDelete = 0;
        int count = 0;
        
        for (int i = 0; i<indexes.size(); i++) {   //Chromosome ch : chosenToCrossover) {
            if (min < population.get(indexes.get(i)).fitVal) {
                toDelete = count;
            }
            count++;
        }
        indexes.remove(toDelete);
    }
    
    private void printChromosomeWithoutDetails(Chromosome ch) {
        
        if (consoleDebug) {
            System.out.print("\t");
            for (Integer i : ch.arrayOfGens) {
                System.out.print(i);
            }
            System.out.println();
        }
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
}
