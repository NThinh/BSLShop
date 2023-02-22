package com.example.dell.bslshop.ultil;


public class SeverLocal {
    //ip mạng của máy tính kết nối với xampp.
    public static String localhost = "192.168.2.6:8080";
    public static String localhost1 = "172.20.10.8:8080";
    public static String localhost2 = "192.168.25.41:8080";
    public static String localhost3 = "192.168.1.165:8080";
    public static String localhost4 = "192.168.13.102:8080";
    public static String localhost5 = "192.168.2.12:8080";

    public static String pathProduct = "http://"+ localhost5 + "/server/getSanPham.php";
    public static String pathOrder = "http://"+ localhost5 + "/server/thongTinKhachHang.php";
    public static String pathOrderDetail = "http://"+ localhost5 + "/server/chiTietDonHang.php";
    public static String pathCustomer = "http://"+ localhost5 + "/server/getThongTinKhachHang.php";

}
