package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

import Do_an_ck1.ComputerComponents;
import Do_an_ck1.Customer;
import Do_an_ck1.Order;


public class DAO {
    private Connection conn;

    public DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://WIN11:1433;databaseName=quan_li_ban_hang_linh_kien_may_tinh;encrypt=true;trustServerCertificate=true";
            String userName = "sa";
            String password = "123";
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Kết nối thành công cơ sở dữ liệu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM nguoidung WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();  
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ComputerComponents> searchItemsByName(String name) {
        ArrayList<ComputerComponents> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%"); 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ComputerComponents cc = new ComputerComponents(
                    rs.getString("IDsanpham"),
                    rs.getString("tensanpham"),
                    rs.getInt("soluong"),
                    rs.getDouble("dongia")
                );
                list.add(cc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addItem(ComputerComponents cc) {
        if (isIdExist(cc.getItemID())) {
            System.out.println("ID đã tồn tại");
            return false; 
        }
        String sql = "INSERT INTO sanpham (IDsanpham, tensanpham, soluong, dongia) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cc.getItemID());
            ps.setString(2, cc.getItemName());
            ps.setInt(3, cc.getAmount());
            ps.setDouble(4, cc.getUnitPrice());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isIdExist(String itemID) {
        String sql = "SELECT COUNT(*) FROM sanpham WHERE IDsanpham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, itemID);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateItem(ComputerComponents cc) {
        String sql = "UPDATE sanpham SET tensanpham = ?, soluong = ?, dongia = ? WHERE IDsanpham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cc.getItemName());
            ps.setInt(2, cc.getAmount()); 
            ps.setDouble(3, cc.getUnitPrice());
            ps.setString(4, cc.getItemID());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteItem(String id) {
        String sql = "DELETE FROM sanpham WHERE IDsanpham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ComputerComponents> getListItem() {
        ArrayList<ComputerComponents> list = new ArrayList<>();
        String sql = "SELECT * FROM sanpham";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ComputerComponents cc = new ComputerComponents(
                    rs.getString("IDsanpham"),
                    rs.getString("tensanpham"),
                    rs.getInt("soluong"),
                    rs.getDouble("dongia")
                );
                list.add(cc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

////////////////////////
    public int getItemAmountById(String itemId) {
        String sql = "SELECT soluong FROM sanpham WHERE IDsanpham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, itemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("soluong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean updateItem1(ComputerComponents cc) {
        String sql = "UPDATE sanpham SET tensanpham = ?, soluong = soluong - ?, dongia = ? WHERE IDsanpham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cc.getItemName());
            ps.setInt(2, cc.getAmount()); 
            ps.setDouble(3, cc.getUnitPrice());
            ps.setString(4, cc.getItemID());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
////////////////////////////////////////    
    public ArrayList<Customer> searchCustomerByName(String name) {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM khachhang WHERE Tenkhachhang LIKE ?"; // Use correct table and column names
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%"); // Use LIKE for name-based search
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getString("IDkhachhang"),
                    rs.getString("Tenkhachhang"),
                    rs.getString("Sodienthoai"),
                    rs.getString("Mail"),
                    rs.getString("Chucvu"),
                    rs.getString("Nguon"),
                    rs.getString("Diachi"),
                    rs.getString("Ghichu")
                );
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean addCustomer(Customer c) {
        if (isCustomerIdExist(c.getCustomerID())) {
            System.out.println("ID đã tồn tại");
            return false; 
        }
        String sql = "INSERT INTO khachhang (IDkhachhang, Tenkhachhang, Sodienthoai, Mail, Chucvu, Nguon, Diachi, Ghichu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCustomerID());
            ps.setString(2, c.getCustomerName());
            ps.setString(3, c.getSdt());
            ps.setString(4, c.getMail());
            ps.setString(5, c.getJob());
            ps.setString(6, c.getSource());
            ps.setString(7, c.getAddress());
            ps.setString(8, c.getNote());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isCustomerIdExist(String customerID) {
        String sql = "SELECT COUNT(*) FROM khachhang WHERE IDkhachhang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customerID);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCustomer(Customer c) {
        String sql = "UPDATE khachhang SET Tenkhachhang = ?, Sodienthoai = ?, Mail = ?, Chucvu = ?, Nguon = ?, Diachi = ?, Ghichu = ? WHERE IDkhachhang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getCustomerName());
            ps.setString(2, c.getSdt());
            ps.setString(3, c.getMail());
            ps.setString(4, c.getJob());
            ps.setString(5, c.getSource());
            ps.setString(6, c.getAddress());
            ps.setString(7, c.getNote());
            ps.setString(8, c.getCustomerID()); 
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteCustomer(String id) {
        String sql = "DELETE FROM khachhang WHERE IDkhachhang = ?"; 
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate(); 
            return true;
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        return false;
    }

    public ArrayList<Customer> getListCustomer() {
        ArrayList<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM khachhang"; 
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer(
                    rs.getString("IDkhachhang"), 
                    rs.getString("Tenkhachhang"),
                    rs.getString("Sodienthoai"),
                    rs.getString("Mail"),
                    rs.getString("Chucvu"),
                    rs.getString("Nguon"),
                    rs.getString("Diachi"),
                    rs.getString("Ghichu")
                );
                list.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 ////////////////////////////////////////     
    public ArrayList<Order> getOrderList() {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM donhang";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Order order = new Order(
                    rs.getString("IDdonhang"),
                    rs.getString("IDkhachhang"),
                    rs.getString("Ngaydat"),
                    rs.getDouble("Tongtien")
                );
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<Order> searchOrderById(String orderId) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM donhang WHERE IDdonhang LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + orderId + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                    rs.getString("IDdonhang"),
                    rs.getString("IDkhachhang"),
                    rs.getString("Ngaydat"),
                    rs.getDouble("Tongtien")
                );
                list.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addOrder(Order order) {
        if (isIdExist2(order.getOrderId())) {
            System.out.println("ID đã tồn tại");
            return false; 
        }
        String sql = "INSERT INTO donhang (IDdonhang, IDkhachhang, Ngaydat, Tongtien) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, order.getOrderId());
            ps.setString(2, order.getCustomerId());
            ps.setString(3, order.getOrderDate());
            ps.setDouble(4, order.getTotalAmount());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private boolean isIdExist2(String orderID) {
        String sql = "SELECT COUNT(*) FROM sanpham WHERE IDsanpham = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, orderID);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean updateOrder(Order order) {
        String sql = "UPDATE donhang SET IDkhachhang = ?, Ngaydat = ?, Tongtien = ? WHERE IDdonhang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, order.getCustomerId());
            ps.setString(2, order.getOrderDate());
            ps.setDouble(3, order.getTotalAmount());
            ps.setString(4, order.getOrderId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteOrder(String orderId) {
        String sql = "DELETE FROM donhang WHERE IDdonhang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, orderId);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đã đóng kết nối cơ sở dữ liệu.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
         new DAO();
    }
}

