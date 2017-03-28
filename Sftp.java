import com.jcraft.jsch.*;
import java.io.*;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class Sftp {

public  void send() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in the name of the server you wish to access: ");
        String sFTPHOST = sc.nextLine();
        int sFTPPORT = 22;
        System.out.println("Please type in your username: ");
        String sFTPUSER = sc.nextLine();
        System.out.println("Please type in your password: ");
        String sFTPPASS = sc.nextLine();
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
    public void uploadMultiple() throws SftpException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in the name of the server you wish to access: ");
        String sFTPHOST = sc.nextLine();
        int sFTPPORT = 22;
        System.out.println("Please type in your username: ");
        String sFTPUSER = sc.nextLine();
        System.out.println("Please type in your password: ");
        String sFTPPASS = sc.nextLine();
        System.out.println("Please type in directory you wish to send to : ");
        String sFTPWORKINGDIR = sc.nextLine();
        System.out.println("Please type in directory you wish to send: ");
        String nameOfFolder = sc.nextLine();
        File localFile = new File(nameOfFolder);
        if (localFile.exists()) {
            Collection<File> fileList = FileUtils.listFiles(localFile, TrueFileFilter.TRUE, null);
        Session session = null;
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
            channelSftp.mkdir(sFTPWORKINGDIR);
}
        catch (Exception e) {
   // ignore if folder exist
  }
    for (File file : fileList) {

    channelSftp.put(file.getAbsolutePath(), sFTPWORKINGDIR);
    System.out.println("upload " + file.getName());
   }

        channelSftp.exit();
        System.out.println("sftp Channel exited.");
        channel.disconnect();
        System.out.println("Channel disconnected.");
        session.disconnect();
        System.out.println("Host Session disconnected.");
    }
    }

public void download(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in the name of the server you wish to access: ");
        String sFTPHOST = sc.nextLine();
        int sFTPPORT = 22;
        System.out.println("Please type in your username: ");
        String sFTPUSER = sc.nextLine();
        System.out.println("Please type in your password: ");
        String sFTPPASS = sc.nextLine();
        System.out.println("Please type in directory you wish to get the file from: ");
        String sFTPWORKINGDIR = sc.nextLine();
        System.out.println("Please type in the name of what you wish to call the file, remember file must end in file type for example jpg: ");
        String directoryToDownloadFrom = sc.nextLine();
        Session session = null;
        System.out.println("Please type in name of file you wish to download: ");
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
            channelSftp.get(fileName, directoryToDownloadFrom);
            System.out.println("File downloaded to directory.");

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
public void compare(){
    ArrayList<String> direc = new ArrayList<String>();
    File dir = new File(".");
    File[] filesList = dir.listFiles();
    for (File file : filesList) {
        if (file.isFile()) {
        direc.add(file.getName());
    }
}
    Vector serverDirec =  new Vector();
    Scanner sc = new Scanner(System.in);
    System.out.println("Please type in the name of the server you wish to access: ");
    String sFTPHOST = sc.nextLine();
    int sFTPPORT = 22;
    System.out.println("Please type in your username: ");
    String sFTPUSER = sc.nextLine();
    System.out.println("Please type in your password: ");
    String sFTPPASS = sc.nextLine();
    System.out.println("Please type in directory you wish compare with: ");
    String sFTPWORKINGDIR = sc.nextLine();
    Session session = null;
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
            //channelSftp.cd(sFTPWORKINGDIR);
            serverDirec = channelSftp.ls(sFTPWORKINGDIR);
        }
        catch (Exception ex) {
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
        System.out.println(serverDirec);

}
public  void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in the name of the server you wish to access: ");
        String sFTPHOST = sc.nextLine();
        int sFTPPORT = 22;
        System.out.println("Please type in your username: ");
        String sFTPUSER = sc.nextLine();
        System.out.println("Please type in your password: ");
        String sFTPPASS = sc.nextLine();
        System.out.println("Please type in directory you wish to delete the file: ");
        String sFTPWORKINGDIR = sc.nextLine();
        Session session = null;
        System.out.println("Please type in name of file you wish to delete: ");
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
            channelSftp.rm(fileName);
            System.out.println("File deleted successfully from host.");

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


    public static void main(String[] args) throws SftpException {
        Sftp tester = new Sftp();
        //tester.send();
        //tester.download();
        //tester.delete();
        //tester.compare();
        tester.uploadMultiple();
    }
        }