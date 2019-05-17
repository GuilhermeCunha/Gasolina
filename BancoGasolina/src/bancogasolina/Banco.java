/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancogasolina;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class Banco {
    private Connection conexao;
    private Statement declaracao;
    private ResultSet resultados;
    
    public boolean conectar(){
        String servidor = "jdbc:mysql://localhost:3306/gasolina?useTimezone=true&serverTimezone=UTC";
        String usuario = "root";
        String senha = "12345678";
        String driver = "com.mysql.cj.jdbc.Driver";
        
        try {
            //JOptionPane.showMessageDialog(null, "ENTREI TRY");
            Class.forName(driver);
            //JOptionPane.showMessageDialog(null, "PASSEI DRIVER");
            this.conexao = (Connection) DriverManager.getConnection(servidor,
            usuario, senha);
            //JOptionPane.showMessageDialog(null, "PASSEI CONEXAO");
            this.declaracao = (Statement) this.conexao.createStatement();
        } catch (Exception e){
            System.out.println("Erro "+ e.getMessage());
        }
        if(this.conexao != null){
            return true;
        } else {
            return false;
        }    
    }
}
