package dijkstraAlg;

import Router.Router;
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
        throw new Exception("I have no fucking idea");
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
