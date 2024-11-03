package ru.bank.model;

import java.util.Locale;

public class DebitCard extends BankCard {

    public DebitCard() {
        super();
    }

    @Override
    public boolean withdraw(double amount) {
        getBankCardLock().lock();
        try {
            if (amount > getBalance()) {
                return false;
            }
            setBalance(getBalance() - amount);
            return true;
        } finally {
            getBankCardLock().unlock();
        }
    }

    @Override
    public void deposit(double amount) {
        getBankCardLock().lock();
        try {
            setBalance(getBalance() + amount);
        } finally {
            getBankCardLock().unlock();
        }
    }

    @Override
    public String getAvailableFundsInformation() {
        getBankCardLock().lock();
        try {
            return String.format(Locale.US, "Баланс: %.2f", getBalance());
        } finally {
            getBankCardLock().unlock();
        }
    }
}
