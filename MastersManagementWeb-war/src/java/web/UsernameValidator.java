package web;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class UsernameValidator implements Validator {
    // ---------------------------- ERROR MESSAGES ----------------------------
    private static final String ERR_INVALID_USERNAME = "ERRO: invalid username.";
    private static final String ERR_USERNAME_LENGTH_LESS_5 = "ERRO: Username necessita no minimo de 5 letras.";
    
    private static final Logger logger = Logger.getLogger("web.UserNameValidator");

    @Override
    public void validate(FacesContext context, UIComponent toValidate, Object value) throws ValidatorException {
        
        try {
            //Your validation code goes here
            String username = (String) value;
            //If the validation fails
            if (username.startsWith("xpto")) {
                FacesMessage message = new FacesMessage(ERR_INVALID_USERNAME);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(toValidate.getClientId(context), message);
                ((UIInput) toValidate).setValid(false);
            }
            if(username.length() < 5){
                FacesMessage message = new FacesMessage(ERR_USERNAME_LENGTH_LESS_5);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                context.addMessage(toValidate.getClientId(context), message);
                ((UIInput) toValidate).setValid(false);
            }
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unkown error.", logger);
        }
    }
}
