/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.guilherme.distancia;

import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //String adress1= JOptionPane.showInputDialog("Digite o primeiro endereço: ");
        //String adress2= JOptionPane.showInputDialog("Digite o segundo endereço: ");
        String adress1 = "Rua Gilberto Freyre";
        String adress2= "Rua Hugo Meijon";
        JOptionPane.showMessageDialog(null, "A distância entre os endereços é de: " + Distance.getDistance(adress1, adress2) + "km's");
        
    }
    
}
