import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class GitJavaTester{
	public static void main(String[] args) {

		GitJava gj = new GitJava();
		String[] result = new String[0];
		String dir; // directory
		Scanner reader = new Scanner(System.in);
		boolean printResult = true;


		//get working dir
		while(true){
			System.out.print("Enter the gitRepo Directory\n>");
			dir = reader.nextLine();
			if(new File(dir).exists()){
				System.out.println("Directory Found\n");
				break;
			}
			else{
				System.out.println("Directory not found");
			}
		}
		


		while(true){
			printResult = true;
			System.out.print(">");
			
			String input = reader.nextLine();
			String[] in = input.split(" ");

			if (in[0].equals("add")){
				ArrayList<String> files = new ArrayList<String>();
				for(int i = 1; i<in.length; i++){
					files.add(in[i]);
				}
				result = gj.add(dir,files.toArray(new String[0]));
			}
			else if(in[0].equals("status")){
				result = gj.status(dir);	
			}
			else if(in[0].equals("commit")){
				result = gj.commit(dir,"test message");
			}
			else if(in[0].equals("init")){
				result = gj.init(dir);
			}
			else if(in[0].equals("remove")){
				ArrayList<String> files = new ArrayList<String>();
				for(int i = 1; i<in.length;i++){
					files.add(in[i]);
				}
				result = gj.remove(dir,files.toArray(new String[0]));
			}
			else if(in[0].equals("pull")){
				result = gj.pull(dir,"origin","branch1");
			}
			else if(in[0].equals("push")){
				result = gj.push(dir,"c1528019@lapis.cs.cf.ac.uk:~/Documents/test_git","b2");	
			}
			else if(in[0].equals("remote")){
				ArrayList<String> perams = new ArrayList<String>();
				for(int i = 1; i<in.length; i++){
					perams.add(in[i]);
				}
				result = gj.remote(dir,perams.toArray(new String[0]));	
			}
			else if(in[0].equals("clone")){
				ArrayList<String> perams = new ArrayList<String>();
				for(int i = 1; i<in.length; i++){
					perams.add(in[i]);
				}
				result = gj.remote(dir,perams.toArray(new String[0]));
			}
			else if(in[0].equals("branch")){
				ArrayList<String> perams = new ArrayList<String>();
				for(int i = 1; i<in.length; i++){
					perams.add(in[i]);
				}
				result = gj.branch(dir,perams.toArray(new String[0]));
			}
			else if(in[0].equals("checkout")){
				ArrayList<String> perams = new ArrayList<String>();
				for(int i = 1; i<in.length; i++){
					perams.add(in[i]);
				}
				result = gj.checkout(dir,perams.toArray(new String[0]));
			}
			else if(in[0].equals("log")){
				ArrayList<String> perams = new ArrayList<String>();
				for(int i = 1; i<in.length; i++){
					perams.add(in[i]);
				}
				result = gj.log(dir,perams.toArray(new String[0]));
			}
			else if(in[0].equals("exit")){
				break;
			}
			else{
				System.out.println("command " + in[0] + " not recognised");
				printResult = false;
			}

			if(printResult){
				printResult(result);
			}
			
		}
	}

	private static void printResult(String[] res){
		if (res.length > 0)
		{
			System.out.println("\nResult:\n");
			for(int i = 0; i<res.length; i++){
				System.out.println("\t" + res[i]);
			}
		}
	}

}
