package com.Utilities;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**Validator for survey and possible start date components
 * @author Bisheswor Devkota
 *
 */
@FacesValidator("com.Utilities.DateRangeValidator")
public class DateRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return; 
        }

        UIInput surveyDateComponent = (UIInput) component.getAttributes().get("surveyDateComponent");

        if (!surveyDateComponent.isValid()) {
            return; 
        }

        Date surveyDate = (Date) surveyDateComponent.getValue();

        if (surveyDate == null) {
            return; 
        }
        Date possibleStartDate = (Date) value;
        if (surveyDate.after(possibleStartDate)) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Semester start date must be greater or equals to survey date", null));
        }
    }

}
