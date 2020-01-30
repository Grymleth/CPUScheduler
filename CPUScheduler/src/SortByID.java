
import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aevan
 */
public class SortByID implements Comparator<Process>{

    @Override
    public int compare(Process a, Process b) {
        return a.getID() - b.getID();
    }
    
}
