package com.lrcv.helpdesk.modules.person.domain.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lrcv.helpdesk.modules.person.domain.enums.Profile;
import com.lrcv.helpdesk.modules.person.domain.models.Customer;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = "O campo Nome é obrigatório")
    protected String name;

    @NotNull(message = "O campo CPF é obrigatório")
    protected String cpf;

    @NotNull(message = "O campo email é obrigatório")
    protected String email;

    @NotNull(message = "O campo senha é obrigatório")
    protected String password;

    protected Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createdAt = LocalDate.now();

    public CustomerDTO() {
        super();

        this.addProfile(Profile.CUSTOMER);
    }

    public CustomerDTO(Customer customer) {
        super();

        this.id = customer.getId();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.profiles = customer.getProfiles().stream().map(profile -> profile.getCode()).collect(Collectors.toSet());
        this.createdAt = customer.getCreatedAt();

        this.addProfile(Profile.CUSTOMER);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return this.profiles.stream().map(profileCode -> Profile.toEnum(profileCode)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CustomerDTO)) {
            return false;
        }
        CustomerDTO customerDTO = (CustomerDTO) o;
        return Objects.equals(id, customerDTO.id) && Objects.equals(cpf, customerDTO.cpf)
                && Objects.equals(email, customerDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email);
    }
}
