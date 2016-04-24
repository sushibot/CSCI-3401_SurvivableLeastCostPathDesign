package dijkstraAlg;

import Router.Router;
import java.util.ArrayList;

/**
 *
 * @author tba_m
 */
public class Path {

    protected double[] costHop;
    protected Router[] routers;
    
    /**Creates a new Path "Route", which holds the next hops and their link cost
     * 
     * @param costHop
     * @param routers 
     */
    public Path(double[] costHop, Router[] routers) {
        this.costHop = costHop;
        this.routers = routers;
    }
    
    public Path(ArrayList<Double> costHop, ArrayList<Router> path)
    {
        this.costHop = new double[costHop.size()];
        this.routers = new Router[path.size()];
        for (int i = 0; i < this.routers.length; i++) {
            this.costHop[i] = costHop.get(i);
            this.routers[i] = path.get(i);
        }
    }
    
    /**Returns the total cost of the path to source to destination
     * 
     * @return double; Cost of path
     */
    public double getTotalCost()
    {
        int cost = 0;
        for (int i = 0; i < costHop.length; i++) {
            cost += costHop[i];
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
        for (double i : costHop) 
            str+=i +", ";
        str+= "]";
        return str;
    }
    
    
    public static int[][] calculateLinksUsed(Router[] routerList, Path path)
    {
        int[][] usedLinks = new int[routerList.length][routerList.length];
        for (int i = 0; i < path.getRouterPath().length-1; i++) {
            
           int routerIndex = 0;
           for(;routerIndex < routerList.length;routerIndex++)
               if(path.getRouterPath()[i].equals(routerList[routerIndex]))
                   break;
           int linkIndex = 0;
           for(;linkIndex < routerList[routerIndex].getLinks().size();linkIndex++)
               if(routerList[routerIndex].getLinks().get(linkIndex).getB().equals(path.getRouterPath()[i + 1]))
                   break;
           usedLinks[routerIndex][linkIndex]++;
        }
       
        
        return usedLinks;
    }
    
}
