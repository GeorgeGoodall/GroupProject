import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class GitJavaTester{
	public static void main(String[] args) {

		GitJava gj = new GitJava();
		String[] result;
		String dir; // directory
		Scanner reader = new Scanner(System.in);


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
			System.out.print(">");
			
			String input = reader.nextLine();
			String[] in = input.split(" ");

			if (in[0].equals("add")){
				ArrayList<String> files = new ArrayList<String>();
				for(int i = 1; i<in.length; i++){
					files.add(in[i]);
				}
				result = gj.add(dir,files.toArray(new String[0]));
				printResult(result);
			}
			else if(in[0].equals("status")){
				result = gj.status(dir);
				printResult(result);
			}
			else if(in[0].equals("commit")){
				result = gj.commit(dir,"test message");
				printResult(result);
			}
			else if(in[0].equals("init")){
				result = gj.init(dir);
				printResult(result);
			}
			else if(in[0].equals("remove")){
				ArrayList<String> files = new ArrayList<String>();
				for(int i = 1; i<in.length;i++){
					files.add(in[i]);
				}
				result = gj.remove(dir,files.toArray(new String[0]));
				printResult(result);
			}
			else if(in[0].equals("pull")){
				
			}
			else if(in[0].equals("exit")){
				break;
			}
			else{
				System.out.println("command " + in[0] + " not recognised");
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