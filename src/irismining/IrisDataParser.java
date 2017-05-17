package irismining;

import java.io.*;
import java.util.*;

public class IrisDataParser {

    private List<Iris> trainList = new ArrayList<>();
    private List<Iris> testList = new ArrayList<>();

    public static void main(String[] args) {
        IrisDataParser dataParser = new IrisDataParser();
        dataParser.parse();
        IrisKNN kNN = new IrisKNN(dataParser.trainList, dataParser.testList, 3);
        kNN.classify();
        System.out.println("==========================TEST RESULTS===========================");
        int countWrong = 0, countRight = 0;
        for (Iris ir: dataParser.testList) {
            int i = ir.isClassificationRight() ? ++countRight : ++countWrong;
            System.out.println(ir);
        }
        System.out.println("==========================RESULTS===========================");
        System.out.println("% of right types: " + (countRight*100/dataParser.testList.size())
                            + "% " + "% of wrong types: " + (countWrong*100/dataParser.testList.size()) + "%");
    }

    private void parse() {
        Set<Integer> idxTestSet = new HashSet<>();  // indexes for trainList
        Random random = new Random();

        // random numbers [0, 149]
        do {
            idxTestSet.add(random.nextInt(149));
        } while (idxTestSet.size() != 50);

        // parse data
        try (BufferedReader bReader = new BufferedReader(new InputStreamReader(new FileInputStream("./data/iris/data.txt")))) {
            int idx = -1;
            String str;
            String[] array;

            while ((str = bReader.readLine()) != null) {
                ++idx;
                array = str.split(",");
                if (idxTestSet.contains(idx)) {
                    testList.add(new Iris(Double.parseDouble(array[0]), Double.parseDouble(array[1]),
                            Double.parseDouble(array[2]), Double.parseDouble(array[3]), array[4]));
                } else {
                    trainList.add(new Iris(Double.parseDouble(array[0]), Double.parseDouble(array[1]),
                            Double.parseDouble(array[2]), Double.parseDouble(array[3]), array[4]));
                }
            }

            System.out.println("==========================INPUT TRAIN DATA===========================");
            for (Iris ir: trainList) {
                System.out.println(ir);
            }
            System.out.println("==========================INPUT TEST DATA===========================");
            for (Iris ir: testList) {
                System.out.println(ir);
            }

            // normalize data
            normalize();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void normalize() {
        double minSL = 100, maxSL = -1 , minSW = 100, maxSW= -1, minPL = 100, maxPL= -1, minPW = 100, maxPW = -1;

        for (Iris ir: trainList) {
            minSL = minSL > ir.getSepalLength() ? ir.getSepalLength() : minSL;
            maxSL = maxSL < ir.getSepalLength() ? ir.getSepalLength() : maxSL;

            minSW = minSW > ir.getSepalWidth() ? ir.getSepalWidth() : minSW;
            maxSW = maxSW < ir.getSepalWidth() ? ir.getSepalWidth() : maxSW;

            minPL = minPL > ir.getPetalLength() ? ir.getPetalLength() : minPL;
            maxPL = maxPL < ir.getPetalLength() ? ir.getPetalLength() : maxPL;

            minPW = minPW > ir.getPetalWidth() ? ir.getPetalWidth() : minPW;
            maxPW = maxPW < ir.getPetalWidth() ? ir.getPetalWidth() : maxPW;
        }

        for (Iris ir: testList) {
            minSL = minSL > ir.getSepalLength() ? ir.getSepalLength() : minSL;
            maxSL = maxSL < ir.getSepalLength() ? ir.getSepalLength() : maxSL;

            minSW = minSW > ir.getSepalWidth() ? ir.getSepalWidth() : minSW;
            maxSW = maxSW < ir.getSepalWidth() ? ir.getSepalWidth() : maxSW;

            minPL = minPL > ir.getPetalLength() ? ir.getPetalLength() : minPL;
            maxPL = maxPL < ir.getPetalLength() ? ir.getPetalLength() : maxPL;

            minPW = minPW > ir.getPetalWidth() ? ir.getPetalWidth() : minPW;
            maxPW = maxPW < ir.getPetalWidth() ? ir.getPetalWidth() : maxPW;
        }

        for (Iris ir: trainList) {
            ir.normalize(minSL, maxSL, minSW, maxSW, minPL, maxPL, minPW, maxPW);
        }

        for (Iris ir: testList) {
            ir.normalize(minSL, maxSL, minSW, maxSW, minPL, maxPL, minPW, maxPW);
        }

        System.out.println("==========================NORM TRAIN DATA===========================");
        for (Iris ir: trainList) {
            System.out.println(ir.normToString());
        }
        System.out.println("==========================NORM TEST DATA===========================");
        for (Iris ir: testList) {
            System.out.println(ir.normToString());
        }
    }

    public List<Iris> getTrainList() {
        return trainList;
    }

    public List<Iris> getTestList() {
        return testList;
    }
}