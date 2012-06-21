package com.ua.shop.web.form;

import com.ua.shop.model.enumeration.Gender;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.EqualToField;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import org.apache.commons.lang.StringUtils;

public class UserForm {

    private static final String DEFAULT_PHOTO = "html/image/user_avatar_empty.png";

    private Long id;

    @NotBlank(message = "First name should be not blank", errorCode = "first.name.should.be.not.blank")
    private String firstName;

    @NotBlank(message = "Last name should be not blank", errorCode = "last.name.should.be.not.blank")
    private String lastName;

    @NotBlank(message = "Email address should be not blank", errorCode = "email.should.be.not.blank")
    @Email(message = "Email address is not valid", errorCode = "email.is.not.valid")
    private String email;

    @NotBlank(message = "Password should be not blank", errorCode = "")
    private String password;

    @NotBlank(message = "Confirm password should be not blank")
    @EqualToField(value = "password", message = "Password should be identical")
    private String confirmPassword;
    @NotBlank(message = "Date of birth should be not blank")
    private String dateOfBirth;

    @NotNull(message = "Gender should be not null")
    private Gender gender;

    private String photoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhotoUrl() {
        if (StringUtils.isEmpty(photoUrl)) {
            return DEFAULT_PHOTO;
        }
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
