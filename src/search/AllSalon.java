package search;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class AllSalon {
	String id,name, address, phone,businessTime,picture;
	ArrayList<Integer> salonID_List = new ArrayList<Integer>();
	ArrayList<String> name_List = new ArrayList<String>();
	ArrayList<String> address_List = new ArrayList<String>();
	ArrayList<String> phone_List = new ArrayList<String>();
	ArrayList<String> businessTime_List = new ArrayList<String>();
	ArrayList<String> picture_List = new ArrayList<String>();
    String serviceName_array[][] = new String[100][100]; 
	
    public AllSalon(ArrayList<Integer> id,ArrayList<String> name,ArrayList<String> address,
    		ArrayList<String> phone,ArrayList<String> picture,String [][] serviceName) {
    	salonID_List = id;
    	name_List = name;
    	address_List = address;
    	phone_List = phone;
		picture_List = picture;
		serviceName_array=serviceName;
    }
    
    public ArrayList<String> getServiceName(int i) {
    	ArrayList<String> serviceName_List = new ArrayList<String>();
    	for(int j=0;j<serviceName_array[i].length;j++) {
			if(serviceName_array[i][j]!=null)
				serviceName_List.add("\""+serviceName_array[i][j]+"\"");
         }
		LinkedHashSet<String> set = new LinkedHashSet<String>(serviceName_List); //把arraylist內重複的元素刪掉
		ArrayList<String> newServiceList = new ArrayList<String>(set);
    	return newServiceList;
    }
    
	@Override
	public String toString () {
		String ans="[";
		for(int i=0;i<salonID_List.size();i++) {
			ans+="{\"id\":"+salonID_List.get(i)+","+
				"\"name\":"+"\""+name_List.get(i)+"\""+","+
				"\"address\":"+"\""+address_List.get(i)+"\""+","+
				"\"phone\":"+"\""+phone_List.get(i)+"\""+","+
				"\"picture\":"+"\""+picture_List.get(i)+"\""+","+
				"\"service\":"+getServiceName(i)+"},";
		}
		ans = ans.substring(0,ans.length()-1); //拿掉最後一個字元
		ans+="]";
		return ans;
	}
}