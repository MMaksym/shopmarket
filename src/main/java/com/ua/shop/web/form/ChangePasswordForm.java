package com.ua.shop.web.form;

/**
 * Author: Lotus
 * Date: 13.03.12
 */
public class ChangePasswordForm {
    private String email;
    private String hash;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
    private boolean forgotPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

    public boolean isForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(boolean forgotPassword) {
        this.forgotPassword = forgotPassword;
    }
}
