package org.example.ad.repository;

import java.util.List;

import org.example.ad.model.CameraImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CameraImageRepository extends JpaRepository<CameraImage, Long>{

	@Query("select i.url from CameraImage i")
	List<String> findAllURL();

	@Query("select i.url from CameraImage i where i.camera.id = :camera_id")
	String findUrlByCameraId(long camera_id);
}
