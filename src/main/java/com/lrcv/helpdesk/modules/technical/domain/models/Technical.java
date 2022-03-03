package com.lrcv.helpdesk.modules.technical.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lrcv.helpdesk.modules.call.domain.models.Call;
import com.lrcv.helpdesk.modules.person.domain.enums.Profile;
import com.lrcv.helpdesk.modules.person.domain.models.Person;
import com.lrcv.helpdesk.modules.technical.domain.models.dtos.TechnicalDTO;

@Entity
@Table(name = "TECHNICALS")
public class Technical extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "technical")
    private List<Call> calls = new ArrayList<>();

    public Technical() {
        super();

        this.addProfile(Profile.TECHNICAL);
    }

    public Technical(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);

        this.addProfile(Profile.TECHNICAL);
    }

    public Technical(TechnicalDTO technicalDTO) {
        super(technicalDTO.getId(), technicalDTO.getName(), technicalDTO.getCpf(), technicalDTO.getEmail(),
                technicalDTO.getPassword());

        this.addProfile(Profile.TECHNICAL);
    }

    public List<Call> getCalls() {
        return this.calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }
}
