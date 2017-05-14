import java.util.ArrayList;

/**
 * Created by lordmcbrian on 14/05/2017.
 */
public class OutputNode {
    public static int epoch = 0;
    private double learningRate;
    private double targetOutput;
    private double output;
    private double error = 0;
    private ArrayList<InputNode> inputsList;

    //Get and Set
    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public ArrayList<InputNode> getInputsList() {
        return inputsList;
    }

    public void setInputsList(ArrayList<InputNode> inputsList) {
        this.inputsList = inputsList;
    }

    public double getTargetOutput() {
        return targetOutput;
    }

    public void setTargetOutput(double targetOutput) {
        this.targetOutput = targetOutput;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    //Methods-------------------------------------------------------#
    //Adjust weights
    public void adjustWeights(){
        for(InputNode i: getInputsList()){
            //Weight-change = (learning-rate x error x input)
            //Weight-update =  weight-change + weight
            i.setWeight( (learningRate * error * i.getInput()) + i.getWeight());
        }
    }

    public void printOutputDetails(){
        System.out.println(">>>-[Current Error]: -----> " + this.getError()
                + "\n>>>-[Output]: " + this.getOutput());
    }

    public void fire(){
        //Sum the layer
        //Output = (input1 x weight1) + (input2 x weight2) + ...
        for(InputNode i: getInputsList()){
            output = output + (i.getInput() * i.getWeight());
        }
        //Bias neuron
        output = output + 1;

        //Calculate the error
        //Error = target-output - output
        error = targetOutput - output;



        System.out.println("\n\n[============ Epoch: " + epoch + " ============]");
        //Print the input node data and weights
        for(InputNode inp: this.getInputsList()){
            inp.printDetails();
        }
        //Print the error
        printOutputDetails();
        epoch ++;

    }

}
