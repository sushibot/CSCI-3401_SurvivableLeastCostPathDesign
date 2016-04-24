
import DataImport.DataInput;
import Router.Router;
import Router.link.Link;
import dijkstraAlg.Dijkstra_Alg;
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
                System.out.println(Double.POSITIVE_INFINITY);
                Router[] routerList = DataInput.read(args[0]);
                //System.out.println(routerList[0].getLinks());
                System.out.println(routerList[0].getLinks());

                System.out.println("src " + routerList[0].getName());
                System.out.println("dest " + routerList[routerList.length-1].getName());
                Dijkstra_Alg test1 = new Dijkstra_Alg(routerList[0],routerList);
                Path[] testPath = test1.runAlg(routerList[routerList.length-1]);
                System.out.println(testPath[0]);
                //test1.printUsedLinks();
                System.out.println(testPath[1]);
                test1.printUsedLinks();
                
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
