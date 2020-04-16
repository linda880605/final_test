package search;
import java.util.ArrayList;
import com.google.gson.Gson;

public class Stylist{

	int salonID;
	String name, job_title,salon, address,picture,description;
	ArrayList<String> serviceName_List = new ArrayList<String>();
	ArrayList<Integer> min_price_List = new ArrayList<Integer>();
	ArrayList<Integer> max_price_List = new ArrayList<Integer>();
	ArrayList<Integer> service_time_List = new ArrayList<Integer>();
	ArrayList<String> description_List = new ArrayList<String>();
	ArrayList<Integer> workID_List = new ArrayList<Integer>();
	ArrayList<String> workPicture_List = new ArrayList<String>();
	
    public Stylist(String name,String job_title,String salon,String address,String picture,
    		ArrayList<String> serviceName_List,ArrayList<Integer> min_price_List,ArrayList<Integer> max_price_List,ArrayList<Integer> service_time_List,ArrayList<String> description_List,
    		ArrayList<Integer> workID_List,ArrayList<String> workPicture_List) {
		this.name = name;
		this.job_title = job_title;
    	this.salon = salon;
		this.address = address;
		this.picture = picture;
		this.serviceName_List = serviceName_List;
    	this.min_price_List = min_price_List;
		this.max_price_List = max_price_List;
    	this.service_time_List = service_time_List;
		this.description_List = description_List;
		this.workID_List = workID_List;
		this.workPicture_List = workPicture_List;
    }
    
    
    @Override
	public String toString () {
    	Gson gson=new Gson();
		String ans="{\"name\":\""+name+"\","
		+ "\"job_title\":\""+job_title+"\","
		+"\"salon\":\""+salon+"\","
		+"\"address\":\""+address+"\","
		+"\"picture\":\""+picture+"\",";

    	ArrayList<String> service_List = new ArrayList<String>();
		for(int i=0;i<serviceName_List.size();i++) {
			ServiceDetail sv = new ServiceDetail(serviceName_List.get(i),min_price_List.get(i),max_price_List.get(i),service_time_List.get(i),description_List.get(i));
			String svObject = gson.toJson(sv);
			if(svObject.equals("{}")==false) 
				service_List.add(svObject);
         }
		ans +="\"service\":"+service_List+",";
		
    	ArrayList<String> work_List = new ArrayList<String>();
		for(int i=0;i<workID_List.size();i++) {
			Work work = new Work(workID_List.get(i),workPicture_List.get(i));
			String svObject = gson.toJson(work);
			if(svObject.equals("{}")==false) 
				work_List.add(svObject);
         }
		ans +="\"works\":"+work_List+"}";
		return ans;
	}
}