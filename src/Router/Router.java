package Router;

import Router.link.Link;
import java.util.ArrayList;

/**
 *
 * @author Tyler_Atiburcio
 */
public class Router {
    
    private ArrayList<Link> links = new ArrayList<Link>();
    protected String name;
    /**Creates a new router with the given name
     * 
     * @param name Name of Router
     */
    public Router(String name)
    {
        this.name = name;
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
    
    

}
