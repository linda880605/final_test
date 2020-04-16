package search;

import java.util.ArrayList;
import com.google.gson.Gson;

public class AllStylist{ 
	int stylistID,salonID;
	String stylistName, job_title,address, picture,salonName;
	ArrayList<Integer> stylistID_List = new ArrayList<Integer>();
	ArrayList<String> stylistName_List = new ArrayList<String>();
	ArrayList<String> job_title_List = new ArrayList<String>();
	ArrayList<String> salonName_List = new ArrayList<String>();
	ArrayList<String> address_List = new ArrayList<String>();
	ArrayList<String> picture_List = new ArrayList<String>();
    String serviceName_array[][] = new String[100][100];
    int min_price_array[][] = new int[100][100];
    int max_price_array[][] = new int[100][100];
    int service_time_array[][] = new int[100][100];
    String description_array[][] = new String[100][100];
	
    public AllStylist(ArrayList<Integer> id,ArrayList<String> name,ArrayList<String> job_title,ArrayList<String> salonName,ArrayList<String> address,ArrayList<String> picture,
    		String [][] serviceName,int [][]  min_price,int [][] max_price,int [][] service_time,String [][] description) {
    	stylistID_List=id;
    	stylistName_List=name;
    	job_title_List=job_title;
    	salonName_List=salonName;
    	address_List=address;
    	picture_List=picture;
    	serviceName_array=serviceName;
    	min_price_array=min_price;
    	max_price_array=max_price;
    	service_time_array=service_time;
    	description_array=description;
    }
    
    public ArrayList<String> getService(int i) {
    	Gson gson=new Gson();
    	ArrayList<String> service_List = new ArrayList<String>();
    	for(int j=0;j<serviceName_array[i].length;j++) {
    		ServiceDetail sv = new ServiceDetail(serviceName_array[i][j],min_price_array[i][j],max_price_array[i][j],service_time_array[i][j],description_array[i][j]);
			String svObject = gson.toJson(sv);
			if(serviceName_array[i][j]!=null) 
				service_List.add(svObject);
    	}
    	return service_List;
    }
    
    @Override
	public String toString () {
		String ans="[";
		for(int i=0;i<stylistID_List.size();i++) {
			ans+="{\"id\":"+stylistID_List.get(i)+","+
				"\"name\":"+"\""+stylistName_List.get(i)+"\""+","+
				"\"job_title\":"+"\""+job_title_List.get(i)+"\""+","+
				"\"salon\":"+"\""+salonName_List.get(i)+"\""+","+
				"\"address\":"+"\""+address_List.get(i)+"\""+","+
				"\"picture\":"+"\""+picture_List.get(i)+"\""+","+
				"\"service\":"+getService(i)+"},";
		}
		ans = ans.substring(0,ans.length()-1); //拿掉最後一個字元
		ans+="]";
		return ans;
	}
}