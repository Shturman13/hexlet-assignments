package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String overTag;
    private TagInterface tag;

    public LabelTag(String overTag, TagInterface tag) {
        this.overTag = overTag;
        this.tag = tag;
    }


    @Override
    public String render() {
        return "<label>Press Submit" + tag.render() + "</label>";
    }
}
// END
