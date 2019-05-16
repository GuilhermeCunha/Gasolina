/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancogasolina;

/**
 *
 * @author Guilherme
 */
public class BancoGasolina {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Banco banco = new Banco();
        if(banco.conectar()){
            System.out.println("Conectado");
        }else{
            System.out.println("Não conectado");
        }
    }
    
}
