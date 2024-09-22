/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import models.Imagem;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ImageRepository;

/**
 *
 * @author eduar
 */
public class ImageService {

    @Autowired
    ImageRepository imgRepository;

    public Imagem salva(Imagem imagem) {
        return imgRepository.save(imagem);
    }

}
