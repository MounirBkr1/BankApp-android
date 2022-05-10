package com.mnr.bankapp_v1;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "transactionsManager";
    //table pour login
    private static final String TABLE_IDENTIFICATION = "identification_table";
    private static final String ID= "id";
    private static final String USERNAME= "username";
    private static final String PASSWORD= "password";
    private static final String RETAPE_PASSWORD= "retaper_password";
    private static final String EMAIL= "email";


    //table pour les transactions
    private static final String TABLE_TRANSACTIONS = "transactions_table";
    private static final String ICON = "icon";
    private static final String LABEL = "label";
    private static final String PRICE = "price";
    private static final String DATE = "date";
    private static final String ACCOUNT_NUMBER = "accountnumber";
    private static final String REFERENCE = "reference";
    private static final String BALANCE = "balance";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_IDENTIFICATION_TABLE= " CREATE TABLE " + TABLE_IDENTIFICATION + "("
                 +USERNAME + " STRING PRIMARY KEY NOT NULL,"
                + PASSWORD + " STRING NOT NULL,"
                + EMAIL + " STRING NOT NULL"
                + ")";

        db.execSQL(CREATE_IDENTIFICATION_TABLE);


        String CREATE_TRANSACTIONS_TABLE = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
                + ICON + " INTEGER,"
                + LABEL + " STRING,"
                + PRICE + " STRING,"
                + DATE + " STRING,"
                + ACCOUNT_NUMBER + " STRING,"
                + REFERENCE + " STRING,"
                + BALANCE + " REAL"
                + ")";
        db.execSQL(CREATE_TRANSACTIONS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //*******table IDENTIFICATION******************
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IDENTIFICATION);

        //*******table TRANSACTION******************
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);

        // Create tables again
        onCreate(db);
    }

    //*************METHODES FOR IDENTIFICATION TABLE********************************

    //INSERT
    public boolean insert(String username,String password,String email){
        //get wraitable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values
        ContentValues valuesIdentification = new ContentValues();

        valuesIdentification.put(USERNAME,username);
        valuesIdentification.put(PASSWORD,password);

        valuesIdentification.put(EMAIL,email);

        // Inserting Row
        long result=db.insert(TABLE_IDENTIFICATION, null, valuesIdentification);
        db.close(); // Closing database connection

        if(result == -1) return false;
        else return true;
    }

    //check if user exist in database
    public boolean checkUsername(String username ){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(" SELECT * FROM " + TABLE_IDENTIFICATION + " WHERE " + USERNAME + " = ? ",new String[]{username});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(" SELECT * FROM "+ TABLE_IDENTIFICATION + " WHERE "+USERNAME+" = ? AND "+PASSWORD+" = ? ",new String[]{username,password} );

        if(cursor.getCount()>0) return  true;
        else return false;
    }


    //*************METHODES FOR TRNSACTION TABLE********************************
    // code to add the new contact
    void addTransaction(Transaction trans) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ICON, trans.getIcon());
        values.put(LABEL, trans.getLabel());
        values.put(PRICE, trans.getPrice());
        values.put(DATE, trans.getDate());
        values.put(ACCOUNT_NUMBER, trans.getNumCompte());
        values.put(REFERENCE, trans.getNumRef());
        values.put(BALANCE, trans.getSolde());

        // Inserting Row
        db.insert(TABLE_TRANSACTIONS, null, values);
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Transaction getTransaction(int ref) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TRANSACTIONS, new String[] {
                        ICON, LABEL,PRICE,DATE,ACCOUNT_NUMBER,REFERENCE,BALANCE
                }, REFERENCE + "=?",
                new String[] { String.valueOf(ref) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Transaction transaction = new Transaction(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                Double.parseDouble(cursor.getString(6)));
        // return contact
        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        String selectQuery = "SELECT  * FROM " + TABLE_TRANSACTIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();

                transaction.setIcon(Integer.parseInt(cursor.getString(0)));
                transaction.setLabel(cursor.getString(1));
                transaction.setPrice(cursor.getString(2));
                transaction.setDate(cursor.getString(3));
                transaction.setNumCompte(cursor.getString(4));
                transaction.setNumRef(cursor.getString(5));
                transaction.setSolde(Double.parseDouble(cursor.getString(6)));

                transactionList.add(transaction);
            } while (cursor.moveToNext());
        }

        return transactionList;
    }


    // Deleting single contact
    public void deleteTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRANSACTIONS, REFERENCE + " = ?",
                new String[] { String.valueOf(transaction.getNumRef()) });
        db.close();
    }

    public int getTransactionsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TRANSACTIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
