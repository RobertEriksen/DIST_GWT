/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtu.client.ui;

//import distriserver.entity.UserI;
import java.rmi.Naming;
import java.util.Scanner;

import dtu.client.ui.RMIServerI;

/**
 *
 * @author FrederikSwag
 */
public class SimpelKlient {

    public static void main(String[] args) throws Exception {

        Scanner reader = new Scanner(System.in);
        System.out.println("Indtast brugernavn: ");
        //String user = reader.nextLine();
        String user = "s123157";
        System.out.println("Indtast kodeord: ");
        String pass = "clancbs";
        //String pass = reader.nextLine();

        System.out.println("Der forsøges at logges ind");

        //Opret forbindelse til server
//        System.setProperty("java.rmi.server.hostname", "192.168.56.1");
        RMIServerI server = (RMIServerI) Naming.lookup("rmi://10.16.227.109/RMIServerImpl");
        if (server.login(user, pass)) {
            System.out.println("Login sucess");
        } else {
            System.out.println("Login fail");
            System.exit(0);
        }
        
    }

}
