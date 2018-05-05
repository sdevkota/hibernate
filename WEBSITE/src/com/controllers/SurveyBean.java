package com.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.models.*;
import com.databases.*;
import com.databases.SurveyEntity;

@ManagedBean(name = "survey")
@SessionScoped
public class SurveyBean {
	// instance variables
	private SurveyEntity student;
	private WinningResult winningResult;
	private DataService dataService;
	private List<SurveyEntity> surveys;
	private List<SurveyEntity> searchResults;
	private List<String> states;

	// constructor
	public SurveyBean() {
		dataService=new DataService();
		student=new SurveyEntity();
		surveys = new ArrayList<SurveyEntity>();
		winningResult = new WinningResult();
	}

	// getters and setters
	public SurveyEntity getStudent() {
		return student;
	}

	public void setStudent(SurveyEntity Student) {
		this.student = Student;
	}
	/**Returns the Winning Results Mean and SD
	 * @return
	 */
	public WinningResult getWinningResult() {
		winningResult.setMean(dataService.calculateMeanValue(student.getNumbers()));
		winningResult.setStandardDeviation(dataService.calculateStandardDeviation(student.getNumbers()));
		return winningResult;
	}
	//get set for search results
	public List<SurveyEntity> getSearchResults() {
		return searchResults;
	}
	public void setSearchResults(List<SurveyEntity> searchResults) {
		this.searchResults= searchResults;
	}

	/**
	 * Controller action methods to save the survey information
	 * 
	 * @return
	 */
	public String saveSurveyInfo() {
		try {
			dataService.saveDatatoTable(student);
			if (!student.getNumbers().trim().isEmpty()) {
				double mean = dataService.calculateMeanValue(student.getNumbers());
				if (mean > 90) {
					return "WinnerAcknowledgement";
				}
			}
			return "SimpleAcknowledgement";
		} catch (Exception e) {
			return "error";
		}
	}
	/**
	 * Controller action methods to auto populate the states
	 * 
	 * @return
	 */
	public List<String> getStates() {
		states = new ArrayList<String>();
		states.add("Alabama");
		states.add("Alaska");
		states.add("Arizona");
		states.add("Arkansas");
		states.add("California");
		states.add("Colorado");
		states.add("Connecticut");
		states.add("Delaware");
		states.add("Florida");
		states.add("Georgia");
		states.add("Hawaii");
		states.add("Idaho");
		states.add("Illinois");
		states.add("Indiana");
		states.add("Iowa");
		states.add("Kansas");
		states.add("Kentucky");
		states.add("Louisiana");
		states.add("Maine");
		states.add("Maryland");
		states.add("Massachusetts");
		states.add("Michigan");
		states.add("Minnesota");
		states.add("Mississippi");
		states.add("Missouri");
		states.add("Montana");
		states.add("Nebraska");
		states.add("Nevada");
		states.add("New Hampshire");
		states.add("New Jersey");
		states.add("New Mexico");
		states.add("New York");
		states.add("North Carolina");
		states.add("North Dakota");
		states.add("Ohio");
		states.add("Oklahoma");
		states.add("Oregon");
		states.add("Pennsylvania");
		states.add("Rhode Island");
		states.add("South Carolina");
		states.add("South Dakota");
		states.add("Tennessee");
		states.add("Texas");
		states.add("Utah");
		states.add("Vermont");
		states.add("Virginia");
		states.add("Washington");
		states.add("West Virginia");
		states.add("Wisconsin");
		states.add("Wyoming");
		states.add("District of Columbia");
		states.add("Puerto Rico");
		states.add("Guam");
		states.add("American Samoa");
		states.add("U.S. Virgin Islands");
		states.add("Northern Mariana Islands");
		return states;
	}

	public void setStates(List<String> states){
		this.states=states;
	}
	/**
	 * @return survey List
	 */
	public List<SurveyEntity> getSurveys() {
		surveys = dataService.getAllSurveys();
		return surveys;
	}
	/**Deletes the survey Entity
	 * @param survey
	 */
	public String deleteSurvey(SurveyEntity survey){
		dataService.deleteSurvey(survey);
		searchResults.remove(survey);
		
	return null;
	}
	/**Search Result
	 * @return
	 */
	public String displaySearchResults(){
		searchResults=dataService.getFullTextSearch(student.getFirstName()==null?"":student.getFirstName(),student.getLastName()==null?"":student.getLastName()
				,student.getCity()==null?"":student.getCity(),student.getState()==null?"":student.getState());
		
		return "searchResults";
	}
	/** Action controller method to get state and city from zip code
	 * @return
	 */
	public void getCityAndStateFromZip(AjaxBehaviorEvent e){
		if(student.getZip()!=null){
			switch(student.getZip()){
			case "22312":
				student.setCity("Alexandria");
				student.setState("VA");
				break;
			case "22030":
				student.setCity("Fairfax");
				student.setState("VA");
				break;
			case "22301":
				student.setCity("Tysons Corner");
				student.setState("VA");
				break;
			case "20148":
				student.setCity("Ashburn");
				student.setState("VA");
				break;
				
			}
		}
		
		
	}

}
