package jwd.wafepa.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.service.AddressService;
import jwd.wafepa.support.AddressDTOToAddress;
import jwd.wafepa.support.AddressToAddressDTO;

@RestController
@RequestMapping(value = "/api/addresses")
public class ApiAddressController {

	@Autowired
	AddressService addressService;

	@Autowired
	AddressToAddressDTO addressToAddressDTO;

	@Autowired
	AddressDTOToAddress addressDTOToAddress;
	
	
	
}
