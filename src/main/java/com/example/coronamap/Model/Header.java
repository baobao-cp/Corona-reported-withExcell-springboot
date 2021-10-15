package com.example.coronamap.Model;

public class Header {
    private String State;
    private String Country;
    private long TotalcaseLatest;

    @Override
    public String toString() {
        return "Header{" +
                "State='" + State + '\'' +
                ", Country='" + Country + '\'' +
                ", TotalcaseLatest=" + TotalcaseLatest +
                '}';
    }

    public Header() {
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public long getTotalcaseLatest() {
        return TotalcaseLatest;
    }

    public void setTotalcaseLatest(long totalcaseLatest) {
        TotalcaseLatest = totalcaseLatest;
    }
}
