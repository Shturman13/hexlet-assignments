package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag{

    public SingleTag(String tagName, Map<String, String> tagAttributes) {
        super(tagName, tagAttributes);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getTagName());
        var attributes = getTagAttributes().entrySet();
        for (Map.Entry<String, String> entry : attributes) {

            stringBuilder.append(" ")
                    .append(entry.getKey())
                    .append("=\"")
                    .append(entry.getValue())
                    .append("\"");

        }

        stringBuilder.append(">");
        return stringBuilder.toString();
    }
}
// END
