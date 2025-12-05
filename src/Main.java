public class Main {
    public static void main(String[] args) {

        System.out.println("=== REGISTRASI ANGGOTA ===");
        Member m1 = new Member("Alice Johnson", "alice.j@email.com", "081234567890", 2020, "Platinum");
        Member m2 = new Member("Bob Smith", "bob.smith@email.com", "081298765432", 2022, "Gold");
        Member m3 = new Member("Charlie Brown", "charlie.b@email.com", "081223456789", 2024, "Silver");
        Member m4 = new Member("Diana Prince", "diana.p@email.com", "081287654321", 2021, "Gold");

        System.out.println("=== REGISTRASI BUKU ===");
        Book b1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 1925, 5);
        Book b2 = new Book("Clean Code", "Robert C. Martin", "Technology", 2008, 8);
        Book b3 = new Book("Sapiens", "Yuval Noah Harari", "History", 2011, 6);
        Book b4 = new Book("1984", "George Orwell", "Fiction", 1949, 4);

        System.out.println("=== TRANSAKSI ===");
        Transaction t1 = new Transaction(m1, b2, "01-12-2025", 14);
        Transaction t2 = new Transaction(m3, b3, "10-11-2025", 14);

        System.out.println("=== PENGEMBALIAN ===");
        t2.processReturn("04-12-2025");

        System.out.println("=== DAFTAR MEMBER ===");
        m1.displayInfo();
        m2.displayInfo();
        m3.displayInfo();
        m4.displayInfo();

        System.out.println("=== DAFTAR BUKU ===");
        b1.displayBookInfo();
        b2.displayBookInfo();
        b3.displayBookInfo();
        b4.displayBookInfo();

        System.out.println("=== DAFTAR TRANSAKSI ===");
        t1.displayTransaction();
        t2.displayTransaction();

        System.out.println("Total Members      : " + Member.getTotalMembers());
        System.out.println("Total Books        : " + Book.getTotalBooks());
        System.out.println("Total Transactions : " + Transaction.getTotalTransactions());
    }
}
