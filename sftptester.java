

import com.jcraft.jsch.*;
import java.io.*;
import java.util.*;

public class sftptester{
        public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in directory you wish to place file: ");
        String sFTPWORKINGDIR = sc.nextLine();
        System.out.println("Please type in name of file you wish to send: ");
        String fileName = sc.nextLine();
        System.out.println("preparing the host information for sftp.");

        Sftp sftp = new Sftp("websites.cs.cf.ac.uk",22,"c1526495","Sharky551");

        sftp.send(sFTPWORKINGDIR,fileName);

        }

}
