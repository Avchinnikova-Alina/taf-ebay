package by.megatop.avchinnikova.alina;

import java.util.Objects;

public class PostObject {
    private String email;
    private String password;
    private String expectedResult;
    private String actualResult;

    public PostObject(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "by.megatop.avchinnikova.alina.PostObject{" +
                "email=" + email +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostObject that = (PostObject) o;
        return email == that.email && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
