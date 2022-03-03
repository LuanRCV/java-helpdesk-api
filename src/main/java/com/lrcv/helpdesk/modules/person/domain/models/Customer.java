package com.lrcv.helpdesk.modules.person.domain.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lrcv.helpdesk.modules.call.domain.models.Call;
import com.lrcv.helpdesk.modules.person.domain.enums.Profile;

@Entity
public class Customer extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Call> calls = new ArrayList<>();

    public Customer() {
        super();

        this.addProfile(Profile.CUSTOMER);
    }

    public Customer(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);

        this.addProfile(Profile.CUSTOMER);
    }

    public List<Call> getCalls() {
        return this.calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }
}
