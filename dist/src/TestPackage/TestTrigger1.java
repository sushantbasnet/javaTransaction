/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackage;

import AllClass.Forms.ClassTrigger;

/**
 *
 * @author Basnet
 */
public class TestTrigger1 {
    public static void main(String[] args) {
        ClassTrigger tri = new ClassTrigger("b", "2017/02/09", "Salary", 10000, "Income");
        System.out.println("id   = " + tri.getTriggerID() );
        System.out.println("date   = " + tri.getDate());
        System.out.println("Description   = " + tri.getDescription());
        System.out.println("Amount  = " + tri.getAmount());
        System.out.println("Transaction Type   = " + tri.getTriggerType());
        System.out.println("username  = " + tri.getUsername());
        
    }
}
