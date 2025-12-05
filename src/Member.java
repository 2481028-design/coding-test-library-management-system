import java.time.Year;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private int registrationYear;
    private String membershipType;

    private static int totalMembers = 0;

    // ======= CONSTRUCTOR =======
    public Member() {
        totalMembers++;
        this.memberId = String.format("MBR%03d", totalMembers);
    }

    public Member(String name, String email, String phoneNumber, int registrationYear, String membershipType) {
        totalMembers++;
        this.memberId = String.format("MBR%03d", totalMembers);

        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setRegistrationYear(registrationYear);
        setMembershipType(membershipType);
    }

    // ======= VALIDATION =======
    private String validateName(String n) {
        if (n == null || n.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong");
        }
        return n;
    }

    private String validateEmail(String e) {
        if (e == null || !e.contains("@") || !e.contains(".")) {
            throw new IllegalArgumentException("Email tidak valid (harus mengandung @ dan .)");
        }
        return e;
    }

    private String validatePhone(String p) {
        if (!p.matches("\\d{10,13}")) {
            throw new IllegalArgumentException("Nomor telepon harus 10-13 digit");
        }
        return p;
    }

    private int validateYear(int y) {
        if (y < 2015 || y > 2025) {
            throw new IllegalArgumentException("Tahun daftar harus antara 2015-2025");
        }
        return y;
    }

    private String validateType(String t) {
        if (!(t.equals("Silver") || t.equals("Gold") || t.equals("Platinum"))) {
            throw new IllegalArgumentException("Membership type harus Silver/Gold/Platinum");
        }
        return t;
    }

    // ======= SETTERS =======
    public void setName(String name) { this.name = validateName(name); }
    public void setEmail(String email) { this.email = validateEmail(email); }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = validatePhone(phoneNumber); }
    public void setRegistrationYear(int year) { this.registrationYear = validateYear(year); }
    public void setMembershipType(String type) { this.membershipType = validateType(type); }

    // ======= GETTERS =======
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public int getRegistrationYear() { return registrationYear; }
    public String getMembershipType() { return membershipType; }
    public static int getTotalMembers() { return totalMembers; }

    // ======= BUSINESS LOGIC =======
    public void displayInfo() {
        System.out.println("[" + memberId + "] " + name);
        System.out.println("Email         : " + email);
        System.out.println("Phone         : " + phoneNumber);
        System.out.println("Membership    : " + membershipType);
        System.out.println("Tahun Daftar  : " + registrationYear);
        System.out.println("Durasi Member : " + getMembershipDuration() + " tahun");
        System.out.println("Batas Pinjam  : " + getMaxBorrowLimit() + " buku");
        System.out.println("Diskon Denda  : " + (int)(getMembershipDiscount() * 100) + "%");
        System.out.println("--------------------------------------------");
    }

    public void upgradeMembership(String newType) {
        String[] order = { "Silver", "Gold", "Platinum" };
        int current = java.util.Arrays.asList(order).indexOf(membershipType);
        int next = java.util.Arrays.asList(order).indexOf(newType);

        if (next <= current) {
            throw new IllegalArgumentException("Upgrade tidak valid");
        }

        this.membershipType = newType;
    }

    public int getMaxBorrowLimit() {
        switch (membershipType) {
            case "Platinum": return 10;
            case "Gold": return 7;
            default: return 5;
        }
    }

    public int getMembershipDuration() {
        return Year.now().getValue() - registrationYear;
    }

    public double getMembershipDiscount() {
        switch (membershipType) {
            case "Platinum": return 0.50;
            case "Gold": return 0.30;
            default: return 0.10;
        }
    }
}
