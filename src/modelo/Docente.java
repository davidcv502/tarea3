/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author David Valenzuela
 */
    public class Docente extends Persona{
    private String codigo, salario, fecha_ingreso, fecha_registro;
    private int id;
    Conexion cn;
    public Docente(){}
    public Docente(int id, String codigo, String salario, String fecha_ingreso, String fecha_registro) {
        this.id = id;
        this.codigo = codigo;
        this.salario = salario;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_registro = fecha_registro;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getFecha_ingeso() {
        return fecha_ingreso;
    }

    public void setFecha_ingeso(String fecha_ingeso) {
        this.fecha_ingreso = fecha_ingeso;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "Select id_docente as id_docente, codigo,salario,fecha_ingeso,fecha_registro from docente";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[] = {"id_docente", "Codigo", "Salario", "Fecha Ingeso", "Fecha Registro"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[5];
            while(consulta.next()){
                datos[0] = consulta.getString("id_docente");
                datos[1] = consulta.getString("codigo");
                datos[2] = consulta.getString("salario");
                datos[3] = consulta.getString("fecha_ingeso");
                datos[4] = consulta.getString("fecha_registro");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            cn.cerrar_conexion();
            System.out.println("Error: "+ ex.getMessage());
        }
        return tabla;
    }
    @Override
    public void agregar(){
        try{
            PreparedStatement parametro;
            String query = "INSERT INTO docente (codigo, salario, fecha_ingeso, fecha_registro) VALUES (?, ?, ?, ?);";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1,this.getCodigo());
            parametro.setString(2,this.getSalario());
            parametro.setString(3,this.getFecha_ingeso());
            parametro.setString(4,this.getFecha_registro());
            
            int executar = parametro.executeUpdate();
            
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro ingresado","agregar",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error " + ex.getMessage());
        }
    }
    @Override
    public void modificar(){
        try{
            PreparedStatement parametro;
            String query = "update docente set codigo= ?, salario= ?, fecha_ingeso= ?, fecha_registro= ?"+
            "where id_docente= ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1,this.getCodigo());
            parametro.setString(2,this.getSalario());
            parametro.setString(3,this.getFecha_ingeso());
            parametro.setString(4,this.getFecha_registro());
            parametro.setInt(5, getId());
            
            int executar = parametro.executeUpdate();
            
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro Actualizado","actualizar",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error " + ex.getMessage());
        }
    }
    @Override
    public void eliminar(){
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query;
            query = "delete from docente where id_docente= ?";
            
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
           
            int executar = parametro.executeUpdate();
            
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar) + " Registro eliminado","eliminar",JOptionPane.INFORMATION_MESSAGE);
        }catch(HeadlessException | SQLException ex){
            System.out.println("Error " + ex.getMessage());
        }
    }

 
}


