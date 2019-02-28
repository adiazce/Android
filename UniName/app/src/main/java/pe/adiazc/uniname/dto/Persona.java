package pe.adiazc.uniname.dto;

import com.google.gson.annotations.SerializedName;

public class Persona {
     @SerializedName("name")
        private     String name;
    @SerializedName("phone")
        private     String phone;
    @SerializedName("email")
        private     String email;
    @SerializedName("photo")
        private     String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
