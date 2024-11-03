package ru.bank.model;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public class SavingsCreditCard extends CreditCard {

    // Кредитная карта с накоплением в размере 0.005% от суммы пополнений
    private double savings;
    private final double SAVINGS_RATE = 0.00005;
    private final ReentrantLock savingsCardLock = new ReentrantLock();

    public SavingsCreditCard(double creditLimit) {
        super(creditLimit);
    }

    public double getSavings() {
        savingsCardLock.lock();
        try {
            return savings;
        } finally {
            savingsCardLock.unlock();
        }
    }

    public double getSavingsRate() {
        return SAVINGS_RATE;
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        savingsCardLock.lock();
        try {
            savings += amount * SAVINGS_RATE;
        } finally {
            savingsCardLock.unlock();
        }
    }

    @Override
    public String getAvailableFundsInformation() {
        savingsCardLock.lock();
        try {
            return super.getAvailableFundsInformation() + String.format(Locale.US, "\nНакопления: %.2f", savings);
        } finally {
            savingsCardLock.unlock();
        }
    }
}
