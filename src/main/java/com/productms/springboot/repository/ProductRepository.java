package com.productms.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.productms.springboot.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "select id, name, description, price from Product p "
			+" where (p.name like %:q% or p.description like %:q%) "
			+ " and (p.price between :minprice and :maxprice) ")
	public List<Product> searchProduct(@Param("q") final String q,
			@Param("minprice") final Double minPrice, @Param("maxprice") final Double maxPrice);
	
	@Query(nativeQuery = true, value = "select id, name, description, price from Product p "
			+ " where (p.name like %:q% or p.description like %:q%)")
	public List<Product> searchProduct(@Param("q") final String q);
	
	@Query(nativeQuery = true, value = "select id, name, description, price from Product p "
			+ " where (p.name like %:q% or p.description like %:q%) "
			+ " and p.price >= :minprice")
	public List<Product> searchProductByMinimumPrice(@Param("q") final String q, @Param("minprice") final Double minPrice);
	
	@Query(nativeQuery = true, value = "select id, name, description, price from Product p "
			+ " where (p.name like %:q% or p.description like %:q%) "
			+ " and p.price <= :maxprice")
	public List<Product> searchProductByMaximumPrice(@Param("q") final String q, @Param("maxprice") final Double maxPrice);
	
	@Query(nativeQuery = true, value = "select id, name, description, price from Product p "
			+ " where p.price <= :maxprice")
	public List<Product> searchProductByMaximumPrice(@Param("maxprice") final Double maxPrice);
	
	@Query(nativeQuery = true, value = "select id, name, description, price from Product p "
			+ " where p.price >= :minprice")
	public List<Product> searchProductByMinimumPrice(@Param("minprice") final Double minPrice);
	
	@Query(nativeQuery = true, value = "select id, name, description, price from Product p "
			+ " where p.price between :minprice and :maxprice")
	public List<Product> searchProductByMinAndMaxPrice(@Param("minprice") final Double minPrice,
			@Param("maxprice") final Double maxPrice);
	
}
