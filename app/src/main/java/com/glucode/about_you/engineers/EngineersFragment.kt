package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import java.util.Collections

class EngineersFragment : Fragment() {
    private lateinit var binding: FragmentEngineersBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpEngineersList(MockData.engineers,"")
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_years) {
            setUpEngineersList(MockData.engineers,"years")
            return true
        }else if(item.itemId == R.id.action_bugs){
            setUpEngineersList(MockData.engineers,"bugs")
            return true;
        }else if(item.itemId == R.id.action_coffees){
            setUpEngineersList(MockData.engineers,"coffees")
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpEngineersList(engineers: List<Engineer>,orderBy : String) {

        var newList : List<Engineer> = listOf()

        when(orderBy){
           "years"->
           newList = engineers.sortedBy { it.quickStats.years }
               "coffees"->
                   newList = engineers.sortedBy { it.quickStats.coffees }
            "bugs"->
                newList = engineers.sortedBy { it.quickStats.bugs }
            ""->
                newList = engineers
        }

        binding.list.adapter = EngineersRecyclerViewAdapter(newList) {
            goToAbout(it)
        }
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }



    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)

        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}