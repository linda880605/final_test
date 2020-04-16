package search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class mySql {
	private Connection con=null;
	private Statement stat=null;
	private ResultSet rs=null;
	private PreparedStatement pst=null;
	
	public mySql() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //register driver
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/searchair?characterEncoding=utf-8", "root", "29118310");
			System.out.println("===連線資料庫成功 ! ===");
		}catch(ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:"+e.toString());
		} catch (SQLException e) {
			System.out.println("SQLException:"+e.toString());
		}
	}

	public void searchSalon() {
		System.out.println("輸出所有店家");
		int count=0; //記錄店家
		int salonID,stylistID;
		ArrayList<Integer> salonID_List = new ArrayList<Integer>();
		ArrayList<String> name_List = new ArrayList<String>();
		ArrayList<String> address_List = new ArrayList<String>();
		ArrayList<String> phone_List = new ArrayList<String>();
		ArrayList<String> picture_List = new ArrayList<String>();
        String serviceName_array[][] = new String[999][999]; 
		try {
			stat=con.createStatement();
			rs=stat.executeQuery("select * from salon");
			while(rs.next()) {
				salonID = rs.getInt("id");
				salonID_List.add(salonID);
				name_List.add(rs.getString("name"));
				address_List.add(rs.getString("address"));
				phone_List.add(rs.getString("phone"));
				picture_List.add(rs.getString("picture"));
				
				Statement ST=null;
				ResultSet RS = null;
				ST=con.createStatement();
				RS=ST.executeQuery("select id from stylist where salon="+salonID);
				while(RS.next()) { //搜尋某店家裡的所有設計師
					stylistID=RS.getInt("id");

			        Statement stt=null;
					ResultSet rst = null;
					stt=con.createStatement();
					rst=stt.executeQuery("select name from service where stylist="+stylistID);
					int flag=0;
					while(rst.next()) //搜尋某設計師有提供的服務
						serviceName_array[count][flag++]=rst.getString("name"); //count 存在那個店家
				}
				count++;
			}
			AllSalon salon = new AllSalon(salonID_List,name_List,address_List,phone_List,picture_List,serviceName_array);
			System.out.println(salon); //======這裡輸出JSON======
		}catch(SQLException e) {
			System.out.println("select table SQLException:"+e.toString());
		}
		finally {
			Close();
		}		
	}
	
	public void searchStylist() { 
		System.out.println("輸出所有設計師");
		int count=0; //記錄設計師
		int stylistID,salonID;
		ArrayList<Integer> stylistID_List = new ArrayList<Integer>();
		ArrayList<String> stylistName_List = new ArrayList<String>();
		ArrayList<String> job_title_List = new ArrayList<String>();
		ArrayList<String> salonName_List = new ArrayList<String>();
		ArrayList<String> address_List = new ArrayList<String>();
		ArrayList<String> picture_List = new ArrayList<String>();
        String serviceName_array[][] = new String[999][999];
        int min_price_array[][] = new int[999][999];
        int max_price_array[][] = new int[999][999];
        int service_time_array[][] = new int[999][999];
        String description_array[][] = new String[999][999];
		try {
			stat=con.createStatement();
			rs=stat.executeQuery("select * from stylist");
			while(rs.next()) {
				salonID = rs.getInt("salon");
				stylistID = rs.getInt("id");
				stylistID_List.add(stylistID);
				stylistName_List.add(rs.getString("name"));
				job_title_List.add(rs.getString("job_title"));
				picture_List.add(rs.getString("picture"));
				
				Statement ST=null;
				ResultSet RS = null;
				ST=con.createStatement();
				RS=ST.executeQuery("select * from salon where id="+salonID);
				if(RS.next()) {
					salonName_List.add(RS.getString("name"));
					address_List.add(RS.getString("address"));
				}

				Statement stt=null;
				ResultSet rst = null;
				stt=con.createStatement();
				rst=stt.executeQuery("select * from service where stylist="+stylistID);
				int flag=0;
				while(rst.next()) { //搜尋設計師有提供的服務
			        serviceName_array[count][flag] = rst.getString("name");
			        min_price_array[count][flag] = rst.getInt("min_price");
			        max_price_array[count][flag] = rst.getInt("max_price");
			        service_time_array[count][flag] = rst.getInt("service_time");
			        description_array[count][flag] = rst.getString("description");
			        flag++;
				}
				count++;
			}
			AllStylist stylist = new AllStylist(stylistID_List,stylistName_List,job_title_List,salonName_List,address_List,picture_List,serviceName_array,min_price_array,max_price_array,service_time_array,description_array);
			System.out.println(stylist); //======這裡輸出JSON======
		}catch(SQLException e) {
			System.out.println("select table SQLException:"+e.toString());
		}
		finally {
			Close();
		}
	}

	public void searchHairstyle() { 
		System.out.println("輸出所有髮型");
		int stylistID;
		ArrayList<Integer> hairstyleID_List = new ArrayList<Integer>();
		ArrayList<String> picture_List = new ArrayList<String>();
		ArrayList<String> stylist_List = new ArrayList<String>();
		ArrayList<String> job_title_List = new ArrayList<String>();
		ArrayList<String> description_List = new ArrayList<String>();
		ArrayList<String> hashtag_List = new ArrayList<String>();
		try {
			stat=con.createStatement();
			rs=stat.executeQuery("select * from stylist_works");
			
			while(rs.next()) {
				stylistID = rs.getInt("stylist");
				hairstyleID_List.add(rs.getInt("id"));
				picture_List.add(rs.getString("picture"));
				description_List.add(rs.getString("description"));
				hashtag_List.add(rs.getString("hashtag"));
				
				Statement ST=null;
				ResultSet RS = null;
				ST=con.createStatement();
				RS=ST.executeQuery("select * from stylist where id="+stylistID);
				if(RS.next()) {
					stylist_List.add(RS.getString("name"));
					job_title_List.add(RS.getString("job_title"));
				}
			}
			AllHairstyle hairstyle = new AllHairstyle(hairstyleID_List,picture_List,stylist_List,job_title_List,description_List,hashtag_List);
			System.out.println(hairstyle); //======這裡輸出JSON======
		}catch(SQLException e) {
			System.out.println("select table SQLException:"+e.toString());
		}
		finally {
			Close();
		}
	}

	public void oneSalon(int num) {
		System.out.println("輸出單一店家");
		int id=num;
		String name, address, phone, businessTime,picture;
		ArrayList<Integer> stylistID_List = new ArrayList<Integer>();
		ArrayList<String> stylistName_List = new ArrayList<String>();
		ArrayList<String> picture_List = new ArrayList<String>();
        int workID_array[][] = new int[999][999]; 
        String workPicture_array[][] = new String[999][999]; 
		try {
			stat=con.createStatement();
			rs=stat.executeQuery("select * from salon where id ="+ num);
			if(rs.next()) {
				name = rs.getString("name");
				address = rs.getString("address");
				phone = rs.getString("phone");
				picture = rs.getString("picture");
				businessTime = rs.getString("businessTime");

		        int count=0;
		        Statement ST=null;
				ResultSet RS = null;
				ST=con.createStatement();
				String stylist = "select * from stylist where salon="+num;
				RS=ST.executeQuery(stylist); //找出某店家的所有設計師
				while(RS.next()) {
					id=RS.getInt("id");
					stylistID_List.add(id);
					stylistName_List.add(RS.getString("name"));
					picture_List.add(RS.getString("picture"));
					
					Statement stt=null;
					ResultSet rst = null;
					stt=con.createStatement();
					rst=stt.executeQuery("select * from stylist_works where stylist="+id); //找出某設計師的所有作品
					int flag=0;
					while(rst.next()) {
						workID_array[count][flag]=rst.getInt("id");
						workPicture_array[count][flag]=rst.getString("picture");
						flag++;
					}
					count++;
				}
				Salon salon = new Salon(name,address, phone,businessTime,picture,stylistID_List,stylistName_List,picture_List,workID_array,workPicture_array);
				System.out.println(salon); //======這裡輸出JSON======
			}
		}catch(SQLException e) {
			System.out.println("select table SQLException:"+e.toString());
		}
		finally {
			Close();
		}
	}

	public void oneStylist(int num) { //num為要找的設計師id號碼
		System.out.println("輸出單一設計師");
		int salonID;
		String name = null, job_title = null,salon = null, address = null,picture = null;
		ArrayList<String> serviceName_List = new ArrayList<String>();
		ArrayList<Integer> min_price_List = new ArrayList<Integer>();
		ArrayList<Integer> max_price_List = new ArrayList<Integer>();
		ArrayList<Integer> service_time_List = new ArrayList<Integer>();
		ArrayList<String> description_List = new ArrayList<String>();
		ArrayList<Integer> workID_List = new ArrayList<Integer>();
		ArrayList<String> workPicture_List = new ArrayList<String>();
		try {
			stat=con.createStatement();
			rs=stat.executeQuery("select * from stylist where id ="+ num);
			if(rs.next()) {
				name = rs.getString("name");
				job_title = rs.getString("job_title");
				salonID = rs.getInt("salon");
				picture = rs.getString("picture");
				
				Statement ST=null;
				ResultSet RS = null;
				ST=con.createStatement();
				address = "select * from salon where id="+salonID;
				RS=ST.executeQuery(address);
				if (RS.next()) {
					salon=RS.getString("name");
					address=RS.getString("address");
				}
			}
			Statement ST=null;
			ResultSet RS = null;
			ST=con.createStatement();
			RS=ST.executeQuery("select * from service where stylist="+num);
			while(RS.next()) {
				serviceName_List.add(RS.getString("name"));
				min_price_List.add(RS.getInt("min_price"));
				max_price_List.add(RS.getInt("max_price")); 
				service_time_List.add(RS.getInt("service_time"));
				description_List.add(RS.getString("description"));
			}
			
			Statement stt=null;
			ResultSet rst = null;
			stt=con.createStatement();
			rst=stt.executeQuery("select * from stylist_works where stylist="+num); //找出某設計師的所有作品
			while(rst.next()) {
				workID_List.add(rst.getInt("id"));
				workPicture_List.add(rst.getString("picture"));
			}
			Stylist stylist = new Stylist(name,job_title,salon, address,picture, serviceName_List,min_price_List,max_price_List,service_time_List,description_List,workID_List,workPicture_List);
			System.out.println(stylist); //======這裡輸出JSON======
		}catch(SQLException e) {
			System.out.println("select table SQLException:"+e.toString());
		}
		finally {
			Close();
		}
	}

	public void oneHairstyle(int num) { 
		System.out.println("輸出單一髮型");
		int id,stylistID;
		String picture,description,hashtag;
		String stylist = null,job_title = null;
		try {
			stat=con.createStatement();
			rs=stat.executeQuery("select * from stylist_works where id ="+ num);
			if(rs.next()) {
				id = rs.getInt("id");
				picture = rs.getString("picture");	
				stylistID = rs.getInt("stylist");
				description = rs.getString("description");
				hashtag = rs.getString("hashtag");			
				
				Statement ST=null;
				ResultSet RS = null;
				ST=con.createStatement();
				RS=ST.executeQuery("select * from stylist where id="+stylistID);
				if(RS.next()) {
					stylist=RS.getString("name");
					job_title=RS.getString("job_title");
				}
				Hairstyle hairstyle = new Hairstyle(id,picture,stylist,job_title,description,hashtag);
		        System.out.println(hairstyle);
			}
		}catch(SQLException e) {
			System.out.println("select table SQLException:"+e.toString());
		}
		finally {
			Close();
		}
	}

	public void Close(){
		try{
			if(rs!=null) {
				rs.close();
				rs=null;
			}
			if(stat!=null) {
				stat.close();
				stat=null;
			}
			if(pst!=null){
				pst.close();
				pst=null;
			}
		}catch(SQLException e){
			System.out.println("close SQLException:"+e.toString());
		}
	}
	
	public static void main(String args[]) {
		mySql test = new mySql();
		test.searchSalon();
		test.oneSalon(5);
		test.searchStylist();
		test.oneStylist(88);
		test.searchHairstyle();
		test.oneHairstyle(1);
	}
}