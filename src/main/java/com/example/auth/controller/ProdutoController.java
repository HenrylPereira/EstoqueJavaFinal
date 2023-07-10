package com.example.auth.controller;

import com.example.auth.controller.form.ProdutoForm;
import com.example.auth.entity.Categoria;
import com.example.auth.entity.Produto;
import com.example.auth.repositories.CategoriaRepository;
import com.example.auth.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    @PostMapping()
    public Produto novoProduto(@RequestBody ProdutoForm produtoForm) {
        Optional<Categoria> categoria = categoriaRepository.findById(produtoForm.getCategoriaId());
        Categoria cat = categoria.get();
        if(cat.getQuantidade() == null){
            cat.setQuantidade(1L);
        }else{
            cat.setQuantidade(cat.getQuantidade() + 1);
        }
        categoriaRepository.save(cat);
        Produto produto = new Produto(produtoForm);
        produto.setCategoria(categoria.get());
        return produtoRepository.save(produto);
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }

    @GetMapping("/{id}")
    public Optional<Produto> findById(@PathVariable("id") Long id) {
        return produtoRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Produto updateById(@RequestBody ProdutoForm form, @PathVariable("id") Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(form.getCategoriaId());
        Produto produto = new Produto(form);
        produto.setCategoria(categoria.get());
        produto.setId(id);
        return produtoRepository.save(produto);
    }
}
