package com.betacom.backend.repositories.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.betacom.backend.model.products.Product;

public interface IProductRepository extends JpaRepository<Product,Long> {
	
	@Query("select p from Product p where " +
	           "(:types is null or p.type in :types) and " +
	           "(:minPrice is null or p.price >= :minPrice) and " +
	           "(:maxPrice is null or p.price <= :maxPrice) and " +
	           "(:brands is null or p.brand in :brands)")
	    List<Product> findFilteredProducts(
	        @Param("types") List<String> types,
	        @Param("minPrice") Double minPrice,
	        @Param("maxPrice") Double maxPrice,
	        @Param("brands") List<String> brands
	    );
}
