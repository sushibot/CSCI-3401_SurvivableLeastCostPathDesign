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
        boolean isBroken = false;
        boolean destIsNeighbor = false;
        /*
        ArrayList<Link> pathLinks = new ArrayList<Link>();
        ArrayList<Double> pathCosts = new ArrayList<Double>();
        */
        /*
        ArrayList<ArrayList<Link>> oldLink = new ArrayList<ArrayList<Link>>();
        for (Router r : routerList) {
            ArrayList<Link> copiedLinks = new ArrayList<Link>();
            for (Link links : r.getLinks()) {
                copiedLinks.add(new Link(links.getA(), links.getB(), links.getCost()));
            }
            oldLink.add(copiedLinks);
        }
        */
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
            //boolean neighborIsDestination = false;
            int numOfRuns = 0;
            
            //for (int i = 0; i < index; i++) {
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
                //}
            
            while (nextHop != dest && !queue.isEmpty()) {
                
                ArrayList<Router> neighbors = nextHop.getNeighbors();
                /*
                if(neighbors.contains(dest) && nextHop.equals(router) && !destIsNeighbor)
                {
                    //neighborIsDestination = true;
                    //numOfRuns++;
                    //neighbors.remove(dest);
                    destIsNeighbor = true;
                    hops.add(dest);
                    hopCost.add(Link.getLinkBetween(router, dest).getCost());
                    break;
                     
                }
                */
                
                
                
                for (Router rm : doneList)
                    neighbors.remove(rm);
                
                Object AlgItData[] = null;
                try{
                AlgItData = AlgIitration(nextHop, neighbors, dest);
                }catch(NoRouteToDestination e)
                {
                    System.err.println(e.getMessage());
                    //return route;
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
                    /*
                    pathLinks.add((Link) AlgItData[2]);
                    pathCosts.add((Double) AlgItData[1]);
                    */
                    
            }

            route[index] = new Path(hopCost,hops);
            
            
            addUsedLinks(Path.calculateLinksUsed(routerList,route[index++]));
            getUsedLinks();
        }
        /*
        for (Link linker : this.usedLink) {
            Router origin = routerList.get(routerList.indexOf(linker.getA()));
            int oldLinkLoc = origin.getLinks().indexOf(linker);
            origin.getLinks().remove(oldLinkLoc);
            origin.getLinks().add(oldLinkLoc, linker);
        }
        this.usedLink.clear();
        */
        /*
        for (int i = 0; i < oldLink.size(); i++)
            routerList.get(i).setLinks(oldLink.get(i));
        */
        /*
        for (int i = 0; i < pathLinks.size(); i++) {
            int linkIndex = Link.getALLLINKS().indexOf(pathLinks.get(i));
            Link.getALLLINKS().get(linkIndex).setCost(pathCosts.get(i));
        }
        */
        if(isBroken) NoRouteToDestination.COUNT--;
        return route;
    }
    
    
    @Deprecated
    //See AlgIitration(Router startPoint, ArrayList<Router> neighbors, Router dest)
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
        Object[] data = new Object[3];
        /*
        if(startPoint.equals(dest)) 
        {
            data[0] = startPoint;
            data[1] = 0.0;
            data[2] = null;
            return data;
        }
        */
        if(neighbors.isEmpty()) throw new NoRouteToDestination(startPoint, dest);
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
        data[2] = tempLink;
        //this.usedLink.add(tempLink);
        //tempLink.setCost(Double.POSITIVE_INFINITY);
        return data;
    }
    
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
    
    
    public ArrayList<Path> getPaths()
    {
        return this.paths;
    }
    
}
