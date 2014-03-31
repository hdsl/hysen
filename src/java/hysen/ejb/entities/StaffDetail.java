/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hysen.ejb.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author AbdulMumin
 */
@Entity
@Table(name = "staff_detail")
@NamedQueries({
    @NamedQuery(name = "StaffDetail.findAll", query = "SELECT s FROM StaffDetail s")})
public class StaffDetail extends CommonEntity{
  
    @Column(name = "staff_name")
    private String staffName;
    
    @Column(name = "gender")
    private Character gender;
    
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
    @Column(name = "nationality")
    private String nationality;
    
    @Column(name = "hometown")
    private String hometown;
    
    @Column(name = "region_id")
    private String regionId;
    
    @Column(name = "disability_status")
    private Character disabilityStatus;
    
    @Column(name = "disability_detail")
    private String disabilityDetail;
    
    @Column(name = "email_address")
    private String emailAddress;
    
    @Column(name = "primary_contact")
    private String primaryContact;
    
    @Column(name = "marital_status")
    private String maritalStatus;
   
    @Column(name = "house_number")
    private String houseNumber;
 
    @Column(name = "physical_location")
    private String physicalLocation;
   
    @Column(name = "postal_location")
    private String postalLocation;
   
    @Column(name = "next_of_kin")
    private String nextOfKin;
    
    @Column(name = "kin_contact")
    private String kinContact;
   
    @Column(name = "relation_to_kin")
    private String relationToKin;
   
    @Column(name = "level_of_education")
    private String levelOfEducation;
    
    @Column(name = "last_school_attended")
    private String lastSchoolAttended;
   
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department")
    private Department department;
    
    @Column(name = "date_of_appointment")
    @Temporal(TemporalType.DATE)
    private Date dateOfAppointment;
    
    @Column(name = "occupation_status")
    private String occupationStatus;
    
    @Column(name = "occupation_type")
    private String occupationType;

    public StaffDetail() {
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Character getDisabilityStatus() {
        return disabilityStatus;
    }

    public void setDisabilityStatus(Character disabilityStatus) {
        this.disabilityStatus = disabilityStatus;
    }

    public String getDisabilityDetail() {
        return disabilityDetail;
    }

    public void setDisabilityDetail(String disabilityDetail) {
        this.disabilityDetail = disabilityDetail;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

    public String getPostalLocation() {
        return postalLocation;
    }

    public void setPostalLocation(String postalLocation) {
        this.postalLocation = postalLocation;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getKinContact() {
        return kinContact;
    }

    public void setKinContact(String kinContact) {
        this.kinContact = kinContact;
    }

    public String getRelationToKin() {
        return relationToKin;
    }

    public void setRelationToKin(String relationToKin) {
        this.relationToKin = relationToKin;
    }

    public String getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(String levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public String getLastSchoolAttended() {
        return lastSchoolAttended;
    }

    public void setLastSchoolAttended(String lastSchoolAttended) {
        this.lastSchoolAttended = lastSchoolAttended;
    }

    public Date getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(Date dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getOccupationStatus() {
        return occupationStatus;
    }

    public void setOccupationStatus(String occupationStatus) {
        this.occupationStatus = occupationStatus;
    }

}
