/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackage;

import AllClass.Forms.AccountAndUserCreation;

/**
 *
 * @author Basnet
 */
public class TestAccountCreation2 {
    public static void main(String[] args) {
        AccountAndUserCreation aauc = new AccountAndUserCreation("Ram Shyam", "Shared", "ramshyam", "ramss");
        System.out.println( "Account Type : " + aauc.getAccountType());
        System.out.println("Acount Holder Name : " +aauc.getName());
        System.out.println("Password : " +aauc.getPassword());
        System.out.println("Username : " +aauc.getUsername());
        
    }
     
    
}
