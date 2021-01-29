package com.example.android_list_search

//import androidx.appcompat.app.AlertController
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

interface Click {
    fun onClickOnViewPosition(string: String)
}

class MainActivity : AppCompatActivity(), Click {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        searchOnQueryText()

        adapter = RecyclerAdapter(getCitiesList(), this)
        recyclerView.adapter = adapter
    }

    private fun searchOnQueryText() {
        val searchView: SearchView = findViewById(R.id.searchView2)
        searchView.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })
    }

    private fun getCitiesList(): ArrayList<String> {
        val arrayListCities = ArrayList<String>()
        val arrayCities = resources.getStringArray(R.array.cities)

        arrayCities.forEach {
            arrayListCities.add(it)
        }
        return arrayListCities
    }

    override fun onClickOnViewPosition(string: String) {
        Toast.makeText(
            this,
            resources.getString(R.string.toast_string) + " " + string,
            Toast.LENGTH_SHORT).show()
    }
}


class RecyclerAdapter(
    private val listCities: ArrayList<String>,
    private val clicked: Click
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(),
    Filterable {

    var filterList = ArrayList<String>()

    init {
        filterList = listCities
    }

    override fun getItemCount(): Int = filterList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int ) {
        holder.textView.text = filterList[position]
        holder.itemView.setOnClickListener {
            clicked.onClickOnViewPosition(filterList[position])
        }
    }

    class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textViewLarge)
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                val filterResults = FilterResults()
                when (charSearch.isEmpty()) {
                    true -> filterResults.values = listCities
                    false -> filterResults.values = listCities.filter {
                        charSearch.toLowerCase(Locale.ROOT) in it.toLowerCase(Locale.ROOT)
                    }
                }
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }
    }

}
