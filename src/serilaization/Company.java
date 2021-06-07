package serilaization;

import java.util.List;

public class Company {

	private String company;
	private String landline;
	private String established_year;
	private List<String> domains;
	private Location location;
	private List<BranchDetails> branchdetails;
	private String website;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLandline() {
		return landline;
	}
	public void setLandline(String landline) {
		this.landline = landline;
	}
	public String getEstablished_year() {
		return established_year;
	}
	public void setEstablished_year(String established_year) {
		this.established_year = established_year;
	}
	public List<String> getDomains() {
		return domains;
	}
	public void setDomains(List<String> domains) {
		this.domains = domains;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<BranchDetails> getBranchdetails() {
		return branchdetails;
	}
	public void setBranchdetails(List<BranchDetails> branchdetails) {
		this.branchdetails = branchdetails;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
}
