package com.soulcode.Servicos.Controllers;


import com.soulcode.Servicos.Services.FuncionarioService;
import com.soulcode.Servicos.Util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("servicos")
public class UploadFileControllers {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping("/funcionarios/enviarFoto/{idFuncionario}")
    public ResponseEntity<Void> enviarFoto(@PathVariable Integer idFuncionario,
                                           MultipartFile file, @RequestParam("nome") String nome) {
        String fileName = nome;
        String uploaaDir = "/C:/Users/CAROL/Desktop/fotosFunc";
        String nomeMaisCaminho = "/C:/Users/CAROL/Desktop/fotosFunc/" + nome;


        try{
            UploadFile.saveFile(uploaaDir, fileName, file);
            funcionarioService.salvarFoto(idFuncionario, nomeMaisCaminho);
        } catch (IOException e) {
           System.out.println("O arquivo não foi enviado" + e.getMessage());
        }
        return ResponseEntity.ok().build();

    }

}