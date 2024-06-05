-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 05, 2024 at 03:27 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dacs3`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `iddonhang` int NOT NULL,
  `idsp` int NOT NULL,
  `soluong` int NOT NULL,
  `gia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`iddonhang`, `idsp`, `soluong`, `gia`) VALUES
(29, 9, 2, '80000'),
(30, 5, 2, '450000'),
(31, 5, 2, '450000'),
(31, 10, 1, '250000'),
(32, 8, 1, '500000'),
(33, 9, 1, '80000'),
(33, 7, 1, '200000'),
(35, 8, 1, '500000'),
(36, 6, 1, '300000'),
(37, 2, 1, '50000'),
(37, 1, 1, '70000'),
(38, 12, 1, '15000'),
(39, 7, 1, '200000'),
(75, 12, 2, '70000'),
(75, 11, 2, '80000'),
(75, 2, 1, '150000'),
(76, 9, 1, '80000'),
(77, 12, 2, '70000'),
(78, 12, 1, '15000'),
(79, 7, 2, '200000'),
(80, 11, 2, '600000'),
(81, 11, 1, '300000'),
(82, 11, 1, '300000'),
(83, 9, 2, '80000'),
(83, 3, 1, '45000'),
(83, 2, 2, '50000'),
(84, 12, 2, '70000'),
(84, 11, 2, '80000'),
(85, 12, 2, '70000'),
(85, 11, 2, '80000'),
(86, 7, 1, '200000'),
(89, 8, 2, '500000'),
(90, 12, 2, '70000'),
(91, 12, 1, '15000'),
(92, 4, 2, '100000'),
(92, 3, 1, '45000'),
(93, 4, 2, '100000'),
(93, 3, 1, '45000'),
(94, 2, 1, '150000'),
(95, 2, 1, '150000'),
(96, 11, 3, '300000'),
(97, 11, 3, '300000'),
(98, 11, 1, '300000'),
(98, 6, 3, '300000'),
(99, 12, 2, '70000'),
(99, 11, 2, '80000'),
(100, 12, 1, '15000'),
(101, 13, 1, '10000'),
(102, 4, 3, '100000'),
(102, 11, 1, '300000'),
(103, 4, 1, '100000'),
(103, 7, 1, '200000'),
(104, 1, 5, '70000'),
(105, 11, 1, '300000'),
(105, 2, 1, '50000'),
(106, 9, 1, '80000'),
(107, 10, 1, '250000'),
(107, 11, 1, '300000');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int NOT NULL,
  `comment` text NOT NULL,
  `user_id` int NOT NULL,
  `idsp` int NOT NULL,
  `create_time` date NOT NULL,
  `update_time` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `comment`, `user_id`, `idsp`, `create_time`, `update_time`) VALUES
(1, 'Sản phẩm tốt', 3, 3, '2024-04-30', '2024-05-17'),
(2, 'ổn không', 3, 3, '2024-04-01', '2024-05-05'),
(11, 'ko', 3, 10, '2024-05-18', '2024-05-18'),
(12, 'hello kitty', 3, 10, '2024-05-18', '2024-05-18'),
(13, '2 sao', 2, 12, '2024-05-19', '2024-05-19'),
(14, 'đắt quá', 4, 7, '2024-05-20', '2024-05-20'),
(15, 'giá cả sao ạ', 5, 12, '2024-05-22', '2024-05-22'),
(16, 'ok', 4, 9, '2024-06-02', '2024-06-02'),
(17, 'ok', 7, 10, '2024-06-03', '2024-06-03');

-- --------------------------------------------------------

--
-- Table structure for table `donhang`
--

