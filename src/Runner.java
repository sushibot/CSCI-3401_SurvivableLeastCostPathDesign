
import DataImport.DataInput;
import Router.Router;
import Router.link.Link;
import dijkstraAlg.Dijkstra_Alg;
import dijkstraAlg.Path;
import javax.swing.JFileChooser;

/**
 *
 * @author Tyler Atiburcio 
 * @author Alter Calubana
 * @author Gabriel Fontanilla
 */
public class Runner {
    static String fileName = "";
    
    public static void main(String[] args) throws Exception {
        try{
            if(args.length == 0)
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.showOpenDialog(null);
                if(!fileChooser.getSelectedFile().isFile()) throw new Error("Invalid file Type!");
                fileName = fileChooser.getSelectedFile().getPath().toUpperCase();
            }
            if(!fileName.isEmpty())
            {
                if(args.length != 0)
                {
                    if(!args[0].toUpperCase().endsWith(".CSV")) throw new Error("Invalid file Type!");
                    fileName = args[0].toUpperCase();
                }
                    
                
                long startTime = System.currentTimeMillis();
                Router[] routerList = DataInput.read(fileName);

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
                    for (Path paths : router.getLinkTable().getPaths()) {
                        System.out.println(paths);
                        System.out.println("");
                    }
                    System.out.println("\n\n\n");
                }
                
                System.out.println("\n\n\nTimeTook(ms): "+( System.currentTimeMillis()-startTime));
            }
            else throw new Error("Invalid file Type!");
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
