package SubsetSum;

import java.awt.Dimension;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jopendocument.model.OpenDocument;
import org.jopendocument.panel.ODSViewerPanel;
import org.jopendocument.print.DefaultDocumentPrinter;

public class SubsetSumHandler {
     
    public ArrayList<Double> doubleResults;
    public ArrayList<Integer> inputNumbers;
    private int singleMaxInputValue;
    private int numberOfInputs;
    private int numberOfChromosomes;
    private double mutateProbability;
    private int searchTolerance;
   
    private final boolean consoleDebug = false;
    
    private SettingsGeneratedInput initializeRandom;
    private SettingsFileInput initializeFile;
    
    public SubsetSumHandler(){
        inputNumbers=new ArrayList<>();
        doubleResults=new ArrayList<>();
    }
      
    public void findSolution(){   
        // the length of chromosome: number of 0s and 1s 
        // example: we have numbers 4 5 6 7 8, the chromosome 10010 means 
        // that we take into concideration first and fourth element in this example it would be 4 and 7
        int chromosomeLength = inputNumbers.size(); 
        
        // general purpose randomizing seed
        Random rnd = new Random();

        // building the population - the structure of certain number of chromosomes
        ArrayList<Chromosome> population = new ArrayList<>();
        for (int i=0; i<numberOfChromosomes; i++) {
            population.add(new Chromosome(chromosomeLength));
        }
        
        int minimumPopulation = 1;
        int minResult = population.get(0).sumOfGens(inputNumbers);
        ArrayList<Integer> minimumValues = new ArrayList<>(population.get(0).arrayOfGens.get(0));
        
        // counter for exiting the algorithm, if it is equal to `searchTolerance` then algorithm finishes 
        int exitCount = -1;
        
        // population counter
        int popCount = 1;
        
        while (true) {
            
            print("\nPOPULATION "+popCount);
            
            SelectionHandler sh = new SelectionHandler(population, inputNumbers, consoleDebug);
            sh.select();
            
            CrossoverHandler crh = new CrossoverHandler(population, rnd, consoleDebug);
            crh.cross();

            MutationHandler mh = new MutationHandler(population, mutateProbability, rnd, consoleDebug);
            mh.mutate();
                   
            int minSumForPopulation = population.get(0).sumOfGens(inputNumbers);
                    
            //testing if population has chromosome with the given condition
            for (Chromosome ch : population) {
                
                // preventing situation where all bits ij chromosome are 0s (so none numbers are selected)
                if (ch.isEmpty(inputNumbers)) 
                    break;

                // 
                int currentSum = ch.sumOfGens(inputNumbers);
                
                if (Math.abs(minSumForPopulation) > Math.abs(currentSum)) 
                    minSumForPopulation = currentSum;
                
                if (currentSum==0) {
                    
                    printResultEqualToZero(ch,inputNumbers);              
                    return;
                    
                } else 
                {
                    if (Math.abs(minResult) > Math.abs(currentSum)) {
                       exitCount = -1;
                       minResult = currentSum;
                       minimumValues.clear();
                       minimumValues.addAll(ch.arrayOfGens);
                       minimumPopulation = popCount;
                    }
                }
            }
            
            ///*
            // checking previous sums on the plot, must have at least one to check
            if (doubleResults.isEmpty())
                doubleResults.add((double)minSumForPopulation);
            else {
                if (Math.abs(minSumForPopulation) < Math.abs(doubleResults.get(doubleResults.size()-1)))
                    doubleResults.add((double)minSumForPopulation);
            }
            //*/
            
            //doubleResults.add((double)minSumForPopulation);
                  
            // if it comes to that point, it means that algorithm didn't return
            exitCount++;
            
            // searching up to `searchTolerance` from previously found minimum chromosome
            if (exitCount == searchTolerance) {
                
                // zero result isn't obtainable, print sum closest to 0
                printResultOtherThatZero(minimumPopulation,minResult,minimumValues);
                return;
            }

            print("\n");
            popCount++;
        }
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

    private void print(String s) {
        
        if (consoleDebug)
            System.out.print(s);
    }

    private void printResultOtherThatZero(int minimumPopulation, int minResult, ArrayList<Integer> minimumValues) {
        if (consoleDebug) {
            System.out.println();
            System.out.println("Couldn't find values which sum is 0. \nFound minimum in "+minimumPopulation+"th population. \nMinimum sum value is "+minResult);
            System.out.println("Chosen numbers which sum is "+minResult+": ");
                for (int i=0;i<minimumValues.size();i++) {
                    if (minimumValues.get(i) == 1){
                        System.out.print(inputNumbers.get(i)+ " ");
                    } 
                }
            System.out.println();
        }
    }
    
    private void printResultEqualToZero(Chromosome ch,ArrayList<Integer> inputNumbers) {
        print("\n\nFOUND IT!!\n");
        printChromosomeWithoutDetails(ch);
        print("\nChosen numbers which sum is 0: ");
        for (int i=0;i<ch.arrayOfGens.size();i++) {
            if (ch.arrayOfGens.get(i) == 1){
                print(inputNumbers.get(i)+ " ");
            } 
        }
        print("\n");
    } 
    
    
    //----------------- GUI functions ------------------------
    
    public void openFileSettings(){       
        if(initializeFile==null){
            initializeFile=new SettingsFileInput();
        }
        else{
            initializeFile.setFields();
        }
        initializeFile.setVisible(true); 
    }
    
    public void readFile(Window w){

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","TXT");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(w);
        Scanner scanner;
        try {
            scanner = new Scanner(new File(chooser.getSelectedFile().getAbsolutePath()));
                    while(scanner.hasNextInt())
                    {          
                         inputNumbers.add(scanner.nextInt());
                    }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SubsetSum.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void openGeneratedSettings(){
        if(initializeRandom==null){
            initializeRandom=new SettingsGeneratedInput();
        }
        else{
            initializeRandom.setFields();
        }
        initializeRandom.setVisible(true);    
    }
 
    public void confirmSettings(boolean fileFlag){
        if(fileFlag){
            numberOfChromosomes = initializeFile.getNumberOfChromosomes();
            mutateProbability = initializeFile.getMutateProbability();
            searchTolerance = initializeFile.getSearchTolerance();
        }
        else{
            // maximum value of each input numbers
            singleMaxInputValue = initializeRandom.getMaxInput();        
            // quantity of inserted numbers
            numberOfInputs = initializeRandom.getNumberOfInputs();       
            // Size of population so the number of chromosome we want to use
            numberOfChromosomes = initializeRandom.getNumberOfChromosomes();
            // Probability for mutation of single bit in chromosome
            mutateProbability = initializeRandom.getMutateProbability();

            // if the answer for the problem is not in these numbers, then the algorithm will return the lowest sum found in `searchTolerance` number of populations
            searchTolerance = initializeRandom.getSearchTolerance();

            // generator of numbers
            inputNumbers = new Generator(numberOfInputs ,singleMaxInputValue).generateNumbers();       
        }
    }
    
    public void drawPlot(){
        Plot  mainPanel = new Plot(doubleResults);
        mainPanel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("Results");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    } 

    void noData(Window w) {
        JOptionPane.showMessageDialog(w, "Set is not loaded. \nRead set from file or generate random one.");
    }

    void showDocumentation() {
        final OpenDocument doc = new OpenDocument();
        doc.loadFrom("test.ods");
        final JFrame mainFrame = new JFrame("Documentation");
        DefaultDocumentPrinter printer = new DefaultDocumentPrinter();
        ODSViewerPanel viewerPanel = new ODSViewerPanel(doc, printer, true);
        mainFrame.setContentPane(viewerPanel);
        mainFrame.pack();
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLocation(10, 10);
        mainFrame.setVisible(true);
    }
}

