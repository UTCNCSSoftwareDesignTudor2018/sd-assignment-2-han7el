package assignment2.utcn.business.validators;

import sun.rmi.transport.proxy.CGIHandler;

import javax.print.attribute.standard.MediaSize;
import java.util.GregorianCalendar;

public class ValidatorFactory {

    public static final String ADDRESS_VALIDATOR = "ADDRV";
    public static final String CNP_VALIDATOR = "CNPV";
    public static final String GROUP_VALIDATOR = "GRPNRV";
    public static final String NAME_VALIDATOR = "NAMEV";

    public Validator getValidator(String validatorType){
        if(validatorType == null){
            return null;
        }
        if(validatorType.equalsIgnoreCase(ADDRESS_VALIDATOR)){
            return new AddressValidator();
        }
        if(validatorType.equalsIgnoreCase(CNP_VALIDATOR)){
            return new CnpValidator();
        }
        if(validatorType.equalsIgnoreCase(GROUP_VALIDATOR)){
            return new GnValidator();
        }
        if(validatorType.equalsIgnoreCase(NAME_VALIDATOR)){
            return new NameValidator();
        }

        return null;
    }
}
