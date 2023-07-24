package uz.abbosbek.firebasedatabase_codial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import uz.abbosbek.firebasedatabase_codial.R
import uz.abbosbek.firebasedatabase_codial.adapters.MyAdapter
import uz.abbosbek.firebasedatabase_codial.databinding.FragmentHomeBinding
import uz.abbosbek.firebasedatabase_codial.models.MyImage

class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var firestore: FirebaseFirestore
    private lateinit var myAdapter: MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        firestore = FirebaseFirestore.getInstance()
        firestore.collection("images")
            .get()
            .addOnCompleteListener {
                val list = ArrayList<MyImage>()
                if (it.isSuccessful) {
                    var result = it.result
                    result?.forEach { queryDocumentSnapshot ->
                        var user = queryDocumentSnapshot.toObject(MyImage::class.java)
                        list.add(user)
                    }
                    myAdapter = MyAdapter(list)
                    binding.rv.adapter = myAdapter
                }
            }

        return binding.root
    }

}