package com.ORS.ProductApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ORS.ProductApplication.common.NotFoundException;
import com.ORS.ProductApplication.entity.ProductPictures;
import com.ORS.ProductApplication.repository.PicturesRepository;

@Service
public class PicturesService {
	@Autowired
	private PicturesRepository pictureRepo;
	
	public List<ProductPictures> addProductPicture(List<ProductPictures> productPictures) {
		return this.pictureRepo.saveAll(productPictures);
	}
	
	public void deletePictureById(String id) throws NotFoundException {
		if(this.pictureRepo.existsById(id)) {
			this.pictureRepo.deleteById(id);
		}else {
			throw new NotFoundException("Pictures with ID "+id+" does not exists.");
		}
	}
	
	public ProductPictures getPictureById(String id) {
		if(this.pictureRepo.existsById(id)) {
			return this.pictureRepo.findById(id).get();
		}
		return null;
	}
	
	public ProductPictures updatePictureById(String id, ProductPictures picture) {
		if(this.pictureRepo.existsById(id)) {
			ProductPictures updatePicture = this.pictureRepo.findById(id).get();
			updatePicture.setName(picture.getName());
			updatePicture.setLocation(picture.getLocation());
			return this.pictureRepo.save(updatePicture);
		}
		return null;
	}
	
	
}
