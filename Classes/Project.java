
import java.util.ArrayList;

public class Project{
	private int id;
	private String projectName;
	private String remoteRepoUrl;
	private String localRepoUrl;
	private ArrayList<Integer> users; 

	public Project(String _projectName, ArrayList<Integer> userIDs){
		projectName = _projectName;
		users = userIDs;
	}

	public int getId(){
		return id;
	}

	public String projectName(){
		return projectName;
	}

	public String getRemoteRepoUrl(){
		return remoteRepoUrl;
	}

	public String getLocalRepoUrl(){
		return localRepoUrl;
	}

	public ArrayList<Integer> getUsers(){
		return users;
	}

	public void addUsers(ArrayList<Integer> userIDs){
			users.addAll(userIDs);
	}

	public void removeUsers(ArrayList<Integer> userIDs){
			users.removeAll(userIDs);
	}



}