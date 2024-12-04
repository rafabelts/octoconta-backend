package org.octoconta.octobackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

import java.time.OffsetDateTime;

public class TransactionDTO {

    private Long transactionId;

    private Long amount;

    @Size(max = 255)
    private String title;

    @Size(max = 255)
    private String type;

    private OffsetDateTime date;

    @JsonProperty("isActive")
    private Boolean isActive=true;

    private Long user;
    private Long category;

    // getters and setters
    public Long getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(final Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAmount() {
        return amount;
    }
    public void setAmount(final Long amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(final String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }
    public void setType(final String type) {
        this.type = type;
    }

    public OffsetDateTime getDate() {
        return date;
    }
    public void setDate(final OffsetDateTime date) {
        this.date = date;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getUser() {
        return user;
    }
    public void setUser(final Long user) {
        this.user = user;
    }

    public Long getCategory() {
        return category;
    }
    public void setCategory(final Long category) {
        this.category = category;
    }


}