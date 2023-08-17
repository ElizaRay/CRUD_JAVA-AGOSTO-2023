package org.crud.sregion;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*CRUD: CREATE, READ, UPDATE, DELETE
	 * 
	 * ESTE SERIA EL PRIMER CRUD QUE HACEMOS EN JAVA,
	 * 
	 * ************************NOTA IMPORTANTE**************************
	 * * CRUD QUE TERMINEMOS, CRUD QUE DEBEN HACER CON LA TABLA REPORTE:

		 DROP TABLE REPORTE;
		
		CREATE TABLE REPORTE(
		ID            NUMBER (10) NOT NULL,
		NOMBRE     VARCHAR2 (35) NOT NULL,
		FECHA       VARCHAR2 (35),
		DESCRIPCION  VARCHAR2 (35) NOT NULL,
		CONSTRAINT REPORTE_PK1 PRIMARY KEY (ID));
		
		COMMIT;

	 
	 * */

public class S_Region {
	
	//CONEXION A LA BASE DE DATOS
	@SuppressWarnings("unused")
	private static Connection connection = null;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	public static void connetDataBaseOracle() throws IOException, SQLException{
		try {
			Class.forName(driver).newInstance();
			System.out.println("CARGO EL DRIVER CORRECTAMENTE: ojdbc6.jar");
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
		try {
			connection = DriverManager.getConnection(URL,"SYSTEM", "eliza123");
			System.out.println("CONEXION EXITOSA: Oracle11g");
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	//METODO DE INSERTAR
	public static void insertarS_Region(int id, String name) throws IOException, SQLException{
		try {
			connetDataBaseOracle();
			String sql = "INSERT INTO S_REGION (ID, NAME) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();
			System.out.println("INSERTO EL REGISTRO: "+ id + "," + name);
			
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	//METODO ACTUALIZAR
	public static void updateS_Region(String name, int id) throws IOException, SQLException{
		
		try {
			connetDataBaseOracle();
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps =connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeQuery();
			System.out.println("ACTUALIZO EL REGISTRO: "+ id + "," + name);
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}	
	}
	//METODO ELIMINAR
	public static void delete_Region(int id) throws IOException, SQLException {
		try {
			connetDataBaseOracle();
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();
			System.out.println("ELIMINO EL REGISTRO: " + id);
			
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
		
	}
	//METODO SELECCIONAR REGISTRO POR ID
	public static void selectS_Region(int id) throws IOException, SQLException {
		try {
			connetDataBaseOracle();
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,id);
			ResultSet rSet = ps.executeQuery();
			if (rSet.next()) {
				System.out.println(rSet.getInt("id") + ", " + rSet.getString("name"));
			}
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	//METODO SELECCIONAR REGISTRO COMPLETO
	public static void selectAlls_Region() throws IOException, SQLException {
		try {
			connetDataBaseOracle();
			String sql = "SELECT * FROM S_REGION";
			PreparedStatement  ps = connection.prepareStatement(sql);
			ResultSet rSet = ps.executeQuery();
			while (rSet.next()) {
				
				System.out.println(rSet.getInt("id") + ", " + rSet.getString("name"));
				
			}
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
	}
	
	//INVOCAR AL PROCEDIMIENTO ALMACENADO
	public static void invocaProcedureS_Region(int id, String name) throws IOException, SQLException {
		try {
			connetDataBaseOracle();
			String sql = "CALL proc(?,?)";
			CallableStatement cs = connection.prepareCall(sql);
			cs.setInt(1, id);
			cs.setString(2, name);
			cs.execute();
			System.out.println("EJECUTO CORRECTAMENTE EL PROCEDIMIENTO PROC");
			
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
		}
		
	}

	
	
	public static void main(String[] args) {
		connetDataBaseOracle();
		//insertarS_Region(13, "MICHOACAN");
		//updateS_Region("REYNOSA TAMAULIPAS", 10);
		//delete_Region(13);
		//selectS_Region(1);
		//selectAlls_Region();
		//invocaProcedureS_Region(13,"CDMX");
	
	}
	
}
			
