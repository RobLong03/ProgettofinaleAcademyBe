package com.betacom.backend.repositories.products;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.backend.model.products.ProductDescription;

public interface IProductDescriptionRepository extends JpaRepository<ProductDescription, Long>{
	@Query("SELECT p FROM ProductDescription p WHERE p.product.id = :productId AND p.lang = :lang")
	Optional<ProductDescription> findByProductIdAndLang(@Param("productId") Long productId, @Param("lang") String lang);

	@Query("SELECT p FROM ProductDescription p WHERE p.product.id = :productId")
	List<Optional<ProductDescription>> findByProductId(@Param("productId") Long productId);

	//da rivedere molto importante
	@Transactional
	@Modifying
	@Query("DELETE FROM ProductDescription p WHERE p.product.id = :productId")
	void deleteAllByProductId(@Param("productId") Long productId);




}
