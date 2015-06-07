package SubsetSum;

import java.util.ArrayList;
import java.util.Random;

public class Chromosome {
    
    // array containing size gens and therefore being chromosome
    public ArrayList<Integer> arrayOfGens;
    
    // length of chromosome
    private int size;
    
    // fitness value of a given chromosome
    public int fitVal;
    
    // probability of crossover based on fitness value
    public double crossProbability;

    public Chromosome(int n) {
        this.size = n;
        this.arrayOfGens = generateChromosome();
    }
    
    public Chromosome(Chromosome ch) {
        this.size = ch.size;
        this.arrayOfGens = new ArrayList(ch.arrayOfGens);
    }

    public ArrayList<Integer> generateChromosome()    {
        arrayOfGens = new ArrayList<>();
        Random rndGen = new Random();
        for (int i = 0; i < this.size; i++) {
            arrayOfGens.add(rndGen.nextInt(2));
        }
        return arrayOfGens;
    }
    
    public int getChromosomeLength() {
        return size;
    }
    
    public int sumOfGens(ArrayList<Integer> inputNumbers) {
        int sum = 0;
        
        for (int i=0; i< inputNumbers.size(); i++) {
            if (arrayOfGens.get(i)==1) {
               sum += inputNumbers.get(i);
            }
        }
        return sum;
    } 
    
    public boolean isEmpty(ArrayList<Integer> inputNumbers) {
 
        for (int i=0; i< inputNumbers.size(); i++) {
            if (arrayOfGens.get(i) != 0)
                return false;
        }
        return true;
    }
        
}