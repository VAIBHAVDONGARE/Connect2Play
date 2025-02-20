package com.connect2play.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.connect2play.entities.Turf;
import com.connect2play.entities.TurfImage;

public interface ITurfImageRepository extends JpaRepository<TurfImage, Long> {
	List<TurfImage> findByTurf(Turf turf);
	void deleteByTurf(Turf turf);
	@Query("SELECT ti FROM TurfImage ti WHERE ti.turf.id = :turfId")
	List<TurfImage> findByTurfId(@Param("turfId") Long turfId);

	@Modifying
	@Query("DELETE FROM TurfImage ti WHERE ti.turf.id = :turfId")
	void deleteByTurfId(@Param("turfId") Long turfId);

}
