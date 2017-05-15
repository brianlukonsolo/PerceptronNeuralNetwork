import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by lordmcbrian on 14/05/2017.
 */
public class NeuralNetwork {
    public static void main(String[] args){

        //Set our variables ==========================================================================#
        double INPUT_1 = 0.9;
        double INPUT_2 = 0.2;

        double TARGET_OUTPUT;
        double LEARNING_RATE;
        double ACCEPTABLE_ERROR_LEVEL;
        //============================================================================================#

        Scanner userInput = new Scanner(System.in);

        //Read user input: Let the user enter a target output, learning rate and acceptable error level
        System.out.println("[#####################################################################]");
        System.out.println("[##########_**__WELCOME TO BRIAN LUKONSOLO's PERCEPTRON__**_##########]");
        System.out.println("[##############    #################################    ##############]");
        System.out.println("[################    #############################    ################]");
        System.out.println("[##################    #########################    ##################]");
        System.out.println("[#################################      ##############################]");
        System.out.println("[#####################################################################]");

        System.out.println("\n[Help: TARGET OUTPUT: A number specifying the output value or target that the perceptron should seek to achieve]");
        System.out.println("[Help: LEARNING RATE: A small multiplier that affects the learning rate of the perceptron]");
        System.out.println("[Help: ACCEPTABLE ERROR DEVIATION: The perceptron will assume success as soon as it reaches within this value of the target output]");


        System.out.println("\n[ >>> Please enter a TARGET OUTPUT value: ]...");
        double target_output = userInput.nextDouble();

            System.out.println("[ >>> Please enter a LEARNING RATE value: ]...");
            double learning_rate = userInput.nextDouble();

                System.out.println("[ >>> Please enter an ACCEPTABLE ERROR DEVIATION value: ]...");
                double acceptable_error_level = userInput.nextDouble();

                    //Set the recieved values
                    TARGET_OUTPUT = target_output;

                        LEARNING_RATE = learning_rate;

                            ACCEPTABLE_ERROR_LEVEL = acceptable_error_level;

        //Read user input: Create input nodes according to the number of inputs provided by the user
        ArrayList<Double> userInputsList = new ArrayList<>();
        double val;
        do {
            System.out.println("[ >>> Please enter an INPUT VALUE then press Enter to add input-neurons. Press any LETTER then Enter to initialize the Perceptron]" +
                    "\nCurrent number of inputs: " + userInputsList.size() + " ...");
            try{
                //Get input and add to the list
                val = userInput.nextDouble();
                userInputsList.add(val);
            }catch (Exception e){
                break;
            }
        }while (val != 'c');

        //Initialize the output node, set learning rate and target output
        OutputNode outputNode = new OutputNode();
        outputNode.setTargetOutput(TARGET_OUTPUT);
        outputNode.setLearningRate(LEARNING_RATE);


        //A list to store the new input-node objects
        ArrayList<InputNode> inputsList = new ArrayList<>();

        //Initialize random number generator
        Random rnd = new Random();
        rnd.setSeed(System.currentTimeMillis());

        //Get all the inputs entered by the user and create input-node objects for each
        for(int i=0; i< userInputsList.size(); i++){
            String name = "input" + i;
            InputNode inputNode = new InputNode(name, userInputsList.indexOf(i));


            //Set initial weights of input nodes
            inputNode.setWeight(rnd.nextDouble());

            //Add the node to the list
            inputsList.add(inputNode);
            System.out.print("\nINITIAL SYNAPSE WEIGHTS: ------==>>> "+ inputNode.getName() +": "+ inputNode.getWeight());
        }

        //Give the output node the input-nodes list
        outputNode.setInputsList(inputsList);

        //FINAL PROMPT TO USER
        System.out.println("\n\n\n[ >>> PERCEPTRON FULLY INITIALIZED! PRESS ENTER TO BEGIN LEARNING PROCESS! ...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //While loop to run the neural net
        do{
            outputNode.fire();

                if(outputNode.getOutput() < TARGET_OUTPUT){
                    outputNode.adjustWeights();
                }else if(outputNode.getOutput() > TARGET_OUTPUT){
                    outputNode.adjustWeights();
                }

                //If acceptable error level is reached, break
                if((outputNode.getOutput() - TARGET_OUTPUT) > -ACCEPTABLE_ERROR_LEVEL && (outputNode.getOutput() - TARGET_OUTPUT) < ACCEPTABLE_ERROR_LEVEL){
                    System.out.print("\n\n>>>### Success!: Target output achieved, lordmcbrian! ###<<<");

                    String inputs_in_list="";
                    String weights_in_list="";
                    for(InputNode i: inputsList) {
                        inputs_in_list = inputs_in_list + ", " + i.getInput();
                        weights_in_list = weights_in_list + ", " + i.getWeight();
                    }
                    System.out.print("\n>>> [ Original inputs:  " + inputs_in_list + "] <<<");
                    System.out.print("\n>>> [ Final synapse weights:  " + weights_in_list + "] <<<");

                    System.out.print("\n>>> [ Target output: " + outputNode.getTargetOutput() + "] [ Actual output: " + outputNode.getOutput() + "] <<<\n\n");
                    break;
                }

                //If input 1 and input 2 are both zero, break
                if(INPUT_1 == 0 && INPUT_2 == 0){
                    //No inputs = no learning!!!
                    System.out.print("\n\n>>> Error: All inputs are empty! Learning is not possible with 0 inputs! <<<\n\n");
                    break;
                }

                //If error too big, break
                if(outputNode.getError() > 100000){
                    System.out.print("\n\n>>> Error: error has grown too big! <<<\n\n");
                    break;
                }
                if(outputNode.getError() < -100000){
                    System.out.print("\n\n>>> Error: error has grown too big! <<<\n\n");
                    break;
                }

        }while(outputNode.getOutput() != TARGET_OUTPUT);
    }
}
