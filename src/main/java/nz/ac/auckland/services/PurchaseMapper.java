package nz.ac.auckland.services;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.dto.ItemDTO;
import nz.ac.auckland.dto.PurchaseDTO;
import nz.ac.auckland.dto.UserDTO;
import nz.ac.auckland.purchaseItems.Item;
import nz.ac.auckland.purchaseItems.Purchase;
import nz.ac.auckland.userDetail.User;

public class PurchaseMapper {
	
	public static Purchase toDomainModel(PurchaseDTO dto){
		List<Item> items = new ArrayList<Item>();
		for(ItemDTO i :dto.getItems()){
			items.add(ItemMapper.toDomainModel(i));
		}
		
		Purchase pur = new Purchase(dto.getId(), dto.getBuyer(),items);
		return pur;
	}
	
	public static PurchaseDTO toDTO (Purchase p){
		List<ItemDTO> items = new ArrayList<ItemDTO>();
		for(Item i :p.getItems()){
			items.add(ItemMapper.toDTO(i));
		}
		PurchaseDTO dto = new PurchaseDTO(p.getId(),p.getBuyer(),items);
		return dto;
	}
}
