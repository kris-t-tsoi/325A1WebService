package nz.ac.auckland.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nz.ac.auckland.dto.CategoryDTO;
import nz.ac.auckland.dto.ItemDTO;
import nz.ac.auckland.purchaseItems.Category;
import nz.ac.auckland.purchaseItems.Item;

public class ItemMapper {

	
	public static Item toDomainModel(ItemDTO dto){
		Set<Category> cat = new HashSet<Category>();
		for(CategoryDTO i :dto.getCategories()){
			cat.add(CategoryMapper.toDomainModel(i));
		}
		Item it = new Item(dto.getId(), dto.getName(),dto.getPrice(),cat);
		return it;
	}
	
	public static ItemDTO toDTO (Item it){
		Set<CategoryDTO> cat = new HashSet<CategoryDTO>();
		for(Category i :it.getCategories()){
			cat.add(CategoryMapper.toDTO(i));
		}
		ItemDTO dto = new ItemDTO(it.getId(),it.getName(),it.getPrice(),cat);
		return dto;
	}
}
