package com.lrcv.helpdesk.modules.call.domain.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lrcv.helpdesk.modules.call.domain.enums.Priority;
import com.lrcv.helpdesk.modules.call.domain.enums.Status;
import com.lrcv.helpdesk.modules.customer.domain.models.Customer;
import com.lrcv.helpdesk.modules.technical.domain.models.Technical;

@Entity
@Table(name = "CALLS")
public class Call implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openedAt = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closedAt;

    @Column()
    private Priority priority;

    @Column()
    private Status status;

    @Column()
    private String title;

    @Column()
    private String comments;

    @ManyToOne
    @JoinColumn(name = "technicalId")
    private Technical technical;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Call() {
        super();
    }

    public Call(Integer id, Priority priority, Status status, String title, String comments, Technical technical,
            Customer customer) {
        super();

        this.id = id;
        this.priority = priority;
        this.status = status;
        this.title = title;
        this.comments = comments;
        this.technical = technical;
        this.customer = customer;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOpenedAt() {
        return this.openedAt;
    }

    public void setOpenedAt(LocalDate openedAt) {
        this.openedAt = openedAt;
    }

    public LocalDate getClosedAt() {
        return this.closedAt;
    }

    public void setClosedAt(LocalDate closedAt) {
        this.closedAt = closedAt;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Technical getTechnical() {
        return this.technical;
    }

    public void setTechnical(Technical technical) {
        this.technical = technical;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Call)) {
            return false;
        }
        Call call = (Call) o;
        return Objects.equals(id, call.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
