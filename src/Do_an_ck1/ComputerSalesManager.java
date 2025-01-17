package Do_an_ck1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import Data.DAO;


public class ComputerSalesManager extends JFrame implements ActionListener {
    private DAO dao = new DAO(); 
    private JTable table;
    private JTextField searchField, idField, nameField, amountField, unitPriceField;
    private JButton addButton, updateButton, deleteButton, resetButton, addToCartButton, removeFromCartButton, updateCartButton, checkoutButton;
    private JPanel homePanel, cartPanel, customerPanel; 
    private JTable cartTable;
    private ArrayList<ComputerComponents> cartItems = new ArrayList<>(); 
    private JTextField searchField1, idField1, nameField1, sdtField, mailField, jobField, adressField, sourceField, noteField;
    private JButton addButton1, updateButton1, deleteButton1, resetButton1;
    private JTable table1;
    private JPanel orderPanel;
    private JTable orderTable;
    private JTextField searchOrderField;
    private JTextField idOrderField, customerIdField, orderDateField, totalAmountField;
    private JButton resetOrderButton, addOrderButton, updateOrderButton, deleteOrderButton, searchOrderButton, resetButton2;
    private JPanel reportPanel;

    public ComputerSalesManager() {
        setTitle("Quản Lý Bán Hàng Linh Kiện Điện Tử");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 660); // 800
        
        JTabbedPane tabbedPane = new JTabbedPane();

        homePanel = new JPanel(new BorderLayout());
        initSearch();
        initInputPanel();
        initTable();
        tabbedPane.addTab("Trang Chủ", homePanel);

        cartPanel = new JPanel(new BorderLayout()); 
        initCartPanel();
        tabbedPane.addTab("Giỏ Hàng", cartPanel);

        customerPanel = new JPanel(new BorderLayout());
        initCustomerSearch();
        initCustomerInputPanel();
        initCustomerTable();
        tabbedPane.addTab("Thông Tin Khách Hàng", customerPanel);
        
        orderPanel = new JPanel(new BorderLayout());
        initOrderSearchPanel();
        initOrderInputPanel();
        initOrderTable();
        tabbedPane.addTab("Quản Lý Đơn Hàng", orderPanel);

