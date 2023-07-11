package pageAPI;

import by.megatop.avchinnikova.alina.PostObject;

import java.util.Objects;

public class ApiPage {
    private String email;
    private String password;
    private String expectedResult;
    private String actualResult;

    public ApiPage(String email, String password) {
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
        ApiPage that = (ApiPage) o;
        return email == that.email && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}

