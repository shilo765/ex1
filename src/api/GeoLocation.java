package api;

public class GeoLocation implements geo_location {
    private double x;
    private double y;
    private double z;
    private double distance;
    public void setX(double x)
    {this.x=x;}
    public void setY(double y)
    {this.y=y;}
    public void setZ(double z)
    {this.z=z;}
    public void setDistance(double distance)
    {this.distance=distance;}
    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(geo_location g) {
        return this.distance;
    }
}
