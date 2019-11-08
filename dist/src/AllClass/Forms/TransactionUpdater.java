package AllClass.Forms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Basnet
 */
public class TransactionUpdater {

    TransactionHolder tranHolder;
    TableTransaction tblTransaction;

    TransactionUpdater(TransactionHolder tranHolder, TableTransaction tblTransaction) {
        this.tranHolder = tranHolder;
        this.tblTransaction = tblTransaction;
    }
    
    
   void write() {
       
        FileWriter writerFile = null;
        BufferedWriter bufferWriter = null;
        PrintWriter printWriter = null;
        File txtFile = null;
        try {
             txtFile = new File("src//Users//" + tblTransaction.getUsername() + "//Transaction.txt" ); 
            writerFile = new FileWriter(txtFile);
            bufferWriter = new BufferedWriter(writerFile);
            printWriter = new PrintWriter(bufferWriter);
            for (ClassTransaction tran :tranHolder) {              
             printWriter.println(tran.getTransactionID()+ "%" + tran.getDate()  + "%" +
             tran.getDescription() +"%" +  tran.getAmount() + "%" + tran.getTransactionType());
                }
            
            printWriter.close();
   }
         catch (IOException iOException) {
            System.out.println(iOException.getMessage());
        } finally {
            try {
                writerFile.close();
            } catch (IOException ex) {
                ex.printStackTrace(); 
//            }
            }
        }
    }

    void read() {
        String LinebyLine;
        FileReader readerFile = null;
        BufferedReader readerBuffer = null;
        File txtFile = null;
        try {
        txtFile = new File("src//Users//" + tblTransaction.getUsername() + "//Transaction.txt" );  
         readerFile = new FileReader(txtFile);
            readerBuffer = new BufferedReader(readerFile);
            tranHolder.clear();
            while ((LinebyLine = readerBuffer.readLine()) != null) {

                String[] eachLine = LinebyLine.split("%");
                ClassTransaction tran = new ClassTransaction(eachLine[0],eachLine[0],eachLine[1],
                        eachLine[2], Double.parseDouble(eachLine[3]),eachLine[4],true);
                tranHolder.add(tran);
            }
            tranHolder.UpdateTableValues(tblTransaction);

        } catch (FileNotFoundException fileEx) {
            System.out.println( fileEx.getMessage());
        } catch (IOException ioe) {  
            System.out.println("Cannot read from file. Error: " + ioe.getMessage());
        } finally {
            try {
                readerBuffer.close();
                readerFile.close();
            } catch (IOException ex) {  
            }
        }
    }

}
