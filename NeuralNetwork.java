import java.util.Scanner;

class NeuralNetwork {
    int numLayers;
    int[] numNodes;
    double[][][] weights;

    public NeuralNetwork(int numLayers, int[] numNodes) {
        this.numLayers = numLayers;
        this.numNodes = numNodes;
        this.weights = new double[numLayers - 1][][];

        for (int i = 0; i < numLayers - 1; i++) {
            weights[i] = new double[numNodes[i + 1]][numNodes[i]];
        }
    }

    public void setEdgeWt(int layer, int fromNode, int toNode, double weight) {
        if (layer < 1 || layer >= numLayers || fromNode < 0 || fromNode >= numNodes[layer - 1]
                || toNode < 0 || toNode >= numNodes[layer]) {
            System.out.println("Invalid layer or node index.");
            return;
        }

        weights[layer - 1][toNode][fromNode] = weight;
    }

    public double getEdgeWt(int layer, int fromNode, int toNode) {
        if (layer < 1 || layer >= numLayers || fromNode < 0 || fromNode >= numNodes[layer - 1]
                || toNode < 0 || toNode >= numNodes[layer]) {
            System.out.println("Invalid layer or node index.");
            return 0;
        }

        return weights[layer - 1][toNode][fromNode];
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insert the number of layers in the neural network: ");
        int numLayers = sc.nextInt();

        int[] numNodes = new int[numLayers];
        for (int i = 0; i < numLayers; i++) {
            System.out.println("Enter the number of nodes in layer " + (i + 1) + ": ");
            numNodes[i] = sc.nextInt();
        }

        NeuralNetwork nn = new NeuralNetwork(numLayers, numNodes);

        for (int layer = 1; layer < numLayers; layer++) {
            for (int toNode = 0; toNode < numNodes[layer]; toNode++) {
                for (int fromNode = 0; fromNode < numNodes[layer - 1]; fromNode++) {
                    System.out.println("Enter edge weight from node " + (fromNode + 1) +
                            " in layer " + layer + " to node " + (toNode + 1) + " in layer " + (layer + 1) + ": ");
                    double weight = sc.nextDouble();
                    nn.setEdgeWt(layer, fromNode, toNode, weight);
                }
            }
        }
        System.out.println("Insert the edge layer: ");
        int layer = sc.nextInt();
        System.out.println("Insert the source node index: ");
        int fromNode = sc.nextInt();
        System.out.println("Insert the destination node index: ");
        int toNode = sc.nextInt();

        double edgeWt = nn.getEdgeWt(layer, fromNode, toNode);
        System.out.println("Weight of the edge: " + edgeWt);

        sc.close();
    }
}
