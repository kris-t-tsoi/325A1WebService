package nz.ac.auckland.services;

import nz.ac.auckland.dto.ItemDTO;
import nz.ac.auckland.purchaseItems.Item;

public class ItemMapper {

	
	public static Item toDomainModel(ItemDTO dto){
		Item it = new Item(dto.getId(), dto.getName(),dto.getPrice());
		return it;
	}
	
	public static ItemDTO toDTO (Item it){
		ItemDTO dto = new ItemDTO(it.getId(),it.getName(),it.getPrice());
		return dto;
	}
}
