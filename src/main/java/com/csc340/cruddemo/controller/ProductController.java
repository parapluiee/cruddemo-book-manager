package com.csc340.cruddemo.controller;

import com.csc340.cruddemo.model.Product;
import com.csc340.cruddemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sunny
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired //depend on product service, injected by spring, not needed to instantiate
    ProductService productService;

    @GetMapping("/all") //map get to specific url
    public String getProducts(Model model) {
        model.addAttribute("productList", productService.getProducts());
        return "products"; //return to view called products, if something with same name is returned will show on that view
        //uses specific model.
        //Here adding attribute with name 'productList', with values of a product array
        //if productList does not exist in view "products", error occurs.
    }

    @GetMapping("/id={productId}")
    public String getProduct(@PathVariable String productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        return "product";
    }

    @GetMapping("/delete/id={productId}")
    public String deleteProduct(@PathVariable String productId, Model model) {
        productService.deleteProduct(productId);
        return "redirect:/products/all";
    }

    @PostMapping("/create") //map post to this url
    public String createProduct(Product product) {

        if (product == null) {
            product = new Product("123x", "sample", 1.25);
        }
        productService.createProduct(product);
        return "redirect:/products/all";
    }

    @PostMapping("/update")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/products/all";
    }

    @GetMapping("/new-product")
    public String newProductForm(Model model) {
        return "new-product";
    } //only sends user to view 'new-product'

    @GetMapping("/update/id={productId}")
    public String updateProductForm(@PathVariable String productId, Model model) {
        model.addAttribute("product", productService.getProduct(productId));
        return "update-product";
    }
}
