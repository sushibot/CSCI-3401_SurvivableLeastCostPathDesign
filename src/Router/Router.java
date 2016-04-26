package Router;

import Router.link.Link;
import dijkstraAlg.Dijkstra_Alg;
import java.util.ArrayList;

/**
 *
 * @author Tyler_Atiburcio
 */
public class Router {
    
    private ArrayList<Link> links = new ArrayList<Link>();
    protected String name;
    protected Dijkstra_Alg linkTable;
    private final int ID;
    /**Creates a new router with the given name
     * 
     * @param name Name of Router
     */
    public Router(String name)
    {
        this.name = name;
        this.ID = new java.util.Random().nextInt(Integer.MAX_VALUE);
        this.linkTable = new Dijkstra_Alg(this);
    }
    
    /**Returns the name of the router
     * 
     * @return String Name of the Router
     */
    public String getName() {
        return name;
    }
    
    /**Sets the Router's name to a new name
     * 
     * @param name Sets name of router
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**Returns all the links of the router
     * 
     * @return ArrayList<Link> All the links to the router
     */
    public ArrayList<Link> getLinks() {
        return links;
    }
    
    public ArrayList<Router> getNeighbors()
    {
        ArrayList<Router> neighbors = new ArrayList<Router>();
        for (Link link : this.getLinks())
             if(link.getCost() != Double.POSITIVE_INFINITY && link.getCost() != 0 )
                 neighbors.add(link.getB());
        return neighbors;
    }

    public Dijkstra_Alg getLinkTable() {
        return linkTable;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Router r = (Router) obj;
        return this.ID == r.ID;
    }
 
    
    
    
    @Override
    public String toString() {
        return "{"+ name + " links=" + links + '}';
    }
    
    
    

}
