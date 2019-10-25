/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.user;

import dto.user.UserDTO;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserDAO {

    private UserDTO dto = null;

    public String authenticate(String username, String password) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = db.DB.makeConnection();
            if (con != null) {
                String sql = "select fullName, email, addr, phone, birthdate, role from "
                        + "tblUser where username = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                rs = stm.executeQuery();

                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String addr = rs.getString("addr");
                    String phone = rs.getString("phone");
                    Date birthdate = rs.getDate("birthdate");
                    int role = rs.getInt("role");

                    dto = new UserDTO(null, null, null, fullName, email, addr, phone, birthdate, role);

                    if (role == 0) {
                        return "user";
                    } else if (role == 1) {
                        return "admin";
                    }

                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return "unknown";
    }

    public List<UserDTO> getAllUsers() throws ClassNotFoundException, SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = db.DB.makeConnection();
            if (con != null) {
                String sql = "select * from tblUser where role = 0";
                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String email = rs.getString("email");
                    String addr = rs.getString("addr");
                    String phone = rs.getString("phone");
                    Date birthdate = rs.getDate("birthdate");
                    int role = rs.getInt("role");
                    UserDTO dto = new UserDTO(id, username, password, fullName, email, addr, phone, birthdate, role);
                    list.add(dto);
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = db.DB.makeConnection();
            if (con != null) {
                String sql = "update tblUser "
                        + "set username = ?, password = ?, "
                        + "fullName = ?, email = ?, addr = ?, phone = ?, role = ? "
                        + "where id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setString(4, dto.getEmail());
                stm.setString(5, dto.getAddr());
                stm.setString(6, dto.getPhone());
                stm.setInt(7, dto.getRole());
                stm.setString(8, dto.getId());

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean addNewUser(UserDTO dto) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = db.DB.makeConnection();
            if (con != null) {
                String sql = "insert into tblUser values(?,?,?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getId());
                stm.setString(2, dto.getUsername());
                stm.setString(3, dto.getPassword());
                stm.setString(4, dto.getFullName());
                stm.setString(5, dto.getEmail());
                stm.setString(6, dto.getAddr());
                stm.setString(7, dto.getPhone());
                stm.setDate(8, dto.getBirthdate());
                stm.setInt(9, dto.getRole());

                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

}
