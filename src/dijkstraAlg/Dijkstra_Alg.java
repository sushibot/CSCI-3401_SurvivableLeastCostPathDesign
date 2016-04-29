package dijkstraAlg;

import Router.Router;
import Router.link.Link;
import java.util.ArrayList;

/**The Dijkstra Algorithm
 *
 * @author Tyler Atiburcio 
 * @author Alter Calubana
 * @author Gabriel Fontanilla
 */
public class Dijkstra_Alg {
    
    private ArrayList<Path> paths = new ArrayList<Path>();
    private int[][] usedLinks;
    private final Router router;
    private static final ArrayList<Router> routerList = new ArrayList<Router>();
    
    private ArrayList<Link> usedLink = new ArrayList<Link>();

    /**Creates a new Link Table
     * 
     * @param router 
     */
    public Dijkstra_Alg(Router router)
    {
        this.router = router;
        routerList.add(this.router);
    }
    
    public Path[] runAlg(Router dest) throws Error
    {
        if(dest == null)
            throw new Error("Router is null!");
        Path[] route = new Path[2];
        boolean isBroken = false;                                               //For Debug
        
        int index = 0;
        while(index != 2){
            ArrayList<Router> queue = new ArrayList<Router>();
            ArrayList<Router> doneList = new ArrayList<Router>();
            for (Router router1 : routerList) {
                queue.add(router1);
            }
            
            //Remove routers that are already used
            if(index != 0)
            for (Router used : route[0].getRouterPath()) {
                if(used != dest && used != router)
                {
                    queue.remove(used);
                    doneList.add(used);
                }
            }

            ArrayList<Router> hops = new ArrayList<Router>();
            ArrayList<Double> hopCost = new ArrayList<Double>();
            Router nextHop = queue.get(queue.indexOf(router));
            hops.add(nextHop);
            hopCost.add(0.0);
            doneList.add(nextHop);
            
            //If destination is a neighbor of the router, force a next hop away dest to find a link disjoint path
            if(route[0] != null)
                if(route[0].getRouterPath().length == 2)
                {
                    int destRouter = nextHop.getNeighbors().indexOf(dest);
                    for (int j = 0; j < nextHop.getNeighbors().size(); j++) {
                        if(destRouter != j) 
                        {
                            nextHop = nextHop.getNeighbors().get(j);
                            hops.add(nextHop);
                            hopCost.add(Link.getLinkBetween(router, nextHop).getCost());
                            doneList.add(nextHop);
                            break;
                        }
                    }


                }

            
            while (nextHop != dest && !queue.isEmpty()) {
                
                ArrayList<Router> neighbors = nextHop.getNeighbors();
                
                for (Router rm : doneList)
                    neighbors.remove(rm);
                
                Object AlgItData[] = null;
                try{
                    AlgItData = AlgIitration(nextHop, neighbors, dest);
                }catch(NoRouteToDestination e)                                  //If there is no Route to destination rerun the route finding process
                {
                    System.err.println(e.getMessage());
                    nextHop = router;
                    for (Router router1 : routerList)
                        queue.add(router1);
                    index = 0;
                    route = new Path[2];
                    isBroken = true;
                    break;  
                }
                    nextHop = (Router) AlgItData[0];
                    hops.add(nextHop);
                    hopCost.add((Double) AlgItData[1]);
                    doneList.add(nextHop);
                    queue.remove(nextHop);
                    
            }

            route[index] = new Path(hopCost,hops);
            
            
            addUsedLinks(Path.calculateLinksUsed(routerList,route[index++]));   //For Debug
            getUsedLinks();                                                     //For Debug
        }
        if(isBroken) NoRouteToDestination.COUNT--;                              //For Debug
        return route;
    }
    
    /**Find and determines the best next hop
     * 
     * @param startPoint
     * @param neighbors
     * @param dest
     * @return nextHopRouter, costToTheNextRouter
     * @throws NoRouteToDestination 
     */
    private Object[] AlgIitration(Router startPoint, ArrayList<Router> neighbors, Router dest) throws NoRouteToDestination
    {
        Object[] data = new Object[2];
        if(neighbors.isEmpty()) throw new NoRouteToDestination(startPoint, dest);
        Link tempLink = neighbors.get(0).getLinks().get(0);
        double linkCost = Double.MAX_VALUE;
        for (Router neighbor : neighbors) {
            
            for (int i = 0; i < neighbor.getLinks().size(); i++) {
                if(neighbor.getLinks().get(i).getA().equals(neighbor.getLinks().get(i).getB())) continue;
                if(neighbor.getLinks().get(i).getB().equals(startPoint))
                {
                    if(neighbor.getLinks().get(i).getA().equals(dest))
                    {
                        tempLink = neighbor.getLinks().get(i);
                        linkCost = neighbor.getLinks().get(i).getCost();
                        break;
                    }
                    if(neighbor.getLinks().get(i).getCost() < linkCost)
                    {
                        tempLink = neighbor.getLinks().get(i);
                        linkCost = neighbor.getLinks().get(i).getCost();
                    }
                }
            }
        }
        data[0] = tempLink.getA();
        data[1] = linkCost;
        return data;
    }
    
    /**Calculate all routes for ever pair of routers.
     * 
     */
    public static void calculateAllRouters()
    {
        for (int i = 0; i < routerList.size(); i++) {
            for (int j = 0; j < routerList.size(); j++) {
                Path[] routes = routerList.get(i).getLinkTable().runAlg(routerList.get(j));
                routerList.get(i).getLinkTable().paths.add(routes[0]);
                routerList.get(i).getLinkTable().paths.add(routes[1]);
            }
        }
    }
    
    /**Adds used links to determine if links are reused
     * 
     * @param linkUse Links used in the path
     */
    public void addUsedLinks(int[][] linkUse)
    {
        if(usedLinks == null) usedLinks = new int[linkUse.length][linkUse[0].length];
        for (int i = 0; i < usedLinks.length; i++) {
            for (int j = 0; j < usedLinks[i].length; j++) {
                usedLinks[i][j] += linkUse[i][j];
            }
        }
    }
    
    /**Print out the how many times a link was used in the Route finding process.
     * 
     */
    public void printUsedLinks()
    {
        for (int i = 0; i < usedLinks.length; i++) {
            System.out.print("{");
            for (int j = 0; j < usedLinks[i].length; j++) {
                System.out.print(" " + usedLinks[i][j] + " ");
            }
            System.out.print("}\n");
        }
    }
    
    /**Return the used Links throughout the Route finding process
     * 
     * @return ArrayList<Link>
     */
    public ArrayList<Link> getUsedLinks()
    {
        for (int i = 0; i < usedLinks.length; i++) {
            for (int j = 0; j < usedLinks[i].length; j++) {
                if(usedLinks[i][j] != 0) usedLink.add(routerList.get(i).getLinks().get(j));
            }
        }
        return usedLink;
    }
    
    /**Return all routes to each pair of routers
     * 
     * @return ArrayList<Path>
     */
    public ArrayList<Path> getPaths()
    {
        return this.paths;
    }
    
}
