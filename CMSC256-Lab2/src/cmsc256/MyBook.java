package cmsc256;

public class MyBook implements Comparable<MyBook> {
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;
    public MyBook() {
        title = "None Given";
        authorFirstName = "None Given";
        authorLastName = "None Given";
        ISBN10 = "0000000000";
        ISBN13 = "0000000000000";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        if (authorFirstName != null) {
            this.authorFirstName = authorFirstName;
        } else {
            throw new IllegalArgumentException("Author First Name cannot be null");
        }
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        if (authorLastName != null) {
            this.authorLastName = authorLastName;
        } else {
            throw new IllegalArgumentException("Author Last Name cannot be null");
        }
    }
    public String getISBN10() {
        return ISBN10;
    }
    public boolean containsDigits(String text) {
        char[] textChaArray = text.toCharArray();
        for (char c: textChaArray) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
    public void setISBN10(String ISBN10) {
        if (ISBN10 != null) {
            if (containsDigits(ISBN10)) {
                if (ISBN10.length() == 10) {
                    this.ISBN10 = ISBN10;
                } else {
                    throw new IllegalArgumentException("ISBN10 must have a length of 10");
                }
            } else {
                throw new IllegalArgumentException("ISBN10 must only contain digits");
            }
        } else {
            throw new IllegalArgumentException("ISBN10 can not be null");
        }
    }
     public String getISBN13() {
        return ISBN13;
    }
    public void setISBN13(String ISBN13) {
        if (ISBN13 != null) {
            if (containsDigits(ISBN13)) {
                if (ISBN13.length() == 13) {
                    this.ISBN13 = ISBN13;
                } else {
                    throw new IllegalArgumentException("ISBN13 must have a length of 13");
                }
                } else {
                    throw new IllegalArgumentException("ISBN13 must only contain digits");
                }
            } else {
                throw new IllegalArgumentException("ISBN13 can not be null");
        }
    }
    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13) {
                setTitle(title);
                setAuthorFirstName(authorFirstName);
                setAuthorLastName(authorLastName);
                setISBN10(ISBN10);
                setISBN13(ISBN13);
    }
    public String toString() {
        String printBook;
        printBook = "Title: " + title + "\nAuthor: " + authorFirstName + " " + authorLastName + "\nISBN10: " + ISBN10 + "\nISBN13: " + ISBN13;
        return printBook;
    }

    public boolean equals(Object otherBook) {
        if (this == otherBook) {
            return true;
        }
        if (!(otherBook instanceof MyBook)) {
            return false;
        }
        MyBook secondBook = (MyBook)otherBook;

        if (this.ISBN10.equals(secondBook.getISBN10()) && this.ISBN13.equals(secondBook.getISBN13())) {
            return true;
        } else {
            return false;
        }
    }
    public int compareTo(MyBook other) {
        int result = authorLastName.compareTo(other.getAuthorLastName());
        if (result == 0) {
            result = authorFirstName.compareTo(other.getAuthorFirstName());
        }
        if (result == 0) {
            result = title.compareTo(other.getTitle());
        }
        return result;
    }
}