CREATE TABLE `donhang` (
  `id` int NOT NULL,
  `iduser` int NOT NULL,
  `diachi` text NOT NULL,
  `sdt` varchar(15) NOT NULL,
  `email` varchar(255) NOT NULL,
  `soluong` int NOT NULL,
  `tongtien` varchar(255) NOT NULL,
  `status` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `donhang`
--

INSERT INTO `donhang` (`id`, `iduser`, `diachi`, `sdt`, `email`, `soluong`, `tongtien`, `status`) VALUES
(87, 2, 'test4', '0339032951', 'tytly@gmail.com', 1, '300000', 0),
(88, 2, 'co chua', '0339032951', 'tytly@gmail.com', 1, '50000', 0),
(89, 2, 'moi nhat', '0339032951', 'tytly@gmail.com', 2, '1000000', 0),
(90, 4, 'danang', '0382635718', 'vy@gmail.com', 1, '20000', 0),
(91, 3, 'kkk', '123', 'lynt@gmail.com', 1, '15000', 0),
(92, 3, 'giao đến đây', '123', 'lynt@gmail.com', 3, '245000', 0),
(93, 3, 'giao đến đây', '123', 'lynt@gmail.com', 3, '245000', 0),
(94, 5, 'ongichkhiem', '0898570140', 'son@gmail.com', 1, '20000', 0),
(95, 5, 'ongichkhiem', '0898570140', 'son@gmail.com', 1, '20000', 0),
(96, 3, 'được không', '123', 'lynt@gmail.com', 3, '900000', 0),
(97, 3, 'Tam Kỳ', '123', 'lynt@gmail.com', 3, '900000', 0),
(98, 3, 'kkk', '123', 'lynt@gmail.com', 4, '1200000', 0),
(99, 3, 'thanh hoa', '258963', 'email', 2, '252000', 0),
(100, 2, 'lăng cô', '0339032951', 'tytly@gmail.com', 1, '15000', 1),
(101, 2, 'aaaaa', '0339032951', 'tytly@gmail.com', 1, '10000', 1),
(102, 3, 'nhà tao', '123', 'lynt@gmail.com', 4, '600000', 1),
(103, 2, 'Thành phố Tam Kỳ', '0339032951', 'tytly@gmail.com', 2, '300000', 0),
(104, 2, 'Trần đức thảo', '0339032951', 'tytly@gmail.com', 5, '350000', 1),
(105, 5, '6 trần đức thảo , Đà Nẵng', '08985714', 'hbsonw2505@gmail.com', 2, '350000', 0),
(106, 4, 'hòa khánh', '0392635718', 'vy@gmail.com', 1, '80000', 1),
(107, 7, '14 tran duc thao', '0123', 'huy@gmail.com', 2, '550000', 1);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int NOT NULL,
  `tensanpham` varchar(255) NOT NULL,
  `hinhanh` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `hinhanh`) VALUES
(1, 'Trang chủ', 'https://png.pngtree.com/png-vector/20190224/ourlarge/pngtree-vector-house-icon-png-image_701136.jpg'),
(2, 'Thuốc không kê đơn', 'https://th.bing.com/th/id/OIP.Y9gMoO7XAezVe42QIaKW7wHaHZ?rs=1&pid=ImgDetMain'),
(3, 'Thực phẩm chức năng', 'https://mypharma.vn/wp-content/uploads/2019/11/thuc-pham-bo-sung-sat.jpg'),
(4, 'Thiết bị y tế', 'https://binhminhmedical.com/wp-content/uploads/2022/04/thiet-bi-y-te-gia-dinh-1.jpg'),
(5, 'Liên hệ', 'https://finpros.vn/uploads/plugin/introduce/2/1573187275-221340289-lien-h.png'),
(7, 'Đơn hàng', 'https://img.lovepik.com/element/40148/6831.png_300.png');

-- --------------------------------------------------------

--
-- Table structure for table `sanphammoi`
--

