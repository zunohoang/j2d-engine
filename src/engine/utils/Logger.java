package engine.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    // Mã màu ANSI
    private static final String RESET = "\u001B[0m";      // Reset về mặc định
    private static final String RED = "\u001B[31m";        // Màu đỏ (Lỗi)
    private static final String GREEN = "\u001B[32m";      // Màu xanh lá (Thành công)
    private static final String YELLOW = "\u001B[33m";     // Màu vàng (Cảnh báo)
    private static final String CYAN = "\u001B[36m";       // Màu xanh dương nhạt (Thông tin)
    private static final String WHITE = "\u001B[37m";      // Màu trắng (Mặc định)

    public static void log(Object object, String message) {
        log(object, message, LOG_TYPE.INFO); // Mặc định là thông tin
    }

    public static void log(Object object, String message, LOG_TYPE type) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String className = object.getClass().getSimpleName();

        // Chọn màu cho message
        String messageColor;
        switch (type) {
            case LOG_TYPE.ERROR:
                messageColor = RED;
                break;
            case LOG_TYPE.WARN:
                messageColor = YELLOW;
                break;
            case LOG_TYPE.SUCCESS:
                messageColor = GREEN;
                break;
            default:
                messageColor = CYAN;
                break;
        }

        // In ra console với màu sắc phù hợp
        System.out.println(
                WHITE + "[" + timeStamp + "] " + className + " :" +
                        messageColor + " " + message + RESET
        );
    }
}
