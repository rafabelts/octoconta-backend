package org.octoconta.octobackend.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name="Transaction")
@EntityListeners(AuditingEntityListener.class)
public class Transaction {

    @Id // Defines Primary Key
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long transactionId;

    @Column
    private Long amount;

    @Column
    private String title;

    @Column
    private String type;


    @CreatedDate
    @Column
    private OffsetDateTime date;

    @Column
    @ColumnDefault("true")
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public User getUser() {
        return user;
    }
    public void setUser(final User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(final Category category) {
        this.category = category;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }
    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}