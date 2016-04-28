
import DataImport.DataInput;
import Router.Router;
import Router.link.Link;
import dijkstraAlg.Dijkstra_Alg;
import dijkstraAlg.NoRouteToDestination;
import dijkstraAlg.Path;
import java.util.ArrayList;

/**
 *
 * @author Tyler_Atiburcio
 */
public class Runner {
    
    public static void main(String[] args) throws Exception {
        try{
            if(args[0].toUpperCase().endsWith(".CSV"))
            {
                long startTime = System.currentTimeMillis();
                //System.out.println(Double.POSITIVE_INFINITY);
                Router[] routerList = DataInput.read(args[0]);
                //System.out.println(routerList[0].getLinks());
                //System.out.println(routerList[0].getLinks());

                //System.out.println("src " + routerList[0].getName());
                //System.out.println("dest " + routerList[routerList.length-1].getName());
                //Dijkstra_Alg test1 = new Dijkstra_Alg(routerList[0],routerList);
                //Path[] testPath = test1.runAlg(routerList[routerList.length-1]);
                //Path[] testPath = routerList[0].getLinkTable().runAlg(routerList[routerList.length-1]);
                //System.out.println(testPath[0]);
                //test1.printUsedLinks();
                //System.out.println(testPath[1]);
                //routerList[0].getLinkTable().printUsedLinks();
                double linkCosts[][] = new double[routerList.length][routerList.length];
                int index = 0;
                for (int i = 0; i < linkCosts.length; i++) {
                    for (int j = 0; j < linkCosts[i].length; j++) {
                        linkCosts[i][j] = Link.getALLLINKS().get(index++).getCost();
                    }
                }
                
                for (int i = 0; i < linkCosts.length; i++) {
                    System.out.print("| ");
                    for (int j = 0; j < linkCosts[i].length; j++) {
                        System.out.print(linkCosts[i][j]+ " ");
                    }
                    System.out.println("|\n");
                }
                
                Dijkstra_Alg.calculateAllRouters();
                for (Router router : routerList) {
                    System.out.println(router.getName());
                    //System.out.println(router.getLinkTable().getPaths());
                    for (Path paths : router.getLinkTable().getPaths()) {
                        System.out.println(paths);
                        System.out.println("");
                    }
                    System.out.println("\n\n\n");
                }

                //Path[] testPath = routerList[0].getLinkTable().runAlg(routerList[routerList.length-1]);
                //System.out.println(testPath[0]);
                //test1.printUsedLinks();
                //System.out.println(testPath[1]);
                routerList[0].getLinkTable().printUsedLinks();
                
                System.out.println("\n\n\nTimeTook(ms): "+( System.currentTimeMillis()-startTime));
                System.out.println("Amount of broken routes: " + NoRouteToDestination.COUNT);
            }
        }catch(Error e){
            System.err.println(e.toString());
        }
        finally{
            System.out.println("\n\nUsage:");
            System.out.println("Input: CSV file containing routers and their links");
            System.out.println("CSCI-3401_SurvivableLeastCostDesignPathDesign.jar (fileName)\n");
        }
            
    }

}
