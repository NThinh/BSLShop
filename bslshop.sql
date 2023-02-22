-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:8111
-- Thời gian đã tạo: Th2 22, 2023 lúc 06:12 PM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `bslshop`
--
CREATE DATABASE IF NOT EXISTS `bslshop` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bslshop`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(10000) NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, 6, 2, 'áo phong', 45456, 1),
(2, 10, 2, 'Quần TRUCKPAIN', 410000, 1),
(3, 11, 4, 'ÁO THUN \"BILLY EILISH\"', 360000, 1),
(4, 12, 1, 'Áo thun Justin', 360000, 1),
(5, 13, 2, 'Quần TRUCKPAIN', 410000, 1),
(6, 14, 4, 'ÁO THUN \"BILLY EILISH\"', 360000, 1),
(7, 15, 4, 'ÁO THUN \"BILLY EILISH\"', 360000, 1),
(8, 16, 24, 'RIPPED TEE', 450000, 1),
(9, 16, 13, 'HEARTLESS BLACK POLO Shirt', 315000, 1),
(10, 17, 1, 'Áo thun Justin', 360000, 1),
(11, 17, 4, 'ÁO THUN \"BILLY EILISH\"', 720000, 2),
(12, 17, 1, 'Áo thun Justin', 720000, 2),
(13, 18, 4, 'ÁO THUN \"BILLY EILISH\"', 720000, 2),
(14, 19, 1, 'Áo thun Justin', 360000, 1),
(15, 20, 2, 'Quần TRUCKPAIN', 820000, 2),
(16, 21, 1, 'Áo thun Justin', 360000, 1),
(17, 21, 2, 'Quần TRUCKPAIN', 410000, 1),
(18, 22, 2, 'Quần TRUCKPAIN', 410000, 1),
(19, 22, 1, 'Áo thun Justin', 360000, 1),
(20, 23, 13, 'HEARTLESS BLACK POLO Shirt', 630000, 2),
(21, 23, 2, 'Quần TRUCKPAIN', 820000, 2),
(22, 23, 2, 'Quần TRUCKPAIN', 820000, 2),
(23, 24, 2, 'Quần TRUCKPAIN', 410000, 1),
(24, 25, 1, 'Áo thun Justin', 360000, 1),
(25, 26, 7, 'BASIC CARDIGAN', 450000, 1),
(26, 27, 5, '\" DENNIS RODMAN \" TSHIRT', 310000, 1),
(27, 28, 5, '\" DENNIS RODMAN \" TSHIRT', 310000, 1),
(28, 29, 1, 'Áo thun Justin', 720000, 2),
(29, 30, 1, 'Áo thun Justin', 360000, 1),
(30, 30, 2, 'Quần TRUCKPAIN', 410000, 1),
(31, 31, 2, 'Quần TRUCKPAIN', 410000, 1),
(32, 32, 2, 'Quần TRUCKPAIN', 410000, 1),
(33, 33, 2, 'Quần TRUCKPAIN', 410000, 1),
(34, 34, 4, 'ÁO THUN \"BILLY EILISH\"', 360000, 1),
(35, 35, 2, 'Quần TRUCKPAIN', 820000, 2),
(36, 36, 2, 'Quần TRUCKPAIN', 410000, 1),
(37, 37, 2, 'Quần TRUCKPAIN', 410000, 1),
(38, 38, 1, 'Áo thun Justin', 720000, 2),
(39, 38, 1, 'Áo thun Justin', 360000, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(200) NOT NULL,
  `sodienthoai` varchar(200) NOT NULL,
  `diachi` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `diachi`, `password`) VALUES
(5, 'thịnh', '0351656', 'hcm', '123456'),
(6, 'ánh', '031232132', 'hcm', '159753'),
(7, 'shgdhf', '132132', 'dsf', '2132'),
(8, 'dfsdf', '34343', 'dsfsdf2', '323'),
(9, 'f', '44', 'dfg', '345'),
(10, 'hgh', '5555', 'tftfft', '6666'),
(11, 'ff', '333', 'sd', '233'),
(12, 'f', '44', 'dfg', 'dfg'),
(13, 'hoang', '123456789', 'hồ hoàn kiếm', '456456'),
(14, 'hoàn kiếm', '025825831', 'hcm', '123123'),
(15, 'hoàn kiếm', '025825831', 'hcm', '123123'),
(16, 'fthong', '4444444444', 'hoa hcm city', '123456'),
(17, 'fthong', '4444444444', 'hoa hcm city', '123456'),
(18, 'fthong', '4444444444', 'hoa hcm city', '123456'),
(19, 'f', '44', 'dfg', '345'),
(20, 'f', '44', 'dfg', '345'),
(21, 'f', '44', 'dfg', '345'),
(22, 'hoàn kiếm', '025825831', 'hcm', '123123'),
(23, 'hoàn kiếm', '025825831', 'hcm', '123123'),
(24, 'hoàn kiếm', '025825831', 'hcm', '123123'),
(25, 'hoàn kiếm', '025825831', 'hcm', '123123'),
(26, 'hoàn kiếm', '025825831', 'hcm', '123123'),
(27, 'nguyễn dình vũ', '0384657011', 'hcm', '123123'),
(28, 'nguyen', '1232323', 'hcm', '123123'),
(29, 'hoang', '0212121', 'hcm', '123123'),
(30, 'hoang năm', '444444', 'hcm', '4444'),
(31, 'nam', '123123123', 'hcm', '1111'),
(32, 'nam', '123123123', 'hcm', '1111'),
(33, 'nam', '123123123', 'hcm', '1111'),
(34, 'nam', '123123123', 'hcm', '1111'),
(35, 'nam', '123123123', 'hcm', '1111'),
(36, 'nam', '123123123', 'hcm', '1111'),
(37, 'sdf', '134234', 'dfsdf', '123123'),
(38, 'sdf', '134234', 'dfsdf', '123123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanhloaisanpham` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanhloaisanpham`) VALUES
(1, 'Áo thun', 'https://cdn-icons-png.flaticon.com/512/1720/1720819.png'),
(2, 'Quần dài', 'https://symbols.vn/wp-content/uploads/2021/11/Mau-icon-quan-denim.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(200) NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(200) NOT NULL,
  `motasanpham` varchar(2000) NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Áo thun Justin', 360000, 'https://product.hstatic.net/200000043184/product/img_9467_cf4b34375353470bbf0ff890f979819e_grande.jpg', 'over size, thoáng mát, phong cách thời thượng ', 1),
(2, 'Quần TRUCKPAIN', 410000, 'https://product.hstatic.net/200000043184/product/09c369a9-df99-4719-b69c-9f0f01920b31_c4ff460ff2af40ccb26071a623121574_grande.jpg', 'Style cuốn hút, thời thượng, thoải mái', 2),
(3, 'Áo Docbertman', 330000, 'https://product.hstatic.net/200000043184/product/c159ca1a-5adb-4f9d-88c0-97b01001c6ae_c5ba8762001c4d34bcb56f500512a4dc_grande.jpg', 'Thời thượng, lôi cuốn, hot trend', 1),
(4, 'ÁO THUN \"BILLY EILISH\"', 360000, 'https://product.hstatic.net/200000043184/product/img_9455_daddc95c64c046969e437321f31ee7af_master.jpg', 'Áo đẹp, thiết kế thời trang.', 1),
(5, '\" DENNIS RODMAN \" TSHIRT', 310000, 'https://product.hstatic.net/200000043184/product/rodman_03_ae404a4eed544b2b9810a6c294111215_master.jpg', 'Model, cotton 100%.', 1),
(6, '\" RIHANNA \" TSHIRT', 360000, 'https://product.hstatic.net/200000043184/product/img_9466_4544d5c176d44618b2435e6187a14c33_1024x1024.jpg', 'Cotton 100%, over size,', 1),
(7, 'BASIC CARDIGAN', 450000, 'https://product.hstatic.net/200000043184/product/a14b09b0-0fbc-44c4-98f1-6951bf81a29c_5f9da461b64f442aa87f945d2ed9d001_1024x1024.jpg', 'Cotton 100%, over size.', 1),
(8, 'BASIC SHORT 2.0', 260000, 'https://product.hstatic.net/200000043184/product/94fc5632-840b-4a6a-beaa-65ea1f5966b2_5d805ea7bf7648bcb127b6116c3aef18_master.jpg', 'Cotton 100%, over size.', 2),
(9, 'BOXY SHIRT - BLACK', 299000, 'https://product.hstatic.net/200000043184/product/28fe873e-71e5-4302-8ea0-96b64c70631b_1cf62859e804400f983a46f79585372f_master.jpg', 'Cotton 100%, over size.', 1),
(10, 'LOGO CARGO PANT ( BLACK )', 500000, 'https://product.hstatic.net/200000043184/product/e722cacd-1c7a-4b12-ae2a-77bb261805be_545c66274fc743dcabd283201073932e_master.jpg', 'Cotton 100%, over size.', 2),
(11, 'REMINDER BLACK JEAN', 359000, 'https://product.hstatic.net/200000043184/product/43ba35af-9dda-462f-ac1f-02c8f8cb81e7_dbfbae2829b849748fdd6e1a2889b474_master.jpg', 'Cotton 100%, over size.', 2),
(12, 'ANKH Polo - BLACK', 342000, 'https://product.hstatic.net/200000043184/product/7_5_9e7bee2dec254ef59cd94017edf3ac34_master.jpg', 'Cotton 100%, over size.', 1),
(13, 'HEARTLESS BLACK POLO Shirt', 315000, 'https://product.hstatic.net/200000043184/product/043602ed-4d5e-40a8-9a04-57996feff11b_b00681e395c94d7f8b1edd191ea719b9_master.jpg', 'Cotton 100%, over size.', 1),
(14, 'HATER MẠKE ME FAMOUS BLACK Hoodie\r\n', 500000, 'https://product.hstatic.net/200000043184/product/e2fc6e6a-c0f9-4854-8ea8-a2d1a8ee0b28_6f3352fa2b864ce5b321947848e882ce_master.jpg', 'Cotton 100%, over size.', 1),
(15, '\" ĐƯỢC RỒI ĐI THÔI \" TSHIRT', 360000, 'https://product.hstatic.net/200000043184/product/fdf256d4-0e50-4553-bfd4-6dc8ec491ce5_618bc81013c94a5486df418be1d84b36_master.jpg', 'Cotton 100%, over size.', 1),
(16, '\" ANH TRAO EM TRÁI TIM CÒN EM TRAO ANH 1 LIKE \" TSHIRT', 340000, 'https://product.hstatic.net/200000043184/product/40526747-aeb4-4cef-974d-8ce9d62afd09_fc2e5f688e324521a09a91e882a33700_master.jpg', 'Cotton 100%, over size.', 1),
(17, '\" STEVE JOBS \" TSHIRT', 360000, 'https://product.hstatic.net/200000043184/product/img_9467_cf4b34375353470bbf0ff890f979819e_1024x1024.jpg', 'Cotton 100%, oversize, model.', 1),
(18, '\" THE WEEKND \" TSHIRT', 360000, 'https://product.hstatic.net/200000043184/product/weedken_4_029d377d3b1947af9f7527472a709785_master.jpg', 'Cotton 100%, oversize, model.', 1),
(19, '\" TUPAC SHAKUR \" TSHIRT', 360000, 'https://product.hstatic.net/200000043184/product/tupac_social_meida_03_8f92579809c34d2089c31387f65b1613_master.jpg', 'Cotton 100%, oversize, model.', 1),
(20, 'AUSPICIOUS T-Shirt', 249000, 'https://product.hstatic.net/200000043184/product/83e4ec0d-64d0-482e-bdcf-cfdb959e6fb9_0575d61b7af541a28694f75a58c3e2b7_master.jpg', 'Cotton 100%, oversize, model.', 1),
(21, 'CUPID', 350000, 'https://product.hstatic.net/200000043184/product/536c6c8e-68b2-42d9-9432-c96cb75204f6_2a993b18bc3a450197e78210778043dc_1024x1024.jpg', 'Cotton 100%, oversize, model.', 1),
(22, 'WATER SPLASH HOODIE', 690000, 'https://product.hstatic.net/1000306633/product/water_splash_hoodie_a5c2cf6248ee4463a9b531d38373c316.jpg', 'Cotton 100%, oversize, model.', 1),
(23, 'MOLTING INSIDE TEE', 420000, 'https://product.hstatic.net/1000306633/product/molting_inside_tee_2f74657f92ff48e3a546362a7bdf3c63.jpg', 'Cotton 100%, oversize, model.', 1),
(24, 'RIPPED TEE', 450000, 'https://product.hstatic.net/1000306633/product/ripped_tee_74cd1096cbe84fcfa52e6a1c38c15a9b.jpg', 'Cotton 100%, oversize, model.', 1),
(25, 'WATER SPLASH TEE', 420000, 'https://product.hstatic.net/1000306633/product/water_splash_tee_83aab3727a8143d497d8c286495e8ec0.jpg', 'Cotton 100%, oversize, model.', 1),
(26, 'VIBRANCE TEE', 420000, 'https://product.hstatic.net/1000306633/product/vibrance_tee_db487538c2224048a18b6882a9e5debf.jpg', 'Cotton 100%, oversize, model.', 1),
(27, 'FACED TIEDYE TEE', 420000, 'https://product.hstatic.net/1000306633/product/faced_tiedye_tee_red_9b6d907bd48e4ddabf6855b13a8ab0ea.jpg', 'Cotton 100%, oversize, model.', 1),
(28, 'MADNESS TEE', 390000, 'https://product.hstatic.net/1000306633/product/madness_tee_78b45ded5aea4d3d89586e1be203f343.jpg', 'Cotton 100%, oversize, model.', 1),
(29, 'GARNITURE BLACK SHIRT', 450000, 'https://product.hstatic.net/1000306633/product/garniture_black_shirt_735f2fe4411b4500b8d0bd6ddf06b84d.jpg', 'Cotton 100%, oversize, model.', 1),
(30, 'CONCRETE TIEDYE SWEATER', 590000, 'https://product.hstatic.net/1000306633/product/concrete_tiredye_sweater_c55fafd30e25445bbf3ac3a78bf7c688.jpg', 'Cotton 100%, oversize, model.', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
