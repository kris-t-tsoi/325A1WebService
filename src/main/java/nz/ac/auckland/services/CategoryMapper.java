package nz.ac.auckland.services;

import nz.ac.auckland.dto.CategoryDTO;
import nz.ac.auckland.purchaseItems.Category;

public class CategoryMapper {

	
	public static Category toDomainModel(CategoryDTO dto){
		Category cat = new Category(dto.getId(),dto.getName());
		return cat;
	}
	
	public static CategoryDTO toDTO (Category cat){
		CategoryDTO dto = new CategoryDTO(cat.getId(),cat.getName());
		return dto;
	}
}
