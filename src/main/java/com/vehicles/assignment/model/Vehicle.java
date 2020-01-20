package com.vehicles.assignment.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;

/**
 * The type Vehicle.
 *
 * @author Michael Baqadi
 */
@Entity
@Table(name = "vehicles")
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "make", nullable = false)
    private String make;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;


  /**
   * Gets id.
   *
   * @return the id
   */
  public long getId() {
        return id;
    }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(long id) {
        this.id = id;
    }

  /**
   * Gets make.
   *
   * @return the make
   */
  public String getMake() {
        return make;
    }

  /**
   * Sets make.
   *
   * @param make the make
   */
  public void setMake(String make) {
        this.make = make;
    }

  /**
   * Gets last name.
   *
   * @return the last name
   */
  public String getModel() {
        return model;
    }

  /**
   * Sets last name.
   *
   * @param model the last name
   */
  public void setModel(String model) {
        this.model = model;
    }

  /**
   * Gets year.
   *
   * @return the year
   */
  public Integer getYear() {
        return year;
    }

  /**
   * Sets email.
   *
   * @param year the year
   */
  public void setYear(Integer year) {
        this.year = year;
    }

  /**
   * Gets created at.
   *
   * @return the created at
   */
  public Date getCreatedAt() {
        return createdAt;
    }

  /**
   * Sets created at.
   *
   * @param createdAt the created at
   */
  public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

  /**
   * Gets created by.
   *
   * @return the created by
   */
  public String getCreatedBy() {
        return createdBy;
    }

  /**
   * Sets created by.
   *
   * @param createdBy the created by
   */
  public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

  /**
   * Gets updated at.
   *
   * @return the updated at
   */
  public Date getUpdatedAt() {
        return updatedAt;
    }

  /**
   * Sets updated at.
   *
   * @param updatedAt the updated at
   */
  public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }


}
