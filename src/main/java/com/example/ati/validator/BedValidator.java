package com.example.ati.validator;

import com.example.ati.domain.Bed;

public class BedValidator implements Validator<Bed>{
    @Override
    public void validate(Bed bed) {
        String errors = "";
        if(bed.getBedType() == null){
            errors += "Bed Type isn't null!\n";
        }
        if(!bed.getVentilation().equals("DA") || !bed.getVentilation().equals("NU")){
            errors += "Invalid ventilation!\n";
        }
        if(bed.getPacientCNP() < 0){
            errors += "Invalid CNP!\n";
        }
        if(!errors.isEmpty()){
            throw new ValidatorException(errors);
        }
    }
}
