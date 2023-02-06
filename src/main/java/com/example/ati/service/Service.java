package com.example.ati.service;

import com.example.ati.domain.Bed;
import com.example.ati.domain.BedType;
import com.example.ati.domain.Pacient;
import com.example.ati.dto.Pacient2PacientDiagnosticDTO;
import com.example.ati.repo.db.BedRepoDB;
import com.example.ati.repo.db.PacientRepoDB;
import com.example.ati.utils.event.ChangeEventType;
import com.example.ati.utils.event.EntityChangeEvent;
import com.example.ati.utils.observer.Observable;
import com.example.ati.utils.observer.Observer;
import com.example.ati.validator.ValidatorException;

import java.util.ArrayList;
import java.util.List;

public class Service implements Observable<EntityChangeEvent> {
    private PacientRepoDB pacientRepoDB;
    private BedRepoDB bedRepoDB;
    private List<Observer<EntityChangeEvent>> observers = new ArrayList<>();

    public Service(PacientRepoDB pacientRepoDB, BedRepoDB bedRepoDB) {
        this.pacientRepoDB = pacientRepoDB;
        this.bedRepoDB = bedRepoDB;
    }

    public List<Pacient> getAllPacients(){
        return pacientRepoDB.findAll();
    }

    public List<Bed> getAllBeds(){
        return bedRepoDB.findAll();
    }

    public List<Pacient> pacientsWithoutBeds(){
       return pacientRepoDB.waitingPacients();

    }

    public void addPacientToBed(BedType type, Pacient2PacientDiagnosticDTO selectedPacient){
        Bed newBed = null;
        List<Pacient> allPacients = pacientRepoDB.waitingPacients();
        for(Pacient pacient: allPacients){
            if(pacient.getCNP() == selectedPacient.getPacientCNP() && bedRepoDB.findAll().size() < 41){
                if(type == BedType.TIM || type == BedType.TIC){
                    newBed = new Bed(pacient.getAge() + 1, type, "DA", pacient.getCNP());
                } else {
                    newBed = new Bed(pacient.getAge() + 1, type, "NU", pacient.getCNP());
                }
            } else {
                throw new ValidatorException("Nu sunt locuri disponibile!\n");
            }
        }
        assert newBed != null;
        bedRepoDB.save(newBed);
        this.notifyObservers(new EntityChangeEvent(ChangeEventType.ADD, newBed));
    }

    @Override
    public void addObserver(Observer<EntityChangeEvent> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(EntityChangeEvent t) {
        observers.forEach(x -> x.update(t));
    }
}
