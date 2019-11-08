/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackage;

import AllClass.Forms.ClassTransaction;
import AllClass.Forms.TransactionHolder;


/**
 *
 * @author Basnet
 */
public class TestTransactionHolder1 {
      public static void main(String[] args) {
        TransactionHolder transHolder = new TransactionHolder();
        ClassTransaction t1 = new ClassTransaction("a","2017/01/09", "Rent Payment", 500000, "Expence");
        transHolder.add(t1);
        System.out.println("Added to Stack");
    }
    
}
