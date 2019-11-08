package AllClass.Forms;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.JOptionPane;


/**
 *
 * @author Basnet
 */
public class TriggerUpdater {
    TableTransaction tableTransaction;
    TransactionHolder transHolder;
    TransactionUpdater transUpdater;
    HomeMenu hm;
    TriggerHolder trigHolder;
    TableTrigger tblTrigger;
    private String Username;
    
    TriggerUpdater(TriggerHolder trigHolder, TableTrigger tblTrigger) {
        this.trigHolder = trigHolder;
        this.tblTrigger = tblTrigger;
    }
   void writeToFile() {
        FileWriter writerFile = null;
        BufferedWriter bufferWriter = null;
        PrintWriter printWriter = null;
        File txtFile = null;
        try {
             txtFile = new File("src//Users//" + tblTrigger.getUsername() + "//Trigger.txt" ); 
            writerFile = new FileWriter(txtFile);
            bufferWriter = new BufferedWriter(writerFile);
            printWriter = new PrintWriter(bufferWriter);
            for (ClassTrigger trig : trigHolder) {              
             printWriter.println(trig.getTriggerID()+ "%" + trig.getDate()  + "%" +
             trig.getDescription() +"%" +  trig.getAmount() + "%" + trig.getTriggerType());
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
            }
        }
    }

    void readFromFile() {
        String LinebyLine;
        FileReader readerFile = null;
        BufferedReader readerBuffer = null;
        File txtFile = null;
        try {
        txtFile = new File("src//Users//" + tblTrigger.getUsername() + "//Trigger.txt" );  
         readerFile = new FileReader(txtFile);
            readerBuffer = new BufferedReader(readerFile);
            trigHolder.clear();
            while ((LinebyLine = readerBuffer.readLine()) != null) {

                String[] eachLine = LinebyLine.split("%");
                ClassTrigger trig = new ClassTrigger(eachLine[0],eachLine[0],eachLine[1],eachLine[2],
                        Double.parseDouble(eachLine[3]),eachLine[4],false);
                trigHolder.add(trig);
            }
            trigHolder.UpdateTableValues(tblTrigger);
        } catch (FileNotFoundException fileEx) {
            System.out.println( fileEx.getMessage());
        } catch (IOException ioe) {  
            System.out.println( ioe.getMessage());
        } finally {
            try {
                readerBuffer.close();readerFile.close();
            } catch (IOException ex) { 
                ex.printStackTrace(); 
            } }
        checkTriggerDate(tblTrigger.getUsername());
    }
    
     void MakeStacksSimilar() {
        transHolder = new TransactionHolder();
        transHolder.clear();
        transHolder = tableTransaction.getTransHolder();
    }
     
     void checkTriggerDate(String Username) {
        tableTransaction = new TableTransaction(Username);
        MakeStacksSimilar();
        this.Username = Username;
        transUpdater = new TransactionUpdater(transHolder, tableTransaction);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        String currentDate = (DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate));
        Calendar cTrigger = Calendar.getInstance();
        Calendar cCurrentDate = Calendar.getInstance();

        for (ClassTrigger triggerholdereach : trigHolder) {
            try {
                cTrigger.setTime(sdf.parse(triggerholdereach.getDate())); 
                cCurrentDate.setTime(sdf.parse(currentDate)); 
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }

            if (cTrigger.getTime().compareTo(cCurrentDate.getTime()) < 0 | cTrigger.getTime().
                    compareTo(cCurrentDate.getTime()) == 0) {
                ClassTransaction newTransaction = new ClassTransaction(Username, triggerholdereach.getDate(),
                        triggerholdereach.getDescription(), (Double) triggerholdereach.getAmount(),
                        triggerholdereach.getTriggerType());
                transHolder.add(newTransaction);
                transUpdater.write();
                tableTransaction.getTransHolder().UpdateTableValues(tableTransaction);
 
                cTrigger.add(Calendar.MONTH, 1); 
                triggerholdereach.setDate(sdf.format(cTrigger.getTime()));
                trigHolder.UpdateTableValues(tblTrigger);
                writeToFile();
            }
        }
    }
     
     public void actionPerformed(ActionEvent e) {
        checkTriggerDate(Username);
    }

}
