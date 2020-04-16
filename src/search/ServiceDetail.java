package search;

public class ServiceDetail {
	String name;
	int min_price,max_price,service_time;
	String description;
	
    public ServiceDetail(String name,int min_price,int max_price,int service_time,String description) {
		this.name = name;
		this.min_price = min_price;
		this.max_price = max_price;
		this.service_time = service_time;
		this.description = description;
    }
}
