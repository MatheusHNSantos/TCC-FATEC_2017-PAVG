package model.entity.person.employee;

import model.entity.person.Person;
import util.connection.ConnectionFactory;
import util.dialogs.FxDialogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
* 
* @author Matheus Henrique
*/
public class Employee extends Person {
	private int idEmployee;
	private String role;

    public static int LAST_ID_INSERT = -1;

	public Employee(int idPerson, int idEmployee){
		super(idPerson);
		this.setIdEmployee(idEmployee);
		this.Load();

	}

	public Employee(){
	}

	public Employee(int idEmployee){
		this.setIdEmployee(idEmployee);
		this.Load();
	}



	private void Load(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = con.prepareStatement("SELECT * FROM employee WHERE id_employee = ?");
			stmt.setInt(1, this.getIdEmployee());
			rs = stmt.executeQuery();
			rs.next();
			this.setRole(rs.getString("role"));
			this.setIdPerson(rs.getInt("id_person"));
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
	}

	public static ArrayList<Employee> ReadAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Employee> employeesList = new ArrayList<>();
		try{
			stmt = con.prepareStatement("SELECT id_person, id_employee FROM employee");
			rs = stmt.executeQuery();
			while(rs.next()){
				Employee employee = new Employee(rs.getInt("id_person"), rs.getInt("id_employee"));
				employeesList.add(employee);
			}
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Leitura!","class: Employee" + " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return employeesList;
	}

	public void Save(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			super.Save();
			stmt = con.prepareStatement("UPDATE employee SET role = ?, id_person = ?  WHERE id_employee= ?");
			stmt.setString(1, this.getRole());
			stmt.setInt(2, this.getIdPerson());
			stmt.setInt(3, this.getIdEmployee());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Atualização!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void Create(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			super.Create();
			stmt = con.prepareStatement("INSERT INTO employee (role,id_person) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, this.getRole());
			stmt.setInt(2, this.getIdPerson());
			stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            LAST_ID_INSERT = rs.next() ? rs.getInt(1) : -1;
            this.setIdEmployee(LAST_ID_INSERT);
        } catch (SQLException ex) {
			FxDialogs.showException("Erro de Gravação! " ,getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void Delete(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try{
			super.Delete();
			stmt = con.prepareStatement("DELETE FROM employee WHERE id_employee = ?");
			stmt.setInt(1, this.getIdEmployee());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			FxDialogs.showException("Erro de Exclusão!",getClass().getSimpleName()+ " - " + ex.getMessage(),ex);
		}
		finally{
			ConnectionFactory.closeConnection(con,stmt);
		}
	}

	public void setIdEmployee(int idEmployee){
		this.idEmployee = idEmployee;
	}

	public int getIdEmployee(){
		return this.idEmployee;
	}

	public void setRole(String role){
		this.role = role;
	}

	public String getRole(){
		return this.role;
	}

} // END class employee

