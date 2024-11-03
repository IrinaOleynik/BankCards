package ru.bank.model;

import java.util.concurrent.locks.ReentrantLock;

public abstract class BankCard {

    private double balance;
    private final ReentrantLock BankCardLock = new ReentrantLock();

    protected BankCard() {
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    protected ReentrantLock getBankCardLock() {
        return BankCardLock;
    }

    public abstract boolean withdraw(double amount);

    public abstract void deposit(double amount);

    public abstract String getAvailableFundsInformation();
}
