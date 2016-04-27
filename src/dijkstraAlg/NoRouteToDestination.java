package dijkstraAlg;

import Router.Router;

/**
 *
 * @author tba_m
 */
public class NoRouteToDestination extends Error {
    
    public static int COUNT = 0;

    public NoRouteToDestination() {
        super("No Route To Destination! Rerouting!");
        COUNT++;
    }

    NoRouteToDestination(Router startPoint, Router dest) {
        super("No Route to " + startPoint.getName() +"<->"+ dest.getName() + "! Rerouting!");
        COUNT++;
    }
    
    

}
