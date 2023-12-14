package resources;

import java.util.ArrayList;
import java.util.List;

import pojos.Location;
import pojos.Place;

public class TestDataBuild {
	
	public Place addPLacePayload(String name, String language, String address)
	{
		Place p=new Place();
		p.setAccuracy(199);
		p.setName(name);
		p.setPhone_number("9963607906");
		p.setAddress(address);
		p.setWebsite("http://jaihindh.com");
		p.setLanguage(language);


		List<String> typesList=new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		p.setTypes(typesList);

		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}

}