CREATE TABLE `sanphammoi` (
  `id` int NOT NULL,
  `tensp` varchar(255) NOT NULL,
  `hinhanh` text NOT NULL,
  `mota` text NOT NULL,
  `giasp` int NOT NULL,
  `loai` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `sanphammoi`
--

INSERT INTO `sanphammoi` (`id`, `tensp`, `hinhanh`, `mota`, `giasp`, `loai`) VALUES
(1, 'Viên nén Cetirizin Stella 10mg ', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P04150.png', 'Cách dùng:\r\nCetirizin STADA 10mg được dùng bằng đường uống. Thức ăn có thể làm giảm nồng độ đỉnh trong máu và kéo dài thời gian đạt nồng độ đỉnh nhưng không ảnh hưởng đến mức hấp thụ thuốc, cho nên có thể uống cùng hoặc ngoài bữa ăn. ', 70000, 2),
(2, 'Viên nén điều trị triệu chứng viêm mũi dị ứng', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P15186_1_l.webp', 'Liều dùng: Dùng đường uống.\r\n- Người lớn và trẻ em từ 6 tuổi trở lên, uống 1 viên 10 mg/ngày hoặc 5 mg x 2 lần/ngày', 50000, 2),
(3, 'Siro trị viêm mũi dị ứng, mày đay, ngứa ', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P26147_1.jpg', 'Liều dùng\r\n\r\nNgười lớn và trẻ em trên 12 tuổi: Ngày 1 lần x 10 ml Người bị suy gan hoặc suy thận nặng (độ thanh thải creatinin < 30ml/ phút): 2 ngày 1 lần x 5 ml', 45000, 2),
(4, 'SOSAllergy Syrup (Hộp 12 ống x 5ml)', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P15786_1_l.webp', 'Cách dùng:\r\n- Dùng đường uống. Thuốc có thể dùng cùng hoặc không cùng với thức ăn để làm giảm các triệu chứng liên quan đến viêm mũi dị ứng (gồm viêm mũi dị ứng theo mùa, viêm mũi dị ứng kinh niên) và mày đay.', 100000, 2),
(6, 'Viên uống Lohha hỗ trợ trí não', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P24896_1.jpg', 'Công dụng\r\nHỗ trợ giảm các triệu chứng sa sút trí tuệ, teo não tuổi già như: Giảm trí nhớ, giảm khả năng ngôn ngữ, rối loạn hành vi.', 300000, 3),
(7, 'Viên uống hỗ trợ chống đột quỵ', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P25545_1.jpg', 'Công dụng\r\nBổ sung Nattokinase. Hỗ trợ giảm nguy cơ hình thành huyết khối, hạn chế mỡ máu, giúp tăng cường lưu thông máu', 200000, 3),
(8, 'Viên dầu cá Solgar Omega-3 Fish Oil', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P17806.png', 'Công dụng\r\nBổ sung omega-3 giúp hỗ trợ hạn chế các bệnh tim mạch, giảm xơ vữa động mạch, giảm cholesterol trong máu.', 500000, 3),
(9, 'Nhiệt kế hồng ngoại Kachi JXB-315', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P21654_11.jpg', 'Công dụng\r\nNhiệt kế điện tử hồng ngoại Kachi JXB315 giúp kiểm tra nhanh các trường hợp nhiệt độ cao, giảm nguy cơ dịch lan rộng tối đa.', 80000, 4),
(10, 'Máy đo huyết áp điện tử cổ tay', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P16046_11.jpg', 'Công dụng: Sản phẩm được dùng để đo huyết áp tâm thu, huyết áp tâm trương và nhịp tim.', 250000, 4),
(11, 'Máy đo đường huyết cá nhân', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P09293.png', 'Công dụng\r\nSản phẩm được dùng để giúp bạn phát hiện, kiểm soát các chỉ số liên quan tới bệnh đại tháo đường (Glucose) hiệu quả', 300000, 4),
(12, 'Kim chích máu 28G Linkfar', 'https://prod-cdn.pharmacity.io/e-com/images/ecommerce/1000x1000/P09468_1_l.webp', 'Công dụng\r\nKim chích lấy máu bệnh nhân.', 15000, 4),
(17, 'Panadol', 'https://th.bing.com/th/id/OIP.fd_mR5KT__Joj8xxjUvm3QHaJV?rs=1&pid=ImgDetMain', ' hoạt chất được sử dụng để giảm đau đầu', 50000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int NOT NULL,
  `email` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `mobile` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `pass`, `username`, `mobile`) VALUES
(3, 'lynt@gmail.com', '123', 'diu ly', '123'),
(4, 'vy@gmail.com', '123', 'vy vy', '0392635718'),
(5, 'hbsonw2505@gmail.com', '123', 'hbson', '08985714'),
(6, 'lan@gmail.com', '123', 'lanlan', '0123456'),
(7, 'huy@gmail.com', '123', 'huyhuy', '0123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sanphammoi`
--
ALTER TABLE `sanphammoi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `sanphammoi`
--
ALTER TABLE `sanphammoi`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
