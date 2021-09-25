package com.raunak.springbootdemootp.auth.entity;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
//import java.time.ZoneOffset;
import java.util.UUID;


@Entity
@Table(name="customer"
//        uniqueConstraints={@UniqueConstraint(columnNames={"email"})}
)
@EntityListeners(AuditingEntityListener.class)
public class Customer {
    @Id @GeneratedValue @Type(type="uuid-char")
    @Column(name="customer_id", unique = true, length = 36)
    private UUID customerId;

    @Column(name="email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name="first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name="last_name", nullable = true, length = 30)
    private String lastName;

    @Column(name="phone", nullable = true, length = 30)
    private String phone;

    @Column(name="enabled", nullable = false)
    private String enabled;

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000;   // 5 minutes
    @Column(name = "one_time_password", nullable = true)
    private String oneTimePassword;

    @Column(name = "otp_requested_time", nullable = true) @LastModifiedDate
    private Instant otpRequestedTime;

    @Column(name="created_date", nullable = false) @CreatedDate
    private Instant createdDate;

    @Column(name="updated_date", nullable = false) @LastModifiedDate
    private Instant updatedDate;

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getOneTimePassword() {
        return oneTimePassword;
    }

    public void setOneTimePassword(String oneTimePassword) {
        this.oneTimePassword = oneTimePassword;
    }

    public Instant getOtpRequestedTime() {
        return otpRequestedTime;
    }

    public void setOtpRequestedTime(Instant otpRequestedTime) {
        this.otpRequestedTime = otpRequestedTime;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

//    public boolean isOTPRequired() {
//        /**
//         * Method to check if otp is valid or not.
//         */
//        if (this.getOneTimePassword() == null) {
//            return false;
//        }
//
//        long currentTimeInMillis = System.currentTimeMillis();
//        long otpRequestedTimeInMillis = this.otpRequestedTime.atZone(ZoneOffset.UTC).getSecond() * 1000;
//
//        if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
//            // OTP expires
//            return false;
//        }
//
//        return true;
//    }
}
