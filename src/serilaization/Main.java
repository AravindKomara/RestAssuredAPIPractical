package serilaization;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws JsonProcessingException {
		// TODO Auto-generated method stub
		Company c = new Company();
		c.setCompany("Genpact");
		c.setLandline("040-1235-8956");
		c.setEstablished_year("1997");
		c.setWebsite("https://www.genpact.com");
		
		List<String> domains = new ArrayList<String>();
		domains.add("Healthcare");
		domains.add("Insurance");
		domains.add("Banking");
		domains.add("Finanicial");
		c.setDomains(domains);
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLog(33.427362);
		c.setLocation(l);
		
		BranchDetails bd = new BranchDetails();
		bd.setName("Hyderabad");
		bd.setPhone("123456");
		BranchDetails bd1 = new BranchDetails();
		bd1.setName("Pune");
		bd1.setPhone("789654");
		BranchDetails bd2 = new BranchDetails();
		bd2.setName("Noida");
		bd2.setPhone("632589");
		
		List<BranchDetails>  alldetails = new ArrayList<BranchDetails>();
		alldetails.add(bd);
		alldetails.add(bd1);
		alldetails.add(bd2);
		
		c.setBranchdetails(alldetails);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String nestedJsonPayload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(c);
		System.out.println(nestedJsonPayload);
		
		

	}

}
