package engine.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringJoiner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.w3c.dom.Document;


public class JsonManager {

    /**
     * Lớp đối tượng JSON
     */
    public static class JsonObject {
        private final Map<String, Object> properties = new HashMap<>();

        /**
         * Thêm thuộc tính vào đối tượng JSON
         */
        public JsonObject put(String key, Object value) {
            properties.put(key, value);
            return this;
        }

        /**
         * Lấy giá trị từ đối tượng JSON theo key
         */
        public Object get(String key) {
            return properties.get(key);
        }

        /**
         * Lấy giá trị String từ đối tượng JSON theo key
         */
        public String getString(String key) {
            Object value = properties.get(key);
            return value != null ? value.toString() : null;
        }

        /**
         * Lấy giá trị Integer từ đối tượng JSON theo key
         */
        public Integer getInt(String key) {
            Object value = properties.get(key);
            if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
            return null;
        }

        /**
         * Lấy giá trị Double từ đối tượng JSON theo key
         */
        public Double getDouble(String key) {
            Object value = properties.get(key);
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            }
            return null;
        }

        /**
         * Lấy giá trị Boolean từ đối tượng JSON theo key
         */
        public Boolean getBoolean(String key) {
            Object value = properties.get(key);
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof String) {
                return Boolean.parseBoolean((String) value);
            }
            return null;
        }

        /**
         * Lấy đối tượng JSON con từ đối tượng JSON hiện tại theo key
         */
        public JsonObject getJsonObject(String key) {
            Object value = properties.get(key);
            return value instanceof JsonObject ? (JsonObject) value : null;
        }

        /**
         * Lấy mảng JSON từ đối tượng JSON hiện tại theo key
         */
        public JsonArray getJsonArray(String key) {
            Object value = properties.get(key);
            return value instanceof JsonArray ? (JsonArray) value : null;
        }

        /**
         * Kiểm tra đối tượng JSON có chứa key không
         */
        public boolean has(String key) {
            return properties.containsKey(key);
        }

        /**
         * Xóa thuộc tính khỏi đối tượng JSON theo key
         */
        public Object remove(String key) {
            return properties.remove(key);
        }

        /**
         * Lấy tất cả các key trong đối tượng JSON
         */
        public Iterable<String> keys() {
            return properties.keySet();
        }

        /**
         * Lấy số lượng thuộc tính trong đối tượng JSON
         */
        public int size() {
            return properties.size();
        }

        /**
         * Chuyển đối tượng JSON thành chuỗi
         */
        @Override
        public String toString() {
            return toString(0);
        }

        /**
         * Chuyển đối tượng JSON thành chuỗi có định dạng đẹp
         */
        public String toString(int indent) {
            StringBuilder sb = new StringBuilder();
            if (indent > 0) {
                sb.append("{\n");
                Iterator<Map.Entry<String, Object>> it = properties.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> entry = it.next();
                    indent(sb, indent);
                    sb.append("\"").append(escapeJson(entry.getKey())).append("\": ");
                    appendValue(sb, entry.getValue(), indent);
                    if (it.hasNext()) {
                        sb.append(",\n");
                    } else {
                        sb.append("\n");
                    }
                }
                indent(sb, indent - 1);
                sb.append("}");
            } else {
                sb.append("{");
                Iterator<Map.Entry<String, Object>> it = properties.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, Object> entry = it.next();
                    sb.append("\"").append(escapeJson(entry.getKey())).append("\":");
                    appendValue(sb, entry.getValue(), 0);
                    if (it.hasNext()) {
                        sb.append(",");
                    }
                }
                sb.append("}");
            }
            return sb.toString();
        }

        private void indent(StringBuilder sb, int indent) {
            for (int i = 0; i < indent; i++) {
                sb.append("  ");
            }
        }

        private void appendValue(StringBuilder sb, Object value, int indent) {
            if (value == null) {
                sb.append("null");
            } else if (value instanceof String) {
                sb.append("\"").append(escapeJson((String) value)).append("\"");
            } else if (value instanceof Number || value instanceof Boolean) {
                sb.append(value);
            } else if (value instanceof JsonObject) {
                sb.append(((JsonObject) value).toString(indent > 0 ? indent + 1 : 0));
            } else if (value instanceof JsonArray) {
                sb.append(((JsonArray) value).toString(indent > 0 ? indent + 1 : 0));
            } else {
                sb.append("\"").append(escapeJson(value.toString())).append("\"");
            }
        }
    }

    /**
     * Lớp mảng JSON
     */
    public static class JsonArray {
        private final List<Object> items = new ArrayList<>();

        /**
         * Thêm phần tử vào mảng JSON
         */
        public JsonArray add(Object value) {
            items.add(value);
            return this;
        }

        /**
         * Lấy phần tử từ mảng JSON theo chỉ số
         */
        public Object get(int index) {
            return items.get(index);
        }

        /**
         * Lấy phần tử String từ mảng JSON theo chỉ số
         */
        public String getString(int index) {
            Object value = items.get(index);
            return value != null ? value.toString() : null;
        }

        /**
         * Lấy phần tử Integer từ mảng JSON theo chỉ số
         */
        public Integer getInt(int index) {
            Object value = items.get(index);
            if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
            return null;
        }

        /**
         * Lấy phần tử Double từ mảng JSON theo chỉ số
         */
        public Double getDouble(int index) {
            Object value = items.get(index);
            if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof String) {
                return Double.parseDouble((String) value);
            }
            return null;
        }

        /**
         * Lấy phần tử Boolean từ mảng JSON theo chỉ số
         */
        public Boolean getBoolean(int index) {
            Object value = items.get(index);
            if (value instanceof Boolean) {
                return (Boolean) value;
            } else if (value instanceof String) {
                return Boolean.parseBoolean((String) value);
            }
            return null;
        }

        /**
         * Lấy đối tượng JSON từ mảng JSON theo chỉ số
         */
        public JsonObject getJsonObject(int index) {
            Object value = items.get(index);
            return value instanceof JsonObject ? (JsonObject) value : null;
        }

        /**
         * Lấy mảng JSON từ mảng JSON theo chỉ số
         */
        public JsonArray getJsonArray(int index) {
            Object value = items.get(index);
            return value instanceof JsonArray ? (JsonArray) value : null;
        }

        /**
         * Xóa phần tử khỏi mảng JSON theo chỉ số
         */
        public Object remove(int index) {
            return items.remove(index);
        }

        /**
         * Lấy số lượng phần tử trong mảng JSON
         */
        public int size() {
            return items.size();
        }

        /**
         * Chuyển mảng JSON thành chuỗi
         */
        @Override
        public String toString() {
            return toString(0);
        }

        /**
         * Chuyển mảng JSON thành chuỗi có định dạng đẹp
         */
        public String toString(int indent) {
            StringBuilder sb = new StringBuilder();
            if (indent > 0) {
                sb.append("[\n");
                for (int i = 0; i < items.size(); i++) {
                    indent(sb, indent);
                    appendValue(sb, items.get(i), indent);
                    if (i < items.size() - 1) {
                        sb.append(",\n");
                    } else {
                        sb.append("\n");
                    }
                }
                indent(sb, indent - 1);
                sb.append("]");
            } else {
                sb.append("[");
                for (int i = 0; i < items.size(); i++) {
                    appendValue(sb, items.get(i), 0);
                    if (i < items.size() - 1) {
                        sb.append(",");
                    }
                }
                sb.append("]");
            }
            return sb.toString();
        }

        private void indent(StringBuilder sb, int indent) {
            for (int i = 0; i < indent; i++) {
                sb.append("  ");
            }
        }

        private void appendValue(StringBuilder sb, Object value, int indent) {
            if (value == null) {
                sb.append("null");
            } else if (value instanceof String) {
                sb.append("\"").append(escapeJson((String) value)).append("\"");
            } else if (value instanceof Number || value instanceof Boolean) {
                sb.append(value);
            } else if (value instanceof JsonObject) {
                sb.append(((JsonObject) value).toString(indent > 0 ? indent + 1 : 0));
            } else if (value instanceof JsonArray) {
                sb.append(((JsonArray) value).toString(indent > 0 ? indent + 1 : 0));
            } else {
                sb.append("\"").append(escapeJson(value.toString())).append("\"");
            }
        }
    }

    /**
     * Phân tích chuỗi JSON thành đối tượng JSON
     */
    public static JsonObject parseObject(String jsonString) throws IOException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");

        try {
            // Để sử dụng JavaScript engine để phân tích JSON
            String script = "JSON.parse('" + jsonString.replace("'", "\\'") + "')";
            Object result = engine.eval(script);

            // Chuyển đổi kết quả thành JsonObject
            return convertToJsonObject(result);
        } catch (ScriptException e) {
            throw new IOException("Lỗi phân tích JSON: " + e.getMessage());
        }
    }

    /**
     * Phân tích chuỗi JSON thành mảng JSON
     */
    public static JsonArray parseArray(String jsonString) throws IOException {
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");

        try {
            // Để sử dụng JavaScript engine để phân tích JSON
            String script = "JSON.parse('" + jsonString.replace("'", "\\'") + "')";
            Object result = engine.eval(script);

            // Chuyển đổi kết quả thành JsonArray
            return convertToJsonArray(result);
        } catch (ScriptException e) {
            throw new IOException("Lỗi phân tích JSON: " + e.getMessage());
        }
    }

    /**
     * Phân tích chuỗi JSON thủ công (không dùng ScriptEngine)
     */
    public static Object parse(String json) throws IOException {
        json = json.trim();
        if (json.startsWith("{")) {
            return parseJsonObject(json);
        } else if (json.startsWith("[")) {
            return parseJsonArray(json);
        } else {
            throw new IOException("Invalid JSON format");
        }
    }

    private static JsonObject parseJsonObject(String json) throws IOException {
        JsonObject result = new JsonObject();
        json = json.trim();

        if (!json.startsWith("{") || !json.endsWith("}")) {
            throw new IOException("Invalid JSON object format");
        }

        // Loại bỏ dấu ngoặc nhọn
        json = json.substring(1, json.length() - 1).trim();

        if (json.isEmpty()) {
            return result; // Object rỗng
        }

        int pos = 0;
        while (pos < json.length()) {
            // Tìm key
            if (json.charAt(pos) != '"') {
                throw new IOException("Expected string key at position " + pos);
            }

            int endKey = findClosingQuote(json, pos + 1);
            String key = json.substring(pos + 1, endKey);

            // Tìm dấu hai chấm
            pos = endKey + 1;
            while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
                pos++;
            }

            if (pos >= json.length() || json.charAt(pos) != ':') {
                throw new IOException("Expected ':' at position " + pos);
            }

            // Tìm giá trị
            pos++;
            while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
                pos++;
            }

            // Phân tích giá trị
            TokenAndPos tokenAndPos = parseValue(json, pos);
            result.put(key, tokenAndPos.token);
            pos = tokenAndPos.pos;

            // Tìm dấu phẩy hoặc kết thúc
            while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
                pos++;
            }

            if (pos >= json.length()) {
                break;
            }

            if (json.charAt(pos) == ',') {
                pos++;
                while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
                    pos++;
                }
            } else if (json.charAt(pos) != '}') {
                throw new IOException("Expected ',' or '}' at position " + pos);
            }
        }

        return result;
    }

    private static JsonArray parseJsonArray(String json) throws IOException {
        JsonArray result = new JsonArray();
        json = json.trim();

        if (!json.startsWith("[") || !json.endsWith("]")) {
            throw new IOException("Invalid JSON array format");
        }

        // Loại bỏ dấu ngoặc vuông
        json = json.substring(1, json.length() - 1).trim();

        if (json.isEmpty()) {
            return result; // Array rỗng
        }

        int pos = 0;
        while (pos < json.length()) {
            // Phân tích giá trị phần tử
            TokenAndPos tokenAndPos = parseValue(json, pos);
            result.add(tokenAndPos.token);
            pos = tokenAndPos.pos;

            // Tìm dấu phẩy hoặc kết thúc
            while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
                pos++;
            }

            if (pos >= json.length()) {
                break;
            }

            if (json.charAt(pos) == ',') {
                pos++;
                while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
                    pos++;
                }
            } else if (json.charAt(pos) != ']') {
                throw new IOException("Expected ',' or ']' at position " + pos);
            }
        }

        return result;
    }

    private static class TokenAndPos {
        Object token;
        int pos;

        TokenAndPos(Object token, int pos) {
            this.token = token;
            this.pos = pos;
        }
    }

    private static TokenAndPos parseValue(String json, int startPos) throws IOException {
        int pos = startPos;

        while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
            pos++;
        }

        if (pos >= json.length()) {
            throw new IOException("Unexpected end of input");
        }

        char c = json.charAt(pos);

        if (c == '"') {
            int endQuote = findClosingQuote(json, pos + 1);
            String value = json.substring(pos + 1, endQuote);
            return new TokenAndPos(unescapeJson(value), endQuote + 1);
        } else if (c == '{') {
            int endBrace = findClosingBrace(json, pos, '{', '}');
            JsonObject obj = parseJsonObject(json.substring(pos, endBrace + 1));
            return new TokenAndPos(obj, endBrace + 1);
        } else if (c == '[') {
            int endBracket = findClosingBrace(json, pos, '[', ']');
            JsonArray arr = parseJsonArray(json.substring(pos, endBracket + 1));
            return new TokenAndPos(arr, endBracket + 1);
        } else if (c == 't' && json.startsWith("true", pos)) {
            return new TokenAndPos(Boolean.TRUE, pos + 4);
        } else if (c == 'f' && json.startsWith("false", pos)) {
            return new TokenAndPos(Boolean.FALSE, pos + 5);
        } else if (c == 'n' && json.startsWith("null", pos)) {
            return new TokenAndPos(null, pos + 4);
        } else if (Character.isDigit(c) || c == '-') {
            // Phân tích số
            int endPos = pos + 1;
            boolean isDecimal = false;

            while (endPos < json.length()) {
                char ch = json.charAt(endPos);
                if (Character.isDigit(ch)) {
                    endPos++;
                } else if (ch == '.' && !isDecimal) {
                    isDecimal = true;
                    endPos++;
                } else if ((ch == 'e' || ch == 'E') && endPos + 1 < json.length()) {
                    endPos++;
                    if (json.charAt(endPos) == '+' || json.charAt(endPos) == '-') {
                        endPos++;
                    }
                } else {
                    break;
                }
            }

            String numStr = json.substring(pos, endPos);
            Object numValue;

            if (isDecimal) {
                numValue = Double.parseDouble(numStr);
            } else {
                try {
                    numValue = Integer.parseInt(numStr);
                } catch (NumberFormatException e) {
                    // Nếu quá lớn cho Integer, sử dụng Long hoặc Double
                    try {
                        numValue = Long.parseLong(numStr);
                    } catch (NumberFormatException e2) {
                        numValue = Double.parseDouble(numStr);
                    }
                }
            }

            return new TokenAndPos(numValue, endPos);
        } else {
            throw new IOException("Unexpected character at position " + pos + ": " + c);
        }
    }

    private static int findClosingQuote(String json, int startPos) throws IOException {
        for (int i = startPos; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '\\') {
                i++; // Bỏ qua ký tự escape và ký tự sau nó
            } else if (c == '"') {
                return i;
            }
        }
        throw new IOException("Unterminated string starting at position " + (startPos - 1));
    }

    private static int findClosingBrace(String json, int startPos, char openBrace, char closeBrace) throws IOException {
        int count = 1;
        boolean inString = false;

        for (int i = startPos + 1; i < json.length(); i++) {
            char c = json.charAt(i);

            if (inString) {
                if (c == '\\') {
                    i++; // Bỏ qua ký tự escape và ký tự sau nó
                } else if (c == '"') {
                    inString = false;
                }
            } else {
                if (c == '"') {
                    inString = true;
                } else if (c == openBrace) {
                    count++;
                } else if (c == closeBrace) {
                    count--;
                    if (count == 0) {
                        return i;
                    }
                }
            }
        }

        throw new IOException("Unterminated " + openBrace + " starting at position " + startPos);
    }

    /**
     * Chuyển đổi đối tượng từ ScriptEngine thành JsonObject
     */
    @SuppressWarnings("unchecked")
    private static JsonObject convertToJsonObject(Object obj) {
        if (obj instanceof Map) {
            JsonObject jsonObj = new JsonObject();
            Map<String, Object> map = (Map<String, Object>) obj;

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Map) {
                    jsonObj.put(entry.getKey(), convertToJsonObject(value));
                } else if (value instanceof List) {
                    jsonObj.put(entry.getKey(), convertToJsonArray(value));
                } else {
                    jsonObj.put(entry.getKey(), value);
                }
            }

            return jsonObj;
        }

        return new JsonObject();
    }

    /**
     * Chuyển đổi đối tượng từ ScriptEngine thành JsonArray
     */
    @SuppressWarnings("unchecked")
    private static JsonArray convertToJsonArray(Object obj) {
        if (obj instanceof List) {
            JsonArray jsonArr = new JsonArray();
            List<Object> list = (List<Object>) obj;

            for (Object item : list) {
                if (item instanceof Map) {
                    jsonArr.add(convertToJsonObject(item));
                } else if (item instanceof List) {
                    jsonArr.add(convertToJsonArray(item));
                } else {
                    jsonArr.add(item);
                }
            }

            return jsonArr;
        }

        return new JsonArray();
    }

    /**
     * Lưu đối tượng JSON vào file
     */
    public static void saveToFile(JsonObject jsonObject, String filePath, int indent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonObject.toString(indent));
        }
    }

    /**
     * Lưu mảng JSON vào file
     */
    public static void saveToFile(JsonArray jsonArray, String filePath, int indent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(jsonArray.toString(indent));
        }
    }

    /**
     * Đọc đối tượng JSON từ file
     */
    public static JsonObject readObjectFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }

        return (JsonObject) parse(content.toString());
    }

    /**
     * Đọc mảng JSON từ file
     */
    public static JsonArray readArrayFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }

        return (JsonArray) parse(content.toString());
    }

    /**
     * Tạo đối tượng JSON mới
     */
    public static JsonObject createObject() {
        return new JsonObject();
    }

    /**
     * Tạo mảng JSON mới
     */
    public static JsonArray createArray() {
        return new JsonArray();
    }

    /**
     * Escape chuỗi JSON
     */
    public static String escapeJson(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    if (c < ' ') {
                        String hex = Integer.toHexString(c);
                        sb.append("\\u");
                        for (int j = 0; j < 4 - hex.length(); j++) {
                            sb.append('0');
                        }
                        sb.append(hex);
                    } else {
                        sb.append(c);
                    }
            }
        }

        return sb.toString();
    }

    /**
     * Unescape chuỗi JSON
     */
    public static String unescapeJson(String input) {
        if (input == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '\\' && i + 1 < input.length()) {
                char next = input.charAt(i + 1);

                switch (next) {
                    case '"':
                        sb.append('"');
                        break;
                    case '\\':
                        sb.append('\\');
                        break;
                    case '/':
                        sb.append('/');
                        break;
                    case 'b':
                        sb.append('\b');
                        break;
                    case 'f':
                        sb.append('\f');
                        break;
                    case 'n':
                        sb.append('\n');
                        break;
                    case 'r':
                        sb.append('\r');
                        break;
                    case 't':
                        sb.append('\t');
                        break;
                    case 'u':
                        if (i + 5 < input.length()) {
                            String hex = input.substring(i + 2, i + 6);
                            try {
                                int code = Integer.parseInt(hex, 16);
                                sb.append((char) code);
                                i += 4; // Bỏ qua 4 ký tự hex
                            } catch (NumberFormatException e) {
                                sb.append(next);
                            }
                        } else {
                            sb.append(next);
                        }
                        break;
                    default:
                        sb.append(next);
                }

                i++; // Bỏ qua ký tự đã xử lý
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // Ví dụ sử dụng
        JsonObject parent = new JsonObject();
        parent.put("name", "John Doe");
        parent.put("age", 30);
        parent.put("isStudent", false);
        parent.put("address", new JsonObject().put("city", "New York").put("zip", "10001"));
        parent.put("courses", new JsonArray().add("Math").add("Science").add("History"));
        parent.put("grades", new JsonObject().put("Math", 90).put("Science", 85).put("History", 88));
        parent.put("isActive", true);

        JsonObject child = new JsonObject();
        child.put("name", "Jane Do1e");
        child.put("age", 25);
        child.put("isStudent", true);
        child.put("address", new JsonObject().put("city", "Los Angeles").put("zip", "90001"));
        child.put("courses", new JsonArray().add("Art").add("Music"));
        child.put("grades", new JsonObject().put("Art", 95).put("Music", 92));
        child.put("isActive", false);

        parent.put("child", child);
    }
}