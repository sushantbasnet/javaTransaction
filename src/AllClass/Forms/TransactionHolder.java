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

public class TransactionHolder extends LinkedList<ClassTransaction> {

    TableTransaction transaction;

    void UpdateTableValues(TableTransaction transaction) {
        JTable tblTransaction = transaction.getTableTransaction();
        DefaultTableModel modelAsTableTransaction = (DefaultTableModel) tblTransaction.getModel();
        modelAsTableTransaction.setRowCount(0);
        String[] eachCellValue = new String[5];
        for (ClassTransaction tra : this) {
            eachCellValue[0] = tra.getTransactionID();
            eachCellValue[1] = tra.getDate();
            eachCellValue[2] = tra.getDescription();
            eachCellValue[3] = Double.toString(tra.getAmount());
            eachCellValue[4] = tra.getTransactionType();
            modelAsTableTransaction.addRow(eachCellValue);
            Arrays.fill(eachCellValue, null);
        }
    }

    void updateHolder(String idTrans, TableTransaction tblTransaction) {
        for (ClassTransaction value : this) {
            if (value.getTransactionID().equals(idTrans)) {
                value.setDate(tblTransaction.txt_date.getText());
                value.setDescription(tblTransaction.txt_des.getText());
                value.setAmount(Double.parseDouble(tblTransaction.txt_amount.getText()));
                value.setTransactionType(tblTransaction.getSelectedTransactionType());
            }
        }

    }

    void deleteFromHolder(String transactionID) {
        ListIterator<ClassTransaction> itr = this.listIterator();
        while (itr.hasNext()) {
            ClassTransaction tran = itr.next();
            if (tran.getTransactionID().equals(transactionID)) {
                itr.remove();
            }
        }
    }

}
