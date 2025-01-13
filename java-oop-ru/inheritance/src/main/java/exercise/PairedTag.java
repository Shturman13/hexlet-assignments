package exercise;

import java.util.Map;
import java.util.List;

// BEGIN
public class PairedTag extends Tag{
    private String tagBody;
    private List<Tag> tags;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String tagBody, List<Tag> tags) {
        super(tagName, tagAttributes);
        this.tagBody = tagBody;
        this.tags = tags;
    }

    public String getTagBody() {
        return tagBody;
    }

    public List<Tag> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(getTagName());

        var attributes = getTagAttributes().entrySet();

        for (Map.Entry<String, String> entry: attributes) {
            stringBuilder.append(" ");
            stringBuilder.append(entry.getKey())
                    .append("=\"")
                    .append(entry.getValue())
                    .append("\"");

        }
        stringBuilder.append(">");

        if (tagBody != null && !tagBody.isEmpty()) {
            stringBuilder.append(tagBody);
        }

        if (tags != null && !tags.isEmpty()) {
            for (Tag tag : tags) {
                stringBuilder.append(tag.toString());
            }
        }

        stringBuilder.append("</").append(getTagName()).append(">");
        return stringBuilder.toString();
    }
}

// END
