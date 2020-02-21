package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Address;

public interface AddressService {

	Address findOne(Long id);

	List<Address> findAll();

	Address save(Address address);

	List<Address> save(List<Address> addresses);

	Address delete(Long id);

	void delete(List<Long> ids);

	List<Address> findByStreet(String street);
}
