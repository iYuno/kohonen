
import java.util.ArrayList;
import java.util.List;

public class Neuron {
    private double[] inputs = new double[Kohonen.size_features];
    private double[] weights = new double[Kohonen.size_features];
    private int x, y;
    private double potential = 0.0625;

    public Neuron() {
        for(int i = 0; i < inputs.length; i++) {
            weights[i] = Math.random();
        }

    }

    public double getPotential() {
        return potential;
    }

    public void setPotential(double potential) {
        this.potential = potential;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double[] getInputs() {
        return inputs;
    }

    public double getInput(int index) {
        return inputs[index];
    }

    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    public double[] getWeights() {
        return weights;
    }

    public double getWeight(int index) {
        return weights[index];
    }

    public void setWeight(int index, double value) {
        weights[index] = value;
    }
}
