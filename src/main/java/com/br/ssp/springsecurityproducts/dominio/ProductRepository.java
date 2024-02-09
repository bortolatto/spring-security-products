package com.br.ssp.springsecurityproducts.dominio;

import com.br.ssp.springsecurityproducts.dominio.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
