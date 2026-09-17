package com.digitalfix.catalog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.digitalfix.catalog.entity.CatalogItem;
import com.digitalfix.catalog.repository.CatalogItemRepository;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogItemRepository repository;

    
    @GetMapping("/services")
    public List<CatalogItem> getAllItems() {
        return repository.findAll();
    }

   
    @PostMapping("/services")
    public CatalogItem createItem(@RequestBody CatalogItem item) {
        return repository.save(item);
    }
}