package com.app.microserviceproduit;

import com.app.microserviceproduit.model.Product;
import com.app.microserviceproduit.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetProductById() throws Exception {
        Product product = new Product();
        product = repository.save(product);

        mockMvc.perform(get("/api/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateProduct() throws Exception {
        String newProductJson = "{\"titre\":\"New Product\", \"description\":\"Description\", \"image\":\"image.jpg\", \"prix\":100.0}";

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newProductJson))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProduct() throws Exception {
        Product product = new Product();
        product = repository.save(product);
        String updatedProductJson = "{\"titre\":\"Updated Product\", \"description\":\"Updated Description\", \"image\":\"updated_image.jpg\", \"prix\":150.0}";

        mockMvc.perform(put("/api/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteProduct() throws Exception {
        Product product = new Product();
        product = repository.save(product);

        mockMvc.perform(delete("/api/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}