package ch.netzwerg.viz.scale;

public final class ScaleLinear {

    private final double domainMin;
    private final double domainSpan;

    private final double rangeMin;
    private final double rangeSpan;

    public ScaleLinear(double domainMin, double domainMax, double rangeMin, double rangeMax) {
        this.domainMin = domainMin;
        this.domainSpan = domainMax - domainMin;

        this.rangeMin = rangeMin;
        this.rangeSpan = rangeMax - rangeMin;
    }

    public double toScreen(double domain) {
        return rangeMin + ((domain - domainMin) / domainSpan) * rangeSpan;
    }

}
