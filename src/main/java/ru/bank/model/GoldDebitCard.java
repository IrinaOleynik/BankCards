package ru.bank.model;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public class GoldDebitCard extends DebitCard {
    /*
     * Дебетовая карта с начислением бонусных баллов в размере 1% от покупок,
     * потенциальным кэшбэком 5% от покупок при условии трат больше 5 000 тыс и
     * накоплением в размере 0.005% от суммы пополнений
     */
    private final double BONUS_RATE = 0.01;
    private double bonusPoints;
    private final ReentrantLock bonusPointsLock = new ReentrantLock();

    private final double CASHBACK_RATE = 0.05;
    private final double SPENDING_MIN_FOR_CASHBACK = 5_000;
    private double spending;
    private double cashback;
    private final ReentrantLock cashbackLock = new ReentrantLock();

    private double savings;
    private final double SAVINGS_RATE = 0.00005;
    private final ReentrantLock savingsLock = new ReentrantLock();

    public GoldDebitCard() {
        super();
    }

    public double getBonusPoints() {
        bonusPointsLock.lock();
        try {
            return bonusPoints;
        } finally {
            bonusPointsLock.unlock();
        }
    }

    public double getBonusRate() {
        return BONUS_RATE;
    }

    public double getCashback() {
        cashbackLock.lock();
        try {
            return cashback;
        } finally {
            cashbackLock.unlock();
        }
    }

    public double getCashbackRate() {
        return CASHBACK_RATE;
    }

    public double getSavings() {
        savingsLock.lock();
        try {
            return savings;
        } finally {
            savingsLock.unlock();
        }
    }

    public double getSavingsRate() {
        return SAVINGS_RATE;
    }

    @Override
    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            cashbackLock.lock();
            try {
                spending += amount;
                addCashback(amount);
            } finally {
                cashbackLock.unlock();
            }
            bonusPointsLock.lock();
            try {
                bonusPoints += amount * BONUS_RATE;
            } finally {
                bonusPointsLock.unlock();
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
    public void deposit(double amount) {
        super.deposit(amount);
        savingsLock.lock();
        try {
            savings += amount * SAVINGS_RATE;
        } finally {
            savingsLock.unlock();
        }
    }

    @Override
    public String getAvailableFundsInformation() {
        bonusPointsLock.lock();
        cashbackLock.lock();
        savingsLock.lock();
        try {
            return super.getAvailableFundsInformation() + String.format(Locale.US, "\nБонусные баллы: %.2f" +
                    "\nКэшбэк: %.2f" +
                    "\nНакопления: %.2f", bonusPoints, cashback, savings);
        } finally {
            bonusPointsLock.unlock();
            cashbackLock.unlock();
            savingsLock.unlock();
        }
    }
}
