package br.com.qcline.repository;

import br.com.qcline.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {


}
