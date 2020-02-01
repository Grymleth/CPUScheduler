
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aevan
 */
public class NoEditTableModel extends DefaultTableModel{
    @Override
    public boolean isCellEditable(int rowIndex, int mColIndex){
        return false;
    }
}