package com.lrcv.helpdesk.modules.technical.domain.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lrcv.helpdesk.modules.person.domain.enums.Profile;
import com.lrcv.helpdesk.modules.technical.domain.models.Technical;

public class TechnicalDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    @NotNull(message = "Field Name is required")
    protected String name;

    @NotNull(message = "Field CPF is required")
    protected String cpf;

    @NotNull(message = "Field E-mail is required")
    protected String email;

    @NotNull(message = "Field Password is required")
    protected String password;

    protected Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createdAt = LocalDate.now();

    public TechnicalDTO() {
        super();

        this.addProfile(Profile.TECHNICAL);
    }

    public TechnicalDTO(Technical technical) {
        super();

        this.id = technical.getId();
        this.name = technical.getName();
        this.cpf = technical.getCpf();
        this.email = technical.getEmail();
        this.password = technical.getPassword();
        this.profiles = technical.getProfiles().stream().map(profile -> profile.getCode()).collect(Collectors.toSet());
        this.createdAt = technical.getCreatedAt();

        this.addProfile(Profile.TECHNICAL);
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
        if (!(o instanceof TechnicalDTO)) {
            return false;
        }
        TechnicalDTO technicalDTO = (TechnicalDTO) o;
        return Objects.equals(id, technicalDTO.id) && Objects.equals(cpf, technicalDTO.cpf)
                && Objects.equals(email, technicalDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf, email);
    }

}
