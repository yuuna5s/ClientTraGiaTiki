package GUI;

import ENTRY.sanpham;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

public class Home extends JFrame{
    private JPanel JPanel1;
    private JButton exitButton;
    private JTable table1;
    private JTextField textField;
    private JButton searchButton;
    private javax.swing.JScrollPane JScrollPane;

    public Home(){
        //Chay giao dien len
        setTitle("JFrame menu");
        setContentPane(JPanel1);
        setSize(1000,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setVisible(true);
        //Ket noi voi server

        //Button
        //search
        // -> khi nhấn tìm kiếm -> gửi lên server search tra tên (tra ra danh sách (mã id và tên))
        // danh sách sẽ được client nhận -> hiện lên table

        //Exit
        // -> khi ấn -> ngắt kết nối server và tắt giao diện và ứng dụng.

        //Table
        //-> nhấp chọn -> số row của table -> tương ứng với số thứ tự list -> lấy id
        // ->Mở trang chi tiết sp và id gửi cho server
        // -> server gửi lại những thông tin cần thiết -> client bố trí thông tin lên trang chi tiết sp

    }

    //Chỉnh sửa ảnh
    // can chinh image
    public ImageIcon load(URL linkImage, int k, int m) {/*linkImage là tên icon, k kích thước chiều rộng mình muốn,m chiều dài và hàm này trả về giá trị là 1 icon.*/
        try {
            BufferedImage image = ImageIO.read(linkImage);//đọc ảnh dùng BufferedImage

            int x = k;
            int y = m;
            int ix = image.getWidth();
            int iy = image.getHeight();
            int dx = 0, dy = 0;

            if (x / y > ix / iy) {
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            return new ImageIcon(image.getScaledInstance(dx, dy,
                    image.SCALE_SMOOTH));

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    //Table
    public void setTableSanPham(ArrayList<sanpham> list) {
        DefaultTableModel tableModel = null;
        Vector header = null;
        header = new Vector();
        header.add("Picture");
        header.add("Name");

        tableModel = new DefaultTableModel(header, 0);

        for(sanpham sp : list){
            Vector row = new Vector();

            //Ảnh
            try {
                URL url = new URL(sp.getPath());
                ImageIcon imageIcon = load(url,60,60);
                JLabel image = new JLabel();
                image.setIcon(imageIcon);
                row.add(0, image);
            }
            catch (IOException e){
                row.add(0, "error");
            }

            //Tên
            row.add(1,sp.getTen());

            //
            tableModel.addRow(row);
        }

        table1.setModel(tableModel);
        table1.getColumn("Picture").setCellRenderer(new myTableCellRenderer());

    }

    class myTableCellRenderer implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            table1.setRowHeight(60);
            return (Component) value;
        }
    }


    public void testTable(){

        sanpham sp = new sanpham();
        sp.setId("p123");
        sp.setTen("123");
        sp.setPath("https://salt.tikicdn.com/cache/400x400/ts/product/65/ef/8f/6be7c1cce5b425cbd3d4c6eb8e280dfe.jpg");
        ArrayList<sanpham> list = new ArrayList<sanpham>();
        list.add(sp);

        setTableSanPham(list);
    }



    public static void main(String[] args){
        Home home = new Home();
        home.testTable();
    }

}
