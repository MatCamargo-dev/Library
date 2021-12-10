public class Editoral extends User {
    private String name;
    private int editoralId;

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public long getEditoralId() {
        return editoralId;
    }

    public void setEditoralId(int editoralId) {
        this.editoralId = editoralId;
    }
}
