import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.lang.Thread;

public class GitJava{

	/*
		***notes***
		need to add the ability to generate ssh keys
	*/

	public GitJava(){

	}

	public String[] status(String directory){
		return runProcess(directory, "gitStatus.sh", new String[0]);
	}

	public String[] add(String directory, String[] filesToAdd){
		return runProcess(directory, "gitadd.sh", filesToAdd);
	}

	public String[] commit(String directory, String message){
		return runProcess(directory, "gitcommit.sh", new String[]{message});
	}

	public String[] remove(String directory, String[] filesToRemove){
		return runProcess(directory, "gitRemove.sh", filesToRemove);
	}

	public String[] init(String directory){
		return runProcess(directory, "gitInit.sh", new String[0]);
	}

	public String[] pull(String directory, String repo, String branchname){
		return runProcess(directory, "gitPull.sh", new String[]{repo,branchname});
	}

	public String[] push(String directory, String repo, String branchname){
		// git push [remote] [branch] pushes the branch to the remote,
		return runProcess(directory, "gitPush.sh", new String[]{repo,branchname});
	}

	public String[] gitclone(String directory, String[] args){
		return runProcess(directory, "gitClone.sh", args);
	}

	public String[] remote(String directory, String[] args){
		return runProcess(directory, "gitRemote.sh", args);
	}	

	public String[] branch(String directory, String[] args){
		return runProcess(directory, "gitBranch.sh", args);
	}

	public String[] checkout(String directory, String[] args){
		return runProcess(directory, "gitCheckout.sh", args);
	}


	public String[] log(String directory, String[] args){
		return runProcess(directory, "gitlog.sh", args);
	}

	public String[] merge(String directory, String[] args){
		return runProcess(directory, "gitMerge.sh", args);
	}

	// a .gitignore file can be created and eddited to allow files to be ignored by git
	public String[] getGitIgnore(String directory){
		ArrayList<String> output = new ArrayList<String>();
		try{
			FileReader fr = new FileReader(directory + "/.gitignore");
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while((line = br.readLine()) != null){
				output.add(line);
			}
			br.close();
		}
		catch(Exception e){
			System.out.println("error : " + e);
		}
		return output.toArray(new String[0]);
	}

	public void setGitIgnore(String directory, String[] lines){
		try{
    		PrintWriter writer = new PrintWriter(directory + "/.gitignore", "UTF-8");
    		for (int i = 0; i<lines.length; i++){
    			writer.println(lines[i]);
    		}
    		writer.close();
		} catch (IOException e) {
   			System.out.println("Error setting gitignore : " + e);
		}
	}

	private String[] runProcess(String directory,String operation, String[] args){

		ArrayList<String> resultlist = new ArrayList<String>();

		// make processBuilder and set directory
		List<String> perams = new ArrayList<String>();
		perams.add(0,System.getProperty("user.dir") + "/" + operation);
		perams.add(1,directory);
		
		for(int i = 0; i < args.length; i++){
			perams.add(args[i]);
		}
		ProcessBuilder pb = new ProcessBuilder("/bin/bash");
		pb.command(perams);
		pb.directory(new File(System.getProperty("user.dir")));
		
		try{
			Process process = pb.start();
			//create needed objects to read output
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			int c;

			try{
				while((line = br.readLine()) != null){
					resultlist.add(line);;
				}
			}
			catch(Exception e){
				System.out.println("Error reading bash script output: " + e);
			}
			//Wait to get exit value
        	try {
            	int exitValue = process.waitFor();
	        } 
	        catch (InterruptedException e) {

	        }
	        
		}
		catch(Exception e){System.out.println("Error starting processBuilder: " + e);}
		
		return resultlist.toArray(new String[0]);
	}



}
