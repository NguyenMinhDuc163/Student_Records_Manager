# Student Records Manager

Phần mềm giúp hỗ trợ các chức năng
- Xem thông tin sinh viên
- Xếp lịch học
- Xem điểm thi
- Tính toán, ước lượng GPA của kỳ

## Cài đặt

Để cài đặt dự án, bạn cần chuẩn bị các điều kiện sau:

1. Java Development Kit (JDK) 20.0.1 trở lên.
2. Intellij IDE hoặc IDE tương tự
3. Mysql Server và công cụ quản lý cơ sở dữ liệu
4. Maven 3.11.0 trở lên.


Sau khi bạn đã cài đặt JDK và Maven, hãy thực hiện các bước sau:

```sh
git clone https://github.com/NguyenMinhDuc163/Student_Records_Manager.git
cd Student_Records_Manager
mvn clean javafx:run
```
### Nếu không sử dụng maven hãy cài thủ công các thư viện sau:
- [JavaFX Controls 20.0.1](https://mvnrepository.com/artifact/org.openjfx/javafx-controls/20.0.1)
- [JavaFX FXML 20.0.1](https://mvnrepository.com/artifact/org.openjfx/javafx-fxml/20.0.1)
- [JUnit Jupiter API 5.9.2](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.9.2)
- [JUnit Jupiter Engine 5.9.2](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.9.2)
- [JavaFX Web 22-ea+16](https://mvnrepository.com/artifact/org.openjfx/javafx-web/22-ea+16)
- [Mail 0.4.4](https://mvnrepository.com/artifact/org.libj/mail/0.4.4)
- [MySQL Connector/J 8.0.30](https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.30)

Sau khi tải xuống, hãy theo hướng dẫn cài đặt từng thư viện để hoàn thành quá trình cài đặt.
## Cấu hình

Nếu bạn gặp vấn đề trong quá trình cài đặt hoặc cấu hình, vui lòng kiểm tra tài liệu cụ thể của từng thư viện hoặc liên hệ với chúng tôi.

## Lưu ý

Chắc chắn rằng bạn đã cài đặt các phiên bản phần mềm được yêu cầu để đảm bảo rằng dự án chạy một cách chính xác trên máy tính của bạn.

## Sử dụng
- Khởi đông intelij đợi phần mềm khởi động và thêm những thứ cần thiết
<<<<<<< HEAD
- Khởi động Mysql workbench hoặc phần mềm khác để import cơ sở dữ liệu [DATA BASE](https://drive.google.com/file/d/1W3d3SFNPUaJ_eBFvalbgjRiW2PXUjT8t/view?usp=sharing)
- Quay lại project vào file **../src/main/dao/JDBCUtil** đổi tên userName, passWord, tên DB theo thiết bị của bạn
- Vào app/ main.java để run chương trình.
=======
- Quay lại project vào file ../src/main/dao/JDBCUtil dổid tên userName, passWord, tên DB theo thiết bị của bạn
- Vào app/ main.java để run chương trình.
>>>>>>> dev
