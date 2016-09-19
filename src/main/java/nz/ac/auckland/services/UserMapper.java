package nz.ac.auckland.services;

import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.userDetail.User;

public class UserMapper {
	
	static User toDomainModel(UserDTO dto){
		User user = new User(dto.getId(),dto.getUserName(), 
				dto.getFirstName(),dto.getLastName(),
				dto.getBillingAddress(),dto.getShippingAddress(),dto.getCCDetails());
		return user;
	}
	
	static UserDTO toDTO (User user){
		UserDTO dto = new UserDTO(user.getId(),user.getUserName(), 
				user.getLastName(), user.getFirstName(), 
				user.getBillingAddress(), user.getShippingAddress(), user.getCCDetails());
		return dto;
	}

}
