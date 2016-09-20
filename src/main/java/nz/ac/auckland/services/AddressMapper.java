package nz.ac.auckland.services;

import nz.ac.auckland.dto.AddressDTO;
import nz.ac.auckland.userDetail.Address;

public class AddressMapper {
	
	public static Address toDomainModel(AddressDTO dto){
		Address cat = new Address(dto.getStreet(),dto.getCity(),dto.getCountry(),dto.getPostcode());
		return cat;
	}
	
	public static AddressDTO toDTO (Address cat){
		AddressDTO dto = new AddressDTO(cat.getStreet(),cat.getCity(),cat.getCountry(),cat.getPostcode());
		return dto;
	}
}
