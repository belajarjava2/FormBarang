/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2latihan;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author DEV
 */
public class koneksi {
    
    public Connection bukakoneksi(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost/java2db","root","AirEngineCoreX3");
            System.out.println("berhasil");
            return con;
        }
        catch (Exception e){
            System.out.println("gagal");
            return null;
        }
    }
    public static void main (String args[]){
        new koneksi().bukakoneksi();
    }

}