        reportPanel = new JPanel(new BorderLayout());
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);   
        JPanel nullJPanel = new JPanel();
        nullJPanel.add(new JLabel("                                                                     "));
        JPanel nullJPanel1 = new JPanel();
        nullJPanel1.add(new JLabel("                                                                     "));       
        reportPanel.add(chartPanel, BorderLayout.CENTER);
        reportPanel.add(nullJPanel, BorderLayout.EAST);
        reportPanel.add(nullJPanel1, BorderLayout.WEST);
        tabbedPane.addTab("Báo Cáo và Phân Tích", reportPanel);      
        
        JPanel other = new JPanel(new GridLayout(1, 7, 10, 10)); 
        other.add(new JLabel());
        other.add(new JLabel());
        other.add(new JLabel());
        other.add(new JLabel());
        other.add(new JLabel());
        other.add(new JLabel());
        JButton logoutButton = new JButton("Đăng xuất");
        logoutButton.setForeground(Color.RED);
        logoutButton.setOpaque(true); 
        logoutButton.setBorderPainted(false);
        other.add(logoutButton);

        logoutButton.addActionListener(e -> logout());
        
        add(other, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER); 
        setVisible(true);
    }
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đăng xuất?", "Đăng xuất", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
        	new LoginFrame(); 
            dispose();
           
        }
    }

    private void initSearch() {
        JPanel searchPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        searchPanel.add(new JLabel("Nhập tên sản phẩm để tìm kiếm:"));
        searchField = new JTextField();
        searchPanel.add(searchField);
        JButton searchButton = new JButton("Tìm kiếm");
        searchPanel.add(searchButton);
        JButton resetButton = new JButton("Trở lại ban đầu");
        searchPanel.add(resetButton);
        searchPanel.add(new JLabel(""));

        searchButton.addActionListener(e -> timKiemSanPham());
        resetButton.addActionListener(e -> xoaOTimKiemSanPham());
        homePanel.add(searchPanel, BorderLayout.NORTH);
    }

    private void initInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Form nhập thông tin sản phẩm"));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Tên:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Số lượng:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Đơn giá(kVNĐ):"));
        unitPriceField = new JTextField();
        inputPanel.add(unitPriceField);

        addButton = new JButton("Thêm");
        inputPanel.add(addButton);
        updateButton = new JButton("Cập nhật");
        inputPanel.add(updateButton);
        deleteButton = new JButton("Xóa");
        inputPanel.add(deleteButton);
        resetButton = new JButton("Xóa Form");
        inputPanel.add(resetButton);
        addToCartButton = new JButton("Thêm vào giỏ");
        inputPanel.add(addToCartButton);
        addToCartButton.addActionListener(e -> themVaoGio());
        addButton.addActionListener(e -> themSanPham());
        updateButton.addActionListener(e -> capNhatSanPham());
        deleteButton.addActionListener(e -> xoaSanPham());
        resetButton.addActionListener(e -> xoaFormSanPham());
        

        homePanel.add(inputPanel, BorderLayout.EAST);
    }

    private void timKiemSanPham() {
        String searchQuery = searchField.getText();
        if (searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm để tìm kiếm.");
            return;
        }

        ArrayList<ComputerComponents> searchResults = dao.searchItemsByName(searchQuery);

        if (searchResults.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.");
        }

        updateTable(searchResults);
    }

    private void updateTable(ArrayList<ComputerComponents> items) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (ComputerComponents cc : items) {
            model.addRow(new Object[]{cc.getItemID(), cc.getItemName(), cc.getAmount(), cc.getUnitPrice(), cc.displayPrice()});
        }
    }

    private void xoaOTimKiemSanPham() {
        searchField.setText("");
        capNhatBang();
    }

    private void initTable() {
        table = new JTable();
        table.setModel(new DefaultTableModel(new Object[]{"ID sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá(kVNĐ)", "Giá(kVNĐ)"}, 0));
        homePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        capNhatBang();
    }

    private void themSanPham() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int amount = Integer.parseInt(amountField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            ComputerComponents cc = new ComputerComponents(id, name, amount, unitPrice);
            dao.addItem(cc);
            capNhatBang();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng .");
        }
    }

    private void capNhatSanPham() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int amount = Integer.parseInt(amountField.getText());
            double unitPrice = Double.parseDouble(unitPriceField.getText());
            ComputerComponents cc = new ComputerComponents(id, name, amount, unitPrice);
            dao.updateItem(cc);
            capNhatBang();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng .");
        }
    }

    private void xoaSanPham() {
        String id = idField.getText();
        dao.deleteItem(id);
        capNhatBang();
    }

    private void capNhatBang() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (ComputerComponents cc : dao.getListItem()) {
            model.addRow(new Object[]{cc.getItemID(), cc.getItemName(), cc.getAmount(), cc.getUnitPrice(), cc.displayPrice()});
        }
    }
    private void xoaFormSanPham() {
    	idField.setText("");
    	nameField.setText("");
    	amountField.setText("");
    	unitPriceField.setText("");
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initCartPanel() {
        cartTable = new JTable();
        cartTable.setModel(new DefaultTableModel(new Object[]{"Tên sản phẩm", "Số lượng", "Đơn giá(kVNĐ)", "Thành tiền(kVNĐ)"}, 0));
        cartPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);

        JPanel cartControlPanel = new JPanel(new GridLayout(1, 4, 10, 10));

        removeFromCartButton = new JButton("Xóa khỏi giỏ");
        updateCartButton = new JButton("Cập nhật giỏ hàng");
        checkoutButton = new JButton("Thanh toán");

        cartControlPanel.add(removeFromCartButton);
        cartControlPanel.add(updateCartButton);
        cartControlPanel.add(checkoutButton);

        removeFromCartButton.addActionListener(e -> xoaGioHang());
        updateCartButton.addActionListener(e -> capNhatGioHang());
        checkoutButton.addActionListener(e -> thanhToanGioHang());

        cartPanel.add(cartControlPanel, BorderLayout.SOUTH);
    }
 
    private void themVaoGio() {
        int selectedRow = table.getSelectedRow(); 
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để thêm vào giỏ hàng.");
            return;
        }

        String id = (String) table.getValueAt(selectedRow, 0);
        String name = (String) table.getValueAt(selectedRow, 1);
        int amount = (Integer) table.getValueAt(selectedRow, 2);  
        double unitPrice = (Double) table.getValueAt(selectedRow, 3); 

        if (amount < 1) {
            JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không hợp lệ.");
            return;
        }
        ComputerComponents item = new ComputerComponents(id, name, amount, unitPrice);
        cartItems.add(item);  
        capNhatBangGioHang();
    }

    private void xoaGioHang() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để xóa khỏi giỏ hàng.");
            return;
        }
        cartItems.remove(selectedRow);
        capNhatBangGioHang();
    }

    private void capNhatGioHang() {
        int selectedRow = cartTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng để cập nhật.");
            return;
        }

        String quantityStr = JOptionPane.showInputDialog("Nhập số lượng mới:");
        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity < 1) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0.");
                return;
            }

            ComputerComponents item = cartItems.get(selectedRow);
            int stockAmount = dao.getItemAmountById(item.getItemID());
            if (quantity > stockAmount) {
                JOptionPane.showMessageDialog(this, "Số lượng vượt quá tồn kho.");
                return;
            }

            item.setAmount(quantity);
            capNhatBangGioHang();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ.");
        }
    }
    private void thanhToanGioHang() {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống. Vui lòng thêm sản phẩm vào giỏ hàng.");
            return;
        }

        boolean success = true;

        for (ComputerComponents item : cartItems) {
            
            int remainingAmount = dao.getItemAmountById(item.getItemID()) - item.getAmount();
            if (remainingAmount < 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm \"" + item.getItemName() + "\" không đủ số lượng trong kho.");
                success = false;
                break;
            }
           
            ComputerComponents updatedItem = new ComputerComponents(
                item.getItemID(),
                item.getItemName(),
                item.getAmount(), 
                item.getUnitPrice()
            );
            if (!dao.updateItem1(updatedItem)) {
                success = false;
                break;
            }
        }

        if (success) {
            cartItems.clear(); 
            capNhatBangGioHang(); 
            capNhatBang(); 
            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
        } else {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi thanh toán. Vui lòng thử lại.");
        }
    }
    private void capNhatBangGioHang() {
        DefaultTableModel model = (DefaultTableModel) cartTable.getModel();
        model.setRowCount(0);

        for (ComputerComponents item : cartItems) {
            double totalPrice = item.getUnitPrice() * item.getAmount();
            model.addRow(new Object[]{item.getItemName(), item.getAmount(), item.getUnitPrice(), totalPrice});
        }

        capNhatTongGia();
    }

    private void capNhatTongGia() {
        double total = 0;
        for (ComputerComponents item : cartItems) {
            total += item.getUnitPrice() * item.getAmount();
        }

        
        JLabel totalPriceLabel = (JLabel) cartPanel.getClientProperty("totalPriceLabel");
        if (totalPriceLabel == null) {
            totalPriceLabel = new JLabel();
            cartPanel.putClientProperty("totalPriceLabel", totalPriceLabel);
            cartPanel.add(totalPriceLabel, BorderLayout.NORTH);
        }
        totalPriceLabel.setText("Tổng cộng: " + total*1000 + "VND");

    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void initCustomerSearch() {
        JPanel searchPanel1 = new JPanel(new GridLayout(1, 4, 5, 5));
        searchPanel1.add(new JLabel("Nhập tên khách hàng để tìm kiếm:"));
        searchField1 = new JTextField();
        searchPanel1.add(searchField1);
        JButton searchButton1 = new JButton("Tìm kiếm");
        searchPanel1.add(searchButton1);
        JButton resetButton1 = new JButton("Trở lại ban đầu");
        searchPanel1.add(resetButton1);
        searchPanel1.add(new JLabel(""));

        searchButton1.addActionListener(e -> timKiemKhachHang());
        resetButton1.addActionListener(e -> xoaOTimKiemKhachHang());
        customerPanel.add(searchPanel1, BorderLayout.NORTH);
    }

    private void initCustomerInputPanel() {
        JPanel inputPanel1 = new JPanel(new GridLayout(10, 3, 5, 5));
        inputPanel1.setBorder(BorderFactory.createTitledBorder("Form thông tin khách hàng"));

        inputPanel1.add(new JLabel("ID KH:"));
        idField1 = new JTextField();
        inputPanel1.add(idField1);

        inputPanel1.add(new JLabel("Tên KH:"));
        nameField1 = new JTextField();
        inputPanel1.add(nameField1);
        
        inputPanel1.add(new JLabel("Số điện thoại:"));
        sdtField = new JTextField();
        inputPanel1.add(sdtField);

        inputPanel1.add(new JLabel("Mail:"));
        mailField = new JTextField();
        inputPanel1.add(mailField);

        inputPanel1.add(new JLabel("Chức vụ:"));
        jobField = new JTextField();
        inputPanel1.add(jobField);
        
        inputPanel1.add(new JLabel("Nguồn:"));
        sourceField = new JTextField();
        inputPanel1.add(sourceField);

        inputPanel1.add(new JLabel("Địa chỉ:"));
        adressField = new JTextField();
        inputPanel1.add(adressField);
        
        inputPanel1.add(new JLabel("Ghi chú:"));
        noteField = new JTextField();
        inputPanel1.add(noteField);
        
        addButton1 = new JButton("Thêm");
        inputPanel1.add(addButton1);
        updateButton1 = new JButton("Cập nhật");
        inputPanel1.add(updateButton1);
        deleteButton1 = new JButton("Xóa");
        inputPanel1.add(deleteButton1);
        resetButton1 = new JButton("Xóa Form");
        inputPanel1.add(resetButton1);
        addButton1.addActionListener(e -> themKhachHang());
        updateButton1.addActionListener(e -> capNhatKhachHang());
        deleteButton1.addActionListener(e -> xoaKhachHang());
        resetButton1.addActionListener(e -> xoaFormKhachHang());

        customerPanel.add(inputPanel1, BorderLayout.EAST);
    }

    private void timKiemKhachHang() {
        String searchQuery = searchField1.getText().trim();
        if (searchQuery.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sản phẩm để tìm kiếm.");
            return;
        }

        ArrayList<Customer> searchResults = dao.searchCustomerByName(searchQuery);

        if (searchResults.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.");
        }

        updateTable1(searchResults);
    }

    private void updateTable1(ArrayList<Customer> customer) {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        for (Customer cc : customer) {
            model.addRow(new Object[]{cc.getCustomerID(), cc.getCustomerName(), cc.getSdt(), cc.getMail(), cc.getJob(), cc.getSource(),
            		cc.getAddress(), cc.getNote()});
        }
    }

    private void xoaOTimKiemKhachHang() {
        searchField1.setText("");
        capNhatBangKhachHang();
    }

    private void initCustomerTable() {
        table1 = new JTable();
        table1.setModel(new DefaultTableModel(new Object[]{"ID khách hàng", "Tên khách hàng", "Số điện thoại", "Mail", "Chức vụ","Nguồn" ,
        		"Địa chỉ","Ghi chú"}, 0));
        customerPanel.add(new JScrollPane(table1), BorderLayout.CENTER);
        capNhatBangKhachHang();
    }

    private void themKhachHang() {
        try {
            String id = idField1.getText();
            String name = nameField1.getText();
            String sdt = sdtField.getText();
            String mail = mailField.getText();
            String job = jobField.getText();
            String source = sourceField.getText();
            String address = adressField.getText();
            String note = noteField.getText();
            Customer c = new Customer(id, name, sdt, mail, job, source, address, note);
            dao.addCustomer(c);
            capNhatBangKhachHang();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số.");
        }
    }

    private void capNhatKhachHang() {

        try {
            String id = idField1.getText();
            String name = nameField1.getText();
            String sdt = sdtField.getText();
            String mail = mailField.getText();
            String job = jobField.getText();
            String source = sourceField.getText();
            String address = adressField.getText();
            String note = noteField.getText();
            Customer c = new Customer(id, name, sdt, mail, job, source, address, note);
            dao.updateCustomer(c);
            capNhatBangKhachHang();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số.");
        }
    }

    private void xoaKhachHang() {
        String id = idField1.getText();
        dao.deleteCustomer(id);
        capNhatBangKhachHang();
    }
    private void xoaFormKhachHang() {
    	idField1.setText("");
    	nameField1.setText("");
    	sdtField.setText("");
    	mailField.setText("");
    	jobField.setText("");
    	sourceField.setText("");
    	adressField.setText("");
    	noteField.setText("");   	
    }

    private void capNhatBangKhachHang() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0); 
        for (Customer c : dao.getListCustomer()) {
            model.addRow(new Object[]{ c.getCustomerID(), c.getCustomerName(), c.getSdt(), c.getMail(), c.getJob(), c.getSource(),c.getAddress(), c.getNote()});
        }
    }
