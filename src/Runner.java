
import DataImport.DataInput;

/**
 *
 * @author Tyler_Atiburcio
 */
public class Runner {
    
    public static void main(String[] args) {
        try{
            if(args[0].toUpperCase().endsWith(".CSV"))
            {
                DataInput.read(args[0]);
            }
        }catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally{
            System.out.println("\n\nUsage:");
            System.out.println("Input: CSV file containing routers and their links");
            System.out.println("CSCI-3401_SurvivableLeastCostDesignPathDesign.jar (fileName)\n");
        }
            
    }

}
