# j2d-engine

**Tool để làm game (sử dụng hoàn toàn Java core)**

---

##  Mục lục
- [Giới thiệu](#giới-thiệu)
- [Tính năng chính](#tính-năng-chính)
- [Điều kiện yêu cầu](#điều-kiện-yêu-cầu)
- [Hướng dẫn sử dụng](#hướng-dẫn-sử-dụng)
- [Cấu trúc thư mục](#cấu-trúc-thư-mục)
- [Ví dụ](#ví-dụ)
- [Đóng góp](#đóng-góp)
- [Hỗ trợ & Liên hệ](#hỗ-trợ--liên-hệ)
- [License](#license)

---

## Giới thiệu  
j2d-engine là một thư viện engine viết hoàn toàn bằng Java (không phụ thuộc vào thư viện bên ngoài), phục vụ cho việc tạo ra các trò chơi 2D. Mục tiêu là cung cấp nền tảng gọn nhẹ, dễ dùng và dễ mở rộng.

⚠️ **Trạng thái dự án:** Dự án hiện tại vẫn đang trong quá trình phát triển, chưa hoàn thiện toàn bộ tính năng (Do vấn đề thời gian của toi chưa có nhiều). Rất hoan nghênh mọi người đóng góp để cùng phát triển engine này mạnh mẽ hơn.  

---

## Tính năng chính
- Vẽ hình học cơ bản: hình chữ nhật, hình tròn, đường thẳng, text,...
- Xử lý sự kiện input: bàn phím, chuột
- Vòng lặp game (game loop): quản lý cập nhật và render
- Hỗ trợ đồ họa Java native (AWT/Swing hoặc Java 2D API)
- Quản lý tài nguyên (assets) như hình ảnh, âm thanh

*(Bạn có thể thêm chi tiết vào đây nếu engine có các tính năng khác như collision detection, sprite animation, v.v.)*

---

## Điều kiện yêu cầu
- Java Development Kit (JDK) phiên bản 17 trở lên 
- Công cụ build (nếu sử dụng): Gradle hoặc Maven (nếu dùng)
- Nếu code hiện tại dùng thư mục `src/`, bạn cũng có thể build và chạy bằng command-line đơn giản với `javac` và `java`.

---

## Hướng dẫn sử dụng

1. Clone repository:
   ```bash
   git clone https://github.com/zunohoang/j2d-engine.git
   cd j2d-engine
````

2. Nếu sử dụng Gradle:

   ```bash
   ./gradlew build
   ./gradlew run
   ```

   (hoặc `gradlew.bat` trên Windows)

3. Nếu chỉ dùng Java thuần:

   ```bash
   javac -d bin src/**/*.java
   java -cp bin com.yourpackage.Main
   ```

4. Tạo game mới:

   * Tạo class kế thừa từ `GameEngine` (ví dụ `MyGame extends GameEngine`)
   * Ghi đè các phương thức như `init()`, `update()`, `render(Graphics2D g)`
   * Khởi chạy từ phương thức `main` để bắt đầu vòng lặp game

---

## Đóng góp

Dự án còn đang phát triển và chưa hoàn chỉnh. Nếu bạn quan tâm, hãy:

* Fork repo này
* Tạo nhánh mới cho tính năng/bugfix
* Gửi Pull Request

Hoặc mở Issue để thảo luận trước khi bắt đầu làm việc.

---

## Hỗ trợ & Liên hệ

Nếu bạn gặp vấn đề, hãy mở issue trên GitHub hoặc liên hệ qua email: **[nguyenvanhoang2005nt@gmail.com](mailto:nguyenvanhoang2005nt@gmail.com)**

---

## License

MIT

```
