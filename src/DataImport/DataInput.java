package DataImport;

import Router.Router;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Tyler_Atiburcio
 */
public class DataInput {

    public static Router[] read(String arg) throws Exception{
        ArrayList<Router> routers = new ArrayList<Router>();
        File file = new File(arg);
        Scanner scan = new Scanner(file);
        String tmp = scan.nextLine().toUpperCase();
        if(!tmp.matches("[DEFINE|RAND]")) throw new Error("Invalid File header!");
        while(scan.hasNext())
        {
            
        }
        Router[] data = new  Router[routers.size()];
        for (int i = 0; i < data.length; i++) {
            data[i] = routers.get(i);
        }
        return data;
    }
    
    

}
