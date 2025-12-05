import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private String transactionId;
    private Member member;
    private Book book;

    private String borrowDate;
    private String dueDate;
    private String returnDate;

    private int daysLate;
    private double lateFee;

    private static int totalTransactions = 0;
    public static final double LATE_FEE_PER_DAY = 2000.0;

    private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Transaction(Member member, Book book, String borrowDate, int durationDays) {
        totalTransactions++;
        this.transactionId = String.format("TRX%03d", totalTransactions);

        this.member = member;
        this.book = book;

        LocalDate borrow = LocalDate.parse(borrowDate, fmt);
        LocalDate due = borrow.plusDays(durationDays);

        this.borrowDate = borrowDate;
        this.dueDate = due.format(fmt);

        if (!book.borrowBook()) {
            throw new IllegalArgumentException("Buku tidak tersedia untuk dipinjam");
        }
    }

    public void processReturn(String returnDate) {
        this.returnDate = returnDate;

        LocalDate borrow = LocalDate.parse(borrowDate, fmt);
        LocalDate due = LocalDate.parse(dueDate, fmt);
        LocalDate ret = LocalDate.parse(returnDate, fmt);

        if (ret.isBefore(borrow)) {
            throw new IllegalArgumentException("Tanggal kembali tidak boleh sebelum tanggal pinjam");
        }

        if (ret.isAfter(due)) {
            daysLate = (int) ChronoUnit.DAYS.between(due, ret);
        }

        calculateLateFee();
        book.returnBook();
    }

    public void calculateLateFee() {
        lateFee = daysLate * LATE_FEE_PER_DAY * (1 - member.getMembershipDiscount());
    }

    public boolean isOverdue(String currentDate) {
        if (returnDate != null) return false;

        LocalDate now = LocalDate.parse(currentDate, fmt);
        LocalDate due = LocalDate.parse(dueDate, fmt);

        return now.isAfter(due);
    }

    public String getTransactionStatus() {
        if (returnDate != null) return "Selesai";
        if (isOverdue(LocalDate.now().format(fmt))) return "Terlambat";
        return "Aktif";
    }

    public void displayTransaction() {
        System.out.println("[" + transactionId + "] " + getTransactionStatus());
        System.out.println("Peminjam      : " + member.getName());
        System.out.println("Buku          : " + book.getTitle());
        System.out.println("Tgl Pinjam    : " + borrowDate);
        System.out.println("Tgl Tempo     : " + dueDate);
        if (returnDate != null) {
            System.out.println("Tgl Kembali   : " + returnDate);
            System.out.println("Terlambat     : " + daysLate + " hari");
            System.out.println("Denda         : Rp " + (int)lateFee);
        }
        System.out.println("--------------------------------------------");
    }

    public static int getTotalTransactions() {
        return totalTransactions;
    }
}
