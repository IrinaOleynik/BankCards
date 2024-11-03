package ru.bank.model;

import java.util.Locale;
import java.util.concurrent.locks.ReentrantLock;

public class CreditCard extends BankCard {

    private final double CREDIT_LIMIT;
    private double creditBalance;
    private final ReentrantLock creditCardLock = new ReentrantLock();

    public CreditCard(double creditLimit) {
        super();
        this.CREDIT_LIMIT = creditLimit;
        this.creditBalance = creditLimit;
    }

    public double getCreditBalance() {
        creditCardLock.lock();
        try {
            return creditBalance;
        } finally {
            creditCardLock.unlock();
        }
    }

    public double getCreditLimit() {
        return CREDIT_LIMIT;
    }

    @Override
    public boolean withdraw(double amount) {
        getBankCardLock().lock();
        try {
            if (amount <= getBalance()) {
                setBalance(getBalance() - amount);
                return true;
            } else if (amount <= getBalance() + creditBalance) {
                double creditBalancePayment = amount - getBalance();
                double balancePayment = amount - creditBalancePayment;
                setBalance(getBalance() - balancePayment);
                creditBalance -= creditBalancePayment;
                return true;
            }
            return false;
        } finally {
            getBankCardLock().unlock();
        }
    }

    @Override
    public void deposit(double amount) {
        getBankCardLock().lock();
        try {
            double creditDebt = CREDIT_LIMIT - creditBalance;
            if (creditDebt == 0) {
                setBalance(getBalance() + amount);
            } else if (creditDebt >= amount) {
                creditBalance += amount;
            } else {
                double depositToBalance = amount - creditDebt;
                setBalance(getBalance() + depositToBalance);
                creditBalance += creditDebt;
            }
        } finally {
            getBankCardLock().unlock();
        }
    }

    @Override
    public String getAvailableFundsInformation() {
        getBankCardLock().lock();
        try {
            return String.format(Locale.US, "Кредитный лимит: %.2f" +
                    "\nКредитные средства: %.2f" +
                    "\nСобственные средства: %.2f", CREDIT_LIMIT, creditBalance, getBalance());
        } finally {
            getBankCardLock().unlock();
        }
    }
}


