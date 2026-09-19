package com.digitalfix.catalog.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.digitalfix.catalog.entity.CatalogItem;
import com.digitalfix.catalog.repository.CatalogItemRepository;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    private CatalogItemRepository repository;

    // 1. Obtener todos (GET)
    @GetMapping("/services")
    public List<CatalogItem> getAllItems() {
        return repository.findAll();
    }

    // 2. Crear nuevo (POST)
    @PostMapping("/services")
    public CatalogItem createItem(@RequestBody CatalogItem item) {
        return repository.save(item);
    }

    // 3. Actualizar tarifa o stock (PUT)
    @PutMapping("/services/{id}")
    public ResponseEntity<CatalogItem> updateItem(@PathVariable Long id, @RequestBody CatalogItem itemDetails) {
        return repository.findById(id)
                .map(item -> {
                    item.setName(itemDetails.getName());
                    item.setPrice(itemDetails.getPrice());
                    item.setStock(itemDetails.getStock());
                    item.setType(itemDetails.getType());
                    return ResponseEntity.ok(repository.save(item));
                })
                .orElse(ResponseEntity.notFound().build());
    }

   // 4. Eliminar un repuesto (DELETE)
  @DeleteMapping("/services/{id}")
  public ResponseEntity<String> deleteItem(@PathVariable Long id) {
      if (repository.existsById(id)) {
          repository.deleteById(id);
          return ResponseEntity.ok("Repuesto eliminado con éxito");
      } else {
          return ResponseEntity.notFound().build();
      }
  }
} 