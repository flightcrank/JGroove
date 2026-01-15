import java.awt.datatransfer.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableRowTransferHandler extends TransferHandler {

@Override
    public int getSourceActions(JComponent c) { return MOVE; }

    @Override
    protected Transferable createTransferable(JComponent c) {
        JTable table = (JTable) c;
        // We just return a dummy string because we'll pull the 
        // actual indices directly from the table during the drop.
        return new StringSelection("dummy");
    }

    @Override
    public boolean canImport(TransferSupport support) { return true; }

    @Override
    public boolean importData(TransferSupport support) {
        JTable table = (JTable) support.getComponent();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int[] rows = table.getSelectedRows(); // Get the multi-selection
        int dropIndex = ((JTable.DropLocation) support.getDropLocation()).getRow();

        // 1. Copy the data out
        List<Vector> data = new ArrayList<>();
 
	for (int i : rows) {
		
		data.add((Vector) model.getDataVector().elementAt(i));
	}

        // 2. Remove old rows (Bottom-to-Top to avoid index shifting)
        for (int i = rows.length - 1; i >= 0; i--) {

		if (rows[i] < dropIndex) {
			dropIndex--;
		}
		
            model.removeRow(rows[i]);
        }

        // 3. Insert at new spot
        for (Vector v : data) {
		
		model.insertRow(dropIndex++, v);
	}

	int indexStart = dropIndex - data.size();
	int indexEnd = indexStart + data.size() - 1;
	table.setRowSelectionInterval(indexStart, indexEnd);
	
        return true;
    }
}