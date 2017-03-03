import java.lang.ProcessBuilder;
import java.util.Arrays;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader


public class GitJava{

	ProcessBuilder pb = new ProcessBuilder("myshellScript.sh", "myArg1", "myArg2");
	Map<String, String> env = pb.environment();
	env.put("VAR1", "myValue");
	env.remove("OTHERVAR");
	env.put("VAR2", env.get("VAR1") + "suffix");
	pb.directory(new File("myDir"));
	Process p = pb.start();

	public void status(){

	}

	public void add(){

	}

	public void commit(){

	}

	public void remove(){

	}

	public void init(){

	}

	public void pull(){

	}

	public void gitignore(){
		// a .gitignore file can be created and eddited to allow files to be ignored
	}

	//git branch MyBranch: makes new branch MyBranch
	//git checkout MyBranch changes to MyBranch

	//git merge MyBranch: must merge from the destination branch, calling the source branch 

	//git push

	//git remote

	private void runProcess(String directory,String... args){

		String[] result;

		// make processBuilder and set directory
		List perams = Arrays.asList(args);
		ProcessBuilder pb = new ProcessBuilder(perams);
		ProcessBuilder.directory(new File(directory));
		Process process = pb.start();

		//read output
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		int i = 0;
		while((line = br.readLine()) != null){
			result[i] = line;
		}

		//Wait to get exit value
        try {
            int exitValue = process.waitFor();
        } 
        catch (InterruptedException e) {

        }

	}



}