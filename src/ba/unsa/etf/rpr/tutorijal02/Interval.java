package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetna, krajnja;
    private boolean poc_pripada, end_pripada;

    public Interval(double pocetna, double krajnja, boolean poc_pripada, boolean end_pripada) {
        if (pocetna > krajnja) throw new IllegalArgumentException();
        this.pocetna = pocetna;
        this.krajnja = krajnja;
        this.poc_pripada = poc_pripada;
        this.end_pripada = end_pripada;
    }

    public Interval() {
        this.pocetna = 0;
        this.krajnja = 0;
        this.poc_pripada = false;
        this.end_pripada = false;
    }

    public static Interval intersect(Interval interval, Interval interval2) {

        if (interval.pocetna > interval2.krajnja || interval.krajnja < interval2.pocetna) return null;
        double start = Math.max(interval.pocetna, interval2.pocetna);
        double end = Math.min(interval.krajnja, interval2.krajnja);

        boolean pripada1, pripada2;
        if (start == interval2.pocetna) pripada1 = interval2.poc_pripada;
        else pripada1 = interval.poc_pripada;

        if (end == interval2.krajnja) pripada2 = interval2.end_pripada;
        else pripada2 = interval.end_pripada;

        return new Interval(start, end, pripada1, pripada2);
    }

    public boolean isIn(double point) {
        if (point == pocetna) return poc_pripada;
        else if (point == krajnja) return end_pripada;
        else return point > pocetna && point < krajnja;
    }

    public boolean isNull() {
        return pocetna == 0 && krajnja == 0 && !poc_pripada && !end_pripada;
    }

    public Interval intersect(Interval interval) {
        if (interval.pocetna > krajnja || interval.krajnja < pocetna) return null;
        double start = Math.max(interval.pocetna, pocetna);
        double end = Math.min(interval.krajnja, krajnja);

        boolean pripada1, pripada2;
        if (start == pocetna) pripada1 = poc_pripada;
        else pripada1 = interval.poc_pripada;

        if (end == krajnja) pripada2 = end_pripada;
        else pripada2 = interval.end_pripada;

        return new Interval(start, end, pripada1, pripada2);
    }

    @Override
    public boolean equals(Object objekat) {
        Interval interval = (Interval) objekat;
        return pocetna == interval.pocetna && krajnja == interval.krajnja && poc_pripada == interval.poc_pripada && end_pripada == interval.end_pripada;
    }

    @Override
    public String toString() {
        if (pocetna == 0 && krajnja == 0) return "()";
        if (poc_pripada && end_pripada) return "[" + pocetna + "," + krajnja + "]";
        else if (!poc_pripada && !end_pripada) return "(" + pocetna + "," + krajnja + ")";
        else if (!poc_pripada) return "(" + pocetna + "," + krajnja + "]";
        else if (poc_pripada) return "[" + pocetna + "," + krajnja + ")";
        return "[" + pocetna + "," + krajnja + "]";
    }
}