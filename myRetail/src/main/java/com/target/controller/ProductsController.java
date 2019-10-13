package com.target.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.target.service.ProductsService;
import com.target.model.Product;

@RestController
@RequestMapping("/products/")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id) throws JSONException {
        Product product = productsService.getProduct(id);
        return product;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Product putProduct(@PathVariable int id, @RequestBody Product product) {
        Product updatedProduct = productsService.putProduct(id, product);
        return updatedProduct;
    }
}