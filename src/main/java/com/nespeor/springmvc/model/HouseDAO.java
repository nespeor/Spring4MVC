package com.nespeor.springmvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HouseDAO {

    public List<House> findAll() {
        List<House> list = new ArrayList<House>();
        Connection c = null;
    	String sql = "SELECT * FROM vivienda ORDER BY poblacion";
        try {
            c = ConnectionHelper.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }

    public Integer generateId(){
    	Connection c = null;
    	Integer id = 0;
    	String sql = "SELECT MAX(ID) FROM vivienda";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            	id = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return id+1;
    }
    
    public List<House> findByPoblacion(String poblacion) {
        List<House> list = new ArrayList<House>();
        Connection c = null;
    	String sql = "SELECT * FROM vivienda as v " +
			"WHERE UPPER(v.poblacion) LIKE ? " +	
			"ORDER BY v.poblacion";
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "%" + poblacion.toUpperCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(processRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return list;
    }
    
    public House findById(int id) {
    	String sql = "SELECT * FROM vivienda WHERE id = ?";
        House house = null;
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                house = processRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return house;
    }

    public House save(House house)
	{
		return house.getId() > 0 ? update(house) : create(house);
	}    
    
    public House create(House house) {
        Connection c = null;
        PreparedStatement ps = null;
        try {
            c = ConnectionHelper.getConnection();
            ps = c.prepareStatement("INSERT INTO house (id, calle, num, codpostal, poblacion, tipo, numhab, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, house.getId());
            ps.setString(2, house.getCalle());
            ps.setInt(3, house.getNum());
            ps.setInt(4, house.getCodpostal());
            ps.setString(5, house.getPoblacion());
            ps.setInt(6, house.getTipo());
            ps.setInt(7, house.getNumhab());
            ps.setString(8, house.getDescripcion());
            ps.executeUpdate();
            
            house.setId(house.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return house;
    }

    public House update(House house) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE house SET calle=?, num=?, codpostal=?, poblacion=?, tipo=?, numhab=?, descripcion=? WHERE id=?");
            ps.setInt(1, house.getId());
            ps.setString(2, house.getCalle());
            ps.setInt(3, house.getNum());
            ps.setInt(4, house.getCodpostal());
            ps.setString(5, house.getPoblacion());
            ps.setInt(6, house.getTipo());
            ps.setInt(7, house.getNumhab());
            ps.setString(8, house.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
        return house;
    }

    public boolean remove(int id) {
        Connection c = null;
        try {
            c = ConnectionHelper.getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM house WHERE id=?");
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
		} finally {
			ConnectionHelper.close(c);
		}
    }

    protected House processRow(ResultSet rs) throws SQLException {
        House house = new House();
        house.setId(rs.getInt("id"));
        house.setCalle(rs.getString("calle"));
        house.setNum(rs.getInt("num"));
        house.setCodpostal(rs.getInt("codpostal"));
        house.setPoblacion(rs.getString("poblacion"));
        house.setTipo(rs.getInt("tipo"));
        house.setNumhab(rs.getInt("numhab"));
        house.setDescripcion(rs.getString("descripcion"));
        return house;
    }
    
}
