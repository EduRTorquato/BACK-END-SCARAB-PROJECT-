/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import models.Imagem;
import models.Produto;
import models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import repository.ImageRepository;
import repository.ProdutoRepository;

/**
 *
 * @author eduar
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImageRepository imageRepository;

    private ImageService imageService;

    public List<Produto> buscaProdutos() {
        return produtoRepository.findAllOrderByIdDesc();
    }
    
    public List<Produto> buscaProdutoPorNome(String nome){
        return produtoRepository.findByNome(nome);
    }
    


    public Produto salvaProduto(Produto produto) {
        System.out.print(produto);

        return produtoRepository.save(produto);
    }

    public void salvarImagensParaProduto(Produto produto, MultipartFile[] files) {
        for (MultipartFile file : files) {
            // Salvar arquivo no sistema de arquivos
            String caminhoImagem = salvarImagemNoSistemaDeArquivos(file);

            // Criar a entidade Imagem e associar ao produto
            Imagem imagem = new Imagem();

            imagem.setUrl(caminhoImagem);
            imagem.setProduto(produto);

            // Salvar a imagem no banco de dados
            imageService.salva(imagem);
        }
    }

    private String salvarImagemNoSistemaDeArquivos(MultipartFile file) {
        try {
            // Definir o caminho para salvar a imagem (ex: /imagens/produto/)
            String diretorio = "C:\\Users\\eduar\\OneDrive\\Área de Trabalho\\FRONT-END 4 Semestre\\FRONT-END-SCARAB-PROJECT\\pi 4 semestre\\IMAGENS\\imgUpload";
            String nomeArquivo = file.getOriginalFilename();
            Path caminho = Paths.get(diretorio + nomeArquivo);

            // Salvar a imagem no caminho especificado
            Files.copy(file.getInputStream(), caminho, StandardCopyOption.REPLACE_EXISTING);

            // Retornar o caminho da imagem que será salvo no banco
            return caminho.toString();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar a imagem", e);
        }
    }

    //Ativa produto no sistema
    public Produto ativaProduto(Produto produto) {
       
        produto.setAtivo(true);
        produtoRepository.save(produto);
        return produto;
    }

    //Desativa produto no sistema
    public Produto desativaProduto(Produto produto) {
        produto.setAtivo(false);
        produtoRepository.save(produto);
        return produto;
    }
}
