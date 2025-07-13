package com.contactmanager;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactManager {
	   private ArrayList<Contact> contacts = new ArrayList<>();
	   private Scanner scanner = new Scanner(System.in);

	   public void addContact() {
	       System.out.println("Enter First Name: ");
	       String firstName = scanner.nextLine().trim();

	       System.out.println("Enter Last Name: ");
	       String lastName = scanner.nextLine().trim();

	       System.out.println("Enter Address: ");
	       String address = scanner.nextLine().trim();

	       System.out.println("Enter Email: ");
	       String email = scanner.nextLine().trim();

	       if (!isValidEmail(email) || isDuplicateEmail(email)) {
	            System.out.println("Invalid or duplicate email. Contact not added.");
	            return;
	        }

	       System.out.println("Enter Phone Number: ");
	       String phone = scanner.nextLine().trim();

	       if (!isValidPhone(phone)) {
	            System.out.println("Invalid phone number. Contact not added.");
	            return;
	        }

	       Contact contact = new Contact(firstName, lastName, address, email, phone);
	       contacts.add(contact);
	       System.out.println("Contact added successfully.");
	    }

	    public void viewContacts() {
	        if (contacts.isEmpty()) {
	            System.out.println("No contacts found.");
	            return;
	        }

	        for (Contact c : contacts) {
	            System.out.println("----------------------------");
	            System.out.println(c);
	        }
	    }

	    public void updateContact() {
	        System.out.println("Enter the email of the contact to update: ");
	        String email = scanner.nextLine().trim();

	        Contact contact = findContactByEmail(email);
	        if (contact == null) {
	            System.out.println("Contact not found.");
	            return;
	        }

	        System.out.println("Enter new address: ");
	        String newAddress = scanner.nextLine().trim();
	        contact.setAddress(newAddress);

	        System.out.println("Enter new phone number: ");
	        String newPhone = scanner.nextLine().trim();

	        if (!isValidPhone(newPhone)) {
	            System.out.println("Invalid phone number. Update cancelled.");
	            return;
	        }

	        contact.setPhone(newPhone);
	        System.out.println("Contact updated successfully.");
	    }

	    public void deleteContact() {
	        System.out.println("Enter the email of the contact to delete: ");
	        String email = scanner.nextLine().trim();

	        Contact contact = findContactByEmail(email);
	        if (contact == null) {
	            System.out.println("Contact not found.");
	            return;
	        }

	        contacts.remove(contact);
	        System.out.println("Contact deleted successfully.");
	    }

	    // Helper methods
	    private Contact findContactByEmail(String email) {
	        for (Contact c : contacts) {
	            if (c.getEmail().equalsIgnoreCase(email)) {
	                return c;
	            }
	        }
	        return null;
	    }

	    private boolean isValidEmail(String email) {
	        return email.contains("@") && email.contains(".");
	    }

	    private boolean isDuplicateEmail(String email) {
	        return findContactByEmail(email) != null;
	    }

	    private boolean isValidPhone(String phone) {
	        return phone.matches("\\d{10}");
	    }
	}
