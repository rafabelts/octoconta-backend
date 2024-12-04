package org.octoconta.octobackend.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.OffsetDateTime;

@Entity
@Table(name="Category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

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
    private Long categoryId;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String icon;

    @Column
    private String color;

    @Column
    @ColumnDefault("true")
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    // getters and setters
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(final Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(final String type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }
    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public String getColor() {
        return color;
    }
    public void setColor(final String color) {
        this.color = color;
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