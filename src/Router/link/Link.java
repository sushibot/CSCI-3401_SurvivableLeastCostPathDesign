package Router.link;

/**
 *
 * @author Tyler_Atiburcio
 */
public class Link {
    private final Router A,B;
    private double cost;

    public Link(Router A, Router B, double cost) {
        this.A = A;
        this.B = B;
        this.cost = cost;
    }

    public Router getA() {
        return A;
    }

    public Router getB() {
        return B;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Link{" + "A=" + A + ", B=" + B + ", cost=" + cost + '}';
    }
    

}
