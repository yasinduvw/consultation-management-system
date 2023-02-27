/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.consultationmanagement;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author MSI
 */

//To run a single test case, comment other 4

public class WestminsterSkinConsultationManagerTest {
   
    /**
     * Test of add method, of class WestminsterSkinConsultationManager.
     */
    @Test
    public void testAdd() {
        
      InputStream stdin = System.in;
      System.setIn(new ByteArrayInputStream("Alan\nHarper\n2022/09/12\n2851261\nd920\nSurgicalDermatology".getBytes()));

      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(byteArrayOutputStream);
      PrintStream stdout = System.out;
      System.setOut(ps);

      WestminsterSkinConsultationManager m = new WestminsterSkinConsultationManager();
      m.add();

      System.setIn(stdin);
      System.setOut(stdout);

      String outputText = byteArrayOutputStream.toString();
      String key = "specialisation: ";
      String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
      assertEquals("Record Added Successfully!  ", output);
    }

    
    /**
     * Test of delete method, of class WestminsterSkinConsultationManager.
     */
//    @Test
//    public void testDelete() {
//      InputStream stdin = System.in;
//      System.setIn(new ByteArrayInputStream("Alan\nHarper\n2022/09/12\n2851261\nd920\nSurgicalDermatology\nd920".getBytes()));
//
//      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//      PrintStream ps = new PrintStream(byteArrayOutputStream);
//      PrintStream stdout = System.out;
//      System.setOut(ps);
//
//      WestminsterSkinConsultationManager m = new WestminsterSkinConsultationManager();
//      m.add();
//      m.delete();
//
//      System.setIn(stdin);
//      System.setOut(stdout);
//
//      String outputText = byteArrayOutputStream.toString();
//      String key = "Doctor's specialization - SurgicalDermatology";
//      String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
//      assertEquals("Record successfully deleted!\nNumber of doctors remaining: 0", output);
//    }

    
    /**
     * Test of print method, of class WestminsterSkinConsultationManager.
     */
//    @Test
//    public void testPrint() {
//      InputStream stdin = System.in;
//      String inputString = "Alan\nHarper\n2022/09/12\n2851261\nd920\nSurgicalDermatology\n"
//              + "Charlie\nArmstrong\n2000/09/01\n0765232717\nd856\nCosmeticDermatology\n";
//      System.setIn(new ByteArrayInputStream(inputString.getBytes()));
//
//      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//      PrintStream ps = new PrintStream(byteArrayOutputStream);
//      PrintStream stdout = System.out;
//      System.setOut(ps);
//
//      WestminsterSkinConsultationManager m = new WestminsterSkinConsultationManager();
//      m.add();
//      m.add();
//      m.print();
//
//      System.setIn(stdin);
//      System.setOut(stdout);
//
//      String outputText = byteArrayOutputStream.toString();
//      String key = "Doctor's specialization - SurgicalDermatology"; //the 1st entry should go to the final stage
//      String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
//      assertEquals("", output);
//    }

    
    /**
     * Test of save method, of class WestminsterSkinConsultationManager.
     */
//    @Test
//    public void testSave() {
//      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//      PrintStream ps = new PrintStream(byteArrayOutputStream);
//      PrintStream stdout = System.out;
//      System.setOut(ps);
//
//      WestminsterSkinConsultationManager m = new WestminsterSkinConsultationManager();
//      m.save();
//
//      System.setOut(stdout);
//
//      String output = byteArrayOutputStream.toString().trim();
//      assertEquals("Save Successful!", output);
//    }

    
    /**
     * Test of load method, of class WestminsterSkinConsultationManager.
     */
//    @Test
//    public void testLoad() {
//      InputStream stdin = System.in;
//      String inputString = "Alan\nHarper\n2022/09/12\n2851261\nd920\nSurgicalDermatology\n"
//              + "Charlie\nArmstrong\n2000/09/01\n0765232717\nd856\nCosmeticDermatology\n";
//      System.setIn(new ByteArrayInputStream(inputString.getBytes()));
//
//      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//      PrintStream ps = new PrintStream(byteArrayOutputStream);
//      PrintStream stdout = System.out;
//      System.setOut(ps);
//
//      WestminsterSkinConsultationManager m = new WestminsterSkinConsultationManager();
//      m.add();
//      m.add();
//      m.save();
//      m.load();
//      
//      System.setIn(stdin);
//      System.setOut(stdout);
//
//      String outputText = byteArrayOutputStream.toString();
//      String key = "Save Successful!"; 
//      String output = outputText.substring(outputText.indexOf(key) + key.length()).trim();
//      assertEquals("SaveFile found. Added 2 record(s)!", output);
//    }
    
}

/**
 * code references 
 * https://www.logicbig.com/how-to/junit/java-test-user-command-line-input.html
 */