package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private String tagName;
    private Map<String, String> tagAttributes;

    public Tag(String tagName, Map<String, String> tagAttributes) {
        this.tagName = tagName;
        this.tagAttributes = tagAttributes;
    }

    public Map<String, String> getTagAttributes() {
        return tagAttributes;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public void setTagAttributes(Map<String, String> tagAttributes) {
        this.tagAttributes = tagAttributes;
    }

    public String getTagName() {
        return tagName;
    }




}
// END
