package tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public class BatchTransparentBackground {

    public static void main(String[] args) {
        // Danh sách ảnh cần xử lý (thay đổi đường dẫn tùy ý)
        List<String> imagePaths = List.of(
                "assets/images/characters/naruto/animation/jumping/Naruto-147.png",
                "assets/images/characters/naruto/animation/jumping/Naruto-148.png",
                "assets/images/characters/naruto/animation/jumping/Naruto-149.png",
                "assets/images/characters/naruto/animation/running/Naruto-108.png",
                "assets/images/characters/naruto/animation/running/Naruto-109.png",
                "assets/images/characters/naruto/animation/running/Naruto-110.png",
                "assets/images/characters/naruto/animation/running/Naruto-111.png",
                "assets/images/characters/naruto/animation/running/Naruto-112.png",
                "assets/images/characters/naruto/animation/running/Naruto-113.png",
                "assets/images/characters/naruto/animation/standing/Naruto-101.png",
                "assets/images/characters/naruto/animation/standing/Naruto-102.png",
                "assets/images/characters/naruto/animation/standing/Naruto-103.png",
                "assets/images/characters/naruto/animation/standing/Naruto-104.png",
                "assets/images/UchihaHideout.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-272.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-273.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-274.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-275.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-276.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-277.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-278.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-279.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-280.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-281.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-282.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-283.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-284.png",
                "assets/images/characters/naruto/animation/normalAttacking/Naruto-285.png",
                "assets/images/characters/itachi/animation/jumping/Itachi-135.png",
                "assets/images/characters/itachi/animation/jumping/Itachi-136.png",
                "assets/images/characters/itachi/animation/jumping/Itachi-137.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-261.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-262.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-263.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-264.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-265.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-266.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-267.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-268.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-269.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-270.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-271.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-272.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-273.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-274.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-275.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-276.png",
                "assets/images/characters/itachi/animation/normalAttacking/Itachi-277.png",
                "assets/images/characters/itachi/animation/running/Itachi-107.png",
                "assets/images/characters/itachi/animation/running/Itachi-108.png",
                "assets/images/characters/itachi/animation/running/Itachi-109.png",
                "assets/images/characters/itachi/animation/running/Itachi-110.png",
                "assets/images/characters/itachi/animation/running/Itachi-111.png",
                "assets/images/characters/itachi/animation/running/Itachi-112.png",
                "assets/images/characters/itachi/animation/standing/Itachi-97.png",
                "assets/images/characters/itachi/animation/standing/Itachi-98.png",
                "assets/images/characters/itachi/animation/standing/Itachi-99.png",
                "assets/images/characters/itachi/animation/standing/Itachi-100.png",
                "assets/images/characters/itachi/animation/standing/Itachi-101.png",
                "assets/images/characters/itachi/animation/standing/Itachi-102.png",
                "assets/images/characters/itachi/animation/standing/Itachi-103.png",
                "assets/images/ItemsEffectsIcons-29.png",
                "assets/images/ItemsEffectsIcons-30.png",
                "assets/images/ItemsEffectsIcons-34.png",
                "assets/images/ItemsEffectsIcons-35.png",
                "assets/images/characters/naruto/profile/ItemsEffectsIcons-40.png",
                "assets/images/characters/itachi/profile/ItemsEffectsIcons-52.png"

        );

        // Màu nền cần xóa (#004080)
        Color bgColor = new Color(0, 64, 128);
        int tolerance = 0; // Ngưỡng chênh lệch màu

        // Xử lý từng ảnh
        for (String inputPath : imagePaths) {
            try {
                processImage(inputPath, bgColor, tolerance);
                System.out.println("Đã xử lý: " + inputPath);
            } catch (IOException e) {
                System.err.println("Lỗi khi xử lý " + inputPath + ": " + e.getMessage());
            }
        }
    }

    private static void processImage(String inputPath, Color bgColor, int tolerance) throws IOException {
        // Đọc ảnh gốc
        BufferedImage inputImage = ImageIO.read(new File(inputPath));

        // Tạo ảnh mới có kênh alpha
        BufferedImage outputImage = new BufferedImage(
                inputImage.getWidth(),
                inputImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB
        );

        // Duyệt qua từng pixel
        for (int y = 0; y < inputImage.getHeight(); y++) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                Color pixelColor = new Color(inputImage.getRGB(x, y), true);

                if (isBackground(pixelColor, bgColor, tolerance)) {
                    outputImage.setRGB(x, y, 0x00FFFFFF); // Trong suốt
                } else {
                    outputImage.setRGB(x, y, pixelColor.getRGB());
                }
            }
        }

        // Tạo tên file đầu ra (thêm "_transparent" trước đuôi .png)
        String outputPath = inputPath;
        ImageIO.write(outputImage, "png", new File(outputPath));
    }

    private static boolean isBackground(Color color, Color bgColor, int tolerance) {
        return Math.abs(color.getRed() - bgColor.getRed()) <= tolerance &&
                Math.abs(color.getGreen() - bgColor.getGreen()) <= tolerance &&
                Math.abs(color.getBlue() - bgColor.getBlue()) <= tolerance;
    }
}