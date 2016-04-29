package dijkstraAlg;

import Router.Router;

/**Route to destination Error, if not route is possible to destination
 *
 * @author Tyler Atiburcio 
 * @author Alter Calubana
 * @author Gabriel Fontanilla
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
