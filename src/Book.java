public class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int publicationYear;
    private boolean isAvailable = true;

    private int totalCopies;
    private int availableCopies;

    private static int totalBooks = 0;

    private static final String[] validCategories = {
            "Fiction", "Non-Fiction", "Science", "Technology", "History"
    };

    public Book() {
        totalBooks++;
        this.bookId = String.format("BK%03d", totalBooks);
    }

    public Book(String title, String author, String category, int publicationYear, int totalCopies) {
        totalBooks++;
        this.bookId = String.format("BK%03d", totalBooks);

        setTitle(title);
        setAuthor(author);
        setCategory(category);
        setPublicationYear(publicationYear);
        setTotalCopies(totalCopies);
        this.availableCopies = totalCopies;
    }

    // ========= VALIDATION =========
    private String validateTitle(String t) {
        if (t == null || t.trim().isEmpty()) {
            throw new IllegalArgumentException("Judul tidak boleh kosong");
        }
        return t;
    }

    private String validateAuthor(String a) {
        if (a == null || a.trim().isEmpty()) {
            throw new IllegalArgumentException("Penulis tidak boleh kosong");
        }
        return a;
    }

    private String validateCategory(String c) {
        for (String cat : validCategories) {
            if (cat.equals(c)) return c;
        }
        throw new IllegalArgumentException("Kategori tidak valid");
    }

    private int validateYear(int y) {
        if (y < 1900 || y > 2025) {
            throw new IllegalArgumentException("Tahun terbit harus 1900-2025");
        }
        return y;
    }

    private int validateCopies(int c) {
        if (c < 0) throw new IllegalArgumentException("Total copies tidak boleh negatif");
        return c;
    }

    // ========= SETTERS =========
    public void setTitle(String title) { this.title = validateTitle(title); }
    public void setAuthor(String author) { this.author = validateAuthor(author); }
    public void setCategory(String category) { this.category = validateCategory(category); }
    public void setPublicationYear(int year) { this.publicationYear = validateYear(year); }
    public void setTotalCopies(int copies) { this.totalCopies = validateCopies(copies); }

    // ========= GETTERS =========
    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public int getAvailableCopies() { return availableCopies; }
    public static int getTotalBooks() { return totalBooks; }

    // ========= BUSINESS LOGIC =========
    public void displayBookInfo() {
        System.out.println("[" + bookId + "] " + title);
        System.out.println("Penulis       : " + author);
        System.out.println("Kategori      : " + category);
        System.out.println("Tahun Terbit  : " + publicationYear);
        System.out.println("Total Copy    : " + totalCopies);
        System.out.println("Tersedia      : " + availableCopies + " | Status: " + getAvailabilityStatus());
        System.out.println("--------------------------------------------");
    }

    public boolean borrowBook() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (availableCopies < totalCopies) availableCopies++;
    }

    public String getAvailabilityStatus() {
        if (availableCopies == 0) return "Tidak Tersedia";
        if (availableCopies <= 5) return "Terbatas";
        return "Banyak Tersedia";
    }

    public int getBookAge() {
        return java.time.Year.now().getValue() - publicationYear;
    }

    public boolean isNewRelease() {
        return getBookAge() <= 2;
    }
}
