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
public class TestAccountCreation1 {

    public static void main(String[] args) {
        AccountAndUserCreation aauc = new AccountAndUserCreation("SushantBasnet", "Home", "sbasnet", "nepal");
        System.out.println(aauc.getAccountType());
    }

}
