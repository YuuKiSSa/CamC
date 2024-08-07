package org.example.ad.service;

import java.util.List;
import java.util.Optional;

import org.example.ad.DTO.CameraListDTO;
import org.example.ad.DTO.CameraListWithTagDTO;
import org.example.ad.DTO.FavoriteCameraDTO;
import org.example.ad.DTO.FavoriteDTO;
import org.example.ad.model.Camera;
import org.example.ad.model.Customer;
import org.example.ad.model.Favorite;
import org.example.ad.model.Tag;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface CustomerService {

	List<CameraListDTO> findAll();

	Optional<Camera> findById(Long id);

	Optional<Customer> findByUsername(String username);

	String findImageByCameraId(Long id);
	
	List<CameraListWithTagDTO> findAllWithTags();
	
	Favorite addFavorite(Long customerId, Long cameraId, Double idealPrice);
	
	void deleteFavorite(Long customerId, Long cameraId);
	
	List<FavoriteDTO> findFavoritesByCustomerId(Long customerId);
	
	List<FavoriteCameraDTO> findFavoriteCamerasByCustomerId(Long customerId);

}
