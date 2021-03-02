package br.com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.product.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
