package br.com.qcline.controllers;

import br.com.qcline.service.ProdutoService;
import br.com.qcline.dto.ProdutoDto;
import br.com.qcline.models.ProdutoModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/produto")
public class ProdutoController {
    final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProduto(@RequestBody @Valid ProdutoDto produtoDto) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        produtoModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoModel>> getAllProdutoModel(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduto(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (!produtoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (!produtoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        produtoService.delete(produtoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletedo sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProdutoDto produtoDto) {
        Optional<ProdutoModel> produtoModelOptional = produtoService.findById(id);
        if (!produtoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDto, produtoModel);
        produtoModel.setId(produtoModelOptional.get().getId());
        produtoModel.setDataRegistro(produtoModelOptional.get().getDataRegistro());
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));
    }

}
