
import javax.swing.table.DefaultTableModel;

public class NoEditTableModel extends DefaultTableModel{
    @Override
    public boolean isCellEditable(int rowIndex, int mColIndex){
        return false;
    }
}