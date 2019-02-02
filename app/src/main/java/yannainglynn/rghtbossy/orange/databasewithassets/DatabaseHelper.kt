package yannainglynn.rghtbossy.orange.databasewithassets

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


import java.util.ArrayList

/**
 * Created by NgocTri on 11/7/2015.
 */
class DatabaseHelper(private val mContext: Context) : SQLiteOpenHelper(mContext, DBNAME, null, 1) {
    private var mDatabase: SQLiteDatabase? = null

    val listProduct: List<Product>
        get() {
            var product: Product? = null
            val productList = ArrayList<Product>()
            openDatabase()
            val cursor = mDatabase!!.rawQuery("SELECT * FROM PRODUCT", null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                product = Product(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getString(3))
                productList.add(product)
                cursor.moveToNext()
            }
            cursor.close()
            closeDatabase()
            return productList
        }

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    private fun openDatabase() {
        val dbPath = mContext.getDatabasePath(DBNAME).path
        if (mDatabase != null && mDatabase!!.isOpen) {
            return
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    private fun closeDatabase() {
        if (mDatabase != null) {
            mDatabase!!.close()
        }
    }

    companion object {
        const val DBNAME = "sample.sqlite"
        @SuppressLint("SdCardPath")
        val DBLOCATION = "/data/data/yannainglynn.rghtbossy.orange.databasewithassets/databases/"
    }
}