/////////////////////////////////////////////////////////////////

    private void initOrderTable() {
        orderTable = new JTable(); 
        orderTable.setModel(new DefaultTableModel(
            new Object[]{"ID Đơn hàng", "ID Khách hàng", "Ngày đặt", "Tổng tiền (kVNĐ)"}, 0));
        orderPanel.add(new JScrollPane(orderTable), BorderLayout.CENTER);

        capNhatBangDonHang(); 
    }

    private void initOrderSearchPanel() {
        JPanel searchPanel = new JPanel(new GridLayout(1, 7, 5, 5));
        searchPanel.add(new JLabel("Nhập ID đơn hàng:"));
        searchOrderField = new JTextField();
        searchPanel.add(searchOrderField);
        searchOrderButton = new JButton("Tìm kiếm");
        searchPanel.add(searchOrderButton);       
        resetOrderButton = new JButton("Trở lại ban đầu");
        searchPanel.add(resetOrderButton);
        searchPanel.add(new JLabel());

        searchOrderButton.addActionListener(e -> timKiemDonHang());
        resetOrderButton.addActionListener(e -> xoaOTimKiemDonHang());
        orderPanel.add(searchPanel, BorderLayout.NORTH);
    }
    private void xoaOTimKiemDonHang() {
    	searchOrderField.setText("");
    	capNhatBangDonHang();
    }
    private void timKiemDonHang() {
        String query = searchOrderField.getText();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ID đơn hàng để tìm kiếm.");
            return;
        }

        ArrayList<Order> results = dao.searchOrderById(query);
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy đơn hàng.");
        } else {
            updateOrderTable(results);
        }
    }
    
    private void updateOrderTable(ArrayList<Order> orders) {
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        model.setRowCount(0);

        for (Order order : orders) {
            model.addRow(new Object[]{order.getOrderId(), order.getCustomerId(), order.getOrderDate(), order.getTotalAmount()});
        }
    }
    private void initOrderInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Form thông tin đơn hàng"));

        inputPanel.add(new JLabel("ID Đơn hàng:"));
        idOrderField = new JTextField();
        inputPanel.add(idOrderField);

        inputPanel.add(new JLabel("ID Khách hàng:"));
        customerIdField = new JTextField();
        inputPanel.add(customerIdField);

        inputPanel.add(new JLabel("Ngày đặt:"));
        orderDateField = new JTextField();
        inputPanel.add(orderDateField);

        inputPanel.add(new JLabel("Tổng tiền:"));
        totalAmountField = new JTextField();
        inputPanel.add(totalAmountField);

        addOrderButton = new JButton("Thêm");
        updateOrderButton = new JButton("Cập nhật");
        deleteOrderButton = new JButton("Xóa");
        resetButton2 = new JButton("Xóa Form");
        
        inputPanel.add(addOrderButton);
        inputPanel.add(updateOrderButton);
        inputPanel.add(deleteOrderButton);
        inputPanel.add(resetButton2);
        
        addOrderButton.addActionListener(e -> themDonHang());
        updateOrderButton.addActionListener(e -> capNhatDonHang());
        deleteOrderButton.addActionListener(e -> xoaDonHang());
        resetButton2.addActionListener(e -> xoaFormDonHang());
        orderPanel.add(inputPanel, BorderLayout.EAST);
    }
    private void themDonHang() {
        try {
            String id = idOrderField.getText();
            String customerId = customerIdField.getText();
            String date = orderDateField.getText();
            double total = Double.parseDouble(totalAmountField.getText());

            Order order = new Order(id, customerId, date, total);
            dao.addOrder(order);

            capNhatBangDonHang();     
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số.");
        }
    }

    private void capNhatDonHang() {
        try {
            String id = idOrderField.getText();
            String customerId = customerIdField.getText();
            String date = orderDateField.getText().trim();
            double total = Double.parseDouble(totalAmountField.getText());

            Order order = new Order(id, customerId, date, total);
            dao.updateOrder(order);

            capNhatBangDonHang();
            JOptionPane.showMessageDialog(this, "Cập nhật đơn hàng thành công!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số.");
        }
    }

    private void xoaDonHang() {
        String id = idOrderField.getText();
        dao.deleteOrder(id);
        capNhatBangDonHang();
        JOptionPane.showMessageDialog(this, "Xóa đơn hàng thành công!");
    }
    
    private void xoaFormDonHang() {
    	idOrderField.setText("");
    	customerIdField.setText("");
    	orderDateField.setText("");
    	totalAmountField.setText("");
    }
    
    private void capNhatBangDonHang() {
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel(); 
        model.setRowCount(0);

        for (Order order : dao.getOrderList()) {
            model.addRow(new Object[]{order.getOrderId(), order.getCustomerId(), order.getOrderDate(), order.getTotalAmount()});
        }
    }
    
    /////////////////////
    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        ArrayList<ComputerComponents> items = dao.getListItem();
        for (ComputerComponents item : items) {
            dataset.setValue(item.getItemName(), item.getAmount());
        }

        return dataset;
    }

    private JFreeChart createChart(PieDataset dataset) {
        return ChartFactory.createPieChart("Biểu đồ báo cáo sản phẩm tồn kho", dataset, true,true,true);
    }
    public static void main(String[] args) {
        new ComputerSalesManager();
        
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

