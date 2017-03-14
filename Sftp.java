import com.jcraft.jsch.*;
import java.io.*;
import java.util.*;
public class Sftp {

public  void send() {
        String sFTPHOST = "websites.cs.cf.ac.uk";
        int sFTPPORT = 22;
        String sFTPUSER = "c1526495";
        String sFTPPASS = "Sharky551";
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in directory you wish to place file: ");
        String sFTPWORKINGDIR = sc.nextLine();
        Session session = null;
        System.out.println("Please type in name of file you wish to send: ");
        String fileName = sc.nextLine();
        Channel channel = null;
        ChannelSftp channelSftp = null;
        System.out.println("preparing the host information for sftp.");
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(sFTPUSER, sFTPHOST, sFTPPORT);
            session.setPassword(sFTPPASS);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            System.out.println("Host connected.");
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("sftp channel opened and connected.");
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(sFTPWORKINGDIR);
            File f = new File(fileName);
            channelSftp.put(new FileInputStream(f), f.getName());
            System.out.println("File transfered successfully to host.");

        } catch (Exception ex) {
             System.out.println("Exception found while tranfer the response.");
        }
        finally{

            channelSftp.exit();
            System.out.println("sftp Channel exited.");
            channel.disconnect();
            System.out.println("Channel disconnected.");
            session.disconnect();
            System.out.println("Host Session disconnected.");
        }
    }
    public static void main(String[] args) {
        Sftp tester = new Sftp();
        tester.send();
    }
        }