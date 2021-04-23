package com.example.tourgk.model;

public class BookingRequest {
    private String email;
    private Long tourId;
    private int nuTickets;

    public BookingRequest(String email, Long tourId) {
        this.email = email;
        this.tourId = tourId;
    }

    public BookingRequest(String email, Long tourId, int nuTickets) {
        this.email = email;
        this.tourId = tourId;
        this.nuTickets = nuTickets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public int getNuTickets() {
        return nuTickets;
    }

    public void setNuTickets(int nuTickets) {
        this.nuTickets = nuTickets;
    }
}
