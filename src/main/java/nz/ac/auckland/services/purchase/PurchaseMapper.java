package nz.ac.auckland.services.purchase;

import java.util.ArrayList;

import nz.ac.auckland.dto.PurchaseDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.purchaseItems.Purchase;
import nz.ac.auckland.userDetail.User;

public class PurchaseMapper {
	
	static Purchase toDomainModel(PurchaseDTO dto){
		Purchase pur = new Purchase(dto.getId(), dto.getBuyer(),dto.getItems());
		return pur;
	}
	
	static PurchaseDTO toDTO (Purchase p){
		PurchaseDTO dto = new PurchaseDTO(p.getId(),p.getBuyer(),p.getItems());
		return dto;
	}
}