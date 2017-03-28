
import java.util.ArrayList;

public class group {
	
	private int ID;
	private String Name;
	private ArrayList<Integer> userIDs = new ArrayList<Integer>();
	private ArrayList<Integer> projects = new ArrayList<Integer>();
	private int Admin;
	
	public int getId(){ return ID;}
	public String getName(){return Name;}
	public ArrayList<Integer> getUserIDs(){return userIDs;}
	public ArrayList<Integer> getProjects(){return projects;}
	
	public void addUsers(int id){
		userIDs.add(id);
		System.out.println("added");
	}
	public void addProject(int projectID){
		projects.add(projectID);
	}
	public void setAdmin(int id){
		Admin=id;
	}

}
