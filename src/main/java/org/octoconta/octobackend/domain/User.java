package org.octoconta.octobackend.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;

import jakarta.validation.constraints.Email;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="\"User\"")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @Column
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
    private Long userId;

    @Column
    private String name;

    @Column
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Column
    @ColumnDefault("true")
    private Boolean isActive = true;

    @OneToMany(mappedBy = "user")
    private Set<Transaction> userTransactions;

    @OneToMany(mappedBy = "user")
    private Set<Category> userCategories;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    // getters and setters
    public Long getUserId(){
        return this.userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }
    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(final String password){
        this.password = password;
    }

    public boolean getIsActive() {
        return this.isActive;
    }
    public void setIsActive(final Boolean isActive) {
        this.isActive = isActive;
    }

    public Set<Transaction> getUserTransactions() {
        return userTransactions;
    }
    public void setUserTransactions(final Set<Transaction> userTransactions) {
        this.userTransactions = userTransactions;
    }

    public Set<Category> getUserCategories() {
        return userCategories;
    }
    public void setUserCategories(final Set<Category> userCategories) {
        this.userCategories = userCategories;
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