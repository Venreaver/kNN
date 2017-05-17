package irismining;

class Neighbor {
    private double distance;
    private String type;

    public Neighbor(double distance, String type) {
        this.distance = distance;
        this.type = type;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}