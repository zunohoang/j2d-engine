package engine.graphics;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import engine.components.Component;
import engine.components.ui.*;
import engine.components.ui.Button;
import engine.maths.Vector3D;
import engine.scenes.SceneManager;
import engine.utils.LOG_TYPE;
import engine.utils.Logger;
import game.GameScene;
import org.w3c.dom.*;

import engine.maths.Vector2D;
import engine.objects.GameObject;
import engine.scenes.Scene;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class UILayoutParser {
    public static void loadLayout(String filePath, Scene scene) {
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            NodeList elements = doc.getElementsByTagName("*");
            for (int i = 0; i < elements.getLength(); i++) {
                Node node = elements.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    GameObject obj = createGameObject(element);
                    if (obj != null) {
                        scene.addObject(obj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static GameObject createGameObject(Element element) {
        // Ensure the 'name' attribute exists
        String name = element.hasAttribute("name") ? element.getAttribute("name") : "UnnamedObject";

        // Parse dimensions with default values
        float width = parseDimension(element.getAttribute("width"), SceneManager.getWidth()); // Default width: 100
        float height = parseDimension(element.getAttribute("height"), SceneManager.getHeight()); // Default height: 100

        // Parse position with default value (0, 0)
        Vector2D position = parsePosition(element.getAttribute("position"), new Vector2D((float) SceneManager.getWidth() /2, (float) SceneManager.getHeight() /2));

        // Parse pivot with default value (0, 0)
        Vector2D pivot = parsePivot(element.getAttribute("pivot"), new Vector2D(0, 0));

        // Create the GameObject
        GameObject obj = new GameObject(name, new engine.components.Transform(position, new Vector3D(0, 0, 0), pivot));

        // Get attribute
        String colorText = element.getAttribute("color");
        String[] color = new String[0];
        if(!colorText.isEmpty()){
            color = colorText.split(",");
        }
        String image = element.getAttribute("src");
        String text = element.getAttribute("text");
        String opacity = element.getAttribute("opacity");
        String fontSize = element.getAttribute("fontSize");
        String textColorString = element.getAttribute("textColor");
        String[] textColors = new String[0];
        if(!textColorString.isEmpty()){
            textColors = textColorString.split(",");
        }

        // set
        obj.addComponent(new BoxRenderer(
                (int) width,
                (int) height,
                color.length == 0 ? null : new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]), Integer.parseInt(color[2])),
                image.isEmpty() ? null : Toolkit.getDefaultToolkit().getImage(image),
                text,
                opacity.isEmpty() ? 1 : Float.parseFloat(opacity),
                text.isEmpty() ? null : new Font("Arial", Font.PLAIN, Integer.parseInt(fontSize)),
                text.isEmpty() ? null : new Color(Integer.parseInt(textColors[0]), Integer.parseInt(textColors[1]), Integer.parseInt(textColors[2]))
        ));

        Logger.log(UILayoutParser.class, "Created GameObject: " + name, LOG_TYPE.INFO);
        return obj;
    }

    private static float parseDimension(String value, float defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        if (value.equals("match_parent")) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            System.err.println("Invalid dimension value: " + value);
            return defaultValue;
        }
    }

    private static Vector2D parsePosition(String value, Vector2D defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            String[] parts = value.split(",");
            if (parts.length == 2) {
                return new Vector2D(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]));
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid position value: " + value);
        }
        return defaultValue;
    }

    private static Vector2D parsePivot(String value, Vector2D defaultValue) {
        if (value == null || value.isEmpty()) {
            return defaultValue;
        }
        try {
            String[] parts = value.split(",");
            if (parts.length == 2) {
                return new Vector2D(Float.parseFloat(parts[0]), Float.parseFloat(parts[1]));
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid pivot value: " + value);
        }
        return defaultValue;
    }

    private static void handleButtonClick(String action) {
        if (action == null || action.isEmpty()) {
            System.err.println("Button action is null or empty");
            return;
        }

        switch (action) {
            case "startGame":
                engine.scenes.SceneManager.loadScene(new GameScene());
                break;
            default:
                System.err.println("Unknown button action: " + action);
                break;
        }
    }
}