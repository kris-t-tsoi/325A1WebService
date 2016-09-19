package nz.ac.auckland.services.item;

import nz.ac.auckland.dto.ItemDTO;
import nz.ac.auckland.purchaseItems.Item;

public class ItemMapper {

	
	static Item toDomainModel(ItemDTO dto){
		Item it = new Item(Long.parseLong(dto.getId()), dto.getName(),dto.getPrice());
		return it;
	}
	
	static ItemDTO toDTO (Item it){
		ItemDTO dto = new ItemDTO(it.getId(),it.getName(),it.getPrice());
		return dto;
	}
}
