package Router.link;

import Router.Router;
import java.util.ArrayList;
import java.util.Random;

/** The link between two routers
 *
 * @author Tyler_Atiburcio
 */
public class Link implements Comparable {
    private final Router A,B;
    private double cost;
    private final static ArrayList<Link> ALLLINKS = new ArrayList<Link>();
    //private final static ArrayList<Link> CLONEDLINKS = new ArrayList<Link>();
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
        ALLLINKS.add(this);
        //CLONEDLINKS.add(new Link(A,B,cost));
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
    
    /**Returns a randomly generated cost from 1-100
     * 
     * @return double; Generated Cost
     */
    public static double generateCost()
    {
        Random random = new Random();
        return (random.nextInt(100) +1);
    }
    
    /**Returns a randomly generated cost given the bound
     * 
     * @param bound
     * @return double; Cost of the link within the bound
     */
    public static double generateCost(int bound)
    {
        Random random = new Random();
        return random.nextInt(bound) +1;
    }

    @Override
    public int compareTo(Object o) {
        Link lk = (Link)o;
        if(this.getCost() > lk.getCost())
            return -1;
        else if(this.getCost() < lk.getCost())
            return 1;
        else return 0;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static ArrayList<Link> getALLLINKS() {
        return ALLLINKS;
    }
    
    /*
    public static ArrayList<Link> getClonedLinks()
    {
        return CLONEDLINKS;
    }
    */
    
    public static Link getLinkBetween(Router a, Router b)
    {
        for (Link link : ALLLINKS) {
            if(link.A.equals(a) && link.B.equals(b))
                return link;
        }
        return null;
    }
 
}
