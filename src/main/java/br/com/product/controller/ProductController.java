package br.com.product.controller;

import br.com.product.domain.Product;
import br.com.product.response.ProductCreatedResponse;
import br.com.product.response.ProductDeletedResponse;
import br.com.product.response.ProductFoundResponse;
import br.com.product.response.ProductUpdatedResponse;
import br.com.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "products")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Create Product",
            nickname = "create",
            notes = "Creates a new product",
            response = ProductCreatedResponse.class,
            tags = {"Creation"}
    )
    @ApiResponses({
            @ApiResponse(code = 201, message = "product-created", response = ProductCreatedResponse.class),
            @ApiResponse(code = 400, message = "product-error"),
    })
    public ProductCreatedResponse create(@RequestBody Product product) {
        final Product created = this.service.createNewProduct(product);
        return new ProductCreatedResponse(created);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Update Product",
            nickname = "update",
            notes = "Updates an existing product",
            response = ProductUpdatedResponse.class,
            tags = {"Update"}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "product-updated", response = ProductUpdatedResponse.class),
            @ApiResponse(code = 400, message = "product-error"),
            @ApiResponse(code = 404, message = "product-not-found", response = ProductFoundResponse.class),
    })
    public ProductUpdatedResponse update(@ApiParam(name = "product id") @PathVariable Long id, @RequestBody Product product) {
        final Product updated = this.service.updateProduct(id, product);
        return new ProductUpdatedResponse(updated);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Find Product by Id",
            nickname = "find",
            notes = "Finds an existing product by id",
            response = ProductFoundResponse.class,
            tags = {"Find"}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "product-found", response = ProductFoundResponse.class),
            @ApiResponse(code = 404, message = "product-not-found", response = ProductFoundResponse.class),
    })
    public ProductFoundResponse find(@ApiParam(name = "product id") @PathVariable String id) {
        long ids=Long.parseLong(id);
    	final Product found = this.service.findById(ids);
        return new ProductFoundResponse(found);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Delete Product by Id",
            nickname = "delete",
            notes = "Deletes an existing product by id",
            response = ProductDeletedResponse.class,
            tags = {"Delete"}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "product-found", response = ProductDeletedResponse.class),
            @ApiResponse(code = 404, message = "product-not-found", response = ProductFoundResponse.class),
    })
    public ProductDeletedResponse delete(@ApiParam(name = "product id") @PathVariable Long id) {
        final Product found = this.service.deleteById(id);
        return new ProductDeletedResponse(found);
    }
}
