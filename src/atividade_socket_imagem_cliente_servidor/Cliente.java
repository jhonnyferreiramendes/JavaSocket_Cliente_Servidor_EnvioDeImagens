/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_socket_imagem_cliente_servidor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Jhonny
 */
public class Cliente {

    public static void main(String[] args) throws IOException {
        
            //CONECTANDO COM A PORTA DO SERVIDOR 
            Socket socket = new Socket("localhost", 1369);
            //ENVIANDO DADOS DO ARQUIVO PARA O SERVIDOR
            OutputStream outputStream = socket.getOutputStream();
            //BUSCANDO NO LOCAL E PEGANDO A IMAGEM NO HD DO COMPUTADOR 
            BufferedImage image = ImageIO.read(new File("C:\\Users\\Jhonny\\OneDrive\\Imagens\\Naruto\\1913977.jpg"));
            //OBEJTO PARA O ENVIO DE DADOS DA AIMAGEM 
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            //ESCREVENDO A IMAGEM DENTRO DO ARRAY DE OBJETO
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            //PEGANDO A QUANTIDADE DE BYTES PARA SALVA A IMAGEM NO SERVIDOR  
            byte[] tamanho = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            System.out.println(byteArrayOutputStream.size());
            //ENVIANDO O TAMANHO DE BYTES DA IMAGEM
            outputStream.write(tamanho);
            //ENVIANDO A IMAGEM 
            outputStream.write(byteArrayOutputStream.toByteArray());
            //MANDANDO OS DADOS PARA O DISCO
            outputStream.flush();

            
        }

    }

