/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TestPackage;

import AllClass.Forms.ClassTrigger;
import AllClass.Forms.TriggerHolder;

/**
 *
 * @author Basnet
 */
public class TestTriggerHolder {
     public static void main(String[] args) {
        TriggerHolder trigHolder = new TriggerHolder();
        ClassTrigger trigger = new ClassTrigger("a","2017/01/09", "Insurace Payment", 5000, "Expence");
        trigHolder.add(trigger);
        System.out.println("Added to Stack");
    
  }
}
 
