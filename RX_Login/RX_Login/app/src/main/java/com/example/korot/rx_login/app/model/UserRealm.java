package com.example.korot.rx_login.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
/**
 * Created by Root on 07.11.2017.
 */

public class UserRealm extends RealmObject {

    @PrimaryKey
    private long id;
    private String login;
    private String lastName;
    private String fristName;
    private String email;
    private String phone;
    private String product;
    private String sosialToken;
    private String serverToken;

    public UserRealm() {
    }

    public UserRealm(String sosialToken) {
        this.sosialToken = sosialToken;
    }

    @Override
    public String toString() {
        return "UserRealm{" +
                "id=" + id +
                ", sosialToken='" + sosialToken + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRealm userRealm = (UserRealm) o;

        if (id != userRealm.id) return false;
        if (login != null ? !login.equals(userRealm.login) : userRealm.login != null) return false;
        if (lastName != null ? !lastName.equals(userRealm.lastName) : userRealm.lastName != null)
            return false;
        if (fristName != null ? !fristName.equals(userRealm.fristName) : userRealm.fristName != null)
            return false;
        if (email != null ? !email.equals(userRealm.email) : userRealm.email != null) return false;
        if (phone != null ? !phone.equals(userRealm.phone) : userRealm.phone != null) return false;
        if (product != null ? !product.equals(userRealm.product) : userRealm.product != null)
            return false;
        if (sosialToken != null ? !sosialToken.equals(userRealm.sosialToken) : userRealm.sosialToken != null)
            return false;
        return serverToken != null ? serverToken.equals(userRealm.serverToken) : userRealm.serverToken == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (fristName != null ? fristName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (sosialToken != null ? sosialToken.hashCode() : 0);
        result = 31 * result + (serverToken != null ? serverToken.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSosialToken() {
        return sosialToken;
    }
    public void setSosialToken(String sosialToken) {
        this.sosialToken = sosialToken;
    }
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getFristName() {return fristName;}
    public void setFristName(String fristName) {this.fristName = fristName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getProduct() {return product;}
    public void setProduct(String product) {this.product = product;}
    public String getServerToken() {return serverToken;}
    public void setServerToken(String serverToken) {this.serverToken = serverToken;}
}
