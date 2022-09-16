/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atividade_socket_imagem_cliente_servidor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;

/**
 *
 * @author Jhonny
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // INICANDO O SERVIROD E CRIANDO UMA PORTA COMO PARAMETRO 
        ServerSocket serverSocket = new ServerSocket(1369);
        System.out.println("Esperndo conexão com o servidor...");
        // ESPERANDO A CONEXÃO COM O CLIENTE
        Socket socket = serverSocket.accept();
        //RECEBENDO E LENDO OS DADOS COM O ImputStream
        InputStream inputStream = socket.getInputStream();
      
        //PEGANDO A QUANTIDADE DE BYTES NECESSARIA PRA ARMAZENAR A IMAGEM
         byte[] tamanhoDoArquivo = new byte[4];
         //LENDO O ARQUIVO QUE FOI ENVIADO
         inputStream.read(tamanhoDoArquivo);
         // PEGANDO ARRAY DA BYTES QUE FOI PASSADO ANTERIORMENTE 
         int tamanho = ByteBuffer.wrap(tamanhoDoArquivo).asIntBuffer().get();
         //RECEBDENDO IMAGEM COM O TAMNHO JA ESPECIFICADO
         byte[] imagemArquivo = new byte[tamanho];
         //LENDO A IMAGEM
         inputStream.read(imagemArquivo);
         //CONVERTENDO OS DADOS DO InputStream PARA UM OBJETO IMAGEM
         BufferedImage imagem = ImageIO.read(new ByteArrayInputStream(imagemArquivo));
         //Salvando imagem
         ImageIO.write(imagem, "jpg", new File("C:\\Users\\Jhonny\\OneDrive\\Imagens\\Naruto\\NovaImagenObito.jpg"));
         //FECHANDO SERVIDOR
         serverSocket.close();
         
        
    }
    
}
