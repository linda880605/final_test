package search;
import java.util.ArrayList;
import com.google.gson.Gson;

public class Salon{
	String name,address,phone,picture,businessTime;
	ArrayList<Integer> stylistID_List = new ArrayList<Integer>();
	ArrayList<String> stylistName_List = new ArrayList<String>();
	ArrayList<String> picture_List = new ArrayList<String>();
    int workID_array[][] = new int[100][100]; 
    String workPicture_array[][] = new String[100][100];
	
    public Salon(String name,String address,String phone,String picture,String businessTime,
    		ArrayList<Integer> stylistID_List,ArrayList<String> stylistName_List,ArrayList<String> picture_List,
    		int workID_array[][],String workPicture_array[][]) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.picture = picture;
		this.businessTime = businessTime;
		this.stylistID_List = stylistID_List;
		this.stylistName_List = stylistName_List;
		this.picture_List = picture_List;
		this.workID_array = workID_array;
		this.workPicture_array = workPicture_array;
    }
    
    @Override
	public String toString () {
    	
    	Gson gson=new Gson();
		String ans="{\"name\":\""+name+"\","
		+ "\"address\":\""+address+"\","
		+"\"phone\":\""+phone+"\","
		+"\"businessTime\":\""+businessTime+"\","
		+"\"picture\":\""+picture+"\","
		+"\"stylist_info\":[";
    	
	    ArrayList<String> work_List = new ArrayList<String>();
		for(int i=0;i<stylistID_List.size();i++) {
			ans+="{\"id\":"+stylistID_List.get(i)+","+
				"\"name\":"+"\""+stylistName_List.get(i)+"\""+","+
				"\"picture\":"+"\""+picture_List.get(i)+"\""+","+
				"\"works\":";
			for(int j=0;j<workID_array[i].length;j++) {
				Work work = new Work(workID_array[i][j],workPicture_array[i][j]);
				String svObject = gson.toJson(work);
				if(workID_array[i][j]!=0) 
					work_List.add(svObject);
	         }
			ans+=work_List+"},";
		}
		ans = ans.substring(0,ans.length()-1); //拿掉最後一個字元
		ans +="]}";
		return ans;
	}
}