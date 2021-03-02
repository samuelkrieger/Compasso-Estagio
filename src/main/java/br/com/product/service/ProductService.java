package br.com.product.service;

import br.com.product.domain.Product;
import br.com.product.error.BadProductException;
import br.com.product.error.ProductNotFoundException;
import br.com.product.repository.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository repository;

    public ProductService(final ProductRepository repository) {
        this.repository = repository;
    }

    public Product createNewProduct(final Product product) {
        try {
            return this.repository.save(product);
        } catch (Exception ex) {
            log.info("error trying to create new product. {}", ex.getMessage());
            throw new BadProductException(product.getDescription());
        }
    }

    public Product updateProduct(final Long id, final Product product) {
        if (product.getId() == null || !product.getId().equals(id))
            throw new BadProductException(product.getDescription());

        this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        try {
            return this.repository.save(product);
        } catch (Exception ex) {
            log.info("error trying to update product. {}", ex.getMessage());
            throw new BadProductException(product.getDescription());
        }
    }

    public Product findById(final Long id) {
        return this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product deleteById(final Long id) {
        Product product = this.repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        this.repository.deleteById(id);
        return product;
    }
}
