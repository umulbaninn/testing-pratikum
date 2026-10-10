package com.stmik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private static final double DELTA = 0.001;
    private BankAccount account;

    @BeforeEach
    public void setUp() {
        account = new BankAccount();
    }

    @Test
    public void testSaldoAwalNol() {
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    public void testDepositMenambahSaldo() {
        account.deposit(100000);
        assertEquals(100000.0, account.getBalance(), DELTA);
    }

    @Test
    public void testDepositBeberapaKali() {
        account.deposit(50000);
        account.deposit(25000);
        assertEquals(75000.0, account.getBalance(), DELTA);
    }

    @Test
    public void testDepositNegatifMelemparException() {
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1));
    }

    @Test
    public void testWithdrawMengurangiSaldo() {
        account.deposit(100000);
        account.withdraw(40000);
        assertEquals(60000.0, account.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawSebesarSaldo() {
        account.deposit(50000);
        account.withdraw(50000);
        assertEquals(0.0, account.getBalance(), DELTA);
    }

    @Test
    public void testWithdrawNegatifMelemparException() {
        account.deposit(100000);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-1));
    }

    @Test
    public void testWithdrawMelebihiSaldoMelemparException() {
        account.deposit(50000);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> account.withdraw(50001));
        assertEquals("Saldo tidak mencukupi", e.getMessage());
    }

    @Test
    public void testSaldoTidakBerubahSetelahWithdrawGagal() {
        account.deposit(50000);
        assertThrows(IllegalStateException.class, () -> account.withdraw(70000));
        assertEquals(50000.0, account.getBalance(), DELTA);
    }

    @Test
    public void testDepositNolTidakMengubahSaldo() {
        account.deposit(0);
        assertEquals(0.0, account.getBalance(), DELTA);
    }
}