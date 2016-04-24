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
    private static int[][] usedLinks;
    private final Router router;
    private final Router[] routerList;

    
    public Dijkstra_Alg(Router router, Router[] routerList)
    {
        this.router = router;
        this.routerList = routerList;
    }
    
    public Path[] runAlg(Router dest) throws Exception
    {
        if(dest == null)
            throw new Error("Router is null!");
        Path[] route = new Path[2];
        int index = 0;
        ArrayList<Router> queue = new ArrayList<Router>();
        ArrayList<Router> doneList = new ArrayList<Router>();
        for (Router router1 : routerList) {
            queue.add(router1);
        }
        //queue.remove(router);
        
        ArrayList<Router> hops = new ArrayList<Router>();
        ArrayList<Double> hopCost = new ArrayList<Double>();
        Router nextHop = queue.get(queue.indexOf(router));
        doneList.add(nextHop);
        do {
            ArrayList<Router> neighbors = nextHop.getNeighbors();
            for (Router rm : doneList)
                neighbors.remove(rm);

            Object AlgItData[] = AlgIitration(nextHop, neighbors, dest);
            if(AlgItData[0] == nextHop && AlgItData[0] != dest)
            {
                
            }
                nextHop = (Router) AlgItData[0];
                hops.add(nextHop);
                hopCost.add((Double) AlgItData[1]);
                doneList.add(nextHop);
                queue.remove(nextHop);
        } while (nextHop != dest && !queue.isEmpty());
        
        route[index++] = new Path(hopCost,hops);
        
        return route;
    }
    
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
    
    private Object[] AlgIitration(Router startPoint, ArrayList<Router> neighbors, Router dest)
    {
        Object[] data = new Object[2];
        Link tempLink = null;
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
    
    /**Adds used links to determine if links are reused
     * 
     * @param bruhhh Links used in the path
     */
    public static void addUsedLinks(int[][] bruhhh)
    {
        for (int i = 0; i < usedLinks.length; i++) {
            for (int j = 0; j < usedLinks[i].length; j++) {
                usedLinks[i][j] += bruhhh[i][j];
            }
        }
    }
    
    
}
