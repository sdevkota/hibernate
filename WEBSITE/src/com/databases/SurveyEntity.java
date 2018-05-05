package com.databases;
import java.util.*;

import javax.persistence.*;

/**
 * Entity class for table mapping
 * @author Bisheswor Devkota
 * @version 1.0
 * @since 4/11/2017
 */
@Entity(name="Surveys")
public class SurveyEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	@Column(name="FirstName")
	private String firstName;
	
	@Column(name="LastName")
	private String lastName;
	
	@Column(name="StreetAddress")
	private String streetAddress;
	
	@Column(name="City")
	private String city;
	
	@Column(name="State")
	private String state;
	
	@Column(name="Zip")
	private String zip;
	
	@Column(name="Phone")
	private String phone;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="DateOfSurvey")
	private Date dateOfSurvey;
	
	@Column(name="ItemsLiked")
	private String likes;
	
	@Transient
	private String[] likedItems;
	
	@Column(name="Medium")
	private String medium;
	

	@Column(name="Recommendation")
	private String recommendation;
	
	@Column(name="Raffle")
	private String numbers;
	

	@Column(name="AdditionalComments")
	private String additionalComments;
	
	@Column(name="PossibleStartDate")
	private Date possibleStartDate;
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}
	// Gets the first name
	public String getFirstName() {
		return firstName;
	}

	// sets the first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Gets the last name
	public String getLastName() {
		return lastName;
	}

	// sets the last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// gets street address
	public String getStreetAddress() {
		return streetAddress;
	}

	// sets street address
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	// gets city
	public String getCity() {
		return city;
	}

	// sets city
	public void setCity(String city) {
		this.city = city;
	}

	// gets state
	public String getState() {
		return state;
	}

	// sets state
	public void setState(String state) {
		this.state = state;
	}

	// gets zip
	public String getZip() {
		return zip;
	}

	// sets zip
	public void setZip(String zip) {
		this.zip = zip;
	}

	// gets phone
	public String getPhone() {
		return phone;
	}

	// sets phone

	public void setPhone(String phone) {
		this.phone = phone;
	}

	// get email

	public String getEmail() {
		return email;
	}

	// sets email
	public void setEmail(String email) {
		this.email = email;
	}

	// gets date
	public Date getDateOfSurvey() {
		return dateOfSurvey;
	}

	// sets date
	public void setDateOfSurvey(Date dateOfSurvey) {
		this.dateOfSurvey = dateOfSurvey;
	}

	// gets medium
	public String getMedium() {
		return medium;
	}

	public String[] getLikedItems() {
		return likedItems;
	}

	public void setLikedItems(String[] itemsLiked) {
		this.likedItems = itemsLiked;
		this.likes=arrayToStringItems(itemsLiked);
	}
	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	// sets medium
	public void setMedium(String medium) {
		this.medium = medium;
	}

	// gets recommendation likelihood
	public String getRecommendation() {
		return recommendation;
	}

	// sets recommendation likelihood
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	// Gets the last name
	public String getNumbers() {
		return numbers;
	}

	// sets the last name
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	// gets additional comments
	public String getAdditionalComments() {
		
		return additionalComments;
	}

	// sets additional comments
	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}
	//get possible StartDate
	public Date getPossibleStartDate() {
		return possibleStartDate;
	}
	//set PossibleStartDate
	public void setPossibleStartDate(Date possibleStartDate) {
		this.possibleStartDate = possibleStartDate;
	}


	/**Array to string 
	 * @param arr
	 * @return
	 */
	public String arrayToStringItems(String[] arr) {
		if (arr.length > 0) {
			String result = "";
			StringBuilder sb = new StringBuilder();
			for (String s : arr) {
				sb.append(s).append(",");
			}
			result = sb.deleteCharAt(sb.length() - 1).toString();
			return result;
		}
		return arr.toString();
	}
	public String getFullName(){
		
		return this.firstName+" "+this.lastName;
	}

}
