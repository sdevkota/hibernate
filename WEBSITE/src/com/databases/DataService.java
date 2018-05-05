package com.databases;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

public class DataService {
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-example");
	/**
	 * @return list of surveys
	 */
	@SuppressWarnings("unchecked")
	public List<SurveyEntity> getAllSurveys() {
		EntityManager em= factory.createEntityManager();
		List<SurveyEntity> surveys = new ArrayList<SurveyEntity>();
		try {
			
			Query q = em.createQuery("SELECT s FROM Surveys s");
			surveys = (List<SurveyEntity>) q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return surveys;
	}

	/**
	 * Saves data to the table
	 * 
	 * @param survey
	 * 
	 */
	public void saveDatatoTable(SurveyEntity survey) {
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-example");
		EntityManager em = factory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(survey);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			em.close();
		}

	}

	/**
	 * Deleting entity from the db
	 * 
	 * @param s
	 */
	public void deleteSurvey(SurveyEntity survey) {
		EntityManager em = factory.createEntityManager();
		  em.getTransaction( ).begin( );
	        //em.createNativeQuery("DELETE FROM Surveys where id="+surveyId);
		  SurveyEntity surveyItem=(SurveyEntity)em.find(SurveyEntity.class, survey.getId());
	        em.remove(surveyItem);
	        em.getTransaction( ).commit( );
	        em.close( ); 
	}
	/**
	 * Search based on the criteria
	 * 
	 * @param string
	 * @param string2
	 * @param string3
	 * @param string4
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SurveyEntity> getFullTextSearch(String firstName, String lastName, String city, String state) {
		//EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-example");
		EntityManager em = factory.createEntityManager();
		List<SurveyEntity> list = new ArrayList<SurveyEntity>();
		
		try {
		

			if (firstName.isEmpty()) {
				firstName = "'%'";
			} else {
				firstName = " '%" + firstName + "%'";
			}
			if (lastName.isEmpty()) {
				lastName = "'%'";
			} else {
				lastName = " '%" + lastName + "%'";
			}
			if (city.isEmpty()) {
				city = "'%'";
			} else {
				city = " '%" + city + "%'";
			}
			if (state.isEmpty()) {
				state = "'%'";
			} else {
				state = " '%" + state + "%'";
			}

			String sql = "SELECT s FROM Surveys s WHERE firstName LIKE " + firstName + " AND LastName LIKE " + lastName
					+ " AND city LIKE " + city + " AND state LIKE " + state;
			Query q = em.createQuery(sql);
			list = (List<SurveyEntity>) q.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
	/* @param values integer array
	 * @return mean calculated based on the formula
	 */
	public double calculateMeanValue(String value){
		if(value.equals(""))
			return 0;
		int[] arrr=parseIntArray(convertCommaSeparatedToArray(value));
		int sum=sumArray(arrr);

		return sum/arrr.length;
	}
	/**
	 * @param values integer array
	 * @return SD using mathematical formula
	 */
	public double calculateStandardDeviation(String value){
		if(value.equals(""))
			return 0;
		int[] data=parseIntArray(convertCommaSeparatedToArray(value));
		double mean=calculateMeanValue(value);
		double temp=0;
		for(double d:data){
			temp+=(d-mean)*(d-mean);
		}
		return Double.parseDouble(new DecimalFormat("##.####").format(Math.sqrt(temp/data.length)));

	}
	/**Conversts comma separated into a string array
	 * @param str
	 * @return
	 */
	public static String[] convertCommaSeparatedToArray(String str){
		return str.split(",",-1);
	}


	/**parses string array to int array
	 * @param arr
	 * @return
	 */
	public static int[] parseIntArray(String[] arr) {
		int[] ints = new int[arr.length];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(arr[i]);
		}
		return ints;
	}
	/**Sum all items in array
	 * @param arr
	 * @return
	 */
	static int sumArray(int[] arr){
		int i=0;
		int sum=0;
		while(i<arr.length){
			sum+=arr[i];
			i++;
		}
		return sum;
	}

}
