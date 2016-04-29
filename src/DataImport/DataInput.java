package DataImport;

import Router.Router;
import Router.link.Link;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**Reads CSV file and reads in following format ROUTER_NAME,LINK_COST[n,'/' no link, ? (random link cost)],...
 *Each line must be the same length (same amount of elements per line)
 * 
 * @author Tyler_Atiburcio
 */
public class DataInput {

    public static Router[] read(String arg) throws Exception{
        ArrayList<Router> routers = new ArrayList<Router>();
        File file = new File(arg);
        Scanner scan = new Scanner(file);
        ArrayList<String> listings = new ArrayList<String>();
        while(scan.hasNext())
        {
            listings.add(scan.nextLine());
        }
        for (String routerName : listings) {
            String[] data = routerName.split(",");
            routers.add(new Router(data[0]));
        }
            
        for (int i = 0; i < routers.size(); i++) {
            String[] routerData = listings.get(i).split(",");
            for (int j = 1; j < routerData.length; j++) {
                double cost = 0;
                if(routerData[j].equals("/"))
                    cost = Double.POSITIVE_INFINITY;
                else if(routerData[j].matches("/[0-9]+"))
                    cost = Link.generateCost(Integer.parseInt(routerData[j].split("?")[1]));
                else if(routerData[j].equals("?"))
                    cost = Link.generateCost();
                else if(routerData[j].matches("[0-9]+"))
                    cost = Integer.parseInt(routerData[j]);
                else 
                    throw new Error("Error Reading File!");
                routers.get(i).getLinks().add(new Link(routers.get(i),routers.get(j-1),cost));
            }

        }
        
        Router[] data = new  Router[routers.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = routers.get(i);
        }
        return data;
    }
    
    

}
