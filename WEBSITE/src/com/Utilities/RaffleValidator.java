package com.Utilities;

import com.databases.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("com.Utilities.RaffleValidator")
public class RaffleValidator implements Validator {

	@Override
   public void validate(FacesContext facesContext,
      UIComponent component, Object value)
      throws ValidatorException {
      String valueEntered = value.toString();
try{
	int[] strArray=DataService.parseIntArray(DataService.convertCommaSeparatedToArray(valueEntered));
	
	boolean numberLessThanOneAndGreaterThanHundred=false;
    boolean lessThanTenNumbers=strArray.length<10;
    for(int  e:strArray){
    	if(e<1 || e>100)
    	{
    		numberLessThanOneAndGreaterThanHundred=true;
    	 	break;
    	}
    }
	if(lessThanTenNumbers || numberLessThanOneAndGreaterThanHundred ){
		FacesMessage msg =
	            new FacesMessage("Raffle numbers are not valid","At least 10 numbers separated by commas are required.");
	         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
 throw new ValidatorException(msg);
}
}
catch(Exception ex){
	FacesMessage msg =
            new FacesMessage("Raffle numbers are not valid","At least 10 numbers  between 1-100 separated by commas are required.");
         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
         throw new ValidatorException(msg);
}
      
      
}
}
