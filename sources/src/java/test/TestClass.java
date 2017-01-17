/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import java.io.*;
import java.net.*;

import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author Fildzzz
 */
public class TestClass {
    
    protected static  ontology.OntologyAccess oa;
    
    public static void main(String args[]){

        oa = new ontology.OntologyAccess();

       // oa.TESTINSERT();

//        System.out.println(oa.acteurs_index());
    //      String queryString ="SELECT ?type \n"+
    //                            "WHERE {\n"+
    //                            "?film :possede ?genre .\n"+
    //                            "?genre :type ?type \n"+
    //                            "}";
    //  
    //        String results = oa.processSPARQLandXSLT(queryString,"genres_index.xsl");
    //        
oa.acteurs_index();
        System.out.println("coco");
        
//        TestController tc = new TestController();
//        try {
//        tc.init();
//        tc.index();
//        }catch(Exception e){ e.printStackTrace(); }
    } 

}
