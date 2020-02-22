package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Address;
import jwd.wafepa.service.AddressService;
import jwd.wafepa.support.AddressDTOToAddress;
import jwd.wafepa.support.AddressToAddressDTO;
import jwd.wafepa.web.dto.AddressDTO;

@RestController
@RequestMapping(value = "/api/addresses")
public class ApiAddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private AddressToAddressDTO toDTO;

	@Autowired
	private AddressDTOToAddress toAddress;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<AddressDTO>> getAddresses(@RequestParam(value = "street", required = false) String street) {

		List<Address> addresses;

		if (street != null) {
			addresses = addressService.findByStreet(street);
		} else {
			addresses = addressService.findAll();
		}

		return new ResponseEntity<>(toDTO.convert(addresses), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<AddressDTO> getAddress(@PathVariable Long id) {
		Address address = addressService.findOne(id);
		if (address == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(address), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<AddressDTO> delete(@PathVariable Long id) {
		Address deleted = addressService.delete(id);

		if (deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<AddressDTO> add(@RequestBody AddressDTO newAddress) {

		Address savedAddress = addressService.save(toAddress.convert(newAddress));

		return new ResponseEntity<>(toDTO.convert(savedAddress), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<AddressDTO> edit(@RequestBody AddressDTO address, @PathVariable Long id) {

		if (!id.equals(address.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Address persisted = addressService.save(toAddress.convert(address));

		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}

}
