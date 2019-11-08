package AllClass.Forms;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author Basnet
 */
public class TriggerHolder extends LinkedList<ClassTrigger> {

    TableTrigger trigger;

    void UpdateTableValues(TableTrigger trigger) {
        JTable tblTrigger = trigger.getTableTrigger();
        DefaultTableModel modelAsTableTrigger = (DefaultTableModel) tblTrigger.getModel();
        modelAsTableTrigger.setRowCount(0);
        String[] eachCellValue = new String[5];
        for (ClassTrigger tri : this) {
            eachCellValue[0] = tri.getTriggerID();
            eachCellValue[1] = tri.getDate();
            eachCellValue[2] = tri.getDescription();
            eachCellValue[3] = Double.toString(tri.getAmount());
            eachCellValue[4] = tri.getTriggerType();
            modelAsTableTrigger.addRow(eachCellValue);
            Arrays.fill(eachCellValue, null);
        }
    }

    void update(String idTrig, TableTrigger tblTrigger) {
        for (ClassTrigger value : this) {
            if (value.getTriggerID().equals(idTrig)) {
                value.setDate(tblTrigger.txt_date.getText());
                value.setDescription(tblTrigger.txt_des.getText());
                value.setAmount(Double.parseDouble(tblTrigger.txt_amount.getText()));
                value.setTriggerType(tblTrigger.getSelectedTriggerType());
            }
        }

    }

    void delete(String triggerID) {
        ListIterator<ClassTrigger> itr = this.listIterator();
        while (itr.hasNext()) {
            ClassTrigger tri = itr.next();
            if (tri.getTriggerID().equals(triggerID)) {
                itr.remove();
            }
        }
    }

}
