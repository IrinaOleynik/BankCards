package ru.bank.model;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public class BonusCreditCard extends CreditCard {

    // Кредитная карта с начислением бонусных баллов в размере 1% от покупок
    private double bonusPoints;
    private final double BONUS_RATE = 0.01;
    private final ReentrantLock bonusCardLock = new ReentrantLock();

    public BonusCreditCard(double creditLimit) {
        super(creditLimit);
    }

    public double getBonusPoints() {
        bonusCardLock.lock();
        try {
            return bonusPoints;
        } finally {
            bonusCardLock.unlock();
        }
    }

    public double getBonusRate() {
        return BONUS_RATE;
    }

    protected void setBonusPoints(double bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    @Override
    public boolean withdraw(double amount) {
        if (super.withdraw(amount)) {
            bonusCardLock.lock();
            try {
                bonusPoints += amount * BONUS_RATE;
            } finally {
                bonusCardLock.unlock();
            }
            return true;
        }
        return false;
    }

    @Override
    public String getAvailableFundsInformation() {
        bonusCardLock.lock();
        try {
            return super.getAvailableFundsInformation() + String.format(Locale.US, "\nБонусные баллы: %.2f", bonusPoints);
        } finally {
            bonusCardLock.unlock();
        }
    }
}
