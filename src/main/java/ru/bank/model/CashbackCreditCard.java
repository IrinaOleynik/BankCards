package ru.bank.model;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public class CashbackCreditCard extends CreditCard {

    // Кредитная карта с потенциальным кэшбэком 5% от покупок при условии трат больше 5 000 тыс
    private final double CASHBACK_RATE = 0.05;
    private final double SPENDING_MIN_FOR_CASHBACK = 5_000;
    private double spending;
    private double cashback;
    private final ReentrantLock cashbackCardLock = new ReentrantLock();

    public CashbackCreditCard(double creditLimit) {
        super(creditLimit);
    }

    public double getCashback() {
        cashbackCardLock.lock();
        try {
            return cashback;
        } finally {
            cashbackCardLock.unlock();
        }
    }

    public double getCashbackRate() {
        return CASHBACK_RATE;
    }

    @Override
    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            cashbackCardLock.lock();
            try {
                spending += amount;
                addCashback(amount);
            } finally {
                cashbackCardLock.unlock();
            }
            return true;
        }
        return false;
    }

    private void addCashback(double amount) {
        if (spending >= SPENDING_MIN_FOR_CASHBACK) {
            cashback += amount * CASHBACK_RATE;
        }
    }

    @Override
    public String getAvailableFundsInformation() {
        cashbackCardLock.lock();
        try {
            return super.getAvailableFundsInformation() + String.format(Locale.US, "\nКэшбэк: %.2f", cashback);
        } finally {
            cashbackCardLock.unlock();
        }
    }
}
