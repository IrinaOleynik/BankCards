package ru.bank;

import ru.bank.model.*;

public class Main {
    public static void main(String[] args) {

        // Дебетовая карта c балансом 0
        BankCard debitCard = new DebitCard();

        debitCard.deposit(5_000); // пополнение на 5 000
        System.out.println(debitCard.getAvailableFundsInformation()); // баланс 5 000

        debitCard.withdraw(2_000); // списание на 2 000
        System.out.println(debitCard.getAvailableFundsInformation()); // баланс 3 000

        debitCard.deposit(1_000); // пополнение на 1 000
        System.out.println(debitCard.getAvailableFundsInformation()); // баланс 4 000

//        // Кредитная карта с кредитным лимитом 10 000
//        BankCard creditCard = new CreditCard(10_000);
//
//        creditCard.deposit(5_000); // пополнение на 5 000
//        // кредитные средства 10 000, собственные средства 5 000
//        System.out.println(creditCard.getAvailableFundsInformation());
//
//        creditCard.withdraw(5_000); // списание на 5 000
//        // кредитные средства 10 000, собственные средства 0
//        System.out.println(creditCard.getAvailableFundsInformation());
//
//        creditCard.withdraw(3_000); // списание на 3 000
//        // кредитные средства 7 000, собственные средства 0
//        System.out.println(creditCard.getAvailableFundsInformation());
//
//        creditCard.deposit(2_000);  // пополнение на 2 000
//        // кредитные средства 9 000, собственные средства 0
//        System.out.println(creditCard.getAvailableFundsInformation());
//
//        creditCard.deposit(2_000); // пополнение на 2 000
//        // кредитные средства 10 000, собственные средства 1 000
//        System.out.println(creditCard.getAvailableFundsInformation());

//        // Бонусная кредитная карта с кредитным лимитом 15 000
//        BankCard bonusCreditCard = new BonusCreditCard(15_000);
//
//        bonusCreditCard.deposit(5_000); // пополнение на 5 000
//        // кредитные средства 15 000, собственные средства 5 000, бонусные баллы 0
//        System.out.println(bonusCreditCard.getAvailableFundsInformation());
//
//        bonusCreditCard.withdraw(5_000); // списание на 5 000
//        // кредитные средства 15 000, собственные средства 0, бонусные баллы 50
//        System.out.println(bonusCreditCard.getAvailableFundsInformation());
//
//        bonusCreditCard.withdraw(3_000); // списание на 3 000
//        // кредитные средства 12 000, собственные средства 0, бонусные баллы 80
//        System.out.println(bonusCreditCard.getAvailableFundsInformation());
//
//        bonusCreditCard.deposit(2_000);  // пополнение на 2 000
//        // кредитные средства 14 000, собственные средства 0, бонусные баллы 80
//        System.out.println(bonusCreditCard.getAvailableFundsInformation());

//        // Кредитная карта с потенциальным кэшбэком, кредитный лимит 5 000
//        BankCard cashbackCreditCard = new CashbackCreditCard(5_000);
//
//        cashbackCreditCard.deposit(5_000); // пополнение на 5 000
//        // кредитные средства 5 000, собственные средства 5 000, кэшбек 0
//        System.out.println(cashbackCreditCard.getAvailableFundsInformation());
//
//        cashbackCreditCard.withdraw(3_000); // списание на 3 000
//        // кредитные средства 5 000, собственные средства 2 000, кэшбэк 0
//        System.out.println(cashbackCreditCard.getAvailableFundsInformation());
//
//        cashbackCreditCard.withdraw(3_000); // списание на 3 000
//        // кредитные средства 4 000, собственные средства 0, кэшбэк 150
//        System.out.println(cashbackCreditCard.getAvailableFundsInformation());
//
//        cashbackCreditCard.withdraw(2_000);  // списание на 2 000
//        // кредитные средства 2 000, собственные средства 0, кэшбэк 250
//        System.out.println(cashbackCreditCard.getAvailableFundsInformation());

//        // Накопительная кредитная карта с кредитным лимитом 1 000
//        BankCard savingCreditCard = new SavingsCreditCard(1_000);
//
//        savingCreditCard.deposit(5_000); // пополнение на 5 000
//        // кредитные средства 1 000, собственные средства 5 000, накопления 0,25
//        System.out.println(savingCreditCard.getAvailableFundsInformation());
//
//        savingCreditCard.withdraw(3_000); // списание на 3 000
//        // кредитные средства 1 000, собственные средства 2 000, накопления 0,25
//        System.out.println(savingCreditCard.getAvailableFundsInformation());
//
//        savingCreditCard.deposit(2_000);  // пополнение на 2 000
//        // кредитные средства 1 000, собственные средства 4 000, накопления 0,35
//        System.out.println(savingCreditCard.getAvailableFundsInformation());
//
//        savingCreditCard.deposit(3_000); // пополнение на 2 000
//        // кредитные средства 1 000, собственные средства 7 000, накопления 0,50
//        System.out.println(savingCreditCard.getAvailableFundsInformation());

//        // Золотая дебетовая карта
//        BankCard goldDebitCard = new GoldDebitCard();
//
//        goldDebitCard.deposit(5_000); // пополнение на 5 000
//        // баланс 5 000, бонусные баллы 0, кэшбэк 0, накопления 0,25
//        System.out.println(goldDebitCard.getAvailableFundsInformation());
//
//        goldDebitCard.withdraw(2_000); // списание на 2 000
//        // баланс 3 000, бонусные баллы 20, кэшбэк 0, накопления 0,25
//        System.out.println(goldDebitCard.getAvailableFundsInformation());
//
//        goldDebitCard.withdraw(1_500); // списание на 1 500
//        // баланс 1 500, бонусные баллы 35, кэшбэк 0, накопления 0,25
//        System.out.println(goldDebitCard.getAvailableFundsInformation());
//
//        goldDebitCard.deposit(4_500); // пополнение на 4 500
//        // баланс 6 000, бонусные баллы 35, кэшбэк 0, накопления 0,48
//        System.out.println(goldDebitCard.getAvailableFundsInformation());
//
//        goldDebitCard.withdraw(3_000); // списание на 3 000
//        // баланс 3 000, бонусные баллы 65, кэшбэк 150, накопления 0,48
//        System.out.println(goldDebitCard.getAvailableFundsInformation());

    }
}