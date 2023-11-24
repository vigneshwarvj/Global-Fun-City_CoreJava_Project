package in.fssa.globalfuncity.validator;

import in.fssa.globalfuncity.exception.ValidationException;
import in.fssa.globalfuncity.model.Offer;

public class OfferValidator {

    public static void validateOffer(Offer offer) throws ValidationException {
    	
        if (offer == null) {
            throw new ValidationException("Offer object cannot be null");
        }
    }
	
}
