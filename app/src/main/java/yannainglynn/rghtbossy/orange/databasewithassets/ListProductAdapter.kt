package yannainglynn.rghtbossy.orange.databasewithassets

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by NgocTri on 11/7/2015.
 */
class ListProductAdapter(private val mContext: Context, private val mProductList: List<Product>) : BaseAdapter() {

    override fun getCount(): Int {
        return mProductList.size
    }

    override fun getItem(position: Int): Any {
        return mProductList[position]
    }

    override fun getItemId(position: Int): Long {
        return mProductList[position].id.toLong()
    }

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val v = View.inflate(mContext, R.layout.item_listview, null)
        val tvName = v.findViewById<View>(R.id.tv_product_name) as TextView
        val tvPrice = v.findViewById<View>(R.id.tv_product_price) as TextView
        val tvDescription = v.findViewById<View>(R.id.tv_product_description) as TextView
        tvName.text = mProductList[position].name
        tvPrice.text = mProductList[position].price.toString() + " $"
        tvDescription.text = mProductList[position].description
        return v
    }
}
