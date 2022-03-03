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

public class TechnicalDTO implements Serializable {
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
    protected LocalDate dataCriacao = LocalDate.now();

    public TechnicalDTO() {

    }

    public TechnicalDTO(Integer id, String name, String cpf, String email, String password, Set<Integer> profiles,
            LocalDate dataCriacao) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.password = password;
        this.profiles = profiles;
        this.dataCriacao = dataCriacao;
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

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TechnicalDTO)) {
            return false;
        }
        TechnicalDTO technicalDTO = (TechnicalDTO) o;
        return Objects.equals(id, technicalDTO.id) && Objects.equals(name, technicalDTO.name)
                && Objects.equals(cpf, technicalDTO.cpf) && Objects.equals(email, technicalDTO.email)
                && Objects.equals(password, technicalDTO.password) && Objects.equals(profiles, technicalDTO.profiles)
                && Objects.equals(dataCriacao, technicalDTO.dataCriacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cpf, email, password, profiles, dataCriacao);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", cpf='" + getCpf() + "'" +
                ", email='" + getEmail() + "'" +
                ", password='" + getPassword() + "'" +
                ", profiles='" + getProfiles() + "'" +
                ", dataCriacao='" + getDataCriacao() + "'" +
                "}";
    }

}
