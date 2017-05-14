/**
 * Created by lordmcbrian on 14/05/2017.
 */
public class InputNode {
    private String name;
    private double input;
    private double weight;

    //Constructor
    public InputNode(String name, double input) {
        this.setName(name);
        this.setInput(input);
    }

    //Get and Set
    public double getInput() {
        return input;
    }

    public void setInput(double input) {
        this.input = input;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Print node details
    public void printDetails(){
        System.out.print("[NAME]: " + getName() + " [INPUT]: " + getInput() + " [WEIGHT]: " + getWeight() + "\n");
    }
}
