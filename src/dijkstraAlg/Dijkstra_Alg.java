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

    public Dijkstra_Alg(Router router) {
        this.router = router;
    }
    
    public Path[] runAlg(Router dest) throws Exception
    {
        Path[] route = new Path[2];
        if(dest == null)
            throw new Error("Router is nulll!");
        throw new Exception("I have no fucking idea");
        
        
    }
    
    private Router AlgIitration()
    {
        Link minLink = null;
        int tempLinkCost = Integer.MAX_VALUE;
        for (int i = 0; i < router.getLinks().size()-1; i++) {
            for (int j = i+1; j < router.getLinks().size(); j++) {
                if(router.getLinks().get(i).compareTo(router.getLinks().get(j)) < 0)
                {
                    if(router.getLinks().get(i).getCost() < tempLinkCost)
                    {
                        tempLinkCost = (int) router.getLinks().get(i).getCost();
                        minLink = router.getLinks().get(i);
                    }
                }
                else if(router.getLinks().get(i).compareTo(router.getLinks().get(j)) > 0)
                {
                    if(router.getLinks().get(j).getCost() < tempLinkCost)
                    {
                        tempLinkCost = (int) router.getLinks().get(j).getCost();
                        minLink = router.getLinks().get(j);
                    }
                }
                else 
                    minLink = router.getLinks().get(i);
            }
        }
        return minLink.getB();
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
