package dijkstraAlg;

import Router.Router;

/**
 *
 * @author tba_m
 */
public class Path {

    protected int[] path;
    protected Router[] routers;
    
    /**Creates a new Path "Route", which holds the next hops and their link cost
     * 
     * @param path
     * @param routers 
     */
    public Path(int[] path, Router[] routers) {
        this.path = path;
        this.routers = routers;
    }
    
    /**Returns the total cost of the path to source to destination
     * 
     * @return double; Cost of path
     */
    public double getTotalCost()
    {
        int cost = 0;
        for (int i = 0; i < path.length; i++) {
            cost += path[i];
        }
        return cost;
    }
    
    /**Returns the array of 'Next Hops'
     * 
     * @return Routers; Next Hops
     */
    public Router[] getRouterPath()
    {
        return this.routers;
    }

    @Override
    public String toString()
    {
        String str = "[";
        for (Router r : routers) 
            str += r.getName() + ", ";
        str += "]\n[";
        for (int i : path) 
            str+=i +", ";
        str+= "]";
        return str;
    }
    
    
}
