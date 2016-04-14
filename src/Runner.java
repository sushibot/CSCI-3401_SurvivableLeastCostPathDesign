
import DataImport.DataInput;
import Router.Router;
import Router.link.Link;
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
                Router[] test = DataInput.read(args[0]);
                for (Router test1 : test)
                    System.out.println(test1.getLinks());
                
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
