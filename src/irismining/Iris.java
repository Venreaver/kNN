package irismining;

class Iris {

    // input data
    private double sepalLength;
    private double sepalWidth;
    private double petalLength;
    private double petalWidth;
    private String type;

    // normalize data
    private double normSepalLength;
    private double normSepalWidth;
    private double normPetalLength;
    private double normPetalWidth;

    // results data
    private String classificationType;
    private boolean isClassificationRight;

    // constructor
    public Iris(double sepL, double sepW, double petL, double petW, String t){
        sepalLength = sepL;
        sepalWidth = sepW;
        petalLength = petL;
        petalWidth = petW;
        type = t;
    }

    // Input data getters
    public double getSepalLength() {
        return sepalLength;
    }
    public double getSepalWidth() {
        return sepalWidth;
    }
    public double getPetalLength() {
        return petalLength;
    }
    public double getPetalWidth() {
        return petalWidth;
    }
    public String getType() {
        return type;
    }

    // normalize data getters
    public double getNormSepalLength() {
        return normSepalLength;
    }
    public double getNormSepalWidth() {
        return normSepalWidth;
    }
    public double getNormPetalLength() {
        return normPetalLength;
    }
    public double getNormPetalWidth() {
        return normPetalWidth;
    }


    // Getters for result data
    public String getClassificationType() {
        return classificationType;
    }
    public boolean isClassificationRight() {
        return isClassificationRight;
    }

    // Setters
    public void setClassificationType(String classificationType) {
        this.classificationType = classificationType;
    }

    // normalize method
    public void normalize(double minSL, double maxSL, double minSW, double maxSW, double minPL, double maxPL, double minPW, double maxPW) {
        normSepalLength = (double) Math.round((sepalLength - minSL) / (maxSL - minSL) * 1000) / 1000;
        normSepalWidth = (double) Math.round((sepalWidth - minSW) / (maxSW - minSW) * 1000) / 1000;
        normPetalLength = (double) Math.round((petalLength - minPL) / (maxPL - minPL) * 1000) / 1000;
        normPetalWidth = (double) Math.round((petalWidth - minPW) / (maxPW - minPW) * 1000) / 1000;
    }

    // testing result
    public void testClassification() {
        isClassificationRight = type.equals(classificationType);
    }

    @Override
    public String toString() {
        return "sepal length: " + sepalLength + " | sepal width: " + sepalWidth
                    + " | petal length: " + petalLength + " | petal width: " + petalWidth
                        + " | type: " + type + " | Classification Type: " + classificationType + " | Classification is "
                            + (isClassificationRight ? "right" : "wrong");
    }

    public String normToString() {
        return String.format("sepal length: " + "%.2f | sepal width: " + "%.2f | petal length: " + "%.2f | petal width: "
                        + "%.2f | type: " + "%s | Classification Type: " + "%s | Classification: " + "%s"
                , normSepalLength, normSepalWidth, normPetalLength, normPetalWidth, type, classificationType, isClassificationRight);
    }
}