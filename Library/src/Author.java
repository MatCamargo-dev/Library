public class Author extends Book {
    private String name;
    private String adress;
    private int authorId;

    public int getAuthorIdId() {
        return authorId;
    }

    public void setAuthorIdId(int authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
