package mybeans;


// be careful to import ManagedBean form faces NOt javax.annotation


import javax.faces.bean.ManagedBean;


// add @ManagedBean annotation
@ManagedBean

public class TourBean {

	private String kindOfTour;
	
	//3- no-argument constructor

	public TourBean() {
		
		System.out.println("Bean constructor is called");
	}

	public String getKindOfTour() {
		return kindOfTour;
	}

	public void setKindOfTour(String kindOfTour) {
		this.kindOfTour = kindOfTour;
	}

	
// method must be public and return a string which is the name of view
	public String startTheTour() {
		if (kindOfTour != null && kindOfTour.equals("city")) {	
			return "city_tour";
		}
		
		else
		{
			return "country_tour";
		}
			
			
	}

	
	


	

	
}

