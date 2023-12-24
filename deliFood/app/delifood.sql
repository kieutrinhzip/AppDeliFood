-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 27, 2023 lúc 08:49 AM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `delifood`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddonhang` int(11) NOT NULL,
  `idsp` int(11) NOT NULL,
  `soluong` int(11) NOT NULL,
  `gia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`iddonhang`, `idsp`, `soluong`, `gia`) VALUES
(10, 19, 1, '89000'),
(10, 18, 3, '260000'),
(13, 0, 1, '89000'),
(14, 0, 1, '89000'),
(18, 19, 1, '89000'),
(18, 18, 3, '260000'),
(20, 17, 1, '89000'),
(21, 17, 1, '89000'),
(22, 17, 1, '89000'),
(22, 9, 2, '170000'),
(23, 1, 1, '25000'),
(24, 1, 1, '25000'),
(25, 1, 1, '25000'),
(26, 1, 1, '25000'),
(27, 14, 3, '234900'),
(28, 18, 2, '260000'),
(29, 2, 3, '20000'),
(30, 12, 2, '230000'),
(31, 4, 3, '28000'),
(31, 3, 3, '48000'),
(32, 1, 2, '25000'),
(32, 13, 3, '124200'),
(33, 9, 1, '85000'),
(34, 3, 1, '192000'),
(35, 1, 1, '25000'),
(36, 19, 1, '89000'),
(37, 16, 1, '69000'),
(38, 26, 2, '298000'),
(39, 8, 1, '65000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(20) NOT NULL,
  `idUser` int(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `sodienthoai` varchar(255) NOT NULL,
  `diachi` text NOT NULL,
  `soluong` int(11) NOT NULL,
  `tongtien` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `idUser`, `email`, `sodienthoai`, `diachi`, `soluong`, `tongtien`) VALUES
(31, 4, 'h@gmail.com', '094444', 'vku', 6, '228000'),
(32, 5, 'trinhthingoclinh@vku.udn.vn', '093444444', 'vku', 5, '422600'),
(33, 5, 'trinhthingoclinh@vku.udn.vn', '093444444', 'vku', 1, '85000'),
(34, 6, 'nganttm.21it@vku.udn.vn', '0988381800', 'yttggg', 1, '192000'),
(35, 6, 'nganttm.21it@vku.udn.vn', '0988381800', 'hhhhhh', 1, '25000'),
(36, 6, 'nganttm.21it@vku.udn.vn', '0988381800', 'nnnn', 1, '89000'),
(37, 6, 'nganttm.21it@vku.udn.vn', '0988381800', 'dd', 1, '69000'),
(38, 1, 'huyht.21it@vku.udn.vn', '934957709', 'do', 2, '596000'),
(39, 7, 'conghaunguyen118@gmail.com', '0386823982', 'Day Nang', 1, '65000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(20) NOT NULL,
  `nameProduct` varchar(255) NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `nameProduct`, `image`) VALUES
(1, 'Trang chủ', 'https://vietadsgroup.vn/Uploads/files/trangchu-la-gi.png'),
(2, 'Cà phê', 'https://file.vfo.vn/hinh/2014/6/ly-ca-phe-tinh-yeu-7.jpg'),
(3, 'Trà', 'https://png.pngtree.com/png-clipart/20201225/ourlarge/pngtree-original-hand-painted-ice-cube-lemon-tea-hi-tea-style-fruit-png-image_2580655.jpg'),
(4, 'Freeze', 'https://static.riviu.co/image/2021/12/20/2d1902233bdb725176f3df918c982a67_output.jpeg'),
(5, 'Khác', 'https://www.pngall.com/wp-content/uploads/13/Not-Equal-To-Sign-PNG-File.png'),
(8, 'Đơn hàng', 'https://media.istockphoto.com/id/639811872/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-gi%E1%BB%8F-h%C3%A0ng.jpg?s=170667a&w=0&k=20&c=-x6Ppwru9ZmRNHRQqEBjA2RR4sow8hhy21OFmA8Vxr0='),
(94, 'Đăng xuất', 'data:image/png;base64,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');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanphammoi`
--

CREATE TABLE `sanphammoi` (
  `id` int(11) NOT NULL,
  `newName` varchar(255) NOT NULL,
  `newPrice` varchar(255) NOT NULL,
  `newImage` text NOT NULL,
  `newDes` text NOT NULL,
  `newType` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanphammoi`
--

INSERT INTO `sanphammoi` (`id`, `newName`, `newPrice`, `newImage`, `newDes`, `newType`) VALUES
(1, 'Phindi Kem Sữa', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__PHINDI_KEM_SUA.jpg', 'Kem', 1),
(2, 'Bạc Xỉu Đá', '29000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__BAC_XIU.jpg', 'Coffee', 1),
(3, 'Caramel Macchiato', '69000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__CARAMEL_MACCHIATTO.jpg', 'Caramel', 1),
(4, 'Mocha Macchiato', '69000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__MOCHA.jpg', 'Mocha\n', 1),
(7, 'Latte', '65000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__LATTE_1.jpg', 'Latte', 1),
(8, 'Cappuccino', '65000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__CAPPUCINO.jpg', 'Coffee', 1),
(9, 'Americano', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__AMERICANO_NONG.jpg', 'Coffee', 1),
(10, 'Esspresso', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__EXPRESSO.jpg', 'Coffee', 1),
(11, 'Phin sữa đá', '29000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__PHIN_SUADA.jpg', 'Phin', 1),
(12, 'Phindi Choco', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__PHINDI_CHOCO.jpg', 'Choco', 1),
(13, 'Trà Thanh Đào', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__TRA_THANH_DAO-08.jpg', 'Tea', 2),
(14, 'Trà Tuyết Phúc Bồn Tử', '56000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/05_2023/thumbs/270_crop_Granita-Avatarpsd4.png', 'Tea', 2),
(15, 'Trà Tuyết Xoài Đào', '59000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/05_2023/thumbs/270_crop_Granita-Avatarpsd3.png', 'Tea', 2),
(16, 'Trà Sen Vàng', '55000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__TRA_SEN_VANG_CU_NANG.jpg', 'Tea', 2),
(17, 'Trà Xanh Đậu Đỏ', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__TRA_XANH_DAU_DO.jpg', 'Tea', 2),
(18, 'Trà Thạch Vải', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__TRA_TACH_VAI.jpg', 'Tea', 2),
(19, 'Trà Thạch Đào', '45000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__TRA_THANH_DAO-09.jpg', 'Tea', 2),
(20, 'Freeze Socola', '50000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/04_2023/New_product/thumbs/270_crop_HLC_New_logo_5.1_Products__FREEZE_CHOCO.jpg', 'Freeze', 3),
(21, 'Caramel Phin Freeze', '55000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__CARAMEL_FREEZE_PHINDI.jpg', 'Freeze', 3),
(22, 'Classic Phin Freeze', '55000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__CLASSIC_FREEZE_PHINDI.jpg', 'Freeze', 3),
(23, 'Freeze Trà Xanh', '55000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__FREEZE_TRA_XANH.jpg', 'Freeze', 3),
(24, 'Cookies & Cream', '55000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__COOKIES_FREEZE.jpg', 'Freeze', 3),
(25, 'Cà Phê Sữa Lon', '69000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_CA_PHE_SUA_185ml_v3.jpg', 'Coffee', 4),
(26, 'Cà Phê Hòa Tan 3 Trong 1', '149000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__3IN1_PLASTIC.jpg', 'Cofee', 4),
(27, 'Cà Phê Hòa Tan 3 Trong 1', '58000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__3IN1_BOX.jpg', 'Coffee', 4),
(29, 'Truyền Thống 1kg', '275000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_RG-TRADITIONAL-1kg-5.1.png', 'Coffee', 4),
(30, 'Truyền Thống 200gr', '59000', 'https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/thumbs/270_crop_HLC_New_logo_5.1_Products__R_G_SANH_DIEU2.jpg', 'Coffee', 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `mobile` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `email`, `username`, `pass`, `mobile`) VALUES
(1, 'huyht.21it@vku.udn.vn', 'hygomu', '12345', '934957709'),
(2, 'huy@.gmail.com', 'hyu', '12321', '0934xxxx'),
(3, 'dd@gmail.com', 'dd', '1221', '1234567890'),
(4, 'h@gmail.com', 'h', '1221', '094444'),
(5, 'trinhthingoclinh@vku.udn.vn', 'Linh', 'linh', '093444444'),
(6, 'nganttm.21it@vku.udn.vn', 'myngan', '1234', '0988381800'),
(7, 'conghaunguyen118@gmail.com', 'haunguyencongg', '0707', '0386823982');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT cho bảng `sanphammoi`
--
ALTER TABLE `sanphammoi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
