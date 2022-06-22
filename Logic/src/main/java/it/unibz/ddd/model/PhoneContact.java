package it.unibz.ddd.model;

import java.util.Objects;

//Value Object
public class PhoneContact {
    private String country;
    private String countryCode;
    private String number;
    private PhoneType type;

    public PhoneContact(String country, String countryCode, String number, PhoneType type) {
        this.country = country;
        this.countryCode = countryCode;
        this.number = number;
        this.type = type;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public PhoneType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneContact that = (PhoneContact) o;
        return countryCode.equals(that.countryCode) && number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, number);
    }
}
