package Library;

public class Constants {
    public enum MEMBERS {
        MEMBER_NAME("Member Name"), MEMBER_ID("Member ID"), PHONE_NUMBER("Phone Number"), JOINING_DATE("Joining Date");

        private final String displayName;

        MEMBERS(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
    public enum BOOKS {
        BOOK_NAME("Book Name"), AUTHOR_NAME("Author Name"), UNIQUE_ID("Unique ID"), CATEGORY("Category"), AVAILABILITY("Availability"), STOCK("Stock");

        private final String displayName;

        BOOKS(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }
}
