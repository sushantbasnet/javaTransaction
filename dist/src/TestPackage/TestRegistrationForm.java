/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackage;

import AllClass.Forms.FormAccountRegistration;
import java.util.LinkedList;

/**
 *
 * @author Basnet
 */
public class TestRegistrationForm {
    public static void main(String[] args) {
        FormAccountRegistration formRegister = new FormAccountRegistration();
        LinkedList<String> HistoryuserNames = new LinkedList();
        HistoryuserNames = formRegister.namesusername();
        for (String username : HistoryuserNames) {
            System.out.println(username);
        }
    }

    }
    

