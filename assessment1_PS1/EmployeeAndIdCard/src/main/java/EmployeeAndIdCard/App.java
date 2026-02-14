package EmployeeAndIdCard;

import jakarta.persistence.*;
import java.time.LocalDate;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("EmployeeIdcard");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Employee emp = new Employee();
        emp.setName("John");
        emp.setEmail("john@example.com");

        IDCard card = new IDCard();
        card.setCardNumber("IDC101");
        card.setIssueDate(LocalDate.of(2026, 2, 14));

        emp.setIdCard(card);
        card.setEmployee(emp);

        em.persist(emp);

        tx.commit();

        System.out.println("Employee Created Successfully\n");

        Employee retrievedEmp = em.find(Employee.class, 1);

        System.out.println("Employee Details:");
        System.out.println("ID: " + retrievedEmp.getId());
        System.out.println("Name: " + retrievedEmp.getName());
        System.out.println("Email: " + retrievedEmp.getEmail());

        System.out.println("\nID Card Details:");
        System.out.println("Card Number: " +
                retrievedEmp.getIdCard().getCardNumber());
        System.out.println("Issue Date: " +
                retrievedEmp.getIdCard().getIssueDate());

        IDCard retrievedCard = em.find(IDCard.class, 1);

        System.out.println("\nRetrieved via IDCard:");
        System.out.println("Employee Name: " +
                retrievedCard.getEmployee().getName());

        em.close();
        emf.close();
    }
}
