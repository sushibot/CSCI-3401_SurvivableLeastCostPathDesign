package Router.link;

import Router.Router;
import java.util.Random;

/** The link between two routers
 *
 * @author Tyler_Atiburcio
 */
public class Link {
    private final Router A,B;
    private double cost;
    /**Link Between two routers
     * 
     * @param A Source Router
     * @param B Destination Router
     * @param cost Cost for link
     */
    public Link(Router A, Router B, double cost) {
        this.A = A;
        this.B = B;
        this.cost = cost;
    }
    
    /**Link Between two routers, with a cost of 0
     * 
     * @param A Source Router
     * @param B Destination Router
     */
    public Link(Router A, Router B)
    {
        this.A = A;
        this.B = B;
        this.cost = 0;
    }
    
    /**Return Source router
     * 
     * @return Router; Source Router
     */
    public Router getA() {
        return A;
    }
    
    /**Return Destination Router
     * 
     * @return Router; Destination Router
     */
    public Router getB() {
        return B;
    }
    
    /**Returns the cost of the link between the two routers
     * 
     * @return double; Cost of Link
     */
    public double getCost() {
        return cost;
    }
    
    /**Set the Cost of the link
     * 
     * @param cost; Cost of Link; 0 cost for itself; NaN for unreachable
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Link{" + A.getName() + "<->" + B.getName() + ": " + cost + '}';
    }
    
    /**Returns a randomly generated cost from 0-100, including no link (NaN)
     * 
     * @return double; Generated Cost
     */
    public static double generateCost()
    {
        Random random = new Random();
        return random.nextBoolean() ? Double.NaN:random.nextInt(101);
    }
    

}
