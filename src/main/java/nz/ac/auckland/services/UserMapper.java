package nz.ac.auckland.services;

import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.userDetail.User;

public class UserMapper {
	
	public static User toDomainModel(UserDTO dto){
		User user = new User(dto.getId(),dto.getUserName()
				,dto.getLastName(), dto.getFirstName(),
				AddressMapper.toDomainModel(dto.getBillingAddress()),AddressMapper.toDomainModel(dto.getShippingAddress()));
		return user;
	}
	
	public static UserDTO toDTO (User user){
		UserDTO dto = new UserDTO(user.getId(),user.getUserName(), 
				user.getLastName(), user.getFirstName(), 
				AddressMapper.toDTO(user.getBillingAddress()), AddressMapper.toDTO(user.getShippingAddress()));
		return dto;
	}

}
