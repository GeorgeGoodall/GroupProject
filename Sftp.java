

import com.jcraft.jsch.*;
import java.io.*;
import java.util.*;
public class Sftp {
 String sFTPHOST;
 int sFTPPORT;
 String sFTPUSER;
 String sFTPPASS;
 Session session;


public Sftp(String sFTP_HOST, int sFTP_PORT, String sFTP_USER, String sFTP_PASS){
    sFTPHOST = sFTP_HOST;
    sFTPPORT = sFTP_PORT;
    sFTPUSER = sFTP_USER;
    sFTPPASS = sFTP_PASS;
    try {

                JSch jsch = new JSch();
                session = jsch.getSession(sFTPUSER, sFTPHOST, sFTPPORT);
                session.setPassword(sFTPPASS);
                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);
        } catch (Exception ex) {
             System.out.println("Exception found while tranfer the response.");
        }
}

public void send(String sFTPWORKINGDIR, String fileName){
    try{
    session.connect();
    Channel channel = session.openChannel("sftp");
    channel.connect();
    channelSftp = (ChannelSftp) channel;
    channelSftp.cd(sFTPWORKINGDIR);
    File f = new File(fileName);
    channelSftp.put(new FileInputStream(f), f.getName());
    System.out.println("File transfered successfully to host.");}
    catch (Exception ex) {
             System.out.println("Exception found while tranfer the response." + ex);
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

}
