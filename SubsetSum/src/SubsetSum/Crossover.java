package SubsetSum;

import java.util.ArrayList;


class Crossover {
    
    ArrayList<Integer> indexList;
    ArrayList<Chromosome> population;
    
    public Crossover(ArrayList<Integer> indexList, ArrayList<Chromosome> population) {
        this.indexList = indexList;
        this.population = population;
        chooseAndCross();
    }
    
    public void chooseAndCross(){
               
        if (indexList.size() == 1) return;

        int firstIndex;
        int secondIndex;
        
        for (int i=0; i<indexList.size() ; i=i+2) {

            firstIndex = i;
            secondIndex = i+1;
            
            Chromosome firstParent = population.get(indexList.get(firstIndex));
            Chromosome secondParent = population.get(indexList.get(secondIndex));

            Chromosome firstChild = new Chromosome(firstParent.getChromosomeLength());
            Chromosome secondChild = new Chromosome(firstParent.getChromosomeLength());
            
            // changing gens of chromosome, point of tearing gens apart is the middle gen
            for (int j=0;j<firstParent.getChromosomeLength();j++) {
                
                if (j<firstParent.getChromosomeLength()/2) {
                  firstChild.arrayOfGens.set(j, firstParent.arrayOfGens.get(j));
                  secondChild.arrayOfGens.set(j, secondParent.arrayOfGens.get(j));
                }
                else {
                  firstChild.arrayOfGens.set(j, secondParent.arrayOfGens.get(j));
                  secondChild.arrayOfGens.set(j, firstParent.arrayOfGens.get(j));
                }
            }
  
            //replace parents with their children
            population.set(indexList.get(firstIndex), firstChild);
            population.set(indexList.get(secondIndex), secondChild);
        }  
    }
}
