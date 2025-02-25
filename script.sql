USE [master]
GO
/****** Object:  Database [quan_li_ban_hang_linh_kien_may_tinh]    Script Date: 17/01/2025 11:48:37 SA ******/
CREATE DATABASE [quan_li_ban_hang_linh_kien_may_tinh]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'quan_li_san_pham', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\quan_li_san_pham.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'quan_li_san_pham_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\quan_li_san_pham_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [quan_li_ban_hang_linh_kien_may_tinh].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET ARITHABORT OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET  DISABLE_BROKER 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET  MULTI_USER 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET DB_CHAINING OFF 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET QUERY_STORE = ON
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [quan_li_ban_hang_linh_kien_may_tinh]
GO
/****** Object:  Table [dbo].[donhang]    Script Date: 17/01/2025 11:48:37 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[donhang](
	[IDdonhang] [nvarchar](50) NOT NULL,
	[IDkhachhang] [nvarchar](50) NOT NULL,
	[Ngaydat] [nvarchar](50) NOT NULL,
	[Tongtien] [float] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[khachhang]    Script Date: 17/01/2025 11:48:37 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[khachhang](
	[IDkhachhang] [nchar](50) NOT NULL,
	[Tenkhachhang] [nchar](50) NOT NULL,
	[Sodienthoai] [nvarchar](50) NOT NULL,
	[Mail] [nchar](50) NULL,
	[Chucvu] [nchar](50) NULL,
	[Nguon] [nchar](50) NOT NULL,
	[Diachi] [nchar](50) NOT NULL,
	[Ghichu] [nchar](10) NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[nguoidung]    Script Date: 17/01/2025 11:48:37 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[nguoidung](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[sanpham]    Script Date: 17/01/2025 11:48:37 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sanpham](
	[IDsanpham] [nchar](50) NOT NULL,
	[tensanpham] [nchar](50) NOT NULL,
	[soluong] [int] NOT NULL,
	[dongia] [int] NOT NULL
) ON [PRIMARY]
GO
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0001', N'KH0001', N'22/11/2024', 1525)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0002', N'KH0001', N'22/11/2024', 200)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0003', N'KH0002', N'26/11/2024', 90)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0004', N'KH0003', N'10/12/2024', 150)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0005', N'KH0004', N'12/12/2024', 200)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0006', N'KH0006', N'19/12/2024', 200)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0007', N'KH0005', N'19/12/2024', 356)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0008', N'KH0007', N'25/12/2024', 50)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0009', N'KH0008', N'30/12/2024', 350)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0010', N'KH0010', N'3/1/2025', 140)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0011', N'KH0011', N'7/1/2025', 200)
INSERT [dbo].[donhang] ([IDdonhang], [IDkhachhang], [Ngaydat], [Tongtien]) VALUES (N'DH0012', N'KH0009', N'13/1/2025', 135)
GO
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0001                                            ', N'Trần Đức Mạnh                                     ', N'0905669588', N'ffmanh@gmail.com                                  ', N'Sinh viên                                         ', N'Được giới thiệu                                   ', N'Đà Nẵng                                           ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0002                                            ', N'Lê Tấn Đạt                                        ', N'07051261895', N'fdat092@gmail.com                                 ', N'Sinh viên                                         ', N'Được giới thiệu                                   ', N'Đà Nẵng                                           ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0010                                            ', N'Lê Anh Dũng                                       ', N'077784561', N'dungsioo1#@gmail.com                              ', N'Học sinh                                          ', N'Từ FaceBook                                       ', N'Hà Nội                                            ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0011                                            ', N'Vũ Ngọc Hậu                                       ', N'0915090711', N'hauvu123@gmail.com                                ', N'Học sinh                                          ', N'Từ Instagram                                      ', N'Vinh                                              ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0012                                            ', N'Đặng Công Quân                                    ', N'0978545464', N'quandeptrai@gmail.com                             ', N'Văn phòng                                         ', N'Từ Instagram                                      ', N'Sài Gòn                                           ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0008                                            ', N'Huỳnh Minh Chiến                                  ', N'0978543543', N'chiengayi@gmail.com                               ', N'Thợ hàn                                           ', N'Từ TikTok                                         ', N'Quảng Bình                                        ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0009                                            ', N'Vũ Ngọc Hải                                       ', N'0708161419', N'haicvfs@gmail.com                                 ', N'X                                                 ', N'Được giới thiệu                                   ', N'Bến Tre                                           ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0003                                            ', N'Nguyễn Trần Hiếu                                  ', N'0709426189', N'hieudagsf092@gmail.com                            ', N'Công Nhân                                         ', N'Từ FaceBook                                       ', N'Đà Nẵng                                           ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0004                                            ', N'Trần Văn Trung                                    ', N'0905884112', N'trug5556@gmail.com                                ', N'Thợ cắt tóc                                       ', N'Từ TikTok                                         ', N'Đà Nẵng                                           ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0005                                            ', N'Nguyễn Thị Vy                                     ', N'098754788634', N'vythinguyen5556@gmail.com                         ', N'Thợ Salon                                         ', N'Người quen                                        ', N'Huế                                               ', N'X         ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0006                                            ', N'Đào Đức Bình                                      ', N'0788646428', N'duckbih0z@gmail.com                               ', N'Chủ tịch cty bất động sản                         ', N'Người quen                                        ', N'Đà Nẵng                                           ', N'khách vip ')
INSERT [dbo].[khachhang] ([IDkhachhang], [Tenkhachhang], [Sodienthoai], [Mail], [Chucvu], [Nguon], [Diachi], [Ghichu]) VALUES (N'KH0007                                            ', N'Bùi Xuân Chiến                                    ', N'09496565461', N'chien11@gmail.com                                 ', N'Giáo viên                                         ', N'Được giới thiệu                                   ', N'Đà Nẵng                                           ', N'X         ')
GO
INSERT [dbo].[nguoidung] ([username], [password]) VALUES (N'admin', N'123')
GO
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0001                                            ', N'Quạt Fan Tản nhiệt 12V                            ', 6, 12)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0002                                            ', N'Nguồn máy tính Xigmatek X                         ', 10, 345)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0003                                            ', N'Keo tản nhiệt Arctic MX4 4g                       ', 26, 70)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0004                                            ', N'Ram Máy Tính .RAM PC4                             ', 11, 169)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0005                                            ', N'Dây Cáp Màn Hình Máy Tính VGA                     ', 48, 30)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0006                                            ', N'Giá đỡ LAPTOP, MACBOOK                            ', 19, 100)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0007                                            ', N'Thùng Máy Tính Dell Core i5                       ', 3, 3700)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0008                                            ', N'Giá treo - Arm màn hình máy tính                  ', 25, 359)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0009                                            ', N'Mainboard B360                                    ', 1, 1845)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0010                                            ', N'Dây chuyển nguồn SATA                             ', 20, 25)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0011                                            ', N'Đầu nối USB 50mm                                  ', 42, 57)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0012                                            ', N'Nút kích nguồn có dây                             ', 10, 4)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0013                                            ', N'Ram Hynix 8GB                                     ', 17, 170)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0014                                            ', N'Main Máy Tính h81                                 ', 36, 330)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0015                                            ', N'Vỏ Case VSP AQUAL X2                              ', 60, 380)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0016                                            ', N'Chuột máy tính SIDOTECH P20                       ', 14, 130)
INSERT [dbo].[sanpham] ([IDsanpham], [tensanpham], [soluong], [dongia]) VALUES (N'SP0017                                            ', N'Màn hình máy tính 22 inch                         ', 3, 1400)
GO
USE [master]
GO
ALTER DATABASE [quan_li_ban_hang_linh_kien_may_tinh] SET  READ_WRITE 
GO
