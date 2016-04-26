package dijkstraAlg;

import Router.Router;
import Router.link.Link;
import java.util.ArrayList;

/**
 *
 * @author tba_m
 */
public class Dijkstra_Alg {
    
    private ArrayList<Path> paths = new ArrayList<Path>();
    private int[][] usedLinks;
    private final Router router;
    private static final ArrayList<Router> routerList = new ArrayList<Router>();
    
    private ArrayList<Link> usedLink = new ArrayList<Link>();

    @Deprecated
    public Dijkstra_Alg(Router router, Router[] routerList)
    {
        this.router = router;
        //this.routerList = routerList;
    }
    
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
        int index = 0;
        while(index != 2){
            ArrayList<Router> queue = new ArrayList<Router>();
            ArrayList<Router> doneList = new ArrayList<Router>();
            for (Router router1 : routerList) {
                queue.add(router1);
            }
            
            if(index != 0)
            for (Router used : route[0].getRouterPath()) {
                if(used != dest && used != router)
                {
                    queue.remove(used);
                    doneList.add(used);
                }
            }
            //queue.remove(router);

            ArrayList<Router> hops = new ArrayList<Router>();
            ArrayList<Double> hopCost = new ArrayList<Double>();
            Router nextHop = queue.get(queue.indexOf(router));
            hops.add(nextHop);
            hopCost.add(0.0);
            doneList.add(nextHop);
            while (nextHop != dest && !queue.isEmpty()) {
                ArrayList<Router> neighbors = nextHop.getNeighbors();
                for (Router rm : doneList)
                    neighbors.remove(rm);
                
                Object AlgItData[] = null;
                try{
                AlgItData = AlgIitration(nextHop, neighbors, dest);
                }catch(NoRouteToDestination e)
                {
                    System.err.println(e.getMessage());
                    return route;
                }
                    nextHop = (Router) AlgItData[0];
                    hops.add(nextHop);
                    hopCost.add((Double) AlgItData[1]);
                    doneList.add(nextHop);
                    queue.remove(nextHop);
            }

            route[index] = new Path(hopCost,hops);
            addUsedLinks(Path.calculateLinksUsed(routerList,route[index++]));
            getUsedLinks();
        
        }
        
        return route;
    }
    
    //See AlgIitration(Router startPoint, ArrayList<Router> neighbors, Router dest)
    @Deprecated
    private Object[] AlgIitration(Router startPoint)
    {
        Object[] data = new Object[2];
        Link minLink = null;
        int tempLinkCost = Integer.MAX_VALUE;
        for (int i = 0; i < startPoint.getLinks().size()-1; i++) {
            for (int j = i+1; j < startPoint.getLinks().size(); j++) {
                if(startPoint.getLinks().get(i).compareTo(startPoint.getLinks().get(j)) < 0)
                {
                    if(startPoint.getLinks().get(i).getCost() < tempLinkCost && startPoint.getLinks().get(i).getB() != startPoint)
                    {
                        tempLinkCost = (int) startPoint.getLinks().get(i).getCost();
                        minLink = startPoint.getLinks().get(i);
                    }
                }
                else if(startPoint.getLinks().get(i).compareTo(startPoint.getLinks().get(j)) > 0 && startPoint.getLinks().get(i).getB() != startPoint)
                {
                    if(startPoint.getLinks().get(j).getCost() < tempLinkCost)
                    {
                        tempLinkCost = (int) startPoint.getLinks().get(j).getCost();
                        minLink = startPoint.getLinks().get(j);
                    }
                }
                else if( startPoint.getLinks().get(i).getB() == startPoint && minLink != null)
                {
                    data[0] = minLink.getB();
                    data[1] = tempLinkCost;
                    return data;
                }
                else 
                    minLink = startPoint.getLinks().get(i);
            }
        }
            data[0] = minLink.getB();
            data[1] = tempLinkCost;
        return data;
                
    }
    
    private Object[] AlgIitration(Router startPoint, ArrayList<Router> neighbors, Router dest) throws NoRouteToDestination
    {
        Object[] data = new Object[2];
        if(neighbors.isEmpty()) throw new NoRouteToDestination();
        Link tempLink = neighbors.get(0).getLinks().get(0);
        double linkCost = Double.MAX_VALUE;
        for (Router neighbor : neighbors) {
            
            for (int i = 0; i < neighbor.getLinks().size(); i++) {
                /*for (int j = 0; j < usedLink.size(); j++) {
                    if(neighbor.getLinks().get(i).equals(usedLink.get(j)))
                    {
                        tempLink = neighbor.getLinks().get(1);
                        linkCost = neighbor.getLinks().get(1).getCost();
                        break;
                    }
                } */
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
    
    /**Adds used links to determine if links are reused
     * 
     * @param bruhhh Links used in the path
     */
    public void addUsedLinks(int[][] bruhhh)
    {
        if(usedLinks == null) usedLinks = new int[bruhhh.length][bruhhh[0].length];
        for (int i = 0; i < usedLinks.length; i++) {
            for (int j = 0; j < usedLinks[i].length; j++) {
                usedLinks[i][j] += bruhhh[i][j];
            }
        }
    }
    
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
    
    public ArrayList<Link> getUsedLinks()
    {
        for (int i = 0; i < usedLinks.length; i++) {
            for (int j = 0; j < usedLinks[i].length; j++) {
                if(usedLinks[i][j] != 0) usedLink.add(routerList.get(i).getLinks().get(j));
            }
        }
        return usedLink;
    }
    
    
}
