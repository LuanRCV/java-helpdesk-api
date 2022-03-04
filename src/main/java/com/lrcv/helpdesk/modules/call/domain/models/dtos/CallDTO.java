package com.lrcv.helpdesk.modules.call.domain.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lrcv.helpdesk.modules.call.domain.models.Call;

public class CallDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openedAt = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closedAt;

    @NotNull(message = "Field Priority is required")
    private Integer priority;

    @NotNull(message = "Field Status is required")
    private Integer status;

    @NotNull(message = "Field Title is required")
    private String title;

    @NotNull(message = "Field Comments is required")
    private String comments;

    @NotNull(message = "Field Technical is required")
    private Integer technical;

    @NotNull(message = "Field Customer is required")
    private Integer customer;

    private String technicalName;

    private String customerName;

    public CallDTO() {
        super();
    }

    public CallDTO(Call call) {
        super();

        this.id = call.getId();
        this.openedAt = call.getOpenedAt();
        this.closedAt = call.getClosedAt();
        this.priority = call.getPriority().getCode();
        this.status = call.getStatus().getCode();
        this.title = call.getTitle();
        this.comments = call.getComments();
        this.technical = call.getTechnical().getId();
        this.customer = call.getCustomer().getId();
        this.technicalName = call.getTechnical().getName();
        this.customerName = call.getCustomer().getName();
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

    public Integer getPriority() {
        return this.priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
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

    public Integer getTechnical() {
        return this.technical;
    }

    public void setTechnical(Integer technical) {
        this.technical = technical;
    }

    public Integer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public String getTechnicalName() {
        return this.technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
