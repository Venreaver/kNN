package irismining;

import java.util.*;

class IrisKNN {
    private List<Neighbor> kNeighbours = new ArrayList<>();
    private List<Iris> train;
    private List<Iris> test;
    private int k;

    IrisKNN(List<Iris> train, List<Iris> test, int k) {
        this.train = train;
        this.test = test;
        this.k  = k;
    }

    public void classify() {
        for (int i = 0, n = test.size(); i < n; ++i) {
            for (int j = 0, t = train.size(); j < t; ++j) {
                double distance = euclideanDistance(j, i);
                if (j < k) {
                    kNeighbours.add(new Neighbor(distance, train.get(j).getType()));
                } else {
                    for (int in = 0; in < k; ++in) {
                        if (distance < kNeighbours.get(in).getDistance()) {
                            kNeighbours.get(in).setDistance(distance);
                            kNeighbours.get(in).setType(train.get(j).getType());
                            break;
                        }
                    }
                }
            }
            vote(i);
            for (Iris ir: test) {
                ir.testClassification();
            }
            kNeighbours.clear();
        }
    }

    private double euclideanDistance(int idxTrain, int idxTest) {
        return Math.sqrt(Math.pow(test.get(idxTest).getNormSepalLength() - train.get(idxTrain).getNormSepalLength(), 2)
                + Math.pow(test.get(idxTest).getNormSepalWidth() - train.get(idxTrain).getNormSepalWidth(), 2)
                + Math.pow(test.get(idxTest).getNormPetalLength() - train.get(idxTrain).getNormPetalLength(), 2)
                + Math.pow(test.get(idxTest).getNormPetalWidth() - train.get(idxTrain).getNormPetalWidth(), 2));
    }

    private void vote(int idx) {
        Map<String, Integer> voteMap = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            if (voteMap.containsKey(kNeighbours.get(i).getType())) {
                voteMap.put(kNeighbours.get(i).getType(), voteMap.get(kNeighbours.get(i).getType()) + 1);
            } else {
                voteMap.put(kNeighbours.get(i).getType(), 1);
            }
        }
        int maxVotes = 0;
        String typeName = "";
        for (Map.Entry<String, Integer> pair: voteMap.entrySet()) {
            if (maxVotes < pair.getValue()) {
                maxVotes = pair.getValue();
                typeName = pair.getKey();
            }
        }
        test.get(idx).setClassificationType(typeName);
    }
}