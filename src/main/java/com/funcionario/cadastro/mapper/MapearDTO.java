package com.funcionario.cadastro.mapper;

import com.funcionario.cadastro.dto.FornecedorDTO;
import com.funcionario.cadastro.dto.FuncDTO;
import com.funcionario.cadastro.dto.ProdutoDTO;
import com.funcionario.cadastro.entidade.Fornecedor;
import com.funcionario.cadastro.entidade.Funcionario;
import com.funcionario.cadastro.entidade.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MapearDTO {

    Funcionario dtoParaEntidade(FuncDTO funcDTO);
    FuncDTO entidadeParaDTO(Funcionario funcionario);

    Fornecedor dtoParaFornecedor(FornecedorDTO fornecedorDTO);
    FornecedorDTO fornecedorParaDto(Fornecedor fornecedor);

    Produto dtoParaProduto(ProdutoDTO produtoDTO);
    ProdutoDTO produtoParaDTO(Produto produto);


    @Mapping(target = "id", ignore = true)
    void atualizarFornecedor(FornecedorDTO dto, @MappingTarget Fornecedor entidade);

    @Mapping(target = "id", ignore = true)
    void atualizarFuncionario(FuncDTO dto, @MappingTarget Funcionario entidade);

    @Mapping(target = "id", ignore = true)
    void atualizarProduto(ProdutoDTO dto, @MappingTarget Produto entidade);


}
