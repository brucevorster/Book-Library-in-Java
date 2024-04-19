package another_try_at_fa2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Another_try_at_FA2 
{
    ///Main Program
    public static void main(String[] args) 
    {
        //Import Scanner
        Scanner mychoice = new Scanner(System.in);
        
        //Boolean condition to make the while loop run
        boolean runLibrary = true;
        
        //New object made from the Managing_Collections class in order to call the methods in Managing_Collections
        Managing_Collections library = new Managing_Collections();
        
        //While loop for managing the programs functionality
        while (runLibrary == true)
        {
            //prints to print all the options the user has
            System.out.print("\n//////Library Management System//////\n");
            System.out.print("\n1. Add Book\n");
            System.out.print("2. Search Book\n");
            System.out.print("3. Add Member\n");
            System.out.print("4. Check out Books\n");
            System.out.print("5. Exit\n");
            
            //prompts user for their input
            System.out.print("\nEnter the number of your action: ");
            //User inputs an integer
            int userChoice = mychoice.nextInt();
            
            //Switch case for each user input and the action that follows
            switch (userChoice) 
            {
                //case for when user inputs 1
                case 1:
                    //calls addBook method in the Managing_Collections class
                    //adds a book
                    library.addBook();
                    break;
                    
                //case for when user inputs 2
                case 2:
                    //prompts user to enter a book title to search for that book
                    System.out.print("Enter the title of the book to search: ");
                    mychoice.nextLine();
                    String searchTitle = mychoice.nextLine();
                    //calls searchBook method in the Managing_Collections class
                    library.searchBook(searchTitle);
                    break;
                    
                //case for when user inputs 3
                case 3:
                    //calls addMember method in the Managing_Collections class
                    //adds a member
                    library.addMember();
                    break;
                
                //case for when user inputs 4
                case 4:
                    //prompts user to enter the book title that they want to check out and their name saved in the addMember method 
                    System.out.print("Enter the title of the book you want to check out: ");
                    mychoice.nextLine();
                    String checkoutTitle = mychoice.nextLine();
                    System.out.print("Enter your name: ");
                    String checkoutMember = mychoice.nextLine();
                    //calls checkoutBooks method in the Managing_Collections class
                    library.checkoutBooks(checkoutTitle, checkoutMember);
                    break;
                    
                //case for when user inputs 5
                case 5:
                    //This case allowes the user to exit the program
                    System.out.print("Exiting Program!");
                    System. exit(0);
                
                //default switch case
                default:
                    //prints out a default message
                    System.out.print("There was a problem make sure your input was between 1 and 5!\n");
            }
        }
    }
    
    //Book Class
    static class Book
    {
        public String title;
        public String author;
        public int isbn;
        public boolean isavailable;

        public Book(String title, String author, int isbn, boolean isavailable) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.isavailable = isavailable;
        }
        
        public String title()
        {
            return title;
        }
        public String author()
        {
            return author;
        }
        public int isbn()
        {
            return isbn;
        }
        public boolean isavailable()
        {
            return isavailable;
        }
    }
    
    //Member class
    static class Member
    {
        public String name;
        public String email;
        public List<Book> borrowed;//allowes user to borrow a book and ads it to a list


        public Member(String name, String email, List borrowed)
        {
            this.name = name;
            
            //Email Validation to ensure the email entered is valid
            if (EmailValidator.isValidEmail(email)) 
            {
                this.email = email;
            } else {
                throw new IllegalArgumentException("Invalid email address: " + email);
            }
            
            this.borrowed = new ArrayList<Book>();
        }
        
        public String name()
        {
            return name;
        }
        public String email()
        {
            return email;
        }
        public List<Book> borrowed()
        {
            return borrowed;
        }
    }
    
    public class EmailValidator {
    //expression used to validate email address
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    // Pattern object to compile the regex
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    // Method to validate an email address
    public static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
    
    //Managing Collections class used for the projects functionality with the member and book class
    static class Managing_Collections {
        
        //Creates an array list for the Book and Member class
        public List<Book> books = new ArrayList<>();
        public List<Member> members = new ArrayList<>();
        
        //adds a book to the project
        public void addBook() {
            //prompts the user to enter the book information
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the Book Title: ");
            String bookName = scanner.nextLine();
            System.out.print("Enter the Book Author: ");
            String bookAuthor = scanner.nextLine();
            System.out.print("Enter the Book ISBN code: ");
            int bookISBN = scanner.nextInt();
            //makes the book available so that it can be searched or borrowed
            boolean isavailable = true;
            Book newBook = new Book(bookName, bookAuthor, bookISBN, isavailable);
            //Adds book
            books.add(newBook);
            System.out.println("Your Book was added successfully");
        }
        
        //adds a member to the project
        public void addMember() {
            //prompts the user to enter their information
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter your Email Address: ");
            String email = scanner.nextLine();
            List<Book> borrowed = new ArrayList<Book>();
            
            //Checks email validation to ensure it is correct
            if (EmailValidator.isValidEmail(email)) 
            {
                Member newMember = new Member(name, email, borrowed);
                //Adds member
                members.add(newMember);
                System.out.println("Member added successfully.");
            } else {
                System.out.println("Invalid email address, note:'@gmail.com'. Member not added.");
            }
        }
        
        //Searches for book that user enters and that is available
         public void searchBook(String bookTitle) {
            boolean found = false;
            //for all the books in the book array list
            for (Book book : books) {
                //if the book title entered matches the title of the book it should display book found and the information of the book
                if (book.title().equalsIgnoreCase(bookTitle)) {
                    System.out.println("Book was Found!");
                    System.out.println("Title: " + book.title());
                    System.out.println("Author: " + book.author());
                    System.out.println("ISBN: " + book.isbn());
                    found = true;
                    break;
                }
            }
            //if book is not found it should display "The Book entered was not found!"
            if (!found) {
                System.out.println("The Book entered was not found!");
            }
         }
        
         //Allowes user to check out book
         public void checkoutBooks(String bookTitle, String memberName) 
         {
            Book bookToCheckout = null;
            Member memberToCheckout = null;
            
            //for all the books in the book array list
            for (Book book : books) 
            {
                //if the book title entered matches the title of the book and if the book is available it should go to checkout
                if (book.title().equalsIgnoreCase(bookTitle) && book.isavailable()) 
                {
                    bookToCheckout = book;
                    break;
                }
            }
            for (Member member : members) 
            {
                //if the member name entered matches the name saved at members members are allowed to check out
                if (member.name().equalsIgnoreCase(memberName)) 
                {
                    memberToCheckout = member;
                    break;
                }
            }
            if (bookToCheckout != null && memberToCheckout != null) 
            {
                //finalizes the checkout
                bookToCheckout.isavailable = false;
                memberToCheckout.borrowed.add(bookToCheckout);
                System.out.println("Book '" + bookToCheckout.title() + "' has been checked out by " + memberToCheckout.name());
            } else {
                System.out.println("Book or member was not found or book is not available!");
            }
        }
    }
}
