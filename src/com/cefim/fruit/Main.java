package com.cefim.fruit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FruitJdbcDAO fruitJdbcDAO = new FruitJdbcDAO();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Hello ! Quel est le nom du fruit à ajouter ?");
        String fruitName = scanner.nextLine();
        System.out.println("Date d'expiration ?");
        LocalDate localDate = LocalDate.parse(scanner.nextLine(), formatter);

        Fruit fruitToCreate = new Fruit(fruitName, localDate);
        Fruit fruitCreated1 = fruitJdbcDAO.create(fruitToCreate);
        System.out.println("Le fruit avec l'id " + fruitCreated1.getId() + " a été ajouté");

        // REQUETE READ
        List<Fruit> fruitList = fruitJdbcDAO.findAll();
        for (Fruit f: fruitList) {
            System.out.println("ID " + f.getId() + " Fruit : " + f.getName() + " Date exp : " + f.getExpirationDate());
        }

//        // REQUETE CREATE
        Fruit fruit = new Fruit("Kiwi", LocalDate.now());
        Fruit fruitCreated = fruitJdbcDAO.create(fruit);
        System.out.println("Le nouveau fruit avec l'id " + fruitCreated.getId()+ " a été ajouté");

        // REQUETE UPDATE
        fruitCreated.setName("UPDATED FRUIT");
        LocalDate modifiedDate = LocalDate.of(2021, 07, 16);
        fruitCreated.setExpirationDate(modifiedDate);

        boolean updateOk = fruitJdbcDAO.update(fruitCreated);
        if (updateOk) {
            System.out.println("Le fruit avec l'id " + fruitCreated.getId() + "a été MAJ");
        }

        // REQUETE DELETE
        fruitJdbcDAO.delete(13L);

        //        boolean deleteOk = fruitJdbcDAO.delete(fruitCreated.getId());
//        if (deleteOk) {
//            System.out.println("Le fruit avec l'id " + fruitCreated.getId() + "a été supprimé");
//        }

        ConnectionManager.closeConnection();


    }


}


