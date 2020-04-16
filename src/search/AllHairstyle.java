package search;

import java.util.ArrayList;

public class AllHairstyle{ 
	ArrayList<Integer> hairstyleID_List = new ArrayList<Integer>();
	ArrayList<String> picture_List = new ArrayList<String>();
	ArrayList<String> stylist_List = new ArrayList<String>();
	ArrayList<String> job_title_List = new ArrayList<String>();
	ArrayList<String> description_List = new ArrayList<String>();
	ArrayList<String> hashtag_List = new ArrayList<String>();
	
    public AllHairstyle(ArrayList<Integer> id,ArrayList<String> picture,ArrayList<String> stylist,ArrayList<String> stylist_job_title,ArrayList<String> description,ArrayList<String> hashtag) {
    	hairstyleID_List = id;
    	picture_List = picture;
    	stylist_List = stylist;
    	job_title_List = stylist_job_title;
    	description_List = description;
    	hashtag_List = hashtag;
    }
    
    @Override
	public String toString () {
		String ans="[";
		for(int i=0;i<hairstyleID_List.size();i++) {
			ans+="{\"id\":"+hairstyleID_List.get(i)+","+
				"\"picture\":"+"\""+picture_List.get(i)+"\""+","+
				"\"stylist\":"+"\""+stylist_List.get(i)+"\""+","+
				"\"stylist_job_title\":"+"\""+job_title_List.get(i)+"\""+","+
				"\"description\":"+"\""+description_List.get(i)+"\""+","+
				"\"hashtag\":"+"\""+hashtag_List.get(i)+"\""+"},";
		}
		ans = ans.substring(0,ans.length()-1); //拿掉最後一個字元
		ans+="]";
		return ans;
	}
}