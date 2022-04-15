package com.techelevator.dao;

import com.techelevator.model.Apartment;


import java.util.List;


public interface ApartmentDao {

    List<Apartment> findAll();

    void updateApartment(Apartment apartment, Long id);

    Apartment findApartment(Long id);

    void createApartment(Apartment apartment);

    void deleteApartment(Long id);

    Apartment findAptForCurrentUser();
}
