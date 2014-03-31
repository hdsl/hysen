/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hysen.web.utils;

import java.io.IOException;
import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author abdulmumin
 */
public class StringConstants {

    public static final String SAVE_MESSAGE = "Saved Successfully";
    public static final String DELETE_MESSAGE = "Deleted Successfully";
    public static final String EDIT_MESSAGE = "Updated Successfully";
    public static final String SAVE_ERRORMESSAGE = "Unable to save";
    public static final String DELETE_ERRORMESSAGE = "Unable to delete";
    public static final String EDIT_ERRORMESSAGE = "Unable to update";

    public static void showApprioprateMessage(String message) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(message);
        facesContext.addMessage(null, facesMessage);

    }

    public static String generateID() {

        return UUID.randomUUID().toString();

    }

    public static String hashGeneratedPassword(String pwd) {

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(pwd.getBytes());
        
    }

    public static String unHashGeneratedPassword(String pwd) {

        BASE64Decoder decoder = new BASE64Decoder();

        try {
            return new String(decoder.decodeBuffer(pwd));
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
