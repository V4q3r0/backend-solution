package co.com.sofka.questions.model;

import javax.validation.constraints.NotBlank;

public class UserDTO {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String photoURL;

    public UserDTO() {
    }

    public UserDTO(String id, String name, String email, String password, String photoURL) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photoURL = photoURL;
    }

    public UserDTO(String name, String email, String password, String photoURL) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.photoURL = photoURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
