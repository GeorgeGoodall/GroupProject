import java.util.Scanner;

public class GitJavaTester{
	public static void main(String[] args) {

		GitJava gj = new GitJava();
		String[] result;





		while(true){
			System.out.print(">");
			Scanner reader = new Scanner(System.in);
			String in = reader.nextLine();

			if (in.equals("add")){
				String[] files = new String[]{"test1.txt","test2.txt","test3.txt"};
				result = gj.add("/home/george/Desktop/testDirectory/",files);
				printResult(result);
			}
			else if(in.equals("status")){
				result = gj.status("/home/george/Desktop/testDirectory/");
				printResult(result);
			}
			else if(in.equals("commit")){
				result = gj.commit("/home/george/Desktop/testDirectory/","test message");
				printResult(result);
			}
			else if(in.equals("init")){
				result = gj.init("/home/george/Desktop/testDirectory/");
				printResult(result);
			}
			else if(in.equals("pull")){
				
			}
			else{
				System.out.println("Input not recognised");
			}
		}
	}

	private static void printResult(String[] res){
		if (res.length > 0)
		{
			System.out.println("\nResult\n");
			for(int i = 0; i<res.length; i++){
				System.out.println("\t" + res[i]);
			}
		}
	}

}