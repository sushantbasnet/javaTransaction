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
public class TestTransactionHolder2 {

    public static void main(String[] args) {
        ClassTransaction t1 = new ClassTransaction("a", "2017/01/09", "Rent Payment", 500000, "Expence");
        System.out.println("id   = " + t1.getTransactionID());
        System.out.println("date   = " + t1.getDate());
        System.out.println("Description   = " + t1.getDescription());
        System.out.println("Amount  = " + t1.getAmount());
        System.out.println("Transaction Type   = " + t1.getTransactionType());
        System.out.println("username  = " + t1.getUsername());

    }
}
