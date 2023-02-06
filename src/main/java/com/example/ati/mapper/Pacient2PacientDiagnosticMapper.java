package com.example.ati.mapper;

import com.example.ati.domain.Pacient;
import com.example.ati.dto.Pacient2PacientDiagnosticDTO;

import java.util.ArrayList;
import java.util.List;

public class Pacient2PacientDiagnosticMapper {
    public Pacient2PacientDiagnosticDTO convert(Pacient pacient){
        return new Pacient2PacientDiagnosticDTO(pacient.getCNP(), pacient.getSeverity(), pacient.getDiagnostic());
    }

    public List<Pacient2PacientDiagnosticDTO> convert(List<Pacient> pacients){
        List<Pacient2PacientDiagnosticDTO> diagnosticDTOS = new ArrayList<>();
        for(Pacient pacient: pacients){
            diagnosticDTOS.add(convert(pacient));
        }
        return diagnosticDTOS;
    }
}
