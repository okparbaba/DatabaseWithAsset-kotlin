package yannainglynn.rghtbossy.orange.databasewithassets

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class MainActivity : AppCompatActivity() {

    private var lvProduct: ListView? = null
    private var adapter: ListProductAdapter? = null
    private var mProductList: List<Product>? = null
    private var mDBHelper: DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        lvProduct = findViewById<View>(R.id.listview_product) as ListView?
        mDBHelper = DatabaseHelper(this)

        //Check exists database
        val database = applicationContext.getDatabasePath(DatabaseHelper.DBNAME)
        if (!database.exists()) {
            mDBHelper!!.readableDatabase
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show()
                return
            }
        }
        //Get product list in db when db exists
        mProductList = mDBHelper!!.listProduct
        //Init adapter
        adapter = ListProductAdapter(this, mProductList!!)
        //Set adapter for listview
        lvProduct!!.adapter = adapter
    }

    private fun copyDatabase(context: Context): Boolean {
        try {
            val inputStream = context.assets.open(DatabaseHelper.DBNAME)
            val outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME
            val outputStream = FileOutputStream(outFileName)
            val buff = ByteArray(1024)
            var length = inputStream.read(buff)
            while ((length ) > 0) {
                outputStream.write(buff, 0, length)
            }
            outputStream.flush()
            outputStream.close()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }
}
