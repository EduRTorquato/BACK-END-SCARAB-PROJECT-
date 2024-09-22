/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author eduar
 */
public class UploadUtil {

    public static boolean uploadImage(MultipartFile img) {
        boolean sucessUpload = false;

        if (!img.isEmpty()) {
            String nomeArquivo = img.getOriginalFilename();

            try {
                String pastaUploadImagem = "C:\\Users\\eduar\\OneDrive\\Área de Trabalho\\FRONT-END 4 Semestre\\FRONT-END-SCARAB-PROJECT\\pi 4 semestre\\IMAGENS\\imgUpload";
                File dir = new File(pastaUploadImagem);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                //criar arquivo na pasta!
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(img.getBytes());
                stream.close();

                System.out.println("Armazenado em: " + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo: " + nomeArquivo + " com sucesso!");

            } catch (Exception e) {
                System.out.println("Arquivo não carregado com sucesso: " + nomeArquivo + " =>" + e.getMessage());
            }
        } else {
               System.out.println("Arquio está vazio :')");
        }
            
        return sucessUpload;
    }

}
