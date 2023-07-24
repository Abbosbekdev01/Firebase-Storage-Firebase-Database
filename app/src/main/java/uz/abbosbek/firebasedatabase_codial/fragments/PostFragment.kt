package uz.abbosbek.firebasedatabase_codial.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import uz.abbosbek.firebasedatabase_codial.R
import uz.abbosbek.firebasedatabase_codial.databinding.FragmentPostBinding
import uz.abbosbek.firebasedatabase_codial.models.MyImage

class PostFragment : Fragment() {
    private val binding by lazy { FragmentPostBinding.inflate(layoutInflater) }
    private lateinit var firebaseFireStore: FirebaseFirestore
    private lateinit var firebaseStorage: FirebaseStorage
    private lateinit var reference: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        firebaseFireStore = FirebaseFirestore.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        reference = firebaseStorage.getReference("my_files")

        binding.apply {
            btnPost.setOnClickListener {
                if (imageUrl != "" && edtName.text.toString().isNotBlank()) {
                    firebaseFireStore.collection("images")
                        .add(MyImage(edtName.text.toString(), imageUrl))
                        .addOnSuccessListener {
                            Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
                            edtName.text.clear()
                            findNavController().navigate(R.id.homeFragment)
                        }
                        .addOnFailureListener {
                            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(requireContext(), "Ma'lumot to'ldirilmagan", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            tvImage.setOnClickListener {
                getImageContent.launch("image/*")
            }
        }

        return binding.root
    }

    var imageUrl = ""
    private var getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            if (uri != null) {
                var m = System.currentTimeMillis()
                val uploadTask = reference.child(m.toString()).putFile(uri)

                uploadTask.addOnSuccessListener {
                    var downloadUrl = it.metadata?.reference?.downloadUrl
                    downloadUrl?.addOnSuccessListener { imageUri ->
                        imageUrl = imageUri.toString()
                    }
                }
            }
            binding.tvImage.setImageURI(uri)
        }

}