package br.com.qcline.ProdutoService;

import br.com.qcline.models.ProdutoModel;
import br.com.qcline.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProdutoService {

final ProdutoRepository produtoRepository;
public ProdutoService(ProdutoRepository produtoRepository) {
      this.produtoRepository = produtoRepository;
}

@Transactional
public ProdutoModel save(ProdutoModel produtoModel) {
    return produtoRepository.save(produtoModel);
}

    public Page<ProdutoModel> findAll(Pageable pageable) {

        return produtoRepository.findAll(pageable);
    }

    public Optional<ProdutoModel> findById(UUID id) {
        return produtoRepository.findById(id);
    }
    @Transactional
    public void delete(ProdutoModel produtoModel) {
        produtoRepository.delete(produtoModel);
    }

}


