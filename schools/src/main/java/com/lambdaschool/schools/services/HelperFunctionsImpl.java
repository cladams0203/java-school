package com.lambdaschool.schools.services;


import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions {

    @Override
    public List<ValidationError> getConstraintViolations(Throwable causes) {
        while ((causes) != null &&  !(causes instanceof ConstraintViolationException) ){
            causes = causes.getCause();
        }
        List<ValidationError> listVE = new ArrayList<>();
        if (causes != null){
            ConstraintViolationException  ex = (ConstraintViolationException) causes;
            for (ConstraintViolation cv : ex.getConstraintViolations()){
                ValidationError newVe = new ValidationError();
                newVe.setCode(cv.getInvalidValue().toString());
                newVe.setMessage(cv.getMessage());
                listVE.add(newVe);
            }
        }
        return listVE;
    }
}
