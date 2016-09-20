package nz.ac.auckland.services;

import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.userDetail.User;

public class UserMapper {
	
	static User toDomainModel(UserDTO dto){
		User user = new User(Long.parseLong(dto.getId()),dto.getUserName()
				,dto.getLastName(), dto.getFirstName(),
				dto.getBillingAddress(),dto.getShippingAddress());
		return user;
	}
	
	public static UserDTO toDTO (User user){
		UserDTO dto = new UserDTO(user.getId(),user.getUserName(), 
				user.getLastName(), user.getFirstName(), 
				user.getBillingAddress(), user.getShippingAddress());
		return dto;
	}

}